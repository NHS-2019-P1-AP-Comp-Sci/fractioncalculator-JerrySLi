package fracCalc;
import java.util.*;

public class FracCalc {
    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	System.out.print("Welcome to Fraction Calculator! ");
    	System.out.println("Enter your operations below.");
    	
    	/* Takes input from user and checks for "quit"; if input is not
    	 * "quit", then use input as parameter for produceAnswer, print
    	 * the result, and take another input */
    	String equation = "";
    	while (!equation.toLowerCase().equals("quit")) {
        	equation = userInput.nextLine();
        	if (!equation.toLowerCase().equals("quit")) {
        		String result = produceAnswer(equation);
            	System.out.println(result);
        	}
    	}
    	
    	System.out.println("Exiting Fraction Calculator.");
    	userInput.close();
    }

    public static String produceAnswer(String input) {
    	/* Splits up the input into three parts; the first operand
    	 * (all characters to the left of the first space), the operator
    	 * (the character to the right of the first space), and the second
    	 * operand (characters to the right of the second space) */
    	String operand1 = input.substring(0, input.indexOf(' '));
    	char operator = input.charAt(input.indexOf(' ') + 1);
    	
    	/* Checks to ensure the operator is only one character; 
    	 * returns an error if there is not a space after the operator */
    	input = input.substring(input.indexOf(' ') + 1);
    	if (input.charAt(input.indexOf(operator) + 1) != ' ') {
    		return "ERROR: Input is in an invalid format.";
    	}	
    	String operand2 = input.substring(input.indexOf(operator) + 2);
    	
    	/* Sends each operand to three methods to parse out each part of the
    	 * mixed fraction; the whole number, numerator, and denominator */
    	int whole1 = parseWhole(operand1);
    	int numer1 = parseNumer(operand1);
    	int denom1 = parseDenom(operand1);
    	
    	int whole2 = parseWhole(operand2);
    	int numer2 = parseNumer(operand2);
    	int denom2 = parseDenom(operand2);
    	
    	// Returns an error for values with a denominator of 0
    	if (denom1 == 0 || denom2 == 0) {
    		return "ERROR: Cannot divide by zero.";
    	}
    	
    	/* Sends each part of the operand to a method to combine the whole
    	 * number with the numerator into one big fraction for operations */
    	numer1 = improperNumer(whole1, numer1, denom1);
    	numer2 = improperNumer(whole2, numer2, denom2);
    	
    	/* Runs calculations on operands based on what the operator is;
    	 * returns an error if operator is not + - * / */
    	String result;
    	if (operator == '+') {
    		result = (numer1 * denom2) + (numer2 * denom1) 
    				+ "/" + (denom1 * denom2);
    	} else if (operator == '-') {
    		result = (numer1 * denom2) - (numer2 * denom1) 
    				+ "/" + (denom1 * denom2);
    	} else if (operator == '*') {
    		result = (numer1 * numer2) + "/" + (denom1 * denom2);
    	} else if (operator == '/') {
    		result = (numer1 * denom2) + "/" + (denom1 * numer2);
    	} else {
    		return "ERROR: Invalid operator.";
    	}
    	
    	// Takes final fraction and simplifies into a reduced, mixed fraction
    	result = simplify(result);
        return result;
    }
    
    // Parses out the whole number from the mixed fraction
    public static int parseWhole(String operand) {
    	/* Checks for the character separating whole number from numerator '_',
    	 * and cuts off all characters past '_' (including '_' itself), 
    	 * then parses out the number; if '_' does not exist but division
    	 * character '/' exists, then whole = 0; if neither '_' nor '/' exist,
    	 * then the entire operand is the whole number */
    	if (operand.indexOf('_') != -1) {
    		operand = operand.substring(0, operand.indexOf('_'));
    		int whole = Integer.parseInt(operand);
    		return whole;
    	} else if (operand.indexOf('/') != -1) {
    		return 0;
    	} else {
    		int whole = Integer.parseInt(operand);
    		return whole;
    	}
    }
    
    // Parses out numerator from mixed fraction
    public static int parseNumer(String operand) {
    	// Cuts off characters left of '_'
        operand = operand.substring(operand.indexOf('_') + 1);
        /* If '/' exists, it and the characters to the right of it are cut
         * off; then the numerator is parsed out and returned; if there is no
         * '/' character, then there is no fraction, so there is no numerator 
         */
    	if (operand.indexOf('/') != -1) {
    		operand = operand.substring(0, operand.indexOf('/'));
        	int numer = Integer.parseInt(operand);
        	return numer;
    	} else {
    		return 0;
    	}
    }
    
    // Parses out denominator from mixed fraction
    public static int parseDenom(String operand) {
    	/* Cuts off all characters left of '/', including '/', and returns
    	 * the remaining characters parsed into an integer; if '/' does not
    	 * exist, then there is no fraction, so the denominator is 1 */
    	if (operand.indexOf('/') != -1) {
    		operand = operand.substring(operand.indexOf('/') + 1);
        	int denom = Integer.parseInt(operand);
        	return denom;
    	} else {
    		return 1;
    	}
    }
    
    /* Combines whole number and numerator from a mixed fraction into one 
     * fraction */
    public static int improperNumer(int whole, int numer, int denom) {
    	/* Multiplies whole by denominator; adds numerator if the whole number
    	 * is positive and subtracts denominator from value if whole is 
    	 * negative; returns value for the improper/combined fraction */
    	if (whole < 0) {
    		numer = (whole * denom) - numer;
    	} else {
        	numer = (whole * denom) + numer;   		
    	}
    	
    	return numer;
    }
    
    // Simplifies and reduces improper fractions into mixed fractions
    public static String simplify(String improper) {
    	/* Takes improper fraction and parses out numerator and denominator
    	 * (characters to the left and right of '/', respectively) */
    	int numer = Integer.parseInt(
    			improper.substring(0, improper.indexOf("/")));
    	int denom = Integer.parseInt(
    			improper.substring(improper.indexOf("/") + 1));
    	
    	/* Set whole value to numerator divided by denominator and set 
    	 * numerator to the remainder of that operation */
    	int whole = numer / denom;
    	numer = numer % denom; 
    	
    	/* Finds greatest common factor of numerator and denominator by running
    	 * through every integer from 1 to the absolute value of the numerator
    	 * or denominator (whichever is greater) and finding the highest value
    	 * that can be divided from the numerator and the denominator with 
    	 * no remainder; then, divides numerator and denominator by GCM to
    	 * simplify out the fraction */
    	int gcm = 1;
    	if (numer != 0) {
    		for (int i = 1; i <= Math.max(Math.abs(numer), Math.abs(denom)); i++) {
    			if (numer % i == 0 && denom % i == 0) {
    				gcm = i;
    			}
    		}
    	}
    	numer /= gcm;
    	denom /= gcm;
    	
    	/* Checks for and eliminates double negatives (numerator and 
    	 * denominator both negative, whole and numerator both negative,
    	 * moving negative from denominator to numerator/whole, numerator
    	 * and whole both negative, denominator and whole both negative */
    	if (denom < 0 && numer > 0) {
    		denom = Math.abs(denom);
    		numer = -numer;
    	}
    	if (numer < 0 && denom < 0) {
    		numer = Math.abs(numer);
    		denom = Math.abs(denom);
    	}
    	if (numer < 0 && whole < 0) {
    		numer = Math.abs(numer);
    	}
    	if (denom < 0 && whole < 0) {
    		denom = Math.abs(denom);
    	}

    	/* Returns whole number if numerator = 0 (no fraction), 
    	 * returns numerator over denominator if whole = 0 (no mixed fraction),
    	 * and returns full mixed fraction if both whole and numerator exist */
    	if (numer == 0) {
    		return Integer.toString(whole);
    	} else if (whole == 0) {
    		return (numer + "/" + denom);
    	} else {
    		return (whole + "_" + numer + "/" + denom);
    	}
    }
}
