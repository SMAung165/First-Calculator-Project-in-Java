public class History {
    private String historyLog = "";
    private String lastEntryOfHistory ="";

    //constructors
    public History() {
    }

    //methods
    public void storeHistory(String operation, double result) {
        String historyString = String.format("%s %.2f", operation, result);

        this.historyLog += historyString + "\n";
        this.lastEntryOfHistory = historyString;
    }

    public void showHistory(String calculatorType) {
        if(historyLog.isEmpty()){
            System.out.println("History is empty");
            return;
        }
        System.out.printf("History of %s:\n%s", calculatorType, this.historyLog);
    }

    public void showLastResult(String calculatorType) {
        if (lastEntryOfHistory.isEmpty()) {
            System.out.printf("No operations are yet to be performed on %s.\n", calculatorType);
            return;
        }

        System.out.printf("Result = %s\n", lastEntryOfHistory);

    }
}