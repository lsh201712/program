package com.test.testVts.model;

public class VoiceUpDownProcessInOutDto {

	/**
	 * 录音ID
	 */
	private String voiceId;
	
	/**
	 * 文件路径 不含文件名称
	 */
	private  String filePath;
	
	/**
	 * 文件名称
	 */
	private  String fileName;
	/**
	 * 下载后存放文件的路径不含文件名称
	 */
	private  String targetFilePath;
	/**
	 * 下载后存放文件名称
	 */
	private String targetFileName;
	
	/**
	 * 下载超时时间。
	 */
	private long timeoutSeconds;

	/**
	 * 处理结果
	 */
	private String processResult;
	
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the targetFilePath
	 */
	public String getTargetFilePath() {
		return targetFilePath;
	}

	/**
	 * @param targetFilePath the targetFilePath to set
	 */
	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	/**
	 * @return the targetFileName
	 */
	public String getTargetFileName() {
		return targetFileName;
	}

	/**
	 * @param targetFileName the targetFileName to set
	 */
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	/**
	 * @return the timeoutSeconds
	 */
	public long getTimeoutSeconds() {
		return timeoutSeconds;
	}

	/**
	 * @param timeoutSeconds the timeoutSeconds to set
	 */
	public void setTimeoutSeconds(long timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	/**
	 * @return the voiceId
	 */
	public String getVoiceId() {
		return voiceId;
	}

	/**
	 * @param voiceId the voiceId to set
	 */
	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}

	/**
	 * @return the processResult
	 */
	public String getProcessResult() {
		return processResult;
	}

	/**
	 * @param processResult the processResult to set
	 */
	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

}
