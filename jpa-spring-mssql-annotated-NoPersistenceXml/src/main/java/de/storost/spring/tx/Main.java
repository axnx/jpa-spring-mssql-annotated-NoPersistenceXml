package de.storost.spring.tx;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.storost.spring.tx.config.Config;
import de.storost.spring.tx.config.HelloWorldBean;
import de.storost.spring.tx.model.User;
import de.storost.spring.tx.user.UserManager;

public class Main {
	public static void main(String[] args) {
		 
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class, HelloWorldBean.class);

		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");

		List<User> list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());

		User user = new User();
		user.setUsername("mmustermann");
		user.setName("Max Mustermann");
		userManager.insertUser(user);
		System.out.println("User inserted!");

		list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());
		
		HelloWorld hwobj = (HelloWorld) ctx.getBean("helloWorld");
		hwobj.getMessage();

	}
}
