/**
 * 
 */
package com.readystatesoftware.search.base;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

/**
 * @file Route.java
 * @author linbin
 * @email linbin.java@gmail.com
 * @date 2012-7-24
 * @description 
 */
public class Route implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 路线名称
	 */
	private String summary;
	/**
	 * 路段集合
	 */
	private ArrayList<Step> steps = new ArrayList<Step>();
	/**
	 * 路线时长 (�?"6037")
	 */
	private String durationValue;
	/**
	 * 路线时常文字描述 (�?"1 小时 41 分钟")
	 */
	private String durationText;
	/**
	 * 总公里数 (�?"153287")
	 */
	private String distanceValue;
	/**
	 * 总公里数文字描述 (�?"153 公里")
	 */
	private String distanceText;
	/**
	 * 起点纬度
	 */
	private float startLat;
	/**
	 * 起点经度
	 */
	private float startLng;
	/**
	 * 终点纬度
	 */
	private float endLat;
	/**
	 * 终点经度
	 */
	private float endLng;
	/**
	 * 起点位置
	 */
	private String startAddress;
	/**
	 * 终点位置
	 */
	private String endAddress;
	/**
	 * 整条路线的点集合
	 */
	private ArrayList<GeoPoint> overviewPolyline = new ArrayList<GeoPoint>();
	/**
	 * 西南范围纬度
	 */
	private float boundSouthwestLat;
	/**
	 * 西南范围经度
	 */
	private float boundSouthwestLng;
	/**
	 * 东北范围纬度
	 */
	private float boundNortheastLat;
	/**
	 * 东北范围经度
	 */
	private float boundNortheastLng;
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the durationValue
	 */
	public String getDurationValue() {
		return durationValue;
	}
	/**
	 * @param durationValue the durationValue to set
	 */
	public void setDurationValue(String durationValue) {
		this.durationValue = durationValue;
	}
	/**
	 * @return the durationText
	 */
	public String getDurationText() {
		return durationText;
	}
	/**
	 * @param durationText the durationText to set
	 */
	public void setDurationText(String durationText) {
		this.durationText = durationText;
	}
	/**
	 * @return the distanceValue
	 */
	public String getDistanceValue() {
		return distanceValue;
	}
	/**
	 * @param distanceValue the distanceValue to set
	 */
	public void setDistanceValue(String distanceValue) {
		this.distanceValue = distanceValue;
	}
	/**
	 * @return the distanceText
	 */
	public String getDistanceText() {
		return distanceText;
	}
	/**
	 * @param distanceText the distanceText to set
	 */
	public void setDistanceText(String distanceText) {
		this.distanceText = distanceText;
	}
	/**
	 * @return the startLat
	 */
	public float getStartLat() {
		return startLat;
	}
	/**
	 * @param startLat the startLat to set
	 */
	public void setStartLat(float startLat) {
		this.startLat = startLat;
	}
	/**
	 * @return the startLng
	 */
	public float getStartLng() {
		return startLng;
	}
	/**
	 * @param startLng the startLng to set
	 */
	public void setStartLng(float startLng) {
		this.startLng = startLng;
	}
	/**
	 * @return the endLat
	 */
	public float getEndLat() {
		return endLat;
	}
	/**
	 * @param endLat the endLat to set
	 */
	public void setEndLat(float endLat) {
		this.endLat = endLat;
	}
	/**
	 * @return the endLng
	 */
	public float getEndLng() {
		return endLng;
	}
	/**
	 * @param endLng the endLng to set
	 */
	public void setEndLng(float endLng) {
		this.endLng = endLng;
	}
	/**
	 * @return the startAddress
	 */
	public String getStartAddress() {
		return startAddress;
	}
	/**
	 * @param startAddress the startAddress to set
	 */
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	/**
	 * @return the endAddress
	 */
	public String getEndAddress() {
		return endAddress;
	}
	/**
	 * @param endAddress the endAddress to set
	 */
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	/**
	 * @return the boundSouthwestLat
	 */
	public float getBoundSouthwestLat() {
		return boundSouthwestLat;
	}
	/**
	 * @param boundSouthwestLat the boundSouthwestLat to set
	 */
	public void setBoundSouthwestLat(float boundSouthwestLat) {
		this.boundSouthwestLat = boundSouthwestLat;
	}
	/**
	 * @return the boundSouthwestLng
	 */
	public float getBoundSouthwestLng() {
		return boundSouthwestLng;
	}
	/**
	 * @param boundSouthwestLng the boundSouthwestLng to set
	 */
	public void setBoundSouthwestLng(float boundSouthwestLng) {
		this.boundSouthwestLng = boundSouthwestLng;
	}
	/**
	 * @return the boundNortheastLat
	 */
	public float getBoundNortheastLat() {
		return boundNortheastLat;
	}
	/**
	 * @param boundNortheastLat the boundNortheastLat to set
	 */
	public void setBoundNortheastLat(float boundNortheastLat) {
		this.boundNortheastLat = boundNortheastLat;
	}
	/**
	 * @return the boundNortheastLng
	 */
	public float getBoundNortheastLng() {
		return boundNortheastLng;
	}
	/**
	 * @param boundNortheastLng the boundNortheastLng to set
	 */
	public void setBoundNortheastLng(float boundNortheastLng) {
		this.boundNortheastLng = boundNortheastLng;
	}
	/**
	 * @return the steps
	 */
	public ArrayList<Step> getSteps() {
		return steps;
	}
	/**
	 * @return the overviewPolyline
	 */
	public ArrayList<GeoPoint> getOverviewPolyline() {
		return overviewPolyline;
	}
	
}
