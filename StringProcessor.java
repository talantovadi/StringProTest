package com.example.stringprocessing;
import java.util.Stack;
public class StringProcessor {
    private String processor;
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public boolean isStrongPassword(String password){
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialSymbol = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("@#$%^&+=".indexOf(c) != -1) {
                hasSpecialSymbol = true;
            }
        }
        return hasUppercase && hasLowercase && hasDigit && hasSpecialSymbol;
    }
    public int calculateDigits(String sentence){
        int count = 0;

        for (char c : sentence.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    public int calculateWords(String sentence) {
        sentence = sentence.trim();
        if (sentence.isEmpty()) {
            return 0;
        }
        return sentence.split("\\s+").length;
    }

    public static double calculateExpression(String expression) {
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--;
                double operand = Double.parseDouble(numBuilder.toString());
                operandStack.push(operand);
            } else if (currentChar == '(') {
                operatorStack.push(currentChar);
            } else if (currentChar == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    applyOperator(operandStack, operatorStack.pop());
                }
                operatorStack.pop();
            } else if (isOperator(currentChar)) {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), currentChar)) {
                    applyOperator(operandStack, operatorStack.pop());
                }
                operatorStack.push(currentChar);
            }
        }
        while (!operatorStack.isEmpty()) {
            applyOperator(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }
    private static void applyOperator(Stack<Double> operandStack, char operator) {
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        switch (operator) {
            case '+':
                operandStack.push(operand1 + operand2);
                break;
            case '-':
                operandStack.push(operand1 - operand2);
                break;
            case '*':
                operandStack.push(operand1 * operand2);
                break;
            case '/':
                operandStack.push(operand1 / operand2);
                break;
        }
    }
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    private static boolean hasHigherPrecedence(char op1, char op2) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }
}
