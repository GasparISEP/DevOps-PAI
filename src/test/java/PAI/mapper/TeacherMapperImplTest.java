package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.factory.ITeacherFactory;
import PAI.persistence.datamodel.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TeacherMapperImplTest {

    //mapper
    private ITeacherFactory _teacherFactoryDouble;
    private ITeacherIDMapper _teacherIDMapperDouble;
    private INIFMapper _nifMapperDouble;
    private IPhoneNumberMapper _phoneNumberMapperDouble;
    private IAddressMapper _addressMapperDouble;
    private ITeacherAcademicEmailMapper _teacherAcademicEmailMapperDouble;

    //toDomain
    private TeacherIDDataModel _teacherIDDataModelDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIFDataModel _nifDataModelDouble;
    private AcademicBackground _academicBackground;
    private AddressDataModel _addressDataModelDouble;
    private PhoneNumberDataModel _phoneNumberDataModelDouble;
    private DepartmentID _departmentIDDouble;

    //toDataModel
    private TeacherID _teacherIDDouble;
    private TeacherAcronym _teacherAcronymDouble;
    private NIF _nifDouble;
    private PhoneNumber _phoneNumberDouble;
    private Address _addressDouble;
    private Street _streetDouble;
    private PostalCode _postalCodeDouble;
    private Location _locationDouble;
    private Country _countryDouble;

    private void createMapperDoubles() {
        _teacherFactoryDouble = mock(ITeacherFactory.class);
        _teacherIDMapperDouble = mock(ITeacherIDMapper.class);
        _nifMapperDouble = mock(INIFMapper.class);
        _phoneNumberMapperDouble = mock(IPhoneNumberMapper.class);
        _addressMapperDouble = mock(IAddressMapper.class);
        _teacherAcademicEmailMapperDouble = mock(ITeacherAcademicEmailMapper.class);
    }

    private void createTeacherDataModelDoubles() {
        _teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        _nameDouble = mock(Name.class);
        _emailDouble = mock(Email.class);
        _nifDataModelDouble = mock(NIFDataModel.class);
        _phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        _academicBackground = mock(AcademicBackground.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _addressDataModelDouble = mock(AddressDataModel.class);
    }

    private void createTeacherDoubles() {
        _teacherIDDouble = mock(TeacherID.class);
        _teacherAcronymDouble = mock(TeacherAcronym.class);
        _nifDouble = mock(NIF.class);
        _phoneNumberDouble = mock(PhoneNumber.class);
        _addressDouble = mock(Address.class);
        _streetDouble = mock(Street.class);
        _postalCodeDouble = mock(PostalCode.class);
        _locationDouble = mock(Location.class);
        _countryDouble = mock(Country.class);
    }

    @Test
    void shouldCreateTeacherMapper() {
        //Arrange
        createMapperDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble);

        //Act + Assert
        assertNotNull(teacherMapper);
    }

    private static Stream<Arguments> nullParametersWhenCreatingTeacherMapper() {
        return Stream.of(
                Arguments.of("teacherFactoryDouble"),
                Arguments.of("teacherIDMapperDouble"),
                Arguments.of("nifMapperDouble"),
                Arguments.of("phoneNumberMapperDouble"),
                Arguments.of("addressMapperDouble"),
                Arguments.of("teacherAcademicEmailMapperDouble")
        );
    }

    @ParameterizedTest
    @MethodSource("nullParametersWhenCreatingTeacherMapper")
    void shouldReturnNullForNullInputsWhenCreatingMapper(String nulls) {
        //Arrange
        createMapperDoubles();

        //Act
        switch(nulls) {
            case "teacherFactoryDouble" -> _teacherFactoryDouble = null;
            case "teacherIDMapperDouble" -> _teacherIDMapperDouble = null;
            case "nifMapperDouble" -> _nifMapperDouble = null;
            case "phoneNumberMapperDouble" -> _phoneNumberMapperDouble = null;
            case "addressMapperDouble" -> _addressMapperDouble = null;
            case "teacherAcademicEmailMapperDouble" -> _teacherAcademicEmailMapperDouble = null;
        }

        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
                new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                        _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble);
        });
    }

    @Test
    void shouldReturnNullWhenProvidedTeacherIsNull() {
        //Arrange
        createMapperDoubles();
        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble);

        //Act
        TeacherDataModel result = teacherMapper.toDataModel(null);

        //Assert
        assertNull(result);
    }

    @Test
    void shouldReturnTeacherDataModelWhenTeacherIsNotNull() {
        //Arrange
        createMapperDoubles();
        createTeacherDataModelDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble);

        Teacher teacherDouble = mock(Teacher.class);

        when(_teacherIDMapperDouble.toDataModel(teacherDouble.getTeacherID())).thenReturn(_teacherIDDataModelDouble);
        when(teacherDouble.getName()).thenReturn(_nameDouble);
        when(teacherDouble.getEmail()).thenReturn(_emailDouble);
        when(_nifMapperDouble.domainToDataModel(teacherDouble.getNIF())).thenReturn(_nifDataModelDouble);
        when(_phoneNumberMapperDouble.domainToDataModel(teacherDouble.getPhoneNumber())).thenReturn(_phoneNumberDataModelDouble);
        when(teacherDouble.getAcademicBackground()).thenReturn(_academicBackground);
        when(_addressMapperDouble.toDataModel(teacherDouble.getAddress())).thenReturn(_addressDataModelDouble);
        when(teacherDouble.getDepartmentID()).thenReturn(_departmentIDDouble);

        //Act
        TeacherDataModel result = teacherMapper.toDataModel(teacherDouble);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnNullWhenProvidedTeacherDataModelIsNull() throws Exception {
        //Arrange
        createMapperDoubles();
        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble);

        //Act
        Teacher result = teacherMapper.toDomain(null);

        //Assert
        assertNull(result);
    }

    @Test
    void shouldReturnTeacherWhenTeacherDataModelIsNotNull() throws Exception {
        //Arrange
        createMapperDoubles();
        createTeacherDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble);

        TeacherDataModel teacherDataModelDouble = mock(TeacherDataModel.class);

        String name = "Henrique";
        String email = "henrique@gmail.com";
        String academicBackground = "PHD";
        String teacherAcronym = "ABC";

        when(_teacherIDMapperDouble.toDomain(teacherDataModelDouble.getTeacherIDDataModel())).thenReturn(_teacherIDDouble);
        when(_teacherIDDouble.getTeacherAcronym()).thenReturn(_teacherAcronymDouble);

        when(teacherDataModelDouble.getName()).thenReturn(name);
        when(teacherDataModelDouble.getEmail()).thenReturn(email);
        when(teacherDataModelDouble.getAcademicBackground()).thenReturn(academicBackground);
        when(teacherDataModelDouble.getDptAcronym()).thenReturn(teacherAcronym);

        when(_nifMapperDouble.dataModelToDomain(teacherDataModelDouble.getNif())).thenReturn(_nifDouble);
        when(_phoneNumberMapperDouble.dataModelToDomain(teacherDataModelDouble.getPhoneNumber())).thenReturn(_phoneNumberDouble);
        when(_addressMapperDouble.toDomain(teacherDataModelDouble.getAddress())).thenReturn(_addressDouble);
        when(_addressDouble.getStreet()).thenReturn(_streetDouble);
        when(_addressDouble.getPostalCode()).thenReturn(_postalCodeDouble);
        when(_addressDouble.getLocation()).thenReturn(_locationDouble);
        when(_addressDouble.getCountry()).thenReturn(_countryDouble);

        Teacher expectedTeacher = mock(Teacher.class);

        when(_teacherFactoryDouble.createTeacher(eq(_teacherAcronymDouble), any(Name.class), any(Email.class), eq(_nifDouble),
                eq(_phoneNumberDouble), any(AcademicBackground.class), eq(_streetDouble), eq(_postalCodeDouble),
                eq(_locationDouble), eq(_countryDouble), any(DepartmentID.class))).thenReturn(expectedTeacher);

        //Act
        Teacher result = teacherMapper.toDomain(teacherDataModelDouble);

        //Assert
        assertEquals(expectedTeacher, result);
    }
}