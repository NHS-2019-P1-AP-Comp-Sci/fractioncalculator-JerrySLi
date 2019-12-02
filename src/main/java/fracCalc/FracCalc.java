package fracCalc;
import java.util.*;

public class FracCalc {
    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	System.out.print("Welcome to Fraction Calculator! ");
    	System.out.println("Enter your operations below.");
    	
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
    	String operand1 = input.substring(0,input.indexOf(' '));
    	char operator = input.charAt(input.indexOf(' ') + 1);
    	String operand2 = input.substring(input.indexOf(' ') + 3);
    	
    	int whole1 = parseWhole(operand1);
    	int numer1 = parseNumer(operand1);
    	int denom1 = parseDenom(operand1);
    	
    	int whole2 = parseWhole(operand2);
    	int numer2 = parseNumer(operand2);
    	int denom2 = parseDenom(operand2);
    	
    	String result = evaluate(whole1, numer1, denom1, 
    			operator, whole2, numer2, denom2);
    	
        return result;
    }
    
    public static String evaluate(int whole1, int numer1, int denom1, 
    		char operator, int whole2, int numer2, int denom2) {
    	if (operator == '+') {
    		return add(whole1, numer1, denom1, whole2, numer2, denom2);
    	} else if (operator == '-') {
    		return subtract(whole1, numer1, denom1, whole2, numer2, denom2);
    	} else if (operator == '*') {
    		return multiply(whole1, numer1, denom1, whole2, numer2, denom2);
    	} else if (operator == '/') {
    		return divide(whole1, numer1, denom1, whole2, numer2, denom2);
    	} else {
    		return "error";
    	}
    }
    
    public static String add(int whole1, int numer1, int denom1, 
    		int whole2, int numer2, int denom2) {

    	int fullNumer1 = improperNumer(whole1, numer1, denom1) * denom2;
    	int fullNumer2 = improperNumer(whole2, numer2, denom2) * denom1;
    	
    	String resultImproper = (fullNumer1 + fullNumer2) + "/" + (denom1 * denom2);
    	return resultImproper;
    }
    
    public static String subtract(int whole1, int numer1, int denom1, 
    		int whole2, int numer2, int denom2) {
    	
    	int fullNumer1 = improperNumer(whole1, numer1, denom1) * denom2;
    	int fullNumer2 = improperNumer(whole2, numer2, denom2) * denom1;
    	
    	String resultImproper = (fullNumer1 - fullNumer2) + "/" + (denom1 * denom2);
    	return resultImproper;
    }
    
    public static String multiply(int whole1, int numer1, int denom1, 
    		int whole2, int numer2, int denom2) {
    	
    	int fullNumer1 = improperNumer(whole1, numer1, denom1);
    	int fullNumer2 = improperNumer(whole2, numer2, denom2);
    	
    	String resultImproper = (fullNumer1 * fullNumer2) + "/" + (denom1 * denom2);
    	return resultImproper;
    }
    
    public static String divide(int whole1, int numer1, int denom1, 
    		int whole2, int numer2, int denom2) {
    	
    	int fullNumer1 = improperNumer(whole1, numer1, denom1);
    	int fullNumer2 = improperNumer(whole2, numer2, denom2);
    	String resultImproper = (fullNumer1 * denom2) + "/" + (denom1 * fullNumer2);
    	return resultImproper;
    }
    
    public static int improperNumer(int whole, int numer, int denom) {
    	int fullNumer;
    	if (whole < 0) {
    		fullNumer = (whole * denom) - numer;
    	} else {
        	fullNumer = (whole * denom) + numer;   		
    	}

    	return fullNumer;
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
