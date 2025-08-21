package com.listener;

import java.util.ArrayList;
import java.util.List;

import com.model.Todo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context Inti");
		List<Todo> list = new ArrayList<Todo>();
		ServletContext context = sce.getServletContext();
		context.setAttribute("todos", list);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Context Destroy");
	}
	
	
	
}
