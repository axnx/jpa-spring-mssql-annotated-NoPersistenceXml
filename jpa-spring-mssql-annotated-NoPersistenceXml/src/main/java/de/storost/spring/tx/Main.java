package de.storost.spring.tx;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.storost.spring.tx.model.User;
import de.storost.spring.tx.user.UserManager;

public class Main {
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");

		List<User> list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());

		User user = new User();
		user.setUsername("mstorost");
		user.setName("Matthias Storost");
		userManager.insertUser(user);
		System.out.println("User inserted!");

		list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());
		
		HelloWorld obj = (HelloWorld) ctx.getBean("helloWorld");
	    obj.getMessage();

	}
}
