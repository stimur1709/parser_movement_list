import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Файл test/resources/movementListInteger.csv (значения целые)")
public class TestMovementListInteger {

    private static final double DELTA = 0.01;
    private static final String CSV_FILENAME = "movementListInteger.csv";

    @Test
    @DisplayName("Сумма прихода")
    void testSumIncome() {
        assertEquals(1500.0, new Movements(getCsvFilenamePath()).getIncomeSum(), DELTA);
    }

    @Test
    @DisplayName("Сумма расходов")
    void testSumExpense() {
        assertEquals(300.0, new Movements(getCsvFilenamePath()).getExpenseSum(), DELTA);
    }

    private String getCsvFilenamePath() {
        return new File("src/test/resources/movementListInteger.csv").getAbsolutePath();
    }

}