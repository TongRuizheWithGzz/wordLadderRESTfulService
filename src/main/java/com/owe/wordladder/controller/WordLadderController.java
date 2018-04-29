package com.owe.wordladder.controller;

import com.owe.wordladder.utils.WordLadder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.owe.wordladder.utils.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WordLadderController {

    private static Logger LOGGER = LogManager.getLogger(WordLadderController.class);

    @GetMapping({"/", "/index", "/home"})
    @CrossOrigin
    public String root() {
        return "index";
    }


    @GetMapping("/login")
    @CrossOrigin
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    @CrossOrigin
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; ++i) {
                System.out.println("here");
                cookies[i].setMaxAge(0);
                cookies[i].setValue("");
                cookies[i].setPath("/");
                cookies[i].setDomain("127.0.0.1");
                response.addCookie(cookies[i]);
            }
        }
        return "login";
    }

    @GetMapping(value = "/login", params = "token")
    @CrossOrigin
    public String loginsuccess(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setDomain("127.0.0.1");
        cookie.setMaxAge(120);
        response.addCookie(cookie);
        return "user/user";
    }

    @GetMapping(value = "/login", params = "msg")
    @CrossOrigin
    public ModelAndView loginfail(@RequestParam("msg") String msg) {
        List<String> messages = new ArrayList<>();
        messages.add(msg);
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("messages", messages);
        return mav;
    }

    @RequestMapping(path = "/wordladder", method = RequestMethod.GET)
    @CrossOrigin
    public ModelAndView wlGetController(HttpServletRequest request, ModelMap model) {
        String msg = request.getParameter("msg");
        if (msg != null) {
            return new ModelAndView("wordladder");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                System.out.println("Success");
                System.out.println(cookies[i].getValue());

            }
            model.put("token", cookies[0].getValue());
        } else {
            model.put("token", "no");
        }
        return new ModelAndView(new RedirectView("http://127.0.0.1:8082/check"), model);
    }

    @RequestMapping(path = "/wordladder", method = RequestMethod.POST)
    public ModelAndView wlGetController(String word1, String word2,HttpServletRequest request) {
        String begin=request.getParameter("word1");
        String end=request.getParameter("word2");
        LOGGER.info("User use my application.");
        WordLadder wl = new WordLadder(begin, end);
        LOGGER.info("The start is " + begin + " , the end is " + end);
        List<String> messages = new ArrayList<>();
        messages.add(wl.getPath());
        ModelAndView mav = new ModelAndView("wordladder");
        mav.addObject("messages", messages);
        return mav;

    }
}



