package PAI.domain;

import PAI.factory.*;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherRepositoryTest {

    @Test
    void shouldCreateValidTeacher() {
        // Arrange
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address addressDouble = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678", "Porto", "Portugal"))
                .thenReturn(addressDouble);

        TeacherCareerProgressionFactory careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression careerProgressionDouble = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionListFactory careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        when(careerProgressionFactoryDouble.createTeacherCareerProgression("20-12-1010", teacherCategoryDouble, 100))
                .thenReturn(careerProgressionDouble);

        // Act
        boolean result = repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street One", "1234-678",
                "Porto", "Portugal", addressFactoryDouble, "20-12-1010", teacherCategoryDouble,
                100, departmentDouble, careerProgressionFactoryDouble, careerProgressionListFactoryDouble
        );

        // Assert
        assertTrue(result, "The teacher should be registered successfully.");
    }

    //Test to register two valid teachers
    @Test
    void testRegisterValidTeacher() throws IllegalArgumentException {
        // Arrange
        ArrayList<Teacher> listDouble = mock(ArrayList.class);

        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address adressDouble1 = mock(Address.class);
        Address adressDouble2 = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(adressDouble1);
        when(addressFactoryDouble.createAddress( "Street Two", "1234-678",
                "Porto", "Portugal")).thenReturn(adressDouble2);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        //create TeacherRepository
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory listFactoryDouble = mock(TeacherListFactory.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        TeacherRepository teacherRepository = new TeacherRepository(teacherFactoryDouble, listFactoryDouble);

        // Mock iterator behavior
        Iterator<Teacher> it = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(it);

        when(it.hasNext()).thenReturn(true, false);

        when(it.next()).thenReturn(teacher1);

        when(teacherFactoryDouble.createTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street One", "1234-678",
                "Porto", "Portugal", addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble)).thenReturn(teacher1);
        when(teacherFactoryDouble.createTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street Two", "1234-678",
                "Porto", "Portugal", addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble)).thenReturn(teacher2);


        // Act
        boolean result1 = teacherRepository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);
        boolean result2 = teacherRepository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street Two", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);

        // Assert
        assertTrue(result1, "The first teacher should be registered successfully.");
        assertTrue(result2, "The second teacher should be registered successfully.");
    }

    //Testing when teacher has an existing acronym
    @Test
    public void testRegisterTeacherWithDuplicateAcronym() {
        // Arrange
        ArrayList<Teacher> listDouble = mock(ArrayList.class);
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address addressDouble1 = mock(Address.class);
        Address addressDouble2 = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678", "Porto", "Portugal"))
                .thenReturn(addressDouble1);
        when(addressFactoryDouble.createAddress("Street Two", "2345-678", "Porto", "Portugal"))
                .thenReturn(addressDouble2);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherListFactoryDouble.newArrayList()).thenReturn(listDouble);

        // Mock iterator behavior
        Iterator<Teacher> it = mock(Iterator.class);
        when(listDouble.iterator()).thenReturn(it);
        when(it.hasNext()).thenReturn(true, false);
        when(it.next()).thenReturn(teacher1);

        when(teacherFactoryDouble.createTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble, "20-12-2010", tcDouble, 100, departmentDouble))
                .thenReturn(teacher1);

        when(teacherFactoryDouble.createTeacher("ABC", "Jane Doe", "abc@isep.ipp.pt", "987654321", "B123",
                "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                addressFactoryDouble, "20-12-2010", tcDouble, 100, departmentDouble))
                .thenReturn(teacher2);

        when(teacher1.hasSameAcronym(teacher2)).thenReturn(true);
        when(teacher2.hasSameAcronym(teacher1)).thenReturn(true);

        // Act
        repository.registerTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble, "20-12-2010", tcDouble, 100, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "Jane Doe", "abc@isep.ipp.pt", "987654321", "B123",
                    "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                    addressFactoryDouble, "20-12-2010", tcDouble, 100, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);
        });

        // Assert
        assertEquals("A teacher with the same acronym already exists.", exception.getMessage());
    }


    //Testing when teacher has an existing nif
    @Test
    public void testRegisterTeacherWithDuplicateNif() {
        // Arrange
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Address addressDouble1 = mock(Address.class);
        Address addressDouble2 = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(addressDouble1);
        when(addressFactoryDouble.createAddress("Street Two", "2345-678",
                "Porto", "Portugal")).thenReturn(addressDouble2);

        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression TCPdouble = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        when(TCPfactoryDouble.createTeacherCareerProgression("20-12-2010", tcDouble, 100)).thenReturn(TCPdouble);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble)).thenReturn(teacher1);

        when(teacherFactoryDouble.createTeacher("DEF", "Jane Doe", "def@isep.ipp.pt", "123456789", "B123",
                "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble)).thenReturn(teacher2);


        when(teacher1.hasSameNif(teacher2)).thenReturn(true);
        when(teacher2.hasSameNif(teacher1)).thenReturn(true);


        ArrayList<Teacher> listDouble = mock(ArrayList.class);
        Iterator<Teacher> iteratorMock = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iteratorMock);
        when(iteratorMock.hasNext()).thenReturn(true, false);
        when(iteratorMock.next()).thenReturn(teacher1);


        when(teacherListFactoryDouble.newArrayList()).thenReturn(listDouble);

        // Act
        repository.registerTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("DEF", "Jane Doe", "def@isep.ipp.pt", "123456789", "B123",
                    "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                    addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);
        });

        // Assert
        assertEquals("A teacher with the same NIF already exists.", exception.getMessage());
    }

    @Test
    void shouldReturnANewListOfTeachersWithSameSize() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        // Mock para lista de professores
        ArrayList<Teacher> listDouble = new ArrayList<>();
        when(teacherListFactoryDouble.newArrayList()).thenReturn(listDouble);

        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher("AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789", "A106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tcDouble, 70, departmentDouble))
                .thenReturn(teacher1);

        when(teacherFactoryDouble.createTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tcDouble, 70, departmentDouble))
                .thenReturn(teacher2);

        // Registrar professores
        repository.registerTeacher("AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789", "A106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tcDouble, 70, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);

        repository.registerTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tcDouble, 70, departmentDouble, TCPfactoryDouble, tcpLFactoryDouble);

        // ACT
        List<Teacher> result = repository.getAllTeachers();

        // ASSERT
        assertEquals(2, result.size(), "The list of teachers should contain 2 teachers.");
    }



    @Test
    void shouldReturnTeacherWhenNIFMatches() {

        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactory careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);
        Teacher teacherMock = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble))
                .thenReturn(teacherMock);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble, careerProgressionFactoryDouble,
                careerProgressionListFactoryDouble);

        when(teacherMock.hasThisNIF("123456789")).thenReturn(true);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF("123456789");

        // ASSERT
        assertTrue(result.isPresent(), "Teacher should be returned when NIF matches.");
    }


    @Test
    void shouldReturnEmptyOptionalWhenTeacherNotFoundByNIF() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactory careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble))
                .thenReturn(teacherDouble);

        // Mocking the list and iterator
        ArrayList<Teacher> teacherListDouble = mock(ArrayList.class);
        Iterator<Teacher> iterator = mock(Iterator.class);

        when(teacherListFactoryDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble, careerProgressionFactoryDouble,
                careerProgressionListFactoryDouble);

        when(teacherDouble.hasThisNIF("123456788")).thenReturn(false);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF("123456788");

        // ASSERT
        assertFalse(result.isPresent(), "Teacher should not be found when NIF does not match.");
    }


    @Test
    void shouldReturnEmptyOptionalWhenNIFIsEmpty() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactory careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble))
                .thenReturn(teacherDouble);

        // Mocking the list and iterator
        ArrayList<Teacher> teacherListDouble = mock(ArrayList.class);
        Iterator<Teacher> iterator = mock(Iterator.class);

        when(teacherListFactoryDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble, careerProgressionFactoryDouble,
                careerProgressionListFactoryDouble);

        // Simulating the behavior of hasThisNIF for empty NIF
        when(teacherDouble.hasThisNIF("")).thenReturn(false);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF("");

        // ASSERT
        assertFalse(result.isPresent(), "The Optional should be empty when the NIF is empty.");
    }


    @Test
    void shouldReturnEmptyOptionalWhenNIFIsBlank() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactory careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble))
                .thenReturn(teacherDouble);

        // Mocking the list and iterator
        ArrayList<Teacher> teacherListDouble = mock(ArrayList.class);
        Iterator<Teacher> iterator = mock(Iterator.class);

        when(teacherListFactoryDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble, careerProgressionFactoryDouble,
                careerProgressionListFactoryDouble);

        // Simulating the behavior of hasThisNIF for blank NIF
        when(teacherDouble.hasThisNIF(" ")).thenReturn(false);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF(" ");

        // ASSERT
        assertFalse(result.isPresent(), "The Optional should be empty when the NIF is blank.");
    }


    @Test
    void shouldReturnEmptyOptionalWhenNIFIsNull() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactory teacherListFactoryDouble = mock(TeacherListFactory.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactory careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble))
                .thenReturn(teacherDouble);

        // Mocking the list and iterator
        ArrayList<Teacher> teacherListDouble = mock(ArrayList.class);
        Iterator<Teacher> iterator = mock(Iterator.class);

        when(teacherListFactoryDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble, careerProgressionFactoryDouble,
                careerProgressionListFactoryDouble);


        when(teacherDouble.hasThisNIF(null)).thenReturn(false);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF(null);

        // ASSERT
        assertFalse(result.isPresent(), "The Optional should be empty when the NIF is null.");
    }
}