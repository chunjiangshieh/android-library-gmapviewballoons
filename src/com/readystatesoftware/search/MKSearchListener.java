package com.readystatesoftware.search;

import java.util.ArrayList;

import com.readystatesoftware.search.base.Route;
/**
 * 搜索接口的回调
 * @author chunjiang.shieh
 *
 */
public interface MKSearchListener {
	
	public void onGetDrivingRouteResult(ArrayList<Route> routes);
	
	public void onGetWalkingRouteResult(ArrayList<Route> routes);
	
	
	public void onGetBicyclingRouteResult(ArrayList<Route> routes);

}
