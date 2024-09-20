package ru.ostrowerkhov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ostrowerkhov.service.VacationPayService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacationPayCalc")
@Tag(name = "VacationPayCalculator", description = "Контроллер для вычисления отпускных")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @Operation(
            summary = "Рассчитать отпускные",
            description = "Рассчитывает сумму отпускных на основе средней зарплаты и количества дней отпуска.")
    @GetMapping("/calculate")
    public Double getVacationPay(@Parameter(description = "Средняя зарплата за последние 12 месяцев")
                                 @RequestParam("avgSalary") double avgSalary,
                                 @Parameter(description = "Количество дней отпуска")
                                 @RequestParam("vacationDays") Integer vacationDays) {

        return vacationPayService.calculateVacationPay(avgSalary, vacationDays);
    }

    @Operation(
            summary = "Рассчитать отпускные с учётом выходных и праздников",
            description = "Рассчитывает сумму отпускных учитывая выходные и праздничные дни.")
    @GetMapping("/nonWorkingDays")
    public Double getVacationPayGivenNonWorkingDays(
            @Parameter(description = "Средняя зарплата за последние 12 месяцев")
            @RequestParam("avgSalary") double avgSalary,
            @Parameter(description = "Дата начала отпуска в формате DD.MM.YYYY")
            @RequestParam("startOfVacation") String startOfVacation,
            @Parameter(description = "Дата окончания отпуска в формате DD.MM.YYYY")
            @RequestParam("endOfVacation") String endOfVacation) {

        return vacationPayService.calculateVacationPayGivenNonWorkingDays(
                avgSalary,
                startOfVacation,
                endOfVacation);
    }
}
