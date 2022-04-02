import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Файл test/resources/movementList.csv")
public class TestMovementList {

    private static final double DELTA = 0.01;
    private static final String CSV_FILENAME = "movementList.csv";

    @Test
    @DisplayName("Сумма прихода")
    void testSumIncome() {
        assertEquals(460_800.0, new Movements(getCsvFilenamePath()).getIncomeSum(), DELTA);
    }

    @Test
    @DisplayName("Сумма расходов")
    void testSumExpense() {
        assertEquals(466_393.07, new Movements(getCsvFilenamePath()).getExpenseSum(), DELTA);
    }

    private String getCsvFilenamePath() {
        return new File("src/test/resources/movementList.csv").getAbsolutePath();
    }

}