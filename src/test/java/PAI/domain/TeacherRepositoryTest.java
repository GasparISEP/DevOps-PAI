package PAI.domain;

import PAI.factory.AddressFactory;
import PAI.factory.TeacherCareerProgressionFactory;
import PAI.factory.TeacherFactory;
import PAI.factory.TeacherListFactory;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherRepositoryTest {

    //testing create a new valid teacher
    @Test
    void shouldCreateValidTeacher() throws Exception {
        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactory, teacherListFactory);

        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address addressDouble = mock(Address.class);
        Department dp1 = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678", "Porto", "Portugal")).thenReturn(addressDouble);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression TCPdouble = mock(TeacherCareerProgression.class);

        when(TCPfactoryDouble.createTeacherCareerProgression("20-12-1010", tc1, 100)).thenReturn(TCPdouble);

        // Act
        boolean result = repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal", addressFactoryDouble, "20-12-1010", tc1, 100, dp1, TCPfactoryDouble);

        // Assert
        assertTrue(result, "The teacher should be registered successfully.");

    }

    //Test to register two valid teachers
    @Test
    void testRegisterValidTeacher() throws IllegalArgumentException {
        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactory, teacherListFactory);

        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address adressDouble1 = mock(Address.class);
        Address adressDouble2 = mock(Address.class);
        Department dp1 = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(adressDouble1);
        when(addressFactoryDouble.createAddress( "Street Two", "1234-678",
                "Porto", "Portugal")).thenReturn(adressDouble2);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression TCPdouble1 = mock(TeacherCareerProgression.class);
        when(TCPfactoryDouble.createTeacherCareerProgression("20-12-2010", tc1, 100)).thenReturn(TCPdouble1);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        when(teacherFactory.createTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street One", "1234-678",
                "Porto", "Portugal", addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble)).thenReturn(teacher1);
        when(teacherFactory.createTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street Two", "1234-678",
                "Porto", "Portugal", addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble)).thenReturn(teacher2);

        // Act
        boolean result1 = repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble);
        boolean result2 = repository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street Two", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble);

        // Assert
        assertTrue(result1, "The first teacher should be registered successfully.");
        assertTrue(result2, "The second teacher should be registered successfully.");
    }

    //Testing when teacher has an existing acronym
    @Test
    public void testRegisterTeacherWithDuplicateAcronym() throws Exception {
        //arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address addressDouble1 = mock(Address.class);
        Address addressDouble2 = mock(Address.class);
        Department dp1 = mock(Department.class);

        when(addressFactoryDouble.createAddress( "Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(addressDouble1);
        when(addressFactoryDouble.createAddress("Street Two", "2345-678",
                "Porto", "Portugal")).thenReturn(addressDouble2);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression TCPdouble = mock(TeacherCareerProgression.class);

        when(TCPfactoryDouble.createTeacherCareerProgression("20-12-2010", tc1, 100)).thenReturn(TCPdouble);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble)).thenReturn(teacher1);

        when(teacherFactory.createTeacher("ABC", "Jane Doe", "abc@isep.ipp.pt", "987654321", "B123",
                "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble)).thenReturn(teacher2);

        when(teacher1.hasSameAcronym(teacher2)).thenReturn(true);
        when(teacher2.hasSameAcronym(teacher1)).thenReturn(true);

        // ACT
        repository.registerTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "Jane Doe", "abc@isep.ipp.pt", "987654321", "B123",
                    "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                    addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble);
        });

        // Assert
        assertEquals("A teacher with the same acronym already exists.", exception.getMessage());
    }

    //Testing when teacher has an existing nif
    @Test
    public void testRegisterTeacherWithDuplicateNif() throws Exception {
        //arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address addressDouble1 = mock(Address.class);
        Address addressDouble2 = mock(Address.class);
        Department dp1 = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(addressDouble1);
        when(addressFactoryDouble.createAddress("Street Two", "2345-678",
                "Porto", "Portugal")).thenReturn(addressDouble2);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression TCPdouble = mock(TeacherCareerProgression.class);

        when(TCPfactoryDouble.createTeacherCareerProgression("20-12-2010", tc1, 100)).thenReturn(TCPdouble);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble)).thenReturn(teacher1);

        when(teacherFactory.createTeacher("DEF", "Jane Doe", "def@isep.ipp.pt", "123456789", "B123",
                "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble)).thenReturn(teacher2);

        when(teacher1.hasSameNif(teacher2)).thenReturn(true);
        when(teacher2.hasSameNif(teacher1)).thenReturn(true);

        // ACT
        repository.registerTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("DEF", "Jane Doe", "def@isep.ipp.pt", "123456789", "B123",
                    "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                    addressFactoryDouble,"20-12-2010", tc1, 100, dp1, TCPfactoryDouble);
        });

        // Assert
        assertEquals("A teacher with the same NIF already exists.", exception.getMessage());
    }

    @Test
    void shouldReturnANewListOfTeachersWithSameSize() throws Exception {
        // ARRANGE
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherFactory.createTeacher("AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789", "A106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble,"15-04-2005", tc1, 70, dp1, TCPfactoryDouble)).thenReturn(teacher1);

        when(teacherFactory.createTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble,"15-04-2005", tc1, 70, dp1, TCPfactoryDouble)).thenReturn(teacher2);

        repository.registerTeacher("AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789", "A106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble,"15-04-2005", tc1, 70, dp1, TCPfactoryDouble);
        repository.registerTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble,"15-04-2005", tc1, 70, dp1, TCPfactoryDouble);

        // ACT
        List<Teacher> result = repository.getAllTeachers();

        // ASSERT
        assertEquals(2, result.size(), "The list of teachers should contain 2 teachers.");
    }

    @Test
    void shouldReturnTeacherWhenNIFMatches() throws Exception {

        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repo1 = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005", tc1, 70, dp1, TCPfactoryDouble))
                .thenReturn(teacherDouble);

        repo1.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005", tc1, 70, dp1, TCPfactoryDouble);

        when(teacherDouble.hasThisNIF("123456789")).thenReturn(true);

        // Act

        Optional<Teacher> result = repo1.getTeacherByNIF("123456789");

        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenTeacherNotFoundByNIF() throws Exception {
        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repo1 = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble))
                .thenReturn(teacherDouble);

        repo1.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble);

        when(teacherDouble.hasThisNIF("123456788")).thenReturn(false);

        // Act
        Optional<Teacher> result = repo1.getTeacherByNIF("123456788");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNIFIsEmpty() throws Exception {
        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repo1 = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble))
                .thenReturn(teacherDouble);

        repo1.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble);

        when(teacherDouble.hasThisNIF("")).thenReturn(false);

        // Act
        Optional<Teacher> result = repo1.getTeacherByNIF("");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNIFIsBlank() throws Exception {
        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repo1 = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble))
                .thenReturn(teacherDouble);

        repo1.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble);

        when(teacherDouble.hasThisNIF(" ")).thenReturn(false);

        // Act
        Optional<Teacher> result = repo1.getTeacherByNIF(" ");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNIFIsNull() throws Exception {
        // Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repo1 = new TeacherRepository(teacherFactory, teacherListFactory);
        TeacherCategory tc1 = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactory.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble))
                .thenReturn(teacherDouble);

        repo1.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tc1, 70, dp1, TCPfactoryDouble);

        when(teacherDouble.hasThisNIF(null)).thenReturn(false);

        // Act
        Optional<Teacher> result = repo1.getTeacherByNIF(null);

        // Assert
        assertFalse(result.isPresent());
    }
}