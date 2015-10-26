package com.avinash.beanclasses;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//Instead of registering this bean in spring.xml I am using annotation to do this.

@Component
public class MyListener implements ApplicationListener<ApplicationEvent> {

	public void onApplicationEvent(ApplicationEvent event) {
		
		System.out.println(event.toString());
		
	}

}
