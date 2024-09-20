package ru.ostrowerkhov;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ostrowerkhov.config.NonWorkingDaysInitializer;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class NonWorkingDaysInitializerTest {

    @InjectMocks
    private NonWorkingDaysInitializer nonWorkingDaysInitializer;

    @Test
    public void canNotParseDateFromNonWorkingDaysFileTest() {
        assertThrows(DateTimeParseException.class,
                () -> nonWorkingDaysInitializer.initNonWorkingDays("src/test/resources/nonWorkingDays.txt"));
    }
}
