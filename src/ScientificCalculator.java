public class ScientificCalculator extends Calculator {

    //constructors
    public ScientificCalculator() {
        super();
       setCalculatorType("Scientific Calculator");
    }

    //methods
    public void power() {
       setResultAndOperation(Math.pow(getNum1(), getNum2()), String.format("%.2f power(%.2f) =", getNum1(), getNum2()));
    }

    public void squareRoot() {
        setResultAndOperation(Math.sqrt(getNum1()), String.format("Square root of %.2f =", getNum1()));
    }

}
