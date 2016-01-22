package calculator.johnmurphy.com.implementation;

public class StackToken extends TypeCheck {

	private String type;
	private int level;

	public StackToken(String type) {
		this.type = type;
		char temp = type.charAt(0);

		if (isOperator(temp)) {
			assignLevel(temp);
		}

	}

	private void assignLevel(char type) {
		switch (type) {
		case '+':
		case '-':
			level = 1;
			break;
		case '*':
		case '/':
			level = 2;
			break;
		}
	}

	public String getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}
}
