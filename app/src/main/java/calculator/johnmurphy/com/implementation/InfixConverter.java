package calculator.johnmurphy.com.implementation;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixConverter extends TypeCheck {

	Stack<StackToken> opStack = new Stack<StackToken>();

	private String input;
	private String postfixString = "";

	/**
	 * Convert input from regular mathematical expression to a postfix one.
	 */
	public void convert(String input) {
		StringHandler sh = new StringHandler();
		this.input = sh.toStandardInput(input);
		StringTokenizer tokenizer = new StringTokenizer(this.input, " ");

		while (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();

			// Single digit, Check if type is digit or operator.
			if (token.length() == 1) {
				char temp = token.charAt(0);

				// Digit
				if (Character.isDigit(temp)) {
					addToOutputQueue(token);
				}
				// Operator
				else if (isOperator(temp)) {
					processOperator(token);
				}
				// Parenthesis
				else if (isParenthesis(temp)) {
					processParenthesis(token);
				}
			}
			// More than one digit. Can only be number
			else {
				addToOutputQueue(token);
			}
		}

		while (opStack.size() != 0) {
			addToOutputQueue(opStack.pop().getType());
		}
	}

	private void addToOutputQueue(String token) {
		postfixString += token + " ";
	}

	/**
	 * Performs comparisons between operators on the stack. Then pushes to or
	 * pops off the stack according to the results of the comparisons.
	 * 
	 * @param token Operator to process
	 */
	private void processOperator(String token) {
		StackToken operator = new StackToken(token);

		// While there is an operator at the top of the stack.
		while (opStack.size() > 0 && isOperator(opStack.peek().getType().charAt(0))) {

			// If new operator is of lower precedence than operator at top.
			if (operator.getLevel() <= opStack.peek().getLevel()) {
				// Pop onto queue
				addToOutputQueue(opStack.pop().getType());
			} else {
				break;
			}
		}

		opStack.push(operator);
	}

	/**
	 * 
	 * @param token Parenthesis to process.
	 */
	private void processParenthesis(String token) {
		char temp = token.charAt(0);

		if (temp == '(')
			opStack.push(new StackToken(token));
		// token is a right parenthesis.
		else {
			while (!opStack.peek().getType().equals("(")) {
				addToOutputQueue(opStack.pop().getType());
			}

			if (opStack.peek().getType().equals("("))
				opStack.pop();
		}
	}

	public String getPostfixString() {
		return postfixString;
	}

}
