package de.storost.spring.tx;

public class HelloWorld  {

   private String message;

   public void setMessage(String message){
      this.message = message;
   }
   public void getMessage(){
      System.out.println("Your Message : " + message);
   }
   public void init(){
      System.out.println("Bean is going through init.");
   }
   public void destroy() {
      System.out.println("Bean will destroy now.");
   }
   public void print() {
       System.out.println("print your Message : " + message);
   }
   
   public String returnMessage() {
	      return message;   
   }
   
   public String test() {
      return "test";
   }
   
	
}
