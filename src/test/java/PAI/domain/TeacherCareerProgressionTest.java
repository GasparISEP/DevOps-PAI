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

    private TeacherCareerProgressionID _idDouble;
    private Date _dateDouble;
    private WorkingPercentage _wpDouble;
    private TeacherCategoryID _tcIDDouble;
    private TeacherID _tIDDouble;

    private void createDoublesForTeacherCareerProgression(){
        _idDouble = mock(TeacherCareerProgressionID.class);
        _dateDouble = mock(Date.class);
        _wpDouble = mock(WorkingPercentage.class);
        _tcIDDouble = mock(TeacherCategoryID.class);
        _tIDDouble = mock(TeacherID.class);
    }

    private TeacherCareerProgression createTeacherCareerProgression(){
        createDoublesForTeacherCareerProgression();

        return new TeacherCareerProgression(_idDouble, _dateDouble, _tcIDDouble, _wpDouble, _tIDDouble);
    }

    @Test
    void shouldCreateObjectWithValidAttributes() {
        //arrange
        createDoublesForTeacherCareerProgression();

        //act
        TeacherCareerProgression tcp = new TeacherCareerProgression(_idDouble, _dateDouble, _tcIDDouble, _wpDouble, _tIDDouble);

        //assert
        assertNotNull(tcp);
    }

    public static Stream<Arguments> provideNullAttributes() {
        return Stream.of(
                arguments(null, mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class), "Teacher Career Progression Id cannot be null!"),
                arguments(mock(TeacherCareerProgressionID.class), null, mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class), "Date cannot be null!"),
                arguments(mock(TeacherCareerProgressionID.class), mock(Date.class), null, mock(WorkingPercentage.class), mock(TeacherID.class), "Teacher Category cannot be null!"),
                arguments(mock(TeacherCareerProgressionID.class), mock(Date.class), mock(TeacherCategoryID.class), null, mock(TeacherID.class), "Working Percentage cannot be null!"),
                arguments(mock(TeacherCareerProgressionID.class), mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), null, "Teacher ID cannot be null!")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNullAttributes")
    void shouldThrowExceptionIfAttributesAreNull(TeacherCareerProgressionID id, Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID, String expectedMessage) {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TeacherCareerProgression(id, date, teacherCategoryID, workingPercentage, teacherID));

        //assert
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void getIDReturnsCorrectTeacherCareerProgressionID() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        TeacherCareerProgressionID result = TCP.getID();

        //assert
        assertEquals(_idDouble, result);
    }

    @Test
    void getCategoryReturnsCorrectCategory() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        TeacherCategoryID result = TCP.getTeacherCategoryID();

        //assert
        assertEquals(_tcIDDouble, result);
    }

    @Test
    void getWorkingPercentageReturnsWorkingPercentage() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        WorkingPercentage result = TCP.getWorkingPercentage();

        //assert
        assertEquals(_wpDouble, result);
    }

    @Test
    void getDateReturnsDate() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        Date result = TCP.getDate();

        //assert
        assertEquals(result, _dateDouble);
    }

    @Test
    void getTeacherIDReturnsTeacherID() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        TeacherID result = TCP.getTeacherID();

        //assert
        assertEquals(result, _tIDDouble);
    }

    @Test
    void shouldReturnTeacherCareerProgressionID() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        TeacherCareerProgressionID result = TCP.identity();

        //assert
        assertNotNull(result);
    }

    //isDateAfter
    @Test
    void shouldReturnTrueIfGivenDateIsEqualOrAfterLastDate() {
        //arrange
        Date dateDouble2 = mock(Date.class);
        LocalDate localDateDouble1 = mock(LocalDate.class);
        LocalDate localDateDouble2 = mock(LocalDate.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();

        when(_dateDouble.getLocalDate()).thenReturn(localDateDouble1);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);
        when(localDateDouble1.isAfter(localDateDouble2)).thenReturn(false);

        //act
        boolean result = TCP.isLastDateEqualOrBeforeNewDate(dateDouble2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfGivenDateIsBeforeLastDate() {
        //arrange
        Date dateDouble2 = mock(Date.class);
        LocalDate localDateDouble1 = mock(LocalDate.class);
        LocalDate localDateDouble2 = mock(LocalDate.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();

        when(_dateDouble.getLocalDate()).thenReturn(localDateDouble1);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);
        when(localDateDouble1.isAfter(localDateDouble2)).thenReturn(true);

        //act
        boolean result = TCP.isLastDateEqualOrBeforeNewDate(dateDouble2);

        //assert
        assertFalse(result);
    }

    //isDateAfter
    @Test
    void shouldReturnTrueIfNewTCPDateIsAfterLastTCPDate() {
        //arrange
        Date dateDouble2 = mock(Date.class);
        LocalDate localDateDouble1 = mock(LocalDate.class);
        LocalDate localDateDouble2 = mock(LocalDate.class);
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();

        when(_dateDouble.getLocalDate()).thenReturn(localDateDouble1);
        when(tcpDouble.getDate()).thenReturn(dateDouble2);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);

        when(localDateDouble1.isAfter(localDateDouble2)).thenReturn(true);

        //act
        boolean result = TCP.isDateAfter(tcpDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNewTCPDateIsBeforeLastTCPDate() {
        //arrange
        Date dateDouble2 = mock(Date.class);
        LocalDate localDateDouble1 = mock(LocalDate.class);
        LocalDate localDateDouble2 = mock(LocalDate.class);
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();

        when(_dateDouble.getLocalDate()).thenReturn(localDateDouble1);
        when(tcpDouble.getDate()).thenReturn(dateDouble2);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);

        when(localDateDouble1.isAfter(localDateDouble2)).thenReturn(false);

        //act
        boolean result = TCP.isDateAfter(tcpDouble);

        //assert
        assertFalse(result);
    }

    //sameAs
    @Test
    void shouldReturnFalseIfObjectsAreNotOfTheSameInstance() {
        //arrange
        Object object = mock(Object.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        boolean result = TCP.sameAs(object);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTeacherCareerProgressionObjectsAreTheSame() {
        //arrange
        TeacherCareerProgression TCP = createTeacherCareerProgression();

        //act
        boolean result = TCP.sameAs(TCP);

        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionObjectsHaveDifferentDate() {
        //arrange
        Date date2Double = mock(Date.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();
        TeacherCareerProgression tcp2 = new TeacherCareerProgression(_idDouble, date2Double, _tcIDDouble, _wpDouble, _tIDDouble);

        //act
        boolean result = TCP.sameAs(tcp2);

        //arrange
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionObjectsHaveDifferentTeacherID() {
        //arrange
        TeacherID tID2Double = mock(TeacherID.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();
        TeacherCareerProgression tcp2 = new TeacherCareerProgression(_idDouble, _dateDouble, _tcIDDouble, _wpDouble, tID2Double);

        //act
        boolean result = TCP.sameAs(tcp2);

        //arrange
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionObjectsHaveDifferentWorkingPercentage() {
        //arrange
        WorkingPercentage wp2Double = mock(WorkingPercentage.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();
        TeacherCareerProgression tcp2 = new TeacherCareerProgression(_idDouble, _dateDouble, _tcIDDouble, wp2Double, _tIDDouble);

        //act
        boolean result = TCP.sameAs(tcp2);

        //arrange
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionObjectsHaveDifferentTeacherCategory() {
        //arrange
        TeacherCategoryID tcID2Double = mock(TeacherCategoryID.class);

        TeacherCareerProgression TCP = createTeacherCareerProgression();
        TeacherCareerProgression tcp2 = new TeacherCareerProgression(_idDouble, _dateDouble, tcID2Double, _wpDouble, _tIDDouble);

        //act
        boolean result = TCP.sameAs(tcp2);

        //arrange
        assertFalse(result);
    }
}
