package com.test;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.testVts.model.BisRouteInfo;
import com.test.testVts.model.VoiceUpDownProcessInOutDto;
import com.util.DateUtils;
import com.util.ExcelUtil;

public class TestVts {
	public static String[] excelKey = new String[]{"sourceid","source","voicepath","audioformat","audiochannel","calltime",
		"worksheetNum","worksheetstatus","billableseconds","customerphoneno","extensionno","createtime","customerid",
		"agentid","businesstype","calltype","worksheetaccepttime","worksheetagentid"};
	public static void main(String[] args) {
		
	/*********************获取随路任务FetchRelaDataJobHandler************************************************************************/
		
		/*1、判断是否编译该类，执行此定时任务
		  2、canRun，写入标识:jedis.hset(VTS_GRAB_SPY,GRAB_PID,1),
	                       jedis.hset(VTS_GRAB_SPY,JOB_MONIT_FETCH_ROUTE_DATA,时间戳)
	                                                          返回true
	      3、随路信息开始获取，这里从excel中获得
		
		*/
		
		String excelPath =  "E:/excel/excel/test.xlsx";
		ArrayList<ArrayList<Object>> list = ExcelUtil.readExcel(new File(excelPath));
		System.out.println(list.size());
		
		
		JSONArray getDataList = new JSONArray();
		for(int i = 0 ; i < list.size(); i ++){
		
			if(i <= 1){
				continue;
			}
			ArrayList<Object> lineData = list.get(i);
			JSONObject data = new JSONObject();
			if(lineData.size() < excelKey.length){
				continue;
			}
			for(int j = 0 ; j < excelKey.length ;  j ++ ){
				String keyName = excelKey[j];
				String value   = (String)lineData.get(j);
				data.put(keyName, value);
				
			}
			getDataList.add(data);
		}
		
		System.out.println(getDataList.size());
		System.out.println(getDataList.toJSONString());
		
		//4、处理获取的数据
		List<BisRouteInfo> successList = new ArrayList<BisRouteInfo>();
		if (getDataList!=null&&getDataList.size()>0) {
			for (int i = 0; i < getDataList.size(); i++) {
				JSONObject json = (JSONObject)getDataList.get(i);
				BisRouteInfo bri = new BisRouteInfo();
				bri.setSourceId(json.getString("sourceid"));
				bri.setSourceFrom(json.getString("source"));
				try {
					bri.setCallTime(DateUtils.StringToDate(json.getString("calltime"), DateUtils.DATETIME_FORMAT));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				bri.setBillableseconds(String.valueOf(json.getString("billableseconds")));
				bri.setVtsId("1");
				String voicePath = json.getString("voicepath");
				
				
				bri.setFileUri(voicePath);
				//添加文件名的字段
				int start = voicePath.lastIndexOf(File.separator);
				String filename = voicePath.substring(start + 1, voicePath.length());
				bri.setFilename(filename);
				
				bri.setAudioChannel(json.getString("audiochannel"));
				bri.setAudioFormat(json.getString("audioformat"));
				//文件后缀
				bri.setAudioExt("."+getFileExt(voicePath));
				
				bri.setJsonData(json);
				bri.setCreateTime(new Date());
				successList.add(bri);
				
			}
		}
		//检查锁定是否还是进程本身？？？？？？？？？？？？？？？？？？？？？？？？？？？
		//保存数据库
		//放入redis中jedis.hset(VTS_DATA_WAIT_MERGE_SEG, bri.getUuid(), JSONObject.toJSONString(bri));
		        //  jedis.rpush(VTS_VOICE_WAIT_DOWNLOAD_SEG,JSONObject.toJSONString(bri));		
		
	/****************录音上传任务VoiceDownloadJobHandler**************************************************/
		//上传类
		List<VoiceUpDownProcessInOutDto> inputDataList = new ArrayList<VoiceUpDownProcessInOutDto>();
		for (BisRouteInfo bisRouteInfo : successList) {
			VoiceUpDownProcessInOutDto voiceUpDownProcessInOutDto = new VoiceUpDownProcessInOutDto();
			voiceUpDownProcessInOutDto.setVoiceId(bisRouteInfo.getUuid());
			voiceUpDownProcessInOutDto.setFilePath(bisRouteInfo.getFileUri());
			voiceUpDownProcessInOutDto.setFileName(bisRouteInfo.getFilename());
			voiceUpDownProcessInOutDto.setTargetFileName(bisRouteInfo.getFilename());
			voiceUpDownProcessInOutDto.setTargetFilePath("D\\:\\VTS_DATA\\download"+File.separator+DateUtils.DateToStr(bisRouteInfo.getNewTime(), "yyyyMMdd"));
			voiceUpDownProcessInOutDto.setTimeoutSeconds(-1);
			
			inputDataList.add(voiceUpDownProcessInOutDto);		
						
		}
		
		//开始下载录音   
		//doProcessByMultiThreads(200,FTPVoiceUpDownServiceImpl,null,null,new VoiceDownLoadAttriteSet(),successList,inputDataList,VTS_VOICE_WAIT_DOWNLOAD_SEG,VTS_VOICE_WAIT_ENCODE_SEG)
		
		List<List<VoiceUpDownProcessInOutDto>> inputListForThread = new ArrayList<List<VoiceUpDownProcessInOutDto>>();
		List<List<BisRouteInfo>> inputListForBisRouteInfo = new ArrayList<List<BisRouteInfo>>();
		
		for(int i = 0 ; i < 10; i++){
			inputListForThread.add( new ArrayList<VoiceUpDownProcessInOutDto>() );
			inputListForBisRouteInfo.add(new ArrayList<BisRouteInfo>());
		}
		
		List<BisRouteInfo> createTargetFolderErrorList 	= new ArrayList<BisRouteInfo>();
		
		for (int briIdx = 0; briIdx < successList.size(); briIdx++) {
			BisRouteInfo bisRouteInfo = successList.get(briIdx);
			VoiceUpDownProcessInOutDto voiceUpDownProcessInOutDto = inputDataList.get(briIdx);
			
			inputListForThread.get(briIdx).add(voiceUpDownProcessInOutDto);
			inputListForBisRouteInfo.get(briIdx).add(bisRouteInfo);
			
			
		}
		
		
		
		
		
	}
	
	public static String getFileExt(String filepath){
		String ret = null;
		if(null != filepath && filepath.length() > 1){
			String[] array = filepath.split("\\.");
			if(array != null && array.length > 0){
				ret = array[array.length -1];
			}
		}
		return ret;
	}

}
