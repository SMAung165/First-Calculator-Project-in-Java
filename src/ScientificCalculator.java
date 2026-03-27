import contracts.ScientificCalculations;
public class ScientificCalculator extends Calculator implements ScientificCalculations {

    //constructors
    public ScientificCalculator() {
       setCalculatorType("Scientific Calculator");
    }

    //methods
    public void power() {
       recordOperation(Math.pow(getNum1(), getNum2()), String.format("%.2f^%.2f =", getNum1(), getNum2()));
    }

    public void squareRoot() {
        // num2 holds the target number passed from inputProcessor
        recordOperation(Math.sqrt(getNum2()), String.format("sqrt%.2f =", getNum2()));
    }
}
