package com.example.demo;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CookieSessionController {
	
	@GetMapping("/setSession")
	@ResponseBody
	public String setSession(HttpSession session) {
		session.setAttribute("username", "endlesskwazar");
		return "Session attribute was set";
	}
	
	@GetMapping("/getSession")
	@ResponseBody
	public String getSession(HttpSession session) {
		String username = (String)session.getAttribute("username");
		if (username != null) {
			return "username " + username;
		}
		return "Session attribute wasn`t set";
	}
	
	@GetMapping("/getSessionAnnotation")
	@ResponseBody
	public String getSessionAnnotation(@SessionAttribute String username) {
		if (username != null) {
			return "username " + username;
		}
		return "Session attribute wasn`t set";
	}

	
	@GetMapping("/setCookie")
	@ResponseBody
	public String setCokkie(HttpServletResponse response) {
		Cookie cookie = new Cookie("username", "exndlesskwazar");
		response.addCookie(cookie);
		return "Cookie was set";
	}
	
	@GetMapping("/getCookie")
	@ResponseBody
	public String getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        return Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
	    return "No cookies";
	}
	
	@GetMapping("/getOneCookie")
	@ResponseBody
	public String getOneCookie(@CookieValue(value = "username", required = true) String username) {
	    return "username " + username;
	}
	
}
