package calculator.johnmurphy.com.implementation;

import java.util.StringTokenizer;
import java.util.Stack;

public class Calculator extends TypeCheck{

	Stack<ValueToken> stack = new Stack<ValueToken>();
	
	private String postfixString;
	
	public double calculate(String input) {
		
		// Reset the stack
		if (stack.size() != 0) {
			stack = new Stack<ValueToken>();
		}
		
		// Convert input from regular expression to postfix expression.
		InfixConverter ic = new InfixConverter();
		ic.convert(input);
		postfixString = ic.getPostfixString();
		
		// Tokenize input string and begin to process the tokens
		StringTokenizer tokenizer = new StringTokenizer(postfixString, " ");
		
		while (tokenizer.hasMoreTokens()) {
			processToken(tokenizer.nextToken());
		}
		
		// return
		return stack.pop().getValue();
				
	}
	
	private void processToken(String token) {
		
		// Single char, check type.
		if (token.length() == 1) {
			char temp = token.charAt(0);
			
			// Digit
			if (Character.isDigit(temp)) {
				stack.push(new ValueToken(token));
			}
			
			// Operator
			else if (isOperator(temp)) {
				processOperator(token);
			}
			// Illegal character
			else {
				throw new IllegalArgumentException("Invalid token character.");
			}
		}
		// Has to be digit since length > 1
		else {
			stack.push(new ValueToken(token));
		}
		
	}
	
	public void processOperator(String token) {
		
		char operator = token.charAt(0);
		double first = stack.pop().getValue();
		double second = stack.pop().getValue();
		ValueToken result = null;
		
		// Check operator to see which action to prefrom.
		switch (operator) {
		case '+':
			result = new ValueToken(second + first);
			stack.push(result);
			break;
		case '-':
			result = new ValueToken(second - first);
			stack.push(result);
			break;
		case '*':
			result = new ValueToken(second * first);
			stack.push(result);
			break;
		case '/':
			if (first == 0)
				throw new IllegalArgumentException("Cannot divide by zero.");
			
			result = new ValueToken(second / first);
			stack.push(result);
		}
	}
}