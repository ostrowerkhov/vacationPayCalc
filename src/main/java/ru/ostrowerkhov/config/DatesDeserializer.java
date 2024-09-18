package ru.ostrowerkhov.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Configuration
@AllArgsConstructor
public class DatesDeserializer {

    public static final DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate fromStringToDate(String date) {
        date = date.replace("\"", "");
        return LocalDate.parse(date, f);
    }
}
