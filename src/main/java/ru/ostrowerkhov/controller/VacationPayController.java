package ru.ostrowerkhov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ostrowerkhov.service.VacationPayService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @GetMapping
    public Double getVacationPay(@RequestParam("avgSalary") double avgSalary,
                                 @RequestParam("vacationDays") Integer vacationDays) {
        return vacationPayService.calculateVacationPay(avgSalary, vacationDays);
    }

    @GetMapping("/holidays")
    public Double getVacationPayGivenNonWorkingDays(
            @RequestParam("avgSalary") double avgSalary,
            @RequestParam("startOfVacation") String startOfVacation,
            @RequestParam("endOfVacation") String endOfVacation
    ) {
        return vacationPayService.calculateVacationPayGivenNonWorkingDays(
                avgSalary,
                startOfVacation,
                endOfVacation);
    }
}
