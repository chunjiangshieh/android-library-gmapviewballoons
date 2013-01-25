/**
 * 
 */
package com.readystatesoftware.search.base;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

/**
 * @file MyRouteOverlay.java
 * @author linbin
 * @email linbin.java@gmail.com
 * @date 2012-7-24
 * @description route overlay to draw on the map
 */
public class MyRouteOverlay extends Overlay {
	
	private List<GeoPoint> points;
	private Paint paint;

	public MyRouteOverlay(List<GeoPoint> points) {
		this.points = points;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setAlpha(150);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setStrokeWidth(4);
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// not draw shadow
		if (!shadow) {
			Projection projection = mapView.getProjection();
			if (points != null && points.size() >= 2) {
				// draw line
				Point start = new Point();
				// geopint to screen point
				projection.toPixels(points.get(0), start);
				for (int i = 1; i < points.size(); i++) {
					Point end = new Point();
					projection.toPixels(points.get(i), end);
					// draw on canvas
					canvas.drawLine(start.x, start.y, end.x, end.y, paint);
					start = end;
				}
			}
		}
	}
	
}
