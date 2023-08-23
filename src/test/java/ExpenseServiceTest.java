import org.baraniecka.BusinessTrip;
import org.baraniecka.ExpenseService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseServiceTest {
    ExpenseService expenseService = ExpenseService.getInstance();
    @Test
    void assert_Expense_Type_Already_Exists_If_Added_Second_Time() {
        //given
        String result = expenseService.addExpenseType("hotel");

        //when
        String expected = "already exists";

        //then
        assertEquals(result, expected);
    }

    @Test
    void assert_Trip_Expense_Equals_0(){
        //given
        double result = expenseService.calculateTripExpenses();
        //when
        double expected = 0.0;
        //then
        assertEquals(result, expected);
    }

    @Test
    void assert_Trip_Expense_Equals_45(){
        //given
        BusinessTrip.getInstance().setDuration(3);
        double result = expenseService.calculateTripExpenses();
        //when
        double expected = 45;
        //then
        assertEquals(result, expected);
    }
}
