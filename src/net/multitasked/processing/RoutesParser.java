package net.multitasked.processing;


import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

public class RoutesParser {

	static List<Route> parse(RouteDrawer parent, String filename) {
		ArrayList<Route> routeList = new ArrayList<Route>();

		Route currentRoute = null;

			String[] lines =  parent.loadStrings(filename);
			int index=0;

			while (index<lines.length) {
				String s =lines[index];
				index++;
				String[] nodeInfos = s.split(",");

				if (nodeInfos.length > 1)
				{
					Float longitudeScaled= Float.parseFloat(nodeInfos[1].trim());
					Float latitudeScaled = Float.parseFloat(nodeInfos[0].trim());
					
					longitudeScaled = ((longitudeScaled-parent.westLimitLongitude)/(parent.eastLimitLongitude-parent.westLimitLongitude))*parent.maxX;
					latitudeScaled= ((latitudeScaled-parent.northLimitLatitude)/(parent.southLimitLatitude-parent.northLimitLatitude))*parent.maxY;		
					
					PVector coordinates = new PVector(longitudeScaled,latitudeScaled);
					currentRoute.nodes.add(coordinates);
					currentRoute.stay.add( Integer.parseInt(nodeInfos[2].trim()));
					currentRoute.travel.add( Integer.parseInt(nodeInfos[3].trim()));
				}
				else {
					if (currentRoute != null)
					{
						routeList.add(currentRoute);
						//System.out.println(currentRoute.nodes.size());
					}
					currentRoute = new Route(parent);
				}
			}
		return routeList;
	}

}
