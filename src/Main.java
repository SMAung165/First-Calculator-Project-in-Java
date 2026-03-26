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
    public static void startCalculator(Calculator calculator, Scanner userInput) {
        while (true) {
            String[] userExpressionInput = inputProcessor("Enter expression: ", calculator, userInput);
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
                    if (calculator instanceof ScientificCalculator sc) sc.power();
                }
                case "sqrt" -> {
                    if (calculator instanceof ScientificCalculator sc) sc.squareRoot();
                }
                default -> {
                }//never reach
            }
            if (isOperationSuccess) calculator.showResult();
        } //while loop
    }

    public static String[] inputProcessor(String prompt, Calculator calculator, Scanner userInput) {
        ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        if (calculator instanceof ScientificCalculator) operators.addAll(Arrays.asList("^", "sqrt"));

        while (true) {
            System.out.print(prompt);
            String[] parts = inputParser(userInput.nextLine());
            if (parts.length != 3) {
                switch (parts[0]) {
                    case "q" -> programEnd();
                    case "h" -> calculator.showHistory();
                    case "sqrt" -> {
                        if (parts.length < 2 || !isNumeric(parts[1])) {
                            invalidInput();
                        } else if (!(calculator instanceof ScientificCalculator)) {
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

    public static String[] inputParser(String input) {
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
    public static void invalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    public static void invalidOperator() {
        System.out.println("Invalid operator. Please try again.");
    }

    public static void invalidSelection() {
        System.out.println("Invalid selection. Please try again.");
    }

    //utility methods
    public static void programEnd() {
        System.out.println("See ya!");
        System.exit(0);
    }

    public static boolean isNumeric(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}