import contracts.ScientificCalculations;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Select Calculator Type (1) -> (Normal), (2) -> (Scientific), (s) -> (calculator selection), (h) - > history, (q) -> (Quit)");
            String calculatorChoice = userInput.nextLine();
            switch (calculatorChoice) {
                case "1" -> {
                    System.out.println("Normal Calculator Selected");
                    startCalculator(new Calculator(), userInput);
                }
                case "2" -> {
                    System.out.println("Scientific Calculator Selected");
                    startCalculator(new ScientificCalculator(), userInput);
                }
                case "q" -> {
                    programEnd();
                    return;// unreachable but suppresses IDE warning
                }
                default -> invalidSelection();
            }
        }
    }

    //core methods
    private static void startCalculator(Calculator calculator, Scanner userInput) {
        while (true) {
            String[] userExpressionInput = inputProcessor(calculator, userInput);
            if (userExpressionInput[0].equals("s")) return;

            calculator.setNum(Double.parseDouble(userExpressionInput[0]), Double.parseDouble(userExpressionInput[2]));
            String operation = userExpressionInput[1];
            boolean isOperationSuccess = true;
            switch (operation) {
                case "+" -> calculator.add();
                case "-" -> calculator.subtract();
                case "/" -> isOperationSuccess = calculator.divide();
                case "*" -> calculator.multiply();
                case "^" -> {
                    if (calculator instanceof ScientificCalculations sc) sc.power();
                }
                case "sqrt" -> {
                    if (calculator instanceof ScientificCalculations sc) sc.squareRoot();
                }
                default -> {
                }//never reach
            }
            if (isOperationSuccess) calculator.showResult();
        } //while loop
    }

    private static String[] inputProcessor(Calculator calculator, Scanner userInput) {
        ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        if (calculator instanceof ScientificCalculations) operators.addAll(Arrays.asList("^", "sqrt"));

        while (true) {
            System.out.print("Enter expression: ");
            String[] parts = inputParser(userInput.nextLine());
            if (parts.length != 3) {
                switch (parts[0]) {
                    case "q" -> programEnd();
                    case "h" -> calculator.showHistory();
                    case "sqrt" -> {
                        if (parts.length < 2 || !isNumeric(parts[1])) {
                            invalidInput();
                        } else if (!(calculator instanceof ScientificCalculations)) {
                           invalidOperator();
                        } else {
                            return new String[]{"0", parts[0], parts[1]};
                        }
                    }
                    case "s" -> {
                        return new String[]{"s"};
                    }
                    default -> invalidInput();
                }
            } else if (isNumeric(parts[0]) && isNumeric(parts[2])) {
                if (operators.contains(parts[1])) {
                    if (parts[1].equals("sqrt")) { //throws error if user tried 1sqrt2
                        invalidInput();
                    } else {
                        return parts;
                    }
                } else {
                    invalidOperator();
                }

            } else {
                invalidInput();
            }
        }
    }

    private static String[] inputParser(String input) {
        input = input.replace("+", " + ")
                .replace("-", " - ")
                .replace("*", " * ")
                .replace("/", " / ")
                .replace("^", " ^ ")
                .replace("sqrt", " sqrt ")
                .replaceAll("\\s+", " ")
                .trim();
        return input.split(" ");
    }
    //error handling methods
    private static void invalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    private static void invalidOperator() {
        System.out.println("Invalid operator. Please try again.");
    }

    private static void invalidSelection() {
        System.out.println("Invalid selection. Please try again.");
    }
    //utility methods
    private static void programEnd() {
        System.out.println("See ya!");
        System.exit(0);
    }
    private static boolean isNumeric(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}