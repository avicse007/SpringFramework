SpringFramework
=================
Tutorials for Spring framework 


## Spring Ioc

Spring Framework is built on the Inversion of Control principle. Dependency injection is the technique to implement IoC in applications. 

### Spring IoC Container

Spring IoC is the mechanism to achieve loose-coupling between Objects dependencies. To achieve loose coupling and dynamic binding of the objects at runtime, objects dependencies are injected by other assembler objects. Spring IoC container is the program that injects dependencies into an object and make it ready for our use.In the Spring framework, the IoC container is represented by the interface ApplicationContext. The Spring container is responsible for instantiating, configuring and assembling objects known as beans, as well as managing their lifecycle.
#### In order to assemble beans, the container uses configuration metadata, which can be in the form of XML configuration or annotations.Dependency Injection in Spring can be done through constructors, setters or fields.


Spring IoC container classes are part of  org.springframework.beans and org.springframework.context packages. Spring IoC container provides us different ways to decouple the object dependencies.

##### BeanFactory is the root interface of Spring IoC container. 
##### ApplicationContext is the child interface of BeanFactory interface that provide Spring AOP features, i18n etc. 
##### A BeanFactory is essentially nothing more than the interface for an advanced factory capable of maintaining a registry of different beans and their dependencies.

## ApplicationContext

Central interface to provide configuration for an application. This is read-only while the application is running, but may be reloaded if the implementation supports this.

An ApplicationContext provides:

   ##### Bean factory methods for accessing application components. Inherited from ListableBeanFactory.
   ##### The ability to load file resources in a generic fashion. Inherited from the ResourceLoader interface.
   ##### The ability to publish events to registered listeners. Inherited from the ApplicationEventPublisher interface.
   ##### The ability to resolve messages, supporting internationalization. Inherited from the MessageSource interface.
   ##### Inheritance from a parent context. Definitions in a descendant context will always take priority. This means, for example, that a single parent context can be used by an entire web application, while each servlet has its own child context that is independent of that of any other servlet. 

In addition to standard BeanFactory lifecycle capabilities, ApplicationContext implementations detect and invoke ApplicationContextAware beans as well as ResourceLoaderAware, ApplicationEventPublisherAware and MessageSourceAware beans.

Some of the useful ApplicationContext implementations that we use are;
##### AnnotationConfigApplicationContext:
##### ClassPathXmlApplicationContext:
##### FileSystemXmlApplicationContext
##### AnnotationConfigWebApplicationContext and XmlWebApplicationContext

### Multiple ApplicationContext in one project

Tt is common to have more than one application context in a Web application, because Spring has a notion of hierachies of ApplicationContext. You could declare them as :

ApplicationContext context1 = new ClassPathXmlApplicationContext("Beans.xml");
ApplicationContext context2 = new ClassPathXmlApplicationContext("Beans.xml", context1);

Here you can retrieve from context1 only beans declared in it, but from context2 you will retrieve beans from context2 and context1. Specifically, beans are first looked for in context2 and if not found then looked for in context1.

This is used in Spring MVC where you normally have one root context (for all beans not directly related to the MVC DispatcherServlet) and one child context dedicated to the DispatcherServlet that will contain the beans for controllers, views, interceptors, etc.

## The Root Web Application Context

Every Spring webapp has an associated application context that is tied to its lifecycle: the root web application context.

This is an old feature that predates Spring Web MVC, so it's not tied specifically to any web framework technology.

The context is started when the application starts, and it's destroyed when it stops, thanks to a servlet context listener. The most common types of contexts can also be refreshed at runtime, although not all ApplicationContext implementations have this capability.

The context in a web application is always an instance of WebApplicationContext. That's an interface extending ApplicationContext with a contract for accessing the ServletContext.

Anyway, applications usually should not be concerned about those implementation details: the root web application context is simply a centralized place to define shared beans.

## The ContextLoaderListener

The root web application context described in the previous section is managed by a listener of class org.springframework.web.context.ContextLoaderListener, which is part of the spring-web module.


## Programmatic Configuration with Servlet 3.x

Version 3 of the Servlet API has made configuration through the web.xml file completely optional. Libraries can provide their web fragments, which are pieces of XML configuration that can register listeners, filters, servlets and so on.

Also, users have access to an API that allows defining programmatically every element of a servlet-based application.

The spring-web module makes use of these features and offers its API to register components of the application when it starts.

Spring scans the application's classpath for instances of the org.springframework.web.WebApplicationInitializer class. This is an interface with a single method, void onStartup(ServletContext servletContext) throws ServletException, that's invoked upon application startup.


## Using Servlet 3.x and a Java Application Context

If we want to use an annotation-based context, we could make uso of an AnnotationConfigWebApplicationContext.
The WebApplicationInitializer class is a general-purpose interface. It turns out that Spring provides a few more specific implementations, including an abstract class called AbstractContextLoaderInitializer.
Its job, as the name implies, is to create a ContextLoaderListener and register it with the servlet container.We only have to tell it how to build the root context:
	
public class AnnotationsBasedApplicationInitializer 
  extends AbstractContextLoaderInitializer {
  
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootContext
          = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootApplicationConfig.class);
        return rootContext;
    }
}

Here we can see that we no longer need to register the ContextLoaderListener, which saves us from a little bit of boilerplate code.

## Dispatcher Servlet Contexts

Spring MVC applications have at least one Dispatcher Servlet configured (but possibly more than one, we'll talk about that case later). This is the servlet that receives incoming requests, dispatches them to the appropriate controller method, and returns the view.

Each DispatcherServlet has an associated application context. Beans defined in such contexts configure the servlet and define MVC objects like controllers and view resolvers.

### Using Servlet 3.x and a Java Application Context

This time, we'll configure an annotations-based context using a specialized implementation of WebApplicationInitializer: AbstractDispatcherServletInitializer.

That's an abstract class that, besides creating a root web application context as previously seen, allows us to register one dispatcher servlet with minimum boilerplate:
	
@Override
protected WebApplicationContext createServletApplicationContext() {
  
    AnnotationConfigWebApplicationContext secureWebAppContext
      = new AnnotationConfigWebApplicationContext();
    secureWebAppContext.register(SecureWebAppConfig.class);
    return secureWebAppContext;
}
 
@Override
protected String[] getServletMappings() {
    return new String[] { "/s/api/*" };
}


## Parent and Child Contexts

So far, we've seen two major types of contexts: the root web application context and the dispatcher servlet contexts. Then, we might have a question: are those contexts related?

It turns out that yes, they are. In fact, the root context is the parent of every dispatcher servlet context. Thus, beans defined in the root web application context are visible to each dispatcher servlet context, but not vice versa.

So, typically, the root context is used to define service beans, while the dispatcher context contains those beans that are specifically related to MVC.

Note that we've also seen ways to create the dispatcher servlet context programmatically. If we manually set its parent, then Spring does not override our decision, and this section no longer applies.

In simpler MVC applications, it's sufficient to have a single context associated to the only one dispatcher servlet. There's no need for overly complex solutions!

Still, the parent-child relationship becomes useful when we have multiple dispatcher servlets configured. But when should we bother to have more than one?

In general, we declare multiple dispatcher servlets when we need multiple sets of MVC configuration

## Spring Boot and Application Context Hierarchy

Consider a contrived use-case of using multiple application contexts and the application context hierarchy — this is to provide two different ports with a different set of endpoints at each of these ports. 

Child1 and Child2 are typical Spring Boot Applications, along these lines:

package child1;   

import org.springframework.beans.factory.annotation.Value; 

import org.springframework.boot.autoconfigure.SpringBootApplication; 

import org.springframework.context.annotation.Bean; 

import org.springframework.context.annotation.PropertySource; 

import root.RootBean;   

@SpringBootApplication 

@PropertySource("classpath:/child1.properties") 

public class ChildContext1 {       

    @Bean     

    public ChildBean1 childBean(RootBean rootBean, 

    @Value("${root.property}") String someProperty) 

    {                        

        return new ChildBean1(rootBean, someProperty);     

    } 

}


Each of the applications resides in its own root package to avoid collisions when scanning for beans. Note that the bean in the child contexts depends on a bean that is expected to come from the root context. 

The port to listen on is provided as properties, since the two contexts are expected to listen on different ports I have explicitly specified the property file to load with a content along these lines:

server.port=8080

spring.application.name=child1



Given this setup, Spring Boot provides a fluid interface to load up the root context and the two child contexts:

SpringApplicationBuilder appBuilder =

       new

SpringApplicationBuilder()

               .parent(RootContext.class)

               .child(ChildContext1.class)

               .sibling(ChildContext2.class);

ConfigurableApplicationContext applicationContext  = appBuilder.run();


The application context returned by the SpringBootApplicationBuilder appears to be the final one in the chain, defined via ChildContext2 above.

If the application is now started up, there would be a root context with two different child contexts, each exposing an endpoint via a different port. 




## Spring Bean Configuration

Spring Framework provides three ways to configure beans to be used in the application.
##### Annotation Based Configuration
##### XML Based Configuration
##### Java Based Configuration


## Autowiring Dependencies

Wiring allows the Spring container to automatically resolve dependencies between collaborating beans by inspecting the beans that have been defined.

There are four modes of autowiring a bean using an XML configuration:
##### no: the default value – this means no autowiring is used for the bean and we have to explicitly name the dependencies
##### byName: autowiring is done based on the name of the property, therefore Spring will look for a bean with the same name as the property that needs to be set
##### byType: similar to the byName autowiring, only based on the type of the property. This means Spring will look for a bean with the same type of the property to set. If there's more than one bean of that type, the framework throws an exception.
##### constructor: autowiring is done based on constructor arguments, meaning Spring will look for beans with the same type as the constructor arguments

