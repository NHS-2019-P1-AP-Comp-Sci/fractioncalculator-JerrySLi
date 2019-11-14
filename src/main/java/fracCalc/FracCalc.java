package fracCalc;
import java.util.*;

public class FracCalc {
    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	System.out.print("Welcome to Fraction Calculator! ");
    	System.out.println("Enter your operations below.");
    	boolean check = true;
    	while (check) {
        	String value = userInput.nextLine();
        	check = checkForQuit(value);
        	
        	if (check) {
        		String output = produceAnswer(value);
            	System.out.println(output);
        	}
    	}
    	System.out.println("Exiting Fraction Calculator.");
    	userInput.close();
    }

    public static String produceAnswer(String input) {
    	String operand1 = input.substring(0,input.indexOf(' '));
    	input = input.substring(input.indexOf(' ') + 1);
    	String operator = input.substring(0, 1);
    	input = input.substring(2);
    	String operand2 = input;
    	
    	int whole1 = parseWhole(operand1);
    	int numer1 = parseNumer(operand1);
    	int denom1 = parseDenom(operand1);
    	
    	int whole2 = parseWhole(operand2);
    	int numer2 = parseNumer(operand2);
    	int denom2 = parseDenom(operand2);
    	String components2 = ("whole:" + whole2 + " numerator:" + numer2 +
    			" denominator:" + denom2);
        return components2;
    }
    
    public static boolean checkForQuit(String input) {
    	String check = input.toLowerCase();
    	if (check.equals("quit")) {
    			return false;
    	} else {
    		return true;
    	}
    }
    
    public static int parseWhole(String operand) {
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
    
    public static int parseNumer(String operand) {
        operand = operand.substring(operand.indexOf('_') + 1);
    	if (operand.indexOf('/') != -1) {
    		operand = operand.substring(0, operand.indexOf('/'));
        	int numer = Integer.parseInt(operand);
        	return numer;
    	} else {
    		return 0;
    	}
    }
    
    public static int parseDenom(String operand) {
    	if (operand.indexOf('/') != -1) {
    		operand = operand.substring(operand.indexOf('/') + 1);
        	int denom = Integer.parseInt(operand);
        	return denom;
    	} else {
    		return 1;
    	}
    }
}
