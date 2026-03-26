import java.util.ArrayList;

public class History {
    private ArrayList<String> historyLog = new ArrayList<>();
    private String lastEntryOfHistory = "";

    //constructors
    public History() {}

    //methods
    public void storeHistory(String operation, double result) {
        String historyString = String.format("%s %.2f", operation, result);
        historyLog.add(historyString);
        lastEntryOfHistory = historyLog.get(historyLog.size() - 1);
    }

    public void showHistory(String calculatorType) {
        if (historyLog.isEmpty()) {
            System.out.println("History is empty");
        } else {

            String outputText = String.format("History of %s:\n", calculatorType);
            System.out.print(outputText);

            barsSeparators(outputText.length());//line separators

            for (String entry : historyLog) {
                System.out.println(entry);
            }

            barsSeparators(outputText.length());//line separators
        }
    }

    public void showResult() {
        if (lastEntryOfHistory.isEmpty()) {
            System.out.println("No operations performed yet!");
            return;
        }
        System.out.printf("%s\n", lastEntryOfHistory);
    }

    //utility methods
    private void barsSeparators(int lengthOfText) {
        for (int i = 0; i < lengthOfText; i++) {
            System.out.print("-");
        }
        System.out.println();
    }


}