package PAI.domain;

import PAI.factory.GradeStudentFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class GradeStudentListFactoryTest {

    @Test
    void newArrayList() {
        // Arrange
        GradeStudentListFactory gradeStudentListFactory = new GradeStudentListFactory();

        // act + assert
        assertNotNull(gradeStudentListFactory);    }
}