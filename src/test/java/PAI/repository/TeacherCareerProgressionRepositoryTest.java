package PAI.repository;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.factory.ITeacherCareerProgressionListFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionRepositoryTest {

    private Object[] createDoublesForTestsWithIsolation() {
        ITeacherCareerProgressionFactory tcpFactoryDouble = mock(ITeacherCareerProgressionFactory.class);
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = mock(ITeacherCareerProgressionListFactory.class);
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        TeacherID tIDDouble = mock(TeacherID.class);
        TeacherCareerProgressionID tcpIDDouble = mock(TeacherCareerProgressionID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);
        LocalDate localDateDouble = mock(LocalDate.class);
        return new Object[]{tcpFactoryDouble, tcpListFactoryDouble, dateDouble, tcIDDouble, tIDDouble, tcpIDDouble, wpDouble, tcpDouble, localDateDouble};
    }

    @Test
    void shouldConstructRepositoryIfValidParameters () {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];

        //Act
        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Assert
        assertNotNull(tcpRepository);
    }

    @Test
    void shouldThrowExceptionAndNotConstructRepositoryIfTCPFactoryNull () {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];

        //Act + Assert
        assertThrows (IllegalStateException.class, () -> new TeacherCareerProgressionRepository(null, tcpListFactoryDouble));
    }

    @Test
    void shouldThrowExceptionAndNotConstructRepositoryIfTCPListFactoryNull () {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];

        //Act + Assert
        assertThrows (IllegalStateException.class, () -> new TeacherCareerProgressionRepository(tcpFactoryDouble, null));
    }

    @Test
    public void shouldCreateTeacherCareerProgression() throws Exception {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        TeacherCareerProgression tcpDouble2 = (TeacherCareerProgression) doubles[7];

        when(tcpFactoryDouble.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble)).thenReturn(tcpDouble);

        //List
        ArrayList<TeacherCareerProgression> listDouble = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(listDouble);

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Iterator
        Iterator<TeacherCareerProgression> itDouble = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true, false);

        when(itDouble.next()).thenReturn(tcpDouble);

        when(tcpDouble.sameAs(tcpDouble2)).thenReturn(false);

        //Act
        boolean result = tcpRepository.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    public void shouldThrowExceptionAndNotCreateTCPWhenDateIsNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> tcpRepository.createTeacherCareerProgression(null, tcIDDouble, wpDouble, tIDDouble));
    }

    @Test
    public void shouldThrowExceptionAndNotCreateTCPWhenTeacherCategoryIDIsNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> tcpRepository.createTeacherCareerProgression(dateDouble, null, wpDouble, tIDDouble));
    }

    @Test
    public void shouldThrowExceptionAndNotCreateTCPWhenWorkingPercentageIsNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID tIDDouble = (TeacherID) doubles[4];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> tcpRepository.createTeacherCareerProgression(dateDouble, tcIDDouble, null, tIDDouble));
    }

    @Test
    public void shouldThrowExceptionAndNotCreateTCPWhenTeacherIDIsNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> tcpRepository.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, null));
    }

    @Test
    public void shouldNotCreateTCPIfDuplicate() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        TeacherCareerProgression tcpDouble2 = (TeacherCareerProgression) doubles[7];

        //List
        ArrayList<TeacherCareerProgression> listDouble = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(listDouble);

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(tcpFactoryDouble.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble)).thenReturn(tcpDouble2);

        //Iterator
        Iterator<TeacherCareerProgression> itDouble = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true);

        when(itDouble.next()).thenReturn(tcpDouble);

        when(tcpDouble.sameAs(tcpDouble2)).thenReturn(true);

        // Act + Assert
        assertThrows(Exception.class, () -> tcpRepository.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble));
    }

    @Test
    public void shouldSaveTCP() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        // Act
        TeacherCareerProgression result = tcpRepository.save(tcpDouble);

        // Assert
        assertEquals(tcpDouble, result);
    }


    @Test
    void shouldReturnListOfTeacherCareerProgressionsWhenNotEmpty() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(tcpDouble.sameAs(tcpDouble)).thenReturn(false);

        tcpRepository.save(tcpDouble);

        //Act
        Iterable<TeacherCareerProgression> result = tcpRepository.findAll();

        //Act + Assert
        assertIterableEquals(List.of(tcpDouble), result);
    }

    @Test
    void shouldThrowExceptionIfListOfTeacherCareerProgressionsIsEmpty () {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> tcpRepository.findAll());
    }

    @Test
    public void shouldReturnOptionalObjectWhenTCPExists() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCareerProgressionID tcpIDDouble = (TeacherCareerProgressionID) doubles[5];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(tcpDouble.identity()).thenReturn(tcpIDDouble);

        tcpRepository.save(tcpDouble);

        //Act
        Optional<TeacherCareerProgression> result = tcpRepository.ofIdentity(tcpIDDouble);

        //Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void shouldReturnOptionalEmptyWhenTCPDoesNotExist() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCareerProgressionID tcpIDDouble = (TeacherCareerProgressionID) doubles[5];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act
        Optional<TeacherCareerProgression> result = tcpRepository.ofIdentity(tcpIDDouble);

        //Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueIfContainsTCPForThisID() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCareerProgressionID tcpIDDouble = (TeacherCareerProgressionID) doubles[5];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(tcpDouble.sameAs(tcpDouble)).thenReturn(false);

        tcpRepository.save(tcpDouble);

        when(tcpDouble.identity()).thenReturn(tcpIDDouble);

        //Act
        boolean result = tcpRepository.containsOfIdentity(tcpIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNotContainsTCPForThisID() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherCareerProgressionID tcpIDDouble = (TeacherCareerProgressionID) doubles[5];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act
        boolean result = tcpRepository.containsOfIdentity(tcpIDDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnOptionalTCPIfLastTCPIsFound() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        Date dateDouble2 = (Date) doubles[2];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        TeacherCareerProgression tcpDouble2 = (TeacherCareerProgression) doubles[7];
        LocalDate localDateDouble = (LocalDate) doubles [8];
        LocalDate localDateDouble2 = (LocalDate) doubles [8];

        TeacherCareerProgressionRepository repo = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(dateDouble.getLocalDate()).thenReturn(localDateDouble);
        when(dateDouble2.getLocalDate()).thenReturn(localDateDouble2);

        when(tcpDouble.getDate()).thenReturn(dateDouble);
        when(tcpDouble2.getDate()).thenReturn(dateDouble2);

        when(tcpDouble.getTeacherID()).thenReturn(tIDDouble);
        when(tcpDouble2.getTeacherID()).thenReturn(tIDDouble);

        repo.save(tcpDouble);
        repo.save(tcpDouble2);

        //Act
        Optional<TeacherCareerProgression> result = repo.findLastTCPFromTeacherID(tIDDouble);

        //Assert
        assertEquals(tcpDouble2, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfLastTCPNotFound() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherID tIDDouble = (TeacherID) doubles[4];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Act
        Optional<TeacherCareerProgression> result = tcpRepository.findLastTCPFromTeacherID(tIDDouble);

        //Assert
        assertEquals(Optional.empty(), result);
    }

    //updateWorkingPercentage
    @Test
    void shouldReturnTrueWhenSuccessfullyUpdatesWorkingPercentageInTeacherCareerProgression() throws Exception {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date date1Double = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID teacherIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wp1Double = (WorkingPercentage) doubles[6];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        LocalDate localDate1Double = mock(LocalDate.class);
        LocalDate localDate2Double = mock(LocalDate.class);
        WorkingPercentage wp2Double = mock(WorkingPercentage.class);
        Date date2Double = mock(Date.class);

        //List
        ArrayList<TeacherCareerProgression> listDouble = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(listDouble);

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Iterator
        Iterator<TeacherCareerProgression> itDouble = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true, false);

        when(itDouble.next()).thenReturn(tcpDouble);

        when(tcpDouble.getTeacherID()).thenReturn(teacherIDDouble);
        when(tcpDouble.getDate()).thenReturn(date1Double);
        when(date1Double.getLocalDate()).thenReturn(localDate1Double);
        when(tcpDouble.getDate()).thenReturn(date2Double);
        when(date1Double.getLocalDate()).thenReturn(localDate2Double);
        when(localDate1Double.isAfter(localDate2Double)).thenReturn(false);

        when(tcpDouble.isLastDateEqualOrBeforeNewDate(date2Double)).thenReturn(true);
        when(tcpDouble.getWorkingPercentage()).thenReturn(wp1Double);
        when(tcpDouble.getTeacherCategoryID()).thenReturn(tcIDDouble);

        when(tcpFactoryDouble.createTeacherCareerProgression(date2Double, tcIDDouble, wp2Double, teacherIDDouble)).thenReturn(tcpDouble);

        //act
        boolean result = tcpRepository.updateWorkingPercentageInTeacherCareerProgression(date2Double, wp2Double, teacherIDDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenThereIsNoLastTCP() throws Exception {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        TeacherID teacherIDDouble = (TeacherID) doubles[4];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        WorkingPercentage wp2Double = mock(WorkingPercentage.class);
        Date date2Double = mock(Date.class);

        //List
        ArrayList<TeacherCareerProgression> listDouble = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(listDouble);

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Iterator
        Iterator<TeacherCareerProgression> itDouble = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(false);

        //act
        boolean result = tcpRepository.updateWorkingPercentageInTeacherCareerProgression(date2Double, wp2Double, teacherIDDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenGivenDateIsBeforeLastTCPDate() throws Exception {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date date1Double = (Date) doubles[2];
        TeacherID teacherIDDouble = (TeacherID) doubles[4];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        LocalDate localDate1Double = mock(LocalDate.class);
        LocalDate localDate2Double = mock(LocalDate.class);
        WorkingPercentage wp2Double = mock(WorkingPercentage.class);
        Date date2Double = mock(Date.class);

        //List
        ArrayList<TeacherCareerProgression> listDouble = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(listDouble);

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Iterator
        Iterator<TeacherCareerProgression> itDouble = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true, false);

        when(itDouble.next()).thenReturn(tcpDouble);

        when(tcpDouble.getTeacherID()).thenReturn(teacherIDDouble);
        when(tcpDouble.getDate()).thenReturn(date1Double);
        when(date1Double.getLocalDate()).thenReturn(localDate1Double);
        when(tcpDouble.getDate()).thenReturn(date2Double);
        when(date1Double.getLocalDate()).thenReturn(localDate2Double);
        when(localDate1Double.isAfter(localDate2Double)).thenReturn(false);

        when(tcpDouble.isLastDateEqualOrBeforeNewDate(date2Double)).thenReturn(false);

        //act
        boolean result = tcpRepository.updateWorkingPercentageInTeacherCareerProgression(date2Double, wp2Double, teacherIDDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenGivenWorkingPercentageIsTheSameAsLastTCPWorkingPercentage() throws Exception {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date date1Double = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID teacherIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];
        LocalDate localDate1Double = mock(LocalDate.class);
        LocalDate localDate2Double = mock(LocalDate.class);
        Date date2Double = mock(Date.class);

        //List
        ArrayList<TeacherCareerProgression> listDouble = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(listDouble);

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        //Iterator
        Iterator<TeacherCareerProgression> itDouble = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true, false);

        when(itDouble.next()).thenReturn(tcpDouble);

        when(tcpDouble.getTeacherID()).thenReturn(teacherIDDouble);
        when(tcpDouble.getDate()).thenReturn(date1Double);
        when(date1Double.getLocalDate()).thenReturn(localDate1Double);
        when(tcpDouble.getDate()).thenReturn(date2Double);
        when(date1Double.getLocalDate()).thenReturn(localDate2Double);
        when(localDate1Double.isAfter(localDate2Double)).thenReturn(false);

        when(tcpDouble.isLastDateEqualOrBeforeNewDate(date2Double)).thenReturn(true);
        when(tcpDouble.getTeacherCategoryID()).thenReturn(tcIDDouble);
        when(tcpDouble.getWorkingPercentage()).thenReturn(wpDouble);

        when(tcpFactoryDouble.createTeacherCareerProgression(date2Double, tcIDDouble, wpDouble, teacherIDDouble)).thenReturn(tcpDouble);

        //act
        boolean result = tcpRepository.updateWorkingPercentageInTeacherCareerProgression(date2Double, wpDouble, teacherIDDouble);

        //assert
        assertFalse(result);
    }


}