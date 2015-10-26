package com.avinash.beanclasses;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TriangleWithApplicationContext implements ApplicationContextAware,BeanNameAware,Shape {
	
	private Point pointA;
	private Point pointB;
	private Point pointC;
	private ApplicationContext context;
	
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

	public void draw(){
		System.out.println("Triangle with application Context with points are : \n("+
				pointA.x+","+pointA.y+")\n"
				+pointB.x+","+pointB.y+")\n"
				+pointC.x+","+pointC.y+")");
	}
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		// TODO Auto-generated method stub
		this.context = context ;
		
	}
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		//this is called or get executed before the program get executed so this will be first print statement
		System.out.println("bean name is "+name);
		
	}
	
}
