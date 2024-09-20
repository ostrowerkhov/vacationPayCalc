package ru.ostrowerkhov;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ostrowerkhov.service.VacationPayService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VacationPayServiceTest {

    @InjectMocks
    private VacationPayService vacationPayService;

    @Test
    public void calculateVacationPayTest() {
        assertEquals(71672.35494880546,
                vacationPayService.calculateVacationPay(100000.0, 21));
    }

    @Test
    public void calculateVacationPayGivenNonWorkingDaysTest() {
        double avgSalary = 100000.0;

        assertEquals(27303.754266211603, vacationPayService.calculateVacationPayGivenNonWorkingDays(
                avgSalary,
                "11.05.2025",
                "18.05.2025"));

        assertEquals(20477.815699658702, vacationPayService.calculateVacationPayGivenNonWorkingDays(
                avgSalary,
                "01.01.2025",
                "14.01.2025"
        ));
    }

    @Test
    public void enterIncorrectSalaryTest() {
        assertThrows(IllegalArgumentException.class,
                () -> vacationPayService.calculateVacationPay(0.0, 21));

        assertThrows(IllegalArgumentException.class,
                () -> vacationPayService.calculateVacationPay(-100000.0, 14));
    }

    @Test
    public void enterIncorrectVacationDaysTest() {
        assertThrows(IllegalArgumentException.class,
                () -> vacationPayService.calculateVacationPay(100000.0, 0));

        assertThrows(IllegalArgumentException.class,
                () -> vacationPayService.calculateVacationPay(100000.0, 30));
    }
}
