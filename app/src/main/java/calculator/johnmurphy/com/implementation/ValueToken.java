
public class ValueToken {
	
	private double value;
	
	public ValueToken(String input) {
		value = Double.parseDouble(input);
	}
	
	public ValueToken(double input) {
		value = input;
	}
	
	public double getValue() {
		return value;
	}
}
