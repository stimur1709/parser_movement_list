import java.util.*;

public class Movements {
    private String operationDescription;
    private double input;
    private double expense;
    private final List<Movements> MOVEMENTS_LIST = new ArrayList<>();

    public Movements(String MOVEMENT_LIST) {
        MOVEMENTS_LIST.addAll(Parser.parsingMovements(MOVEMENT_LIST));
    }

    public Movements(String operationDescription1, Double input, Double expense) {
        this.operationDescription = operationDescription1;
        this.input = input;
        this.expense = expense;
    }

    public double getExpenseSum() {
        return MOVEMENTS_LIST.stream().map(Movements::getExpense).reduce(Double::sum).get();
    }

    public double getIncomeSum() {
        return MOVEMENTS_LIST.stream().map(Movements::getInput).reduce(Double::sum).get();
    }

    public String getExpenseOrganizationSum() {
        StringBuilder result = new StringBuilder();
        double sum = 0;
        ArrayList<Movements> sortedMovementList = new ArrayList<>(MOVEMENTS_LIST);
        sortedMovementList.sort(Comparator.comparing(Movements::getOperationDescription));
        for (int i = 0; i < sortedMovementList.size(); i++) {
            for (Movements movements : sortedMovementList) {
                if (movements.getOperationDescription().equals(sortedMovementList.get(i).getOperationDescription())
                        && movements.getExpense() > 0) {
                    sum += movements.getExpense();
                    movements.setExpense();
                }
            }
            if (sum > 0) {
                result.append("\t").append(sortedMovementList.get(i).getOperationDescription()).append(" ").append(sum).append(" руб.\n");
                sum = 0;
            }
        }
        return result.toString();
    }

    private double getInput() {
        return input;
    }

    private double getExpense() {
        return expense;
    }

    private void setExpense() {
        this.expense = 0.0;
    }

    private String getOperationDescription() {
        return operationDescription;
    }
}
