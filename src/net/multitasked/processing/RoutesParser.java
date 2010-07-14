package net.multitasked.processing;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

public class RoutesParser {

	static List<Route> parse(RouteDrawer parent, String filename) {
		ArrayList<Route> routeList = new ArrayList<Route>();
		File file = new File(filename);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		Route currentRoute = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);


			while (dis.available() != 0) {


				String s = dis.readLine();

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
						System.out.println(currentRoute.nodes.size());
					}
					currentRoute = new Route(parent);
				}
			}

			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return routeList;
	}

}
