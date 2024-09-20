package ru.ostrowerkhov.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static ru.ostrowerkhov.config.DatesDeserializer.f;

@Configuration
public class NonWorkingDaysInitializer {

    @SneakyThrows
    public Set<LocalDate> initNonWorkingDays(String path) {
        Set<LocalDate> nonWorkingDays = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("[-/]", ".");
            LocalDate date = LocalDate.parse(line.trim(), f);
            nonWorkingDays.add(date);
        }
        return nonWorkingDays;
    }
}
