public class Calculator {
    private double num1;
    private double num2;
    private String calculatorType;
    private final History history;

    //constructors
    public Calculator() {
        history = new History();
        setCalculatorType("Normal Calculator");
    }

    //getters
    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    //setters
    protected void setCalculatorType(String type) {
        calculatorType = type;
    }

    public void setNum(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    protected void recordOperation(double result, String operation) {
        history.storeHistory(operation, result);
    }

    //methods
    public void add() {
        recordOperation(num1 + num2, String.format("%.2f + %.2f =", num1, num2));
    }

    public void subtract() {
        recordOperation(num1 - num2, String.format("%.2f - %.2f =", num1, num2));
    }

    public void multiply() {
        recordOperation(num1 * num2, String.format("%.2f * %.2f =", num1, num2));
    }

    public boolean divide() {
        if (num2 == 0) {
            System.out.println("Cannot divide by zero!");
            return false;
        }
        recordOperation(num1 / num2, String.format("%.2f / %.2f =", num1, num2));
        return true;

    }

    public void showHistory() {
        history.showHistory(calculatorType);
    }

    public void showResult() {
        history.showResult();
    }
}



