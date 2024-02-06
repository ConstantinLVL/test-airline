package com.david.app;

import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

import java.util.List;

@Command(name = "calculator", description = "A simple calculator")
public class Calculator implements Runnable {

    @Option(name = {"-o", "--operation"}, description = "Operation: add, subtract, multiply")
    private String operation;

    @Arguments(description = "Numbers to operate on")
    private List<String> operands;

    @Override
    public void run() {
        if (operation == null || operands == null || operands.size() < 2) {
            System.out.println("Invalid input. Please provide operation and at least two operands.");
            return;
        }
        double result = calculateResult();
        System.out.println("Result: " + result);
    }

    private double calculateResult() {

        if (operation.toLowerCase().equals("add") && operands.size() > 2){
            return addAllNumbers(operands);
        } else {
            double operand1 = Double.parseDouble(operands.get(0));
            double operand2 = Double.parseDouble(operands.get(1));

            switch (operation.toLowerCase()) {
                case "add":
                    return operand1 + operand2;
                case "subtract":
                    return operand1 - operand2;
                case "multiply":
                    return operand1 * operand2;
                case "split":
                    if (operand2 != 0){
                        return operand1 / operand2;
                    }
                    throw new IllegalArgumentException("Invalid divider: " + operand2);
                default:
                    throw new IllegalArgumentException("Invalid operation: " + operation);
            }
        }
    }

    private double addAllNumbers(List<String> operands){
        Double result = 0.0d;
        for (int i = 0; i < operands.size(); i++) {
            result += Double.parseDouble(operands.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        SingleCommand<Calculator> parser = SingleCommand.singleCommand(Calculator.class);
        Calculator cmd = parser.parse(args);
        cmd.run();
    }
}
