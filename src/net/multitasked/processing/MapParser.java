package net.multitasked.processing;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapParser {

	@SuppressWarnings("deprecation")
	static void parse(RouteDrawer parent, String filename) {
		File file = new File(filename);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		Route currentRoute = null;
		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			String s = dis.readLine();
			parent.northLimitLatitude=Float.parseFloat(s.split(":")[1]);
			s = dis.readLine();
			parent.eastLimitLongitude=Float.parseFloat(s.split(":")[1]);
			s = dis.readLine();
			parent.southLimitLatitude=Float.parseFloat(s.split(":")[1]);
			s = dis.readLine();
			parent.westLimitLongitude=Float.parseFloat(s.split(":")[1]);
			s = dis.readLine();
			parent.maxX=Integer.parseInt(s.split(":")[1]);
			s = dis.readLine();
			parent.maxY=Integer.parseInt(s.split(":")[1]);
			s = dis.readLine();
			parent.mapName= s.split(":")[1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}