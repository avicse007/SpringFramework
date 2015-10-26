package com.avinash.beanclasses;

public class Square implements Shape {
	
	private String type;
	private String size;
	private Integer height;
	
	public Square(Integer height){
		this.height=height;
	}
	public Square(String type){
		this.type=type;
	}
	public Square(String type ,String size){
		this.type = type;
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public  void draw() {
		//System.out.println("I am a Square of type "+type);
		//System.out.println("I am a Square with size "+size);
		System.out.println("I am a Square with height "+height);
	}


}
