package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class SchoolYearFactoryImplTest {

    @Test
    void shouldCreateSchoolYear() {
        //arrange
        String description = "School Year 23/24";
        String startDate = "01-09-2023";
        String endDate = "31-08-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (MockedConstruction<SchoolYear> schoolYearDouble = mockConstruction(
                SchoolYear.class, (mock, context) -> {
                    String actualStartDate = (String) context.arguments().get(1);
                    String actualEndDate = (String) context.arguments().get(2);
                    when(mock.getStartDate()).thenReturn(LocalDate.parse(actualStartDate, formatter));
                    when(mock.getEndDate()).thenReturn(LocalDate.parse(actualEndDate, formatter));
                })) {

            SchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();

            //act
            SchoolYear schoolYear = schoolYearFactory.createSchoolYear(description, startDate, endDate);

            //assert
            List<SchoolYear> schoolYears = schoolYearDouble.constructed();
            assertEquals(1, schoolYears.size());

            assertEquals(LocalDate.parse(startDate, formatter), schoolYear.getStartDate());
            assertEquals(LocalDate.parse(endDate, formatter), schoolYear.getEndDate());
        }
    }

    public static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                arguments(null, "14-10-2024", "30-06-2025"),
                arguments("", "14-10-2024", "30-06-2025"),
                arguments(" ", "14-10-2024", "30-06-2025"),
                arguments("School Year 23/24", null, "30-06-2024"),
                arguments("School Year 23/24", "", "30-06-2024"),
                arguments("School Year 23/24", " ", "30-06-2024"),
                arguments("School Year 23/24", "14-10-2024", null),
                arguments("School Year 23/24", "14-10-2024", ""),
                arguments("School Year 23/24", "14-10-2024", " "),
                arguments("School Year 23/24", "14-10-2024", "13-10-2024"),
                arguments("School Year 24/25", "24 de setembro de 2024", "24 de junho de 2025"),
                arguments("School Year 24/25", "2024-09-24", "2025-06-24"),
                arguments("School Year 24/25", "20-09-24", "20-06-25")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void shouldThrowAnExceptionWhenTheParametersAreInvalid(String description, String startDate, String endDate) {
        //arrange
        SchoolYearFactory factory = new SchoolYearFactoryImpl();

        try (MockedConstruction<SchoolYear> schoolYearDouble = mockConstruction(
                SchoolYear.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("SchoolYear constructor failed"));
                })) {
            try {
                factory.createSchoolYear(description, startDate, endDate);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("SchoolYear constructor failed"));
            }
        }
    }
}