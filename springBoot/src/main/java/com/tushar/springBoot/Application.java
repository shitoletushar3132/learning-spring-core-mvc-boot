package com.tushar.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tushar.springBoot.config.db;
import com.tushar.springBoot.service.Greeting;

@SpringBootApplication // @ComponentScan + @EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext contatiner = SpringApplication.run(Application.class, args);

		Greeting g = contatiner.getBean(Greeting.class);
		g.call();

		db d = contatiner.getBean(db.class);
		d.connnect();

		int count = contatiner.getBeanDefinitionCount();
		System.out.println(count);

		String[] beanNames = contatiner.getBeanDefinitionNames();
		for (String name : beanNames) {
			System.out.println(name);
		}

	}

}
