import org.baraniecka.EmployeeExpense;
import org.baraniecka.ExpenseService;
import org.baraniecka.ReceiptType;
import org.baraniecka.Reimbursement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReimbursementTest {
    Reimbursement reimbursement = new Reimbursement();
    List<EmployeeExpense> employeeExpenses = List.of(
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(1), 3),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(2), 5),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(1), 7),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(3), 1),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(4), 12),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(3), 5),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(2), 8),
            new EmployeeExpense(ExpenseService.getInstance().getExpenseTypes().get(0), 1)
    );


    @Test
    void filter_By_Hotel_Get_2() {
        //given
        reimbursement.setExpenses(employeeExpenses);
        int result = reimbursement.filterByType("hotel").size();
        //when
        int expected = 2;
        //then
        assertEquals(result,expected);
    }

    @Test
    void test_Sum_By_Type() {
        //given
        reimbursement.setExpenses(employeeExpenses);
        double result = reimbursement.sumByType("taxi");
        //when
        double expected = 1;
        //then
        assertEquals(result,expected);
    }
}
