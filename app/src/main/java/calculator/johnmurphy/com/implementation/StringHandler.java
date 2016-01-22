
public class StringHandler extends TypeCheck {

	/**
	 * Takes a string and formats it so that it can be processed by the the
	 * infix converter.
	 * 
	 * @param input
	 *            String to be processed
	 * @return The new modified string.
	 */
	public String toStandardInput(String input) {

		String str = input;
		String modifiedStr = "";
		String value = "";

		str = str.replaceAll(" ", "");
		str = removeDuplicateOperaters(str);

		for (int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			value += Character.toString(str.charAt(i));

			// End of the string has been reached. Adds the last digit and adds
			// it to modifiedStr.
			if (i + 1 == str.length()) {
				modifiedStr += value;
				break;
			}

			// End of a number or on an operator. Adds the complete number or
			// operator to modifiedStr along with a space at the end.
			if (isNonNumeric(str.charAt(i + 1)) || isNonNumeric(temp)) {
				modifiedStr += value + " ";
				value = "";
			}
		}

		return modifiedStr;
	}

	/**
	 * Removes any duplicate mathematical operators.
	 * 
	 * @param str
	 *            String to be processed.
	 * @return Modified string with no duplicates
	 */
	public String removeDuplicateOperaters(String str) {
		String modifiedStr = str;

		for (int i = 0; i < modifiedStr.length() - 1; i++) {
			char temp = modifiedStr.charAt(i);

			// Start of check for duplicates.
			if (isOperator(temp)) {

				// There is an operator after the current position.
				while (isOperator(modifiedStr.charAt(i + 1))) {

					// Construct new string, ignoring extra operator.
					modifiedStr = modifiedStr.substring(0, i) + modifiedStr.substring(i + 1);
				}
			}
		}

		return modifiedStr;

	}

}
