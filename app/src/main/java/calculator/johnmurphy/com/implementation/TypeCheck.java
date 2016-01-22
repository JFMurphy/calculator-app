
public class TypeCheck {
	public boolean isOperator(char token) {
		switch (token) {
		case '+':
		case '-':
		case '*':
		case '/':
			return true;

		default:
			return false;
		}
	}

	public boolean isParenthesis(char token) {
		switch (token) {
		case '(':
		case ')':
			return true;
		default:
			return false;
		}
	}
	
	public boolean isNonNumeric(char c) {
		switch (c) {
		case '+':
		case '-':
		case '*':
		case '/':
		case '(':
		case ')':
			return true;

		default:
			return false;
		}
	}
}
