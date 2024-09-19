package ru.ostrowerkhov.config;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

import static ru.ostrowerkhov.config.DatesDeserializer.f;

public class NonWorkingDaysInitializer {

    @SneakyThrows
    @PostConstruct
    public static Set<LocalDate> initNonWorkingDays() throws DateTimeParseException {

        Set<LocalDate> nonWorkingDays = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/nonWorkingDays.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            LocalDate date = LocalDate.parse(line.trim(), f);
            nonWorkingDays.add(date);
        }
        return nonWorkingDays;
    }

}
