/**
 * 
 */
package com.readystatesoftware.search.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.util.Log;

import com.google.android.maps.GeoPoint;

/**
 * @file MapUtils.java
 * @author linbin
 * @email linbin.java@gmail.com
 * @date 2012-7-23
 * @description google map utils
 */
public class GMapUtils {

	private static GMapUtils gMapUtils;
	
	private GMapUtils(){}
	
	public static GMapUtils getInstance() {
		if (gMapUtils == null) {
			gMapUtils = new GMapUtils();
		}
		return gMapUtils;
	} 
	
	/**
	 * calculate route with start point and end point
	 * @param start start point
	 * @param end end point
	 * @return routes
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Route> decodeResponse2Route(String start, String end,String url){
//		String url = "http://maps.google.com/maps/api/directions/xml?origin=" + start +
//				"&destination=" + end + "&language=zh&sensor=false&mode=driving";
//		String url = "http://maps.google.com/maps/api/directions/xml?origin=30.32317512735784,120.37664137780666&destination=29.96199144883833,121.63961380720138&language=zh&sensor=false&mode=driving";
		
		ArrayList<Route> routes = new ArrayList<Route>();
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(getXmlData(url));
			Element rootElement = document.getRootElement();
			if(rootElement != null && "OK".equalsIgnoreCase(rootElement.elementText("status"))){
				List<Element> routeElements = rootElement.elements("route");
				if(routeElements != null && routeElements.size() != 0){
					Route route = null;
					Element tempElement = null;
					for(Element e : routeElements){
						route = new Route();
						// get /DirectionsResponse/route/summary value
						route.setSummary(e.elementText("summary"));
						// get /DirectionsResponse/route/bounds/southwest
						tempElement = (Element) document.selectSingleNode("/DirectionsResponse/route/bounds/southwest");
						route.setBoundSouthwestLat(Float.valueOf(tempElement.elementText("lat")));
						route.setBoundSouthwestLng(Float.valueOf(tempElement.elementText("lng")));
						// get /DirectionsResponse/route/bounds/northeast
						tempElement = (Element) document.selectSingleNode("/DirectionsResponse/route/bounds/southwest");
						route.setBoundNortheastLat(Float.valueOf(tempElement.elementText("lat")));
						route.setBoundNortheastLng(Float.valueOf(tempElement.elementText("lng")));
						// get /DirectionsResponse/route/overview_polyline/points
						tempElement = (Element) document.selectSingleNode("/DirectionsResponse/route/overview_polyline");
						decodePolyline(tempElement.elementText("points"), route.getOverviewPolyline());
						// get /DirectionsResponse/route/leg/step ----> list
						tempElement = (Element) document.selectSingleNode("/DirectionsResponse/route/leg");
						List<Element> steps = tempElement.elements("step");
						if(steps != null && steps.size() != 0){
							Step step = null;
							Element temp = null;
							for(Element s : steps){
								step = new Step();
								// get /DirectionsResponse/route/leg/step/start_location
								temp = s.element("start_location");
								step.setStartLat(Float.valueOf(temp.elementText("lat")));
								step.setStartLng(Float.valueOf(temp.elementText("lng")));
								// get /DirectionsResponse/route/leg/step/end_location
								temp = s.element("end_location");
								step.setEndLat(Float.valueOf(temp.elementText("lat")));
								step.setEndLng(Float.valueOf(temp.elementText("lng")));
								// get /DirectionsResponse/route/leg/step/polyline ---> list
								temp = s.element("polyline");
								decodePolyline(temp.elementText("points"), step.getPolyline());
								// get /DirectionsResponse/route/leg/step/duration
								temp = s.element("duration");
								step.setDurationValue(temp.elementText("value"));
								step.setDurationText(temp.elementText("text"));
								// get /DirectionsResponse/route/leg/step/html_instructions
								String instructions = s.elementText("html_instructions");
								step.setInstructions(html2Text(instructions));
								// get /DirectionsResponse/route/leg/step/distance
								temp = s.element("distance");
								step.setDistanceValue(temp.elementText("value"));
								step.setDistanceText(temp.elementText("text"));
								route.getSteps().add(step);
								step = null;
								temp = null;
							}
						}
						// get /DirectionsResponse/route/leg/duration
						Element duration = tempElement.element("duration");
						route.setDurationValue(duration.elementText("value"));
						route.setDurationText(duration.elementText("text"));
						duration = null;
						// get /DirectionsResponse/route/leg/distance
						Element distance = tempElement.element("distance");
						route.setDistanceValue(distance.elementText("value"));
						route.setDistanceText(distance.elementText("text"));
						distance = null;
						// get /DirectionsResponse/route/leg/start_location
						Element start_location = tempElement.element("start_location");
						route.setStartLat(Float.valueOf(start_location.elementText("lat")));
						route.setStartLng(Float.valueOf(start_location.elementText("lng")));
						start_location = null;
						// get /DirectionsResponse/route/leg/start_location
						Element end_location = tempElement.element("end_location");
						route.setEndLat(Float.valueOf(end_location.elementText("lat")));
						route.setEndLng(Float.valueOf(end_location.elementText("lng")));
						end_location = null;
						// get /DirectionsResponse/route/leg/start_address
						route.setStartAddress(tempElement.elementText("start_address"));
						// get /DirectionsResponse/route/leg/end_address
						route.setEndAddress(tempElement.elementText("end_address"));
						tempElement = null;
						routes.add(route);
					}
				}
			}else{
				return null;
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			routes = null;
		}
		
		return routes;
	}
	
	/**
	 * get data from google server
	 * @param requestUrl request url 
	 * @return inputStream
	 */
	private InputStream getXmlData(String requestUrl){
		URL url = null;
		HttpURLConnection urlcon = null;
		InputStream is = null;
		try {
			url = new URL(requestUrl);
			Log.e("request-url", url.toString());
			urlcon = (HttpURLConnection) url.openConnection();
			urlcon.connect();
			urlcon.setConnectTimeout(5000);
			is = urlcon.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return is;
	}
	
	/**
	 * decode overview_polyline from xml data
	 * @param encryption encryption text
	 * @return list of geopoint
	 */
	private ArrayList<GeoPoint> decodePolyline(String encryption, ArrayList<GeoPoint> polyline) {

	    int index = 0, len = encryption.length();
	    int lat = 0, lng = 0;
	    GeoPoint p;
	    while (index < len) {
	        int b, shift = 0, result = 0;
	        do {
	            b = encryption.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lat += dlat;

	        shift = 0;
	        result = 0;
	        do {
	            b = encryption.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lng += dlng;

	        p = new GeoPoint((int) (((double) lat / 1E5) * 1E6), (int) (((double) lng / 1E5) * 1E6));
	        polyline.add(p);
	    }

	    return polyline;
	}
	
	/**
	 * html to normal text
	 * @param htmlStr text with html
	 * @return text without html
	 */
	private String html2Text(String htmlStr) {
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; 
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; 
			String regEx_html = "<[^>]+>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); 
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); 
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr.replaceAll("&nbsp;", "");
			textStr = htmlStr.replaceAll("<",  "<");
			textStr = htmlStr.replaceAll(">",  ">");
			textStr = htmlStr.replaceAll("®", "®");
			textStr = htmlStr.replaceAll("&", "&");
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}
}
