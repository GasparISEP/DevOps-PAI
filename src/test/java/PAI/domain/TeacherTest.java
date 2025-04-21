package PAI.domain;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TeacherTest {

    private TeacherAcronym _teacherAcronymDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIF _nifDouble;
    private PhoneNumber _phoneNumberDouble;
    private AcademicBackground _academicBackgroundDouble;
    private DepartmentID _departmentIDDouble;
    private Address _addressDouble;

    private void createDoubles (){
        _teacherAcronymDouble = mock(TeacherAcronym.class);
        _nameDouble = mock(Name.class);
        _emailDouble = mock(Email.class);
        _nifDouble = mock(NIF.class);
        _phoneNumberDouble = mock(PhoneNumber.class);
        _academicBackgroundDouble = mock(AcademicBackground.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _addressDouble = mock(Address.class);
    }

    @Test
    void shouldCreateTeacherWhenAllFieldsAreValid() {
        // Arrange
        createDoubles();

        // Act
        new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Assert
    }

    private static Stream<Arguments> nullParameterCases() {
        return Stream.of(
                Arguments.of("Acronym"),
                Arguments.of("Name"),
                Arguments.of("Email"),
                Arguments.of("NIF"),
                Arguments.of("PhoneNumber"),
                Arguments.of("AcademicBackground"),
                Arguments.of("Department")
        );
    }
    @ParameterizedTest(name = "shouldThrowExceptionWhen{0}IsNull")
    @MethodSource("nullParameterCases")
    void shouldThrowExceptionWhenCreatingTeacherWithAnyNullParameter(String nullField) {
        // Arrange
        createDoubles();

        // Make each field at a time Null
        switch (nullField) {
            case "Acronym" -> _teacherAcronymDouble = null;
            case "Name" -> _nameDouble = null;
            case "Email" -> _emailDouble = null;
            case "NIF" -> _nifDouble = null;
            case "PhoneNumber" -> _phoneNumberDouble = null;
            case "AcademicBackground" -> _academicBackgroundDouble = null;
            case "Department" -> _departmentIDDouble = null;
        }

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                    _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
            );
        });
    }

    @Test
    public void shouldReturnTeacherIDWhenIdentityIsCalled() {
        // Arrange
        createDoubles();

        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Act
        TeacherID result = teacher.identity();

        // Assert
        assertEquals(result.getTeacherAcronym(), _teacherAcronymDouble);
    }

    @Test
    public void shouldReturnTrueWhenComparingTheSameObject() {
        // Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Act
        boolean result = teacher.sameAs(teacher);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenSameAsIsCalledWithNullValue() {
        // Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Act
        boolean result = teacher.sameAs(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenSameAsIsCalledWithADifferentClass() {
        // Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);
        Object otherDouble = mock(Object.class);

        // Act
        boolean result = teacher.sameAs(otherDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenSameAsIsCalledWithSameTeacherID() {
        // Arrange
        createDoubles();

        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        Teacher teacher2 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Act
        boolean result = teacher1.sameAs(teacher2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenSameAsIsCalledWithSameNif() {
        // Arrange
        createDoubles();
        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        Teacher teacher2 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Act
        boolean result = teacher1.sameAs(teacher2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenSameAsIsCalledWithDifferentTeacherIDAndNif() {
        // Arrange
        createDoubles();
        TeacherAcronym acronymDouble2 = mock(TeacherAcronym.class);
        NIF nifDouble2 = mock(NIF.class);

        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        Teacher teacher2 = new Teacher(acronymDouble2, _nameDouble, _emailDouble, nifDouble2,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble);

        // Act
        boolean result = teacher1.sameAs(teacher2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfThereIsATeacherWithThisNif() {
        // arrange
        createDoubles();
        NIF nifDouble2 = mock(NIF.class);

        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        when(_nifDouble.getNIF()).thenReturn("123456789");
        when(nifDouble2.getNIF()).thenReturn("123456789");

        //act
        boolean result = teacher1.hasThisNIF(nifDouble2);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfThereIsNoTeacherWithThisNif() throws IllegalArgumentException {
        // arrange
        createDoubles();
        NIF nifDouble2 = mock(NIF.class);

        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        when(_nifDouble.getNIF()).thenReturn("123456789");
        when(nifDouble2.getNIF()).thenReturn("987654321");

        //act
        boolean result = teacher1.hasThisNIF(nifDouble2);

        // assert
        assertFalse(result);
    }

    @Test
    void testIfTeacherIsInASpecificDepartment() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //act
        boolean result = teacher1.isInDepartment(_departmentIDDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void testIfTeacherIsNotInASpecificDepartment() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        DepartmentID departmentIDDouble2 = mock(DepartmentID.class);

        Teacher teacher1 = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //act
        boolean result = teacher1.isInDepartment(departmentIDDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTeacherIDThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        TeacherID result = teacher.getTeacherID();

        //Assert
        assertEquals(_teacherAcronymDouble, result.getTeacherAcronym());
    }

    @Test
    void shouldReturnNameThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        Name result = teacher.getName();

        //Assert
        assertEquals(_nameDouble, result);
    }

    @Test
    void shouldReturnEmailThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        Email result = teacher.getEmail();

        //Assert
        assertEquals(_emailDouble, result);
    }

    @Test
    void shouldReturnNIFThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        NIF result = teacher.getNIF();

        //Assert
        assertEquals(_nifDouble, result);
    }

    @Test
    void shouldReturnPhoneNumberThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        PhoneNumber result = teacher.getPhoneNumber();

        //Assert
        assertEquals(_phoneNumberDouble, result);
    }

    @Test
    void shouldReturnAcademicBackgroundThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        AcademicBackground result = teacher.getAcademicBackground();

        //Assert
        assertEquals(_academicBackgroundDouble, result);
    }

    @Test
    void shouldReturnAddressThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        Address result = teacher.getAddress();

        //Assert
        assertEquals(_addressDouble, result);
    }

    @Test
    void shouldReturnDepartmentIDThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );

        //Act
        DepartmentID result = teacher.getDepartmentID();

        //Assert
        assertEquals(_departmentIDDouble, result);
    }

    @Test
    void shouldReturnTeacherAcademicEmailThroughGetter() {
        //Arrange
        createDoubles();
        Teacher teacher = new Teacher(_teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble,
                _phoneNumberDouble, _academicBackgroundDouble, _addressDouble, _departmentIDDouble
        );
        TeacherAcademicEmail teacherAcademicEmail = new TeacherAcademicEmail(_teacherAcronymDouble);

        //Act
        TeacherAcademicEmail result = teacher.getTeacherAcademicEmail();

        //Assert
        assertEquals(teacherAcademicEmail, result);
    }

}