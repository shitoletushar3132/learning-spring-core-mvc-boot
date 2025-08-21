package com.ctrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Todo;

import jakarta.servlet.ServletContext;


@Controller
public class HomeCtrl {
	
	@Autowired
	ServletContext context;

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("page", "home");

//		mod
		List<Todo> todos = (List<Todo>) context.getAttribute("todos");
		model.addAttribute("todos",todos);
		return "home";
	}

	@RequestMapping("/add")
	public String addTodo(Model model) {

		Todo todo = new Todo();

		model.addAttribute("page", "add");
		model.addAttribute("todo", todo);
		return "home";
	}

	@RequestMapping(value = "/saveTodo", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t, Model m) {
		t.setTodoDate(new Date());
		// getting the todo list from context
		List<Todo> todos = (List<Todo>) context.getAttribute("todos");
		todos.add(t);
		m.addAttribute("msg", "Successfully added..");
		return "redirect:/home";
	}
}