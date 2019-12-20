package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/calculate")
	public String calculate(@RequestParam("expression") String expression, Model model) {
		if(expression == null || expression.isEmpty()) {
			model.addAttribute("message", "Wrong expression");
			return "res";
		}
		String[] operators = {"\\+", "-"};
		String currentOperator = null;
		ArrayList<String> operands = null;
		for (String operator:operators) {
			String[] spliceRes = expression.split(operator);
			if (spliceRes.length == 2) {
				operands = new ArrayList<String>(Arrays.asList(spliceRes));
				currentOperator = operator;
				break;
			}
		}
		if (operands == null) {
			model.addAttribute("message", "Operation not supported.");
			return "res";
		}
		int a = 0;
		int b = 0;
		try {
			a = Integer.parseInt(operands.get(0));
			b = Integer.parseInt(operands.get(1));
		}
		catch ( NumberFormatException e ) {
			model.addAttribute("message", "Please enter an integer numbers.");
			return "res";
		}
		int res = 0;
		if (currentOperator.equals("\\+")) {
			res = a + b;
		} else {
			res = a - b;
		}
		model.addAttribute("message", "Result of evaluation " + res);
		return "res";
	}
}
