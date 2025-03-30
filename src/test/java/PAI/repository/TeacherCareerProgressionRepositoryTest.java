package PAI.repository;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.factory.ITeacherCareerProgressionListFactory;
import org.junit.jupiter.api.Test;

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
        return new Object[]{tcpFactoryDouble, tcpListFactoryDouble, dateDouble, tcIDDouble, tIDDouble, tcpIDDouble, wpDouble, tcpDouble};
    }

    @Test
    void shouldConstructRepositoryIfValidParameters () {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        TeacherCareerProgressionID tcpIDDouble = (TeacherCareerProgressionID) doubles[5];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];

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

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(tcpFactoryDouble.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble)).thenReturn(tcpDouble);

        when(tcpDouble.sameAs(tcpDouble)).thenReturn(false);

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
    public void shouldNotCreateTeacherCareerProgressionIfDuplicate() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ITeacherCareerProgressionFactory tcpFactoryDouble = (ITeacherCareerProgressionFactory) doubles[0];
        ITeacherCareerProgressionListFactory tcpListFactoryDouble = (ITeacherCareerProgressionListFactory) doubles[1];
        Date dateDouble = (Date) doubles[2];
        TeacherCategoryID tcIDDouble = (TeacherCategoryID) doubles[3];
        TeacherID tIDDouble = (TeacherID) doubles[4];
        WorkingPercentage wpDouble = (WorkingPercentage) doubles[6];
        TeacherCareerProgression tcpDouble = (TeacherCareerProgression) doubles[7];

        TeacherCareerProgressionRepository tcpRepository = new TeacherCareerProgressionRepository(tcpFactoryDouble, tcpListFactoryDouble);

        when(tcpFactoryDouble.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble)).thenReturn(tcpDouble);

        tcpRepository.save(tcpDouble);

        when(tcpDouble.sameAs(tcpDouble)).thenReturn(true);

        //Act + Assert
        assertThrows(Exception.class, () -> tcpRepository.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, tIDDouble));
    }

    @Test
    public void shouldSaveTCPIfNotDuplicate() {
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
}