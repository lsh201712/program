package com.test.testVts.model;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class BisRouteInfo {
	private String uuid;
	private String vtsId;
	private String sourceFrom;
	private String sourceId;
	private String audioFormat;
	private String audioChannel;
	private String fileUri;
	private String billableseconds;
	private Date callTime;
	private Date vtsEncodeComplete;
	private String vtsEncodeType;
	private Date vtsVoiceDownloadComplete;
	private Date vtsToAsr;
	private Date asrToVts;
	private Date vtsVoiceUploadComplete;
	private Date vtsToSap;
	private String filename;
	private String audioPlayPath;
	private String reservedField2;
	private String reservedField3;
	private String reservedField4;
	private String reservedField5;
	private Date errorTime;
	private String errorReason;
	private String errorCode;
	private Integer playAudio = 0;
	private Date createTime;
	private String audioExt;
	private JSONObject jsonData;
	private Date newTime = new Date();
	
	public Date getNewTime() {
		return newTime;
	}
	public void setNewTime(Date newTime) {
		this.newTime = newTime;
	}
	public String getUuid() {
		return uuid;
	}
	public JSONObject getJsonData() {
		return jsonData;
	}
	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getVtsId() {
		return vtsId;
	}
	public void setVtsId(String vtsId) {
		this.vtsId = vtsId;
	}
	public String getSourceFrom() {
		return sourceFrom;
	}
	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getAudioFormat() {
		return audioFormat;
	}
	public void setAudioFormat(String audioFormat) {
		this.audioFormat = audioFormat;
	}
	public String getAudioChannel() {
		return audioChannel;
	}
	public void setAudioChannel(String audioChannel) {
		this.audioChannel = audioChannel;
	}
	public String getFileUri() {
		return fileUri;
	}
	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}
	public String getBillableseconds() {
		return billableseconds;
	}
	public void setBillableseconds(String billableseconds) {
		this.billableseconds = billableseconds;
	}
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public Date getVtsEncodeComplete() {
		return vtsEncodeComplete;
	}
	public void setVtsEncodeComplete(Date vtsEncodeComplete) {
		this.vtsEncodeComplete = vtsEncodeComplete;
	}
	public String getVtsEncodeType() {
		return vtsEncodeType;
	}
	public void setVtsEncodeType(String vtsEncodeType) {
		this.vtsEncodeType = vtsEncodeType;
	}
	public Date getVtsVoiceDownloadComplete() {
		return vtsVoiceDownloadComplete;
	}
	public void setVtsVoiceDownloadComplete(Date vtsVoiceDownloadComplete) {
		this.vtsVoiceDownloadComplete = vtsVoiceDownloadComplete;
	}
	public Date getVtsToAsr() {
		return vtsToAsr;
	}
	public void setVtsToAsr(Date vtsToAsr) {
		this.vtsToAsr = vtsToAsr;
	}
	public Date getAsrToVts() {
		return asrToVts;
	}
	public void setAsrToVts(Date asrToVts) {
		this.asrToVts = asrToVts;
	}
	public Date getVtsVoiceUploadComplete() {
		return vtsVoiceUploadComplete;
	}
	public void setVtsVoiceUploadComplete(Date vtsVoiceUploadComplete) {
		this.vtsVoiceUploadComplete = vtsVoiceUploadComplete;
	}
	public Date getVtsToSap() {
		return vtsToSap;
	}
	public void setVtsToSap(Date vtsToSap) {
		this.vtsToSap = vtsToSap;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getAudioPlayPath() {
		return audioPlayPath;
	}
	public void setAudioPlayPath(String audioPlayPath) {
		this.audioPlayPath = audioPlayPath;
	}
	public String getReservedField2() {
		return reservedField2;
	}
	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}
	public String getReservedField3() {
		return reservedField3;
	}
	public void setReservedField3(String reservedField3) {
		this.reservedField3 = reservedField3;
	}
	public String getReservedField4() {
		return reservedField4;
	}
	public void setReservedField4(String reservedField4) {
		this.reservedField4 = reservedField4;
	}
	public String getReservedField5() {
		return reservedField5;
	}
	public void setReservedField5(String reservedField5) {
		this.reservedField5 = reservedField5;
	}
	public Date getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}
	public String getErrorReason() {
		return errorReason;
	}
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Integer getPlayAudio() {
		return playAudio;
	}
	public void setPlayAudio(Integer playAudio) {
		this.playAudio = playAudio;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAudioExt() {
		return audioExt;
	}
	public void setAudioExt(String audioExt) {
		this.audioExt = audioExt;
	}

	
}
