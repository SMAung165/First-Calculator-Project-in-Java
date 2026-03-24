import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Select calculator-type 1 -> (Normal), 2 -> (Scientific), s -> (calculator selection) q -> (Quit)");
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
            String num1 = getUserInput("Enter 1st Number", calculator, userInput);
            if (num1.equals("s")) return;

            String num2 = getUserInput("Enter 2nd Number", calculator, userInput);
            if (num2.equals("s")) return;


            calculator.setNum(Double.parseDouble(num1), Double.parseDouble(num2));

            if (calculator instanceof ScientificCalculator) {
                System.out.println("Which operation would you like to perform?: 1 -> (+), 2 -> (-), 3 -> (/), 4 -> (*), 5 -> (Power), 6 -> (Square Root), h -> (See History), s -> (switch calculator), q -> (Quit)");
            } else {
                System.out.println("Which operation would you like to perform?: 1 -> (+), 2 -> (-), 3 -> (/), 4 -> (*), h -> (See History), s -> (switch calculator), q -> (Quit)");
            }

            String operation = userInput.nextLine();
            switch (operation) {
                case "1" -> {
                    calculator.add();
                    calculator.showLastResult();
                }
                case "2" -> {
                    calculator.subtract();
                    calculator.showLastResult();
                }
                case "3" -> {
                    calculator.divide();
                    calculator.showLastResult();
                }
                case "4" -> {
                    calculator.multiply();
                    calculator.showLastResult();
                }
                case "5" -> {
                    if (calculator instanceof ScientificCalculator sc) {
                        sc.power();
                        sc.showLastResult();
                    } else {
                        invalidOperation();
                    }
                }
                case "6" -> {
                    if (calculator instanceof ScientificCalculator sc) {
                        sc.squareRoot();
                        sc.showLastResult();
                    } else {
                        invalidOperation();
                    }
                }
                case "h" -> calculator.showHistory();
                case "s" -> {
                    return;
                }
                case "q" -> programEnd();
                default -> invalidOperation();
            }
        } //while loop
    }

    public static String getUserInput(String prompt, Calculator calculator, Scanner userInput) {
        String input = "";
        while (!isNumeric(input)) {
            System.out.println(prompt);
            input = userInput.nextLine();
            switch (input) {
                case "q" -> programEnd();
                case "h" -> calculator.showHistory();
                case "s" -> {
                    return "s";
                }
            }
        }
        return input;
    }

    //error handling methods
    public static void invalidOperation() {
        System.out.println("Invalid operation. Please try again.");
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