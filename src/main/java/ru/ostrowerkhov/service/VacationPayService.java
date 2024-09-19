package ru.ostrowerkhov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ostrowerkhov.config.DatesDeserializer;
import ru.ostrowerkhov.config.NonWorkingDaysInitializer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VacationPayService {

    private final DatesDeserializer datesDeserializer = new DatesDeserializer();

    public Double calculateVacationPay(Double avgSalary, Integer vacationDays) {

        validate(avgSalary, vacationDays);
        double avgMonthDays = 29.3;
        return (avgSalary / avgMonthDays) * vacationDays;
    }

    public Double calculateVacationPayGivenNonWorkingDays(Double avgSalary,
                                                          String startOfVacation,
                                                          String endOfVacation) {

        return calculateVacationPay(avgSalary,
                getVacationWithoutNonWorkingDays(startOfVacation, endOfVacation));

    }

    private Integer getVacationWithoutNonWorkingDays(String startOfVacation,
                                                     String endOfVacation) {

        LocalDate startDate = datesDeserializer.fromStringToDate(startOfVacation);
        LocalDate endDate = datesDeserializer.fromStringToDate(endOfVacation);

        Set<LocalDate> nonWorkingDays = NonWorkingDaysInitializer.initNonWorkingDays();
        Set<LocalDate> vacationDates = new HashSet<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (!nonWorkingDays.contains(currentDate)) {
                vacationDates.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        return vacationDates.size();
    }

    private void validate(Double avgSalary, Integer vacationDays) {

        if (avgSalary <= 0) {
            throw new IllegalArgumentException("Average salary must be greater than 0");
        }
        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Vacation days must be greater than 0");
        } else if (vacationDays > 28) {
            throw new IllegalArgumentException("Vacation days must be less than 28");
        }
    }

}
