package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.print.PrintSystem;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	    PrintSystem printSystem = applicationContext.getBean("printSystem", PrintSystem.class);
		System.out.println(printSystem.print());
	}

}
