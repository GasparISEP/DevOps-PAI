package PAI.factory;

import PAI.VOs.Description;
import PAI.domain.SchoolYear;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SchoolYearFactoryImplTest {

    @Test
    void whenConstructorInvokedThenMockedObjectShouldBeCreated() {
        //arrange
        Description description = mock(Description.class);
        when(description.getDescription()).thenReturn("School Year 2023/2024");
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

            ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();

            //act
            SchoolYear schoolYear = schoolYearFactory.createSchoolYear( description, startDate, endDate);

            //assert
            List<SchoolYear> schoolYears = schoolYearDouble.constructed();
            assertEquals(1, schoolYears.size());

            assertEquals(LocalDate.parse(startDate, formatter), schoolYear.getStartDate());
            assertEquals(LocalDate.parse(endDate, formatter), schoolYear.getEndDate());
        }
    }

    @Test
    void mockingConstructorThrowingException() {
        //arrange
        ISchoolYearFactory factory = new SchoolYearFactoryImpl();
        Description description = mock(Description.class);
        when(description.getDescription()).thenReturn("School Year 2023/2024");

        String startDate = "01-09-2023";
        String endDate = "31-08-2024";

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