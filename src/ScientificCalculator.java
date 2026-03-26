public class ScientificCalculator extends Calculator {

    //constructors
    public ScientificCalculator() {
       setCalculatorType("Scientific Calculator");
    }

    //methods
    public void power() {
       setResultAndOperation(Math.pow(getNum1(), getNum2()), String.format("%.2f^%.2f =", getNum1(), getNum2()));
    }

    public void squareRoot() {
        // num2 holds the target number passed from inputProcessor
        setResultAndOperation(Math.sqrt(getNum2()), String.format("sqrt%.2f =", getNum2()));
    }

}
