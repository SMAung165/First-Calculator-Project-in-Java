import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Select Calculator Type (1) -> (Normal), (2) -> (Scientific), (s) -> (calculator selection), (h) - > history, (q) -> (Quit)");
            String calculatorType = userInput.nextLine();
            switch (calculatorType) {
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
            switch (operation) {
                case "+" -> {
                    calculator.add();
                    calculator.showLastResult();
                }
                case "-" -> {
                    calculator.subtract();
                    calculator.showLastResult();
                }
                case "/" -> {
                    calculator.divide();
                    calculator.showLastResult();
                }
                case "*" -> {
                    calculator.multiply();
                    calculator.showLastResult();
                }
                case "^" -> {
                    if (calculator instanceof ScientificCalculator sc) {
                        sc.power();
                        sc.showLastResult();
                    }
                }
                case "sqrt" -> {
                    if (calculator instanceof ScientificCalculator sc) {
                        sc.squareRoot();
                        sc.showLastResult();
                    }
                }
                default -> invalidOperator();
            }
        } //while loop
    }

    public static String[] inputProcessor(String prompt, Calculator calculator, Scanner userInput) {
        while (true) {
            System.out.print(prompt);
            String[] parts = inputParser(userInput.nextLine());
            if (parts.length != 3) {
                switch (parts[0]) {
                    case "q" -> programEnd();
                    case "h" -> calculator.showHistory();
                    case "s" -> {
                        return new String[]{"s"};
                    }
                    default -> invalidInput();

                }
            } else if (isNumeric(parts[0]) && isNumeric(parts[2])) {
                String[] operators;
                if (calculator instanceof ScientificCalculator) {
                    operators = new String[]{"+", "-", "*", "/", "^", "sqrt"};
                } else {
                    operators = new String[]{"+", "-", "*", "/"};
                }
                if (Arrays.asList(operators).contains(parts[1])) {
                    return parts;
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
//                .replace("sqrt", " sqrt 0") to be continued
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