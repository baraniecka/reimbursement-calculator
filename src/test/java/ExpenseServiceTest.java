import org.baraniecka.ExpenseService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseServiceTest {

    @Test
    void assertTrue() {
        ExpenseService expenseService = ExpenseService.getInstance();
        String result = expenseService.addExpenseType("hotel");
        String expected = "already exists";
        assertEquals(result, expected);
    }
}
