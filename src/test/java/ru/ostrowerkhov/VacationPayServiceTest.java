package ru.ostrowerkhov;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ostrowerkhov.service.VacationPayService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VacationPayServiceTest {

    @InjectMocks
    private VacationPayService vacationPayService;

    @Test
    public void calculateVacationPayTest() {

        Double avgSalary = 100000.0;
        Integer vacationDays = 21;

        Double compensation = vacationPayService.calculateVacationPay(avgSalary, vacationDays);

        assertEquals(71672.35494880546, compensation);
    }

    @Test
    public void calculateVacationPayGivenNonWorkingDaysTest() {

        Double avgSalary = 100000.0;

        String startOfVacationWithoutNonWorkingDays = "11.05.2025";
        String endOfVacationWithoutNonWorkingDays = "18.05.2025";

        Double vacationPayWithoutNonWorkingDays = vacationPayService.calculateVacationPayGivenNonWorkingDays(
                avgSalary,
                startOfVacationWithoutNonWorkingDays,
                endOfVacationWithoutNonWorkingDays);

        String startOfVacationWithNonWorkingDays = "01.01.2025";
        String endOfVacationWithNonWorkingDays = "14.01.2025";

        Double vacationPayWithNonWorkingDays = vacationPayService.calculateVacationPayGivenNonWorkingDays(
                avgSalary,
                startOfVacationWithNonWorkingDays,
                endOfVacationWithNonWorkingDays
        );

        assertEquals(27303.754266211603, vacationPayWithoutNonWorkingDays);
        assertEquals(20477.815699658702, vacationPayWithNonWorkingDays);
    }

}
