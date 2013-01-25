package com.readystatesoftware.search;

import java.net.URLEncoder;
import java.util.ArrayList;



import com.readystatesoftware.search.base.GMapUtils;
import com.readystatesoftware.search.base.Route;
/**
 * GoogleMap 搜索服务类
 * @author chunjiang.shieh
 *
 */
public class MKSearch {
	
	
	public static final String OUTPUT_JSON = "json";
	public static final String OUTPUT_XML = "xml";
	
//	private static final String TAG = MKSearch.class.getName();
	/**
	 * Google directions API  url
	 */
	private static final String URL = "http://maps.google.com/maps/api/directions/";
	
	private static final String MODE_DRIVING = "driving";
	private static final String MODE_WALKING = "walking";
	private static final String MODE_BICYCLING = "bicycling"; //目前只有美国支持
	
	private MKSearchListener mMKSearchListener;
	private String mHostUrl;
	
	public MKSearch(){
		
	}
	
	/**
	 * 初始化GoogleMap搜索服务
	 * @param output	请求返回数据的类型
	 * @param mSearchListener		搜索请求结果的回调接口
	 */
	public void init(String output,MKSearchListener mSearchListener){
		this.mMKSearchListener = mSearchListener;
		if(output == null){		//默认XML
			output = OUTPUT_XML;
		}
		this.mHostUrl = URL + output;
	}
	
	/**
	 * 驾车导航
	 * @param origin		起点
	 * @param destination		终点
	 * @param sensor	是否依赖传感器
	 */
	public void drivingSearch(String origin,String destination,boolean sensor){
		String url = getUrl(origin, destination, sensor, MODE_DRIVING);
//		String url = "http://maps.google.com/maps/api/directions/xml?origin=30.32317512735784,120.37664137780666&destination=29.96199144883833,121.63961380720138&language=zh&sensor=true&mode=driving";
		ArrayList<Route> routes = GMapUtils.getInstance().decodeResponse2Route(origin,
				destination,url);
		if(mMKSearchListener != null){
			mMKSearchListener.onGetDrivingRouteResult(routes);
		}
	}
	
	
	/**
	 * 步行导航
	 * @param origin		起点
	 * @param destination		终点
	 * @param sensor	是否依赖传感器
	 */
	public void walkingSearch(String origin,String destination,boolean sensor){
		String url = getUrl(origin, destination, sensor, MODE_WALKING);
		ArrayList<Route> routes = GMapUtils.getInstance().decodeResponse2Route(origin,
				destination,url);
		if(mMKSearchListener != null){
			mMKSearchListener.onGetWalkingRouteResult(routes);
		}
	}
	
	/**
	 * 自行车导航
	 * @param origin		起点
	 * @param destination		终点
	 * @param sensor	是否依赖传感器
	 */
	public void bicyclingSearch(String origin,String destination,boolean sensor){
		String url = getUrl(origin, destination, sensor, MODE_BICYCLING);
		ArrayList<Route> routes = GMapUtils.getInstance().decodeResponse2Route(origin,
				destination,url);
		if(mMKSearchListener != null){
			mMKSearchListener.onGetBicyclingRouteResult(routes);
		}
	}
	
	/**
	 * 拼接请求的URL
	 * @param origin
	 * @param destination
	 * @param sensor
	 * @param mode
	 * @return
	 */
	private String getUrl(String origin,String destination,boolean sensor,String mode){
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(mHostUrl);
		urlBuffer.append("?origin=");
		urlBuffer.append(URLEncoder.encode(origin));
		urlBuffer.append("&destination=");
		urlBuffer.append(URLEncoder.encode(destination));
		urlBuffer.append("&language=zh");
		urlBuffer.append("&sensor=");
		urlBuffer.append(sensor);
		urlBuffer.append("&mode=");
		urlBuffer.append(mode);
		return urlBuffer.toString();
	}

}
