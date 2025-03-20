package PAI.repository;

import PAI.domain.*;
import PAI.factory.*;
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
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Address addressDouble = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        TeacherCareerProgressionFactoryImpl TCPfactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionListFactoryImpl tcplFDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
        List<TeacherCareerProgression> mockList = mock(ArrayList.class);

        when(TCPfactoryDouble.createTeacherCareerProgression("15-04-2005", teacherCategoryDouble, 70)).thenReturn(tcpDouble);
        when(tcplFDouble.createTeacherCareerProgressionList()).thenReturn(mockList);
        when(addressFactoryDouble.createAddress("Street One", "1234-678", "Porto", "Portugal")).thenReturn(addressDouble);

        // Act
        boolean result = repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street One", "1234-678",
                "Porto", "Portugal", addressFactoryDouble, "20-12-1010", teacherCategoryDouble,
                100, departmentDouble
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
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Address adressDouble1 = mock(Address.class);
        Address adressDouble2 = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        TeacherCareerProgressionFactoryImpl TCPfactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionListFactoryImpl tcplFDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
        List<TeacherCareerProgression> mockList = mock(ArrayList.class);

        when(TCPfactoryDouble.createTeacherCareerProgression("15-04-2005", tcDouble, 70)).thenReturn(tcpDouble);
        when(tcplFDouble.createTeacherCareerProgressionList()).thenReturn(mockList);
        when(addressFactoryDouble.createAddress("Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(adressDouble1);
        when(addressFactoryDouble.createAddress( "Street Two", "1234-678",
                "Porto", "Portugal")).thenReturn(adressDouble2);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        //create TeacherRepository
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactoryImpl listFactoryDouble = mock(TeacherListFactoryImpl.class);

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
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble);
        boolean result2 = teacherRepository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Street Two", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble);

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
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Address addressDouble1 = mock(Address.class);
        Address addressDouble2 = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678", "Porto", "Portugal"))
                .thenReturn(addressDouble1);
        when(addressFactoryDouble.createAddress("Street Two", "2345-678", "Porto", "Portugal"))
                .thenReturn(addressDouble2);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

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
                addressFactoryDouble, "20-12-2010", tcDouble, 100, departmentDouble);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "Jane Doe", "abc@isep.ipp.pt", "987654321", "B123",
                    "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                    addressFactoryDouble, "20-12-2010", tcDouble, 100, departmentDouble);
        });

        // Assert
        assertEquals("A teacher with the same acronym already exists.", exception.getMessage());
    }


    //Testing when teacher has an existing nif
    @Test
    public void testRegisterTeacherWithDuplicateNif() {
        // Arrange
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Address addressDouble1 = mock(Address.class);
        Address addressDouble2 = mock(Address.class);
        Department departmentDouble = mock(Department.class);

        when(addressFactoryDouble.createAddress("Street One", "1234-678",
                "Porto", "Portugal")).thenReturn(addressDouble1);
        when(addressFactoryDouble.createAddress("Street Two", "2345-678",
                "Porto", "Portugal")).thenReturn(addressDouble2);

        TeacherCareerProgressionFactoryImpl TCPfactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherCareerProgression TCPdouble = mock(TeacherCareerProgression.class);

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


        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        // Act
        repository.registerTeacher("ABC", "John Doe", "abc@isep.ipp.pt", "123456789", "A123",
                "PhD in Engineering", "Street One", "1234-678", "Porto", "Portugal",
                addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("DEF", "Jane Doe", "def@isep.ipp.pt", "123456789", "B123",
                    "PhD in Engineering", "Street Two", "2345-678", "Porto", "Portugal",
                    addressFactoryDouble,"20-12-2010", tcDouble, 100, departmentDouble);
        });

        // Assert
        assertEquals("A teacher with the same NIF already exists.", exception.getMessage());
    }

    @Test
    void shouldReturnANewListOfTeachersWithSameSize() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);

        // Mock para lista de professores
        ArrayList<Teacher> listDouble = new ArrayList<>();
        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department departmentDouble = mock(Department.class);

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
                "Portugal", addressFactoryDouble, "15-04-2005", tcDouble, 70, departmentDouble);

        repository.registerTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto",
                "Portugal", addressFactoryDouble, "15-04-2005", tcDouble, 70, departmentDouble);

        // ACT
        List<Teacher> result = repository.getAllTeachers();

        // ASSERT
        assertEquals(2, result.size(), "The list of teachers should contain 2 teachers.");
    }



    @Test
    void shouldReturnTeacherWhenNIFMatches() {

        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department departmentDouble = mock(Department.class);
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
                teacherCategoryDouble, 70, departmentDouble);

        when(teacherMock.hasThisNIF("123456789")).thenReturn(true);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF("123456789");

        // ASSERT
        assertTrue(result.isPresent(), "Teacher should be returned when NIF matches.");
    }


    @Test
    void shouldReturnEmptyOptionalWhenTeacherNotFoundByNIF() throws Exception {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);


        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department departmentDouble = mock(Department.class);
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

        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(teacherListDouble);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(false, true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble);

        when(teacherDouble.hasThisNIF("123456788")).thenReturn(false);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF("123456788");

        // ASSERT
        assertFalse(result.isPresent());
    }


    @Test
    void shouldReturnEmptyOptionalWhenNIFIsEmpty() {
        // ARRANGE
        TeacherFactoryImpl teacherFactoryDouble = mock(TeacherFactoryImpl.class);
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department departmentDouble = mock(Department.class);
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

        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble);

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
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department departmentDouble = mock(Department.class);
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

        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble);

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
        TeacherListFactoryImpl teacherListFactoryImplDouble = mock(TeacherListFactoryImpl.class);
        TeacherRepository repository = new TeacherRepository(teacherFactoryDouble, teacherListFactoryImplDouble);

        TeacherCategory teacherCategoryDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department departmentDouble = mock(Department.class);
        TeacherCareerProgressionFactoryImpl careerProgressionFactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherCareerProgressionListFactoryImpl careerProgressionListFactoryDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
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

        when(teacherListFactoryImplDouble.newArrayList()).thenReturn(teacherListDouble);
        when(teacherListDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false); // Only one teacher in the list
        when(iterator.next()).thenReturn(teacherDouble);

        repository.registerTeacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores",
                "4444-098", "Porto", "Portugal", addressFactoryDouble, "15-04-2005",
                teacherCategoryDouble, 70, departmentDouble);


        when(teacherDouble.hasThisNIF(null)).thenReturn(false);

        // ACT
        Optional<Teacher> result = repository.getTeacherByNIF(null);

        // ASSERT
        assertFalse(result.isPresent(), "The Optional should be empty when the NIF is null.");
    }
}