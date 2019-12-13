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

