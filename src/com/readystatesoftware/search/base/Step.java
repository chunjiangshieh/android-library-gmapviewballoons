/**
 * 
 */
package com.readystatesoftware.search.base;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

/**
 * @file Step.java
 * @author linbin
 * @email linbin.java@gmail.com
 * @date 2012-7-24
 * @description 
 */
public class Step implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 路段起点纬度
	 */
	private float startLat;
	/**
	 * 路段起点经度
	 */
	private float startLng;
	/**
	 * 路段终点纬度
	 */
	private float endLat;
	/**
	 *路段起点经度
	 */
	private float endLng;
	/**
	 * 路段的点集合
	 */
	private ArrayList<GeoPoint> polyline = new ArrayList<GeoPoint>();
	/**
	 * 路段时长 (�?"2")
	 */
	private String durationValue;
	/**
	 * 路段时长文字描述 (�?"1 分钟")
	 */
	private String durationText;
	/**
	 * 路段详情
	 */
	private String instructions;
	/**
	 * 路段公里�?(�?"253")
	 */
	private String distanceValue;
	/**
	 * 路段公里数文字描�?(�?"0.3 公里")
	 */
	private String distanceText;
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
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}
	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
	 * @return the polyline
	 */
	public ArrayList<GeoPoint> getPolyline() {
		return polyline;
	}
	
}
