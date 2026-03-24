import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Select calculator-type 1 -> (Normal), 2 -> (Scientific), s-> (calculator selection) q -> (Quit)");
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
                    return;
                }
                default -> {
                    invalidSelection();
                }
            }
        }


    }

    public static void startCalculator(Calculator calculator, Scanner userInput) {
        while (true) {

            String num1 = "";
            String num2 = "";

            while (!isNumeric(num1)) {
                System.out.println("Enter 1st number: ");
                num1 = userInput.nextLine();
                if (num1.equals("q")) {
                    programEnd();
                } else if (num1.equals("h")) {
                    calculator.showHistory();
                } else if (num1.equals("s")) {
                    return;
                }
            }

            while (!isNumeric(num2)) {
                System.out.println("Enter 2nd number: ");
                num2 = userInput.nextLine();
                if (num2.equals("q")) {
                    programEnd();
                } else if (num2.equals("h")) {
                    calculator.showHistory();
                }
                else if (num2.equals("s")) {
                    return;
                }
            }

            calculator.setNum(Double.parseDouble(num1), Double.parseDouble(num2));

            if (calculator instanceof ScientificCalculator) {
                System.out.println("Which operation would you like to perform?: 1 -> (+), 2 -> (-), 3 -> (/), 4 -> (*), 5 -> (Power), 6 -> (Square Root), h -> (See History)");
            } else {
                System.out.println("Which operation would you like to perform?: 1 -> (+), 2 -> (-), 3 -> (/), 4 -> (*), h -> (See History)");
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
                    if (calculator instanceof ScientificCalculator scientificCalculator) {
                        scientificCalculator.power();
                        scientificCalculator.showLastResult();
                    } else {
                        invalidOperation();
                    }
                }
                case "6" -> {
                    if (calculator instanceof ScientificCalculator scientificCalculator) {
                        scientificCalculator.squareRoot();
                        scientificCalculator.showLastResult();
                    } else {
                        invalidOperation();
                    }
                }
                case "h" -> {
                    calculator.showHistory();
                }
                case "s" -> {
                    return;
                }
                case "q" -> {
                    programEnd();
                }
                default -> {
                    invalidOperation();
                }
            }
        } //while loop
    }

    public static void invalidOperation() {
        System.out.println("Invalid operation. Please try again.");
    }

    public static void invalidSelection() {
        System.out.println("Invalid selection. Please try again.");
    }

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