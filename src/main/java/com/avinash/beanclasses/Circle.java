package com.avinash.beanclasses;

public class Circle implements Shape{
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public  void draw() {
		System.out.println("I am a Circle of type "+type);

	}


}
