/**
 * 
 */
package com.readystatesoftware.search.base;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * @file MyNaviRouteItemizedOverlay.java
 * @author linbin
 * @email linbin.java@gmail.com
 * @date 2012-7-24
 * @description 
 */
public class MyNaviRouteItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
	private Drawable defaultMarker;
	
	/**
	 * @param defaultMarker
	 */
	public MyNaviRouteItemizedOverlay(Drawable defaultMarker) {
		super(defaultMarker);
		this.defaultMarker = defaultMarker;
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return items.get(i);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return items.size();
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(defaultMarker);
	}
	
	public void addItem(OverlayItem item){
		items.add(item);
		populate();
	}
	
	public void removeAllItems(){
		items.clear();
		populate();
	}

}
