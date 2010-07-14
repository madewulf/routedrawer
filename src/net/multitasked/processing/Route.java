package net.multitasked.processing;

import java.util.ArrayList;

import processing.core.PVector;

public class Route {

	ArrayList<PVector> nodes;
	PVector stroke;
	PVector fill;
	ArrayList<Integer> stay;
	ArrayList<Integer> travel;
	public Integer index;
	int frameCount;
	RouteDrawer parent;

	public Route(RouteDrawer parent) {
		this.parent = parent;
		stroke = new PVector(parent.random(255), 
							 parent.random(255), 
							 parent.random(255));
		fill = new PVector(parent.random(255), 
						   parent.random(255), 
						   parent.random(255));
		stay = new ArrayList<Integer>();
		travel = new ArrayList<Integer>();
		nodes = new ArrayList<PVector>();
		index = 0;
		frameCount = 0;
	}

	public void draw() {
		parent.stroke(fill.x, fill.y, fill.z);
		parent.fill(fill.x, fill.y, fill.z);
		float currentTime = (float) frameCount;
		PVector currentPoint;
		int currentFrame = frameCount % parent.rate;
		float timeSpent = 0;
		for (int i = 0; i < index; i++) {
			timeSpent += stay.get(i) + travel.get(i);
			parent.line(nodes.get(i).x, 
					    nodes.get(i).y, 
					    nodes.get(i + 1).x,
					    nodes.get(i + 1).y);

		}
		float remainder = currentTime - timeSpent;
		if (remainder < stay.get(index) || index == nodes.size() - 1) {
			currentPoint = nodes.get(index);
		} else {
			remainder -= stay.get(index);
			PVector previousNode = nodes.get(index);
			PVector nextNode = nodes.get(index + 1);
			currentPoint = new PVector(previousNode.x, previousNode.y);

			currentPoint.add(PVector.mult(PVector.sub(nextNode, previousNode),
							(float) (remainder / travel.get(index))));
			parent.line(previousNode.x, 
					    previousNode.y, 
					    currentPoint.x,
					    currentPoint.y);
			
		}
		parent.ellipse(
				currentPoint.x, 
				currentPoint.y, 
				parent.cos(parent.PI* ((float) currentFrame / parent.rate))* parent.dotSize, 
				parent.cos(parent.PI* ((float) currentFrame / parent.rate))* parent.dotSize
				);
		if (timeSpent + stay.get(index) + travel.get(index) <= currentTime
				&& index < nodes.size() - 1) {
			index++;
		}
		frameCount++;
	}
}
