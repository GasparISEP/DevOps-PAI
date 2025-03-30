package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SchoolYearFactoryImplTest {

    @Test
    void whenConstructorInvokedThenMockedObjectShouldBeCreated() {
        //arrange
        SchoolYearID schoolYearIDDouble = mock(SchoolYearID.class);
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        when(description.getDescription()).thenReturn("School Year 2023/2024");

        try (MockedConstruction<SchoolYear> schoolYearDouble = mockConstruction(
                SchoolYear.class, (mock, context) -> {
                    Date actualStartDate = (Date) context.arguments().get(2);
                    Date actualEndDate = (Date) context.arguments().get(3);
                    when(mock.getStartDate()).thenReturn(actualStartDate);
                    when(mock.getEndDate()).thenReturn(actualEndDate);
                })) {

            ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();

            //act
            SchoolYear schoolYear = schoolYearFactory.createSchoolYear(schoolYearIDDouble, description, startDate, endDate);

            //assert
            List<SchoolYear> schoolYears = schoolYearDouble.constructed();
            assertEquals(1, schoolYears.size());

            assertEquals(startDate.getLocalDate(), schoolYear.getStartDate().getLocalDate());
            assertEquals(endDate.getLocalDate(), schoolYear.getEndDate().getLocalDate());
        }
    }

    @Test
    void mockingConstructorThrowingException() {
        //arrange
        ISchoolYearFactory factory = new SchoolYearFactoryImpl();
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        Description description = mock(Description.class);
        Date startDate = new Date("01-09-2023");
        Date endDate = new Date("31-08-2024");
        when(description.getDescription()).thenReturn("School Year 2023/2024");

        try (MockedConstruction<SchoolYear> schoolYearDouble = mockConstruction(
                SchoolYear.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("SchoolYear constructor failed"));
                })) {
            try {
                factory.createSchoolYear(schoolYearID, description, startDate, endDate);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("SchoolYear constructor failed"));
            }
        }
    }
}