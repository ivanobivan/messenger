package ru.messenger.server.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    @RequestMapping(value = "/application",method = RequestMethod.GET)
    public ModelAndView method() {
        return null;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public void methodIndex(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        dispatcher.forward(request,response);
    }

}