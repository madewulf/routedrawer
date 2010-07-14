package net.multitasked.processing;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

public class RouteDrawer extends PApplet {
	private static final long serialVersionUID = 1L;

	int rate = 30;
	int dotSize = 10;
	int maxX ;
	int maxY ;
	float westLimitLongitude ;
	float northLimitLatitude ;
	float eastLimitLongitude ;
	float southLimitLatitude ;
	String inputFileName = "data/handmade.txt";
	String mapName;
	int minutes=0;
	List<Route> routes;
	PImage bg;

	public void setup() {
		routes = RoutesParser.parse(this, inputFileName);
		MapParser.parse(this, "data/france.map");
		frameRate(rate);
		size(maxX, maxY, P2D);
		bg = loadImage(mapName);
		background(0);
		smooth();

		reinit();

	}

	private void reinit() {
		minutes=0;
		routes = RoutesParser.parse(this, inputFileName);
	}

	public void draw() {
		background(bg);
	
		text((minutes/60)+ ":" +(minutes%60), 15, 20, 400, 400);
		minutes++;
		boolean allDone = true;
		for (int i = 0; i < routes.size(); i++) {
			routes.get(i).draw();
			allDone &= (routes.get(i).nodes.size() - 1 == routes.get(i).index);
		}
		if (allDone)
			reinit();
	}

	static public void main(String args[]) {
		PApplet.main(new String[] { "--bgcolor=#D4D0C8",
				"net.multitasked.processing.RouteDrawer" });
	}
}
