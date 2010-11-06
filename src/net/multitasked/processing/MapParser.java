package net.multitasked.processing;


public class MapParser {

	@SuppressWarnings("deprecation")
	static void parse(RouteDrawer parent, String filename) {




			String[] strings=parent.loadStrings(filename);
			// Here BufferedInputStream is added for fast reading.

			String s = strings[0];
			parent.northLimitLatitude=Float.parseFloat(s.split(":")[1]);
			s = strings[1];
			parent.eastLimitLongitude=Float.parseFloat(s.split(":")[1]);
			s = strings[2];
			parent.southLimitLatitude=Float.parseFloat(s.split(":")[1]);
			s = strings[3];
			parent.westLimitLongitude=Float.parseFloat(s.split(":")[1]);
			s = strings[4];
			parent.maxX=Integer.parseInt(s.split(":")[1]);
			s = strings[5];
			parent.maxY=Integer.parseInt(s.split(":")[1]);
			s = strings[6];
			parent.mapName= s.split(":")[1];


	}
}