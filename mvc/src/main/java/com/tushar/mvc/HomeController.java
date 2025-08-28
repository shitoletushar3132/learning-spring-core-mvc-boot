package com.tushar.mvc;

import com.tushar.mvc.model.Alien;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
//        System.out.println("requestd");
        return "about";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(@RequestParam("num1") int i, @RequestParam("num2") int j, Model model) {

//        int i = Integer.parseInt(req.getParameter("num1"));
//        int j = Integer.parseInt(req.getParameter("num2"));

        model.addAttribute("num3", i + j);


        return "result";
    }

    @RequestMapping(value = "addAlien", method = RequestMethod.GET)
    public String addAlien(@ModelAttribute("alien") Alien a ) {

//        m.addAttribute("alien", a);

        return "result";
    }
}
