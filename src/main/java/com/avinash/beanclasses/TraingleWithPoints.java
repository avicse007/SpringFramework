package com.avinash.beanclasses;

public class TraingleWithPoints implements Shape{
	private Point pointA;
	private Point pointB;
    private Point pointC;
    
    public void draw(){
    	System.out.println("Triangle with points");
    	System.out.println("("+pointA.x+","+pointA.y+")");
    	System.out.println("("+pointB.x+","+pointB.y+")");
    	System.out.println("("+pointC.x+","+pointC.y+")");
    	
    }

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
}
