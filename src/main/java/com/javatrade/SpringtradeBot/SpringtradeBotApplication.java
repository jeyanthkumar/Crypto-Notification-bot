package com.javatrade.SpringtradeBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.javatrade.SpringtradeBot.main.Main;

@SpringBootApplication
@ComponentScan(basePackages = { "com.javatrade.SpringtradeBot" })
public class SpringtradeBotApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringtradeBotApplication.class, args);
		Main main = (Main) ctx.getBean("main");
		main.start();
	}

}
