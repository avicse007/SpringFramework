package com.avinash.beanclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;

public class CircleWithCenter implements Shape,ApplicationEventPublisherAware{
	private Point center;
	@Autowired
	private MessageSource messageSource;

	private ApplicationEventPublisher publisher;
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	public void draw(){
		System.out.println(messageSource.getMessage("greeting", null, "Default Message", null));
		System.out.println(messageSource.getMessage("drawing.circle", null, "Default Message", null));
		System.out.println(messageSource.getMessage("drawing.Points", new Object[]{center.getX(),center.getY()}, "Default Message", null));
		//System.out.println("Circle with center point is : ("+center.getX()+","+center.getY()+")");
		DrawEvent drawEvent =new DrawEvent(this);
		publisher.publishEvent(drawEvent);
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		// TODO Auto-generated method stub
		this.publisher=publisher;
		
	}

}
