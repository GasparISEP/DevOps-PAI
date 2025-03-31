package PAI.domain;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionTest {

    @Test
    void shouldCreateObjectWithValidAttributes() {
        //arrange
        Date date = mock(Date.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherCategoryID tcDouble = mock(TeacherCategoryID.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        //act
        TeacherCareerProgression tcp = new TeacherCareerProgression(date, tcDouble, wpDouble, tIDDouble);

        //assert
        assertNotNull(tcp);
    }

    public static Stream<Arguments> provideNullAttributes() {
        return Stream.of(
                arguments(null, mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class), "Date cannot be null!"),
                arguments(mock(Date.class), null, mock(WorkingPercentage.class), mock(TeacherID.class), "Teacher Category cannot be null!"),
                arguments(mock(Date.class), mock(TeacherCategoryID.class), null, mock(TeacherID.class), "Working Percentage cannot be null!"),
                arguments(mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), null, "Teacher ID cannot be null!")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNullAttributes")
    void shouldThrowExceptionIfAttributesAreNull(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID, String expectedMessage) {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherID));

        //assert
        assertEquals(exception.getMessage(), expectedMessage);
    }


    @Test
    void getCategoryReturnsCorrectCategory() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression TCP = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //act
        TeacherCategoryID result = TCP.getTeacherCategoryID();

        //assert
        assertEquals(tcIDDouble, result);
    }

    @Test
    void getWorkingPercentageReturnsWorkingPercentage() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression TCP = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //act
        WorkingPercentage result = TCP.getWorkingPercentage();

        //assert
        assertEquals(wpDouble, result);
    }

    @Test
    void getDateReturnsDate() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression TCP = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //act
        Date result = TCP.getDate();

        //assert
        assertEquals(result, dateDouble);
    }

    @Test
    void getTeacherIDReturnsTeacherID() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression TCP = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //act
        TeacherID result = TCP.getTeacherID();

        //assert
        assertEquals(result, tIDDouble);
    }

    @Test
    void shouldReturnTeacherCareerProgressionID() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression tcp = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //act
        TeacherCareerProgressionID result = tcp.identity();

        //assert
        assertNotNull(result);
    }

    //isDateAfter
    @Test
    void shouldReturnFalseIfGivenDateIsAfterLastDate() {
        //arrange
        Date dateDouble1 = mock(Date.class);
        Date dateDouble2 = mock(Date.class);
        LocalDate localDateDouble1 = mock(LocalDate.class);
        LocalDate localDateDouble2 = mock(LocalDate.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression TCP = new TeacherCareerProgression(dateDouble1, tcIDDouble, wpDouble, tIDDouble);

        when(dateDouble1.getLocalDate()).thenReturn(localDateDouble1);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);
        when(localDateDouble1.isAfter(localDateDouble2)).thenReturn(true);

        //act
        boolean result = TCP.isDateAfter(dateDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfGivenDateIsAfterLastDate() {
        //arrange
        Date dateDouble1 = mock(Date.class);
        Date dateDouble2 = mock(Date.class);
        LocalDate localDateDouble1 = mock(LocalDate.class);
        LocalDate localDateDouble2 = mock(LocalDate.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression TCP = new TeacherCareerProgression(dateDouble1, tcIDDouble, wpDouble, tIDDouble);

        when(dateDouble1.getLocalDate()).thenReturn(localDateDouble1);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);
        when(localDateDouble1.isAfter(localDateDouble2)).thenReturn(false);

        //act
        boolean result = TCP.isDateAfter(dateDouble2);

        //assert
        assertTrue(result);
    }

    //sameAs
    @Test
    void shouldReturnFalseIfObjectsAreNotOfTheSameInstance() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);
        Object object = mock(Object.class);

        TeacherCareerProgression tcp = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //act
        boolean result = tcp.sameAs(object);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTeacherCareerProgressionObjectsAreTheSame() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression tcp1 = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        when(tIDDouble.sameAs(tIDDouble)).thenReturn(true);

        //act
        boolean result = tcp1.sameAs(tcp1);

        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionObjectsHaveTheSameTeacherIDButDifferentDate() {
        //arrange
        Date date1Double = mock(Date.class);
        Date date2Double = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tIDDouble = mock(TeacherID.class);

        TeacherCareerProgression tcp1 = new TeacherCareerProgression(date1Double, tcIDDouble, wpDouble, tIDDouble);
        TeacherCareerProgression tcp2 = new TeacherCareerProgression(date2Double, tcIDDouble, wpDouble, tIDDouble);

        when(tIDDouble.sameAs(tIDDouble)).thenReturn(true);

        //act
        boolean result = tcp1.sameAs(tcp2);

        //arrange
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionObjectsHaveTheSameDateButDifferentTeacherID() {
        //arrange
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID tID1Double = mock(TeacherID.class);
        TeacherID tID2Double = mock(TeacherID.class);

        TeacherCareerProgression tcp1 = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tID1Double);
        TeacherCareerProgression tcp2 = new TeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tID2Double);

        when(tID1Double.sameAs(tID2Double)).thenReturn(false);


        //act
        boolean result = tcp1.sameAs(tcp2);

        //arrange
        assertFalse(result);
    }
}
