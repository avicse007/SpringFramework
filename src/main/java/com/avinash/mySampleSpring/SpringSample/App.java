package com.avinash.mySampleSpring.SpringSample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.avinash.beanclasses.Circle;
import com.avinash.beanclasses.CircleWithCenter;
import com.avinash.beanclasses.Shape;
import com.avinash.beanclasses.Square;
import com.avinash.beanclasses.TraingleWithPoints;
import com.avinash.beanclasses.Triangle;
import com.avinash.beanclasses.TriangleWithApplicationContext;
import com.avinash.beanclasses.TriangleWithListOfPoints;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
      // BeanFactory factory = new Xml	BeanFactory(new FileSystemResource("spring.xml"));
     
    	//Using Application context also you can get beans it a Big B of BeanFactory
    	ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
    	
    	Shape triangle= (Triangle) factory.getBean("triangle");
       Shape square = (Square)factory.getBean("square");
       Shape shape = (Circle)factory.getBean("circle");
       
       Shape triangleWithPoints = (TraingleWithPoints) factory.getBean("triangleWithPoints"); 
      // Shape triangleWithPoints = (TraingleWithPoints) factory.getBean("triangleWithPoints");
      // TriangleWithListOfPoints trianglewithpointlists = (TriangleWithListOfPoints) factory.getBean("trianglewithpointlists");
        Shape circleWithCenter = (CircleWithCenter) factory.getBean("CircleWithCenter");
      // TriangleWithApplicationContext triangelWithApplicationContext = (TriangleWithApplicationContext) factory.getBean("TriangleWithApplicationContext");
      // TraingleWithPoints triangleWithPointsChildBeanInheritance = (TraingleWithPoints) factory.getBean("triangle1");
      // triangleWithPointsChildBeanInheritance.draw();
       //triangle.draw();
       //square.draw();
       shape.draw();
       triangleWithPoints.draw();
       //trianglewithpointlists.draw();
       circleWithCenter.draw();
       //triangelWithApplicationContext.draw();
       
       //System.out.println(factory.getMessage("greeting", null, "Default message",null));
       
    }
}
