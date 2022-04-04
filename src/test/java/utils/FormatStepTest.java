package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormatStepTest {

    // Тесты метода преобразования формата записи ячеек
    @Test
    public void formatStep() {

        assertEquals(1, FormatStep.FormatStep("1,1"));
        assertEquals(4,FormatStep.FormatStep("2 1"));
        assertEquals(9,FormatStep.FormatStep("3 3"));
        assertEquals(5,FormatStep.FormatStep("2:2"));
        assertEquals(2,FormatStep.FormatStep("1.2"));
    }
}