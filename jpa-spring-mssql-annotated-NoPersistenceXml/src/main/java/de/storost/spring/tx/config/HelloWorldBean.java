package de.storost.spring.tx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import de.storost.spring.tx.HelloWorld;

@Configuration
@PropertySource("classpath:app.properties")
public class HelloWorldBean {

	@Value("${my.message}") 
    String name;
	
	@Autowired
    Environment env;
	
	@Bean(name = "helloWorld", initMethod = "print")
	public HelloWorld helloWorld(){
		HelloWorld hw = new HelloWorld();
		System.out.println(env.getProperty("my.prop"));
		hw.setMessage(name);
		return hw;
	}
}
