import java.io.File;

public class Main {
    public static void main(String[] args) {
        final String S = File.separator;
        final String MOVEMENT_LIST = String.format("src%smain%sresources%smovementList.csv", S, S, S);

        Movements movements = new Movements(MOVEMENT_LIST.replace("/", "\\"));
        System.out.println("Сумма расходов: " + movements.getExpenseSum() + " руб.");
        System.out.println("Сумма доходов: " + movements.getIncomeSum() + " руб.");
        System.out.println("Суммы расходов по организациям: \n" + movements.getExpenseOrganizationSum());
    }
}
