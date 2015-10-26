package com.avinash.beanclasses;

import java.util.List;


public class TriangleWithListOfPoints implements Shape{
	private List<Point> points;

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
	public void draw(){
		System.out.println("Triangle with points as list are ");
		for(Point p : points){
			System.out.println("("+p.x+","+p.y+")");
		}
		
	}
	

}
