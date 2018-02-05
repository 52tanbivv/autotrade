package com.space.wechat.entity.company;

public class ProgressEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 14325812391231211L;
	private long pBytesRead = 0L; // 到目前为止读取文件的比特数
	private long pContentLength = 0L; // 文件总大小
	private int pItems; // 目前正在读取第几个文件

	private Double percent = 0d;
	private String pBytesReadch = "";
	private String pContentLengthch = ""; // 文件总大小

	// 处理起始时间
	private long processStartTime = 0l;
	// 处理终止时间
	private long processEndTime = 0l;
	// 本轮处理执行时间
	private long processRunningTime = 0l;

	private Double pMin = 0d;// 速度

	private Double pLeftMin = 0d;// 剩余上传实际

	public long getpBytesRead() {
		return pBytesRead;
	}

	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}

	public long getpContentLength() {
		return pContentLength;
	}

	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}

	public int getpItems() {
		return pItems;
	}

	public void setpItems(int pItems) {
		this.pItems = pItems;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getpBytesReadch() {
		return pBytesReadch;
	}

	public void setpBytesReadch(String pBytesReadch) {
		this.pBytesReadch = pBytesReadch;
	}

	public String getpContentLengthch() {
		return pContentLengthch;
	}

	public void setpContentLengthch(String pContentLengthch) {
		this.pContentLengthch = pContentLengthch;
	}

	public long getProcessStartTime() {
		return processStartTime;
	}

	public void setProcessStartTime(long processStartTime) {
		this.processStartTime = processStartTime;
	}

	public long getProcessEndTime() {
		return processEndTime;
	}

	public void setProcessEndTime(long processEndTime) {
		this.processEndTime = processEndTime;
	}

	public long getProcessRunningTime() {
		return processRunningTime;
	}

	public void setProcessRunningTime(long processRunningTime) {
		this.processRunningTime = processRunningTime;
	}

	public Double getpMin() {
		return pMin;
	}

	public void setpMin(Double pMin) {
		this.pMin = pMin;
	}

	public Double getpLeftMin() {
		return pLeftMin;
	}

	public void setpLeftMin(Double pLeftMin) {
		this.pLeftMin = pLeftMin;
	}

	@Override
	public String toString() {
		return "ProgressEntity [pBytesRead=" + pBytesRead + ", pContentLength="
				+ pContentLength + ", pItems=" + pItems + "]";
	}
}