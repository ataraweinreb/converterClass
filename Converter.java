
/**
 * Contains methods to convert between the binary, decimal, and hexadecimal number systems.
 * Decimal numbers are represented using int type
 * Binary and hex numbers are represented using strings
 *  
 * valid binary strings are formatted as: "0bBB...BB" 
 * where BB...BB is a sequence of 1 to 31 binary characters 
 * valid binary characters are 0, 1 
 *  
 * valid hexadecimal strings are formatted as: "0xHH...HH"
 * where HH...HH is a sequence of 1 to 8 hexadecimal characters
 * valid hexadecimal characters are 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
 * 
 * @author atara
 */
public class Converter {
	
	/**
	 * Checks if the binary string is valid.
	 * @param binary the binary string to check
	 * @return false if binary string is invalid, true if it is valid
	 */
	private static boolean checkBinary(String binary) {
		//Check if binary string is empty or is longer than 31 characters
		if (binary.length() < 1 || binary.length() > 31) {
			return false;
		}
		//Check if binary string contains a non 0/1 character
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) != '0' && binary.charAt(i) != '1') {
				return false;
			}
		}
		//Binary string is valid
		return true;
	}
	
	/**
	 * Checks if the hexadecimal string is valid.
	 * @param hex the hex string to check
	 * @return false if hex string is invalid, true if it is valid
	 */
	private static boolean checkHex(String hex) {
		//Check if hex string is empty or is longer than 8 characters
		if (hex.length() < 1 || hex.length() > 8) {
			return false;
		}
		//Check that the hex string only contains digits and A-F letters
		for (int i = 0; i < hex.length(); i++) {
			char character = hex.charAt(i);
			if (!Character.isDigit(character)) {
				if (hex.charAt(i) < 'A' || hex.charAt(i) > 'F') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Converts a 1 character hexadecimal string to a 4 character binary string
	 * @param s the hex string to convert
	 * @return the equivalent 4 character binary string
	 */
	private static String convertToBinary(String s) {
		//Checks all of the cases and converts to binary when the match is found
		switch(s) {
			case "0": 
				return "0000";
			case "1":
				return "0001";
			case "2":
				return "0010";
			case "3":
				return "0011";
			case "4":
				return "0100";
			case "5":
				return "0101";
			case "6":
				return "0110";
			case "7":
				return "0111";
			case "8":
				return "1000";
			case "9":
				return "1001";
			case "A":
				return "1010";
			case "B":
				return "1011";
			case "C":
				return "1100";
			case "D":
				return "1101";
			case "E":
				return "1110";
			case "F":
				return "1111";
		}
		return null;
	}
	
	/**
	 * Converts a 4 character binary string to a 1 character hexadecimal string
	 * @param s the binary string to convert
	 * @return the equivalent 1 character hex string
	 */
	private static String convertToHex(String s) {
		//Checks all of the cases and converts to hex when the match is found
		switch(s) {
			case "0000":
				return "0";
			case "0001":
				return "1";
			case "0010":
				return "2";
			case "0011":
				return "3";
			case "0100":
				return "4";
			case "0101":
				return "5";
			case "0110":
				return "6";
			case "0111":
				return "7";
			case "1000": 
				return "8";
			case "1001":
				return "9";
			case "1010":
				return "A";
			case "1011":
				return "B";
			case "1100":
				return "C";
			case "1101":
				return "D";
			case "1110":
				return "E";
			case "1111":
				return "F";
		}
		return null;
	}
	
	/**
	 * Trims binary string by removing extra leading 0s
	 * @param str the binary string to trim
	 * @param index the current first position in the string 
	 * @return str the trimmed string
	 */
	private static String removePadding(String str, int index) {
		//if the first character is a 1, no more trimming needed
		if (str.charAt(0) != '0' || str.length() <= 1) {
			return str;
		}
		//recursively call removePadding with a the first character of the string removed
		return removePadding(str.substring(index), index + 1);
	}
	
	/**
	 * Converts binary strings to decimal numbers. 
	 * More specifically, given a valid string representing a binary number,
	 * the method returns a non-negative decimal integer with the same value.
	 * 
	 * @param binary the binary string to be converted
	 * @return the decimal number equal in value to the binary string passed as the parameter
	 * @throws IllegalArgumentException if the binary string passed to the function is invalid
	 */
	public static int binaryToDecimal(String binary) throws IllegalArgumentException {
		if (binary == null) {
			throw new NullPointerException("The binary string cannot be null.");
		}
		//Check that the binary string begins with the proper header
		if (!binary.substring(0, 2).equals("0b")) {
			throw new IllegalArgumentException("The binary string is invalid.");
		}
		binary = binary.substring(2);
		
		if (!checkBinary(binary)) {
			throw new IllegalArgumentException("The binary string is invalid.");
		}
		//Calls recursive method with the validated string and 0 as the starting index
		return binaryToDecimal(binary, 0);
	}
	
	/**
	 * Recursive private method that converts a binary string to equivalent decimal integer
	 * @param binary the binary string to convert
	 * @param index the current position in the binary string
	 * @return the decimal integer equal to the binary string value
	 */
	private static int binaryToDecimal(String binary, int index) {
		//base case
		if (index == binary.length() - 1) {
			if (binary.charAt(index) == '0') {
				return 0;
			}
			else {
				return 1;
			}
		}
		//recursive call
		if (binary.charAt(index) == '1') {
			//if the character at the index is '1', then add 2^(position in string)
			//recursively call binaryToDecimal with a larger index
			//repeat process until the base case is satisfied 
			return (int) (1 * Math.pow(2, (binary.length() - 1 - index))) 
					+ binaryToDecimal(binary, index + 1);
		}
		else {
			//else the character at the index is '0', so nothing is added
			//recursively call binaryToDecimal with a larger index
			//repeat process until the base case is satisfied 
			return binaryToDecimal(binary, index + 1);
		}
	}
	
	/**
	 * Converts decimal numbers to binary strings. 
	 * More specifically, given a non-negative decimal integer,
	 * the method returns the string representing the binary number with the same value.
	 * @param decimal the decimal number to be converted
	 * @return the binary string equal in value to the decimal number passed as the parameter,
	 * or null if the decimal is negative
	 */
	public static String decimalToBinary(int decimal) {
		//check if decimal is a negative number
		if (decimal < 0) {
			return null;
		}
		//if decimal is 0, then simply return 0 with 0b header
		//no recursing needed
		if (decimal == 0) {
			return "0b0";
		}
		
		String answer = "";
		
		//recursive call with validated decimal number and blank string as parameters
		return decimalToBinary(decimal, answer);
	}
	
	/**
	 * Recursive private method that converts a decimal integer to equivalent binary string
	 * @param decimal the decimal integer to convert
	 * @param answer an empty string to concatenate the binary numbers, to form the equivalent binary string
	 * @return the binary string equal to the decimal number
	 */
	private static String decimalToBinary(int decimal, String answer) {
		//base case
		if (decimal == 0) {
			return "0b" + answer;
		}
		if (decimal == 1) {
			return "0b" + "1" + answer;
		}
		
		//recursive
		if (decimal % 2 == 0) { 
			//if decimal is even, then concatenate a "0" to the answer string
			//the "0" is added to the FRONT of the answer string -  
			//otherwise the method would return the answer in reverse!
			//recursively call decimalToBinary with decimal divided by 2
			return decimalToBinary(decimal/2, "0" + answer);
		}
		else {
			//else, decimal is odd so concatenate a "1" to the answer string
			//the "1" is added to the FRONT of the answer string -  
			//otherwise the method would return the answer in reverse!
			//recursively call decimalToBinary with decimal divided by 2
			return decimalToBinary(decimal/2, "1" + answer);
		}
	}
	
	/**
	 * Converts binary strings to hexadecimal strings.
	 * More specifically, given a valid string representing a binary number,
	 * the method returns the string representing the hex number with the same value.
	 * 
	 * @param binary the binary string to be converted
	 * @return the hexadecimal string equal in value to the binary string passed as the parameter
	 * or null if the binary string is not valid
	 */
	public static String binaryToHex(String binary) {
		if (binary == null) {
			return null;
		}
		//must be at least 2 to accommodate for "0b" header
		if (binary.length() < 2) {
			return null;
		}
		//verify that binary is a valid binary string
		//if binary is not valid, return null
		if (!binary.substring(0, 2).equals("0b")) {
			return null;
		}
		binary = binary.substring(2);
		
		//check if valid, if not return null
		if (!checkBinary(binary)) {
			return null;
		}

		int index = binary.length();
		
		//pad front of string with 0's if needed
		if (index % 4 == 1) {
			//3 0's needed to make the number of characters a multiple of 4
			binary = "000" + binary; 
			index += 3; 
		}
		else if (index % 4 == 2) {
			//2 0's needed to make the number of characters a multiple of 4
			binary = "00" + binary; 
			index += 2; 
		}
		else if (index % 4 == 3) {
			//1 0 needed to make the number of characters a multiple of 4
			binary = "0" + binary;
			index += 1;
		}
		//calls recursive method with padded binary string, updated index, and empty answer string
		return binaryToHex(binary, index, "");
	}

	/**
	 * Recursive private method that converts a binary string to equivalent hex string
	 * @param binary the binary string to convert
	 * @param index the current position in the binary string
	 * @param answer an empty string to concatenate the hex values, to form the equivalent hex string
	 * @return a hex string that is equal to the binary string
	 */
	private static String binaryToHex(String binary, int index, String answer) {
		//base case
		if (index == 4) {
			return "0x" + convertToHex(binary.substring(index - 4, index)) + answer;
		}
		//update values
		String currentString = binary;
		int currentIndex = index - 4;
		String currentAnswer = convertToHex(binary.substring(index - 4, index)) + answer;
		//make recursive call with the new values
		return binaryToHex(currentString, currentIndex, currentAnswer);
	}

	/**
	 * Converts hexadecimal strings to binary strings. 
	 * More specifically given a valid string representing a hexadecimal number
	 * the method returns the string representing the binary number with the same value.
	 * @param hex the hexadecimal string to be converted
	 * @return the binary string equal in value to the hexadecimal string passed as the parameter 
	 * or null if the hexadecimal string is not valid
	 */
	public static String hexToBinary(String hex) {
		//Check if hex is null, if so return null
		if (hex == null) {
			return null;
		}
		//Must be at least 2 to accommodate for "0x"
		if (hex.length() < 2) {
			return null;
		}
		//verify that the hex string begins with 0x, if not return null
		if (!hex.substring(0, 2).equals("0x")) {
			return null;
		}
		hex = hex.substring(2);
		
		//if hex string is invalid, return null
		if (!checkHex(hex)) {
			return null;
		}
		//hex string is valid. 
		//call private recursive recHexToBinary to compute the equivalent binary string
		return recHexToBinary(hex);
	}
	
	/**
	 * Recursive private method that converts a hex string to equivalent binary string
	 * @param hex the hex string to convert
	 * @return a binary string that is equal to the hex string
	 */
	private static String recHexToBinary(String hex) {
		//base case
		//if the length is 1, simply convert the character to binary,
		//remove the extra 0s and add the 0b heading
		if (hex.length() == 1) {
			return "0b" + removePadding(convertToBinary(hex), 0);
		}
		
		//update values
		int currentLength = hex.length();
		String newString = hex.substring(0, (hex.length() - 1));
		String toConvert = hex.substring(currentLength - 1, currentLength);
		
		//recursive
		//recursively call recHexToBinary with the new, shorter string until the base case is reached,
		//converting each hex character to binary and concatenating it as we go along
		return recHexToBinary(newString) + convertToBinary(toConvert);
	}		

} //class
