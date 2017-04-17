package de.storost;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.storost.spring.tx.HelloWorld;
import de.storost.spring.tx.config.Config;
import de.storost.spring.tx.config.HelloWorldBean;
  
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HelloWorldBean.class, Config.class})
//@ActiveProfiles("development")  // Does NOT work
public class HelloWorldBeanTest {
    
  @Autowired 
  HelloWorld helloWorld;
   
  public HelloWorldBeanTest () {
    // set active profile manually in constructor
    //System.setProperty("spring.profiles.active","production");
  }
    
  @Test public void testSayHello() {
	System.out.println("test" + helloWorld.returnMessage());
    assertEquals("test",helloWorld.test());
  }
  
  @Test public void testSayHello2() {
    assertEquals("Hello","Hello");
  }
}