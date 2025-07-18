package PAI.mapper.teacher;

import PAI.VOs.*;
import PAI.domain.course.ICourseFactory;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacher.ITeacherFactory;
import PAI.mapper.IAddressMapper;
import PAI.mapper.INIFMapper;
import PAI.mapper.IPhoneNumberMapper;
import PAI.mapper.ITeacherAcademicEmailMapper;
import PAI.mapper.course.CourseMapperImpl;
import PAI.mapper.course.ICourseIDMapper;
import PAI.mapper.department.DepartmentIDMapperImpl;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.persistence.datamodel.*;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
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
    private IDepartmentIDMapper _departmentIDMapper;

    //toDomain
    private TeacherIDDataModel _teacherIDDataModelDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIFDataModel _nifDataModelDouble;
    private AcademicBackground _academicBackground;
    private AddressDataModel _addressDataModelDouble;
    private PhoneNumberDataModel _phoneNumberDataModelDouble;
    private DepartmentIDDataModel _departmentIDDataModelDouble;

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
    private DepartmentID _departmentIDDouble;

    private void createMapperDoubles() {
        _teacherFactoryDouble = mock(ITeacherFactory.class);
        _teacherIDMapperDouble = mock(ITeacherIDMapper.class);
        _nifMapperDouble = mock(INIFMapper.class);
        _phoneNumberMapperDouble = mock(IPhoneNumberMapper.class);
        _addressMapperDouble = mock(IAddressMapper.class);
        _teacherAcademicEmailMapperDouble = mock(ITeacherAcademicEmailMapper.class);
        _departmentIDMapper = mock(DepartmentIDMapperImpl.class);
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
        _departmentIDDataModelDouble = mock(DepartmentIDDataModel.class);
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
        _departmentIDDouble = mock(DepartmentID.class);
    }

    @Test
    void shouldCreateTeacherMapper() {
        //Arrange
        createMapperDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble, _departmentIDMapper);

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
                Arguments.of("teacherAcademicEmailMapperDouble"),
                Arguments.of("departmentIDMapper")
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
            case "departmentIDMapper" -> _departmentIDMapper = null;
        }

        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
                new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                        _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble, _departmentIDMapper);
        });
    }

    @Test
    void shouldReturnNullWhenProvidedTeacherIsNull() {
        //Arrange
        createMapperDoubles();
        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble, _departmentIDMapper);

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> teacherMapper.toDataModel(null));

        //Assert
        assertEquals("Teacher cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTeacherDataModelWhenTeacherIsNotNull() {
        //Arrange
        createMapperDoubles();
        createTeacherDataModelDoubles();
        DepartmentAcronym departmentAcronymDouble = mock(DepartmentAcronym.class);

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble, _departmentIDMapper);

        Teacher teacherDouble = mock(Teacher.class);

        when(_teacherIDMapperDouble.toDataModel(teacherDouble.getTeacherID())).thenReturn(_teacherIDDataModelDouble);
        when(teacherDouble.getName()).thenReturn(_nameDouble);
        when(teacherDouble.getEmail()).thenReturn(_emailDouble);
        when(_nifMapperDouble.domainToDataModel(teacherDouble.getNIF())).thenReturn(_nifDataModelDouble);
        when(_phoneNumberMapperDouble.domainToDataModel(teacherDouble.getPhoneNumber())).thenReturn(_phoneNumberDataModelDouble);
        when(teacherDouble.getAcademicBackground()).thenReturn(_academicBackground);
        when(_addressMapperDouble.toDataModel(teacherDouble.getAddress())).thenReturn(_addressDataModelDouble);
        when(teacherDouble.getDepartmentID()).thenReturn(_departmentIDDouble);
        when(_departmentIDDouble.getAcronym()).thenReturn(departmentAcronymDouble);
        when(_departmentIDMapper.toDataModel(_departmentIDDouble)).thenReturn(_departmentIDDataModelDouble);

        //Act
        TeacherDataModel result = teacherMapper.toDataModel(teacherDouble);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenProvidedTeacherDataModelIsNull() {
        //Arrange
        createMapperDoubles();
        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble, _departmentIDMapper);

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> teacherMapper.toDomain(null));

        //Assert
        assertEquals("Teacher Data Model cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTeacherWhenTeacherDataModelIsNotNull() {
        //Arrange
        createMapperDoubles();
        createTeacherDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(_teacherFactoryDouble, _teacherIDMapperDouble, _nifMapperDouble,
                _phoneNumberMapperDouble, _addressMapperDouble, _teacherAcademicEmailMapperDouble, _departmentIDMapper);

        TeacherDataModel teacherDataModelDouble = mock(TeacherDataModel.class);

        String name = "Henrique";
        String email = "henrique@gmail.com";
        String academicBackground = "PHD";

        when(_teacherIDMapperDouble.toDomain(teacherDataModelDouble.getTeacherIDDataModel())).thenReturn(_teacherIDDouble);
        when(_teacherIDDouble.getTeacherAcronym()).thenReturn(_teacherAcronymDouble);

        when(teacherDataModelDouble.getName()).thenReturn(name);
        when(teacherDataModelDouble.getEmail()).thenReturn(email);
        when(teacherDataModelDouble.getAcademicBackground()).thenReturn(academicBackground);

        when(_nifMapperDouble.dataModelToDomain(teacherDataModelDouble.getNif())).thenReturn(_nifDouble);
        when(_phoneNumberMapperDouble.dataModelToDomain(teacherDataModelDouble.getPhoneNumber())).thenReturn(_phoneNumberDouble);
        when(_addressMapperDouble.toDomain(teacherDataModelDouble.getAddress())).thenReturn(_addressDouble);
        when(_addressDouble.getStreet()).thenReturn(_streetDouble);
        when(_addressDouble.getPostalCode()).thenReturn(_postalCodeDouble);
        when(_addressDouble.getLocation()).thenReturn(_locationDouble);
        when(_addressDouble.getCountry()).thenReturn(_countryDouble);
        when(teacherDataModelDouble.getDepartmentID()).thenReturn(_departmentIDDataModelDouble);
        when(_departmentIDMapper.toDomainModel(_departmentIDDataModelDouble)).thenReturn(_departmentIDDouble);

        Teacher expectedTeacher = mock(Teacher.class);

        when(_teacherFactoryDouble.createTeacher(eq(_teacherIDDouble), any(Name.class), any(Email.class), eq(_nifDouble),
                eq(_phoneNumberDouble), any(AcademicBackground.class), eq(_streetDouble), eq(_postalCodeDouble),
                eq(_locationDouble), eq(_countryDouble), eq(_departmentIDDouble))).thenReturn(expectedTeacher);

        //Act
        Teacher result = teacherMapper.toDomain(teacherDataModelDouble);

        //Assert
        assertEquals(expectedTeacher, result);
    }
    @Test
    void shouldThrowExceptionWhenProvidedTeacherDataModelListIsNull() {
        // Arrange
        createMapperDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(
                _teacherFactoryDouble,
                _teacherIDMapperDouble,
                _nifMapperDouble,
                _phoneNumberMapperDouble,
                _addressMapperDouble,
                _teacherAcademicEmailMapperDouble,
                _departmentIDMapper
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> teacherMapper.listToDomain(null)
        );

        assertEquals("Teacher Data Model list cannot be null.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenTeacherDataModelListIsNull() {

        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherIDMapper teacherIDMapperDouble = mock(ITeacherIDMapper.class);
        INIFMapper nifMapperDouble = mock(INIFMapper.class);
        IPhoneNumberMapper phoneNumberMapperDouble = mock(IPhoneNumberMapper.class);
        IAddressMapper addressMapperDouble = mock(IAddressMapper.class);
        ITeacherAcademicEmailMapper emailMapperDouble = mock(ITeacherAcademicEmailMapper.class);
        IDepartmentIDMapper departmentIDMapperDouble = mock(IDepartmentIDMapper.class);

        TeacherMapperImpl teacherMapperImpl = new TeacherMapperImpl(
                teacherFactoryDouble,
                teacherIDMapperDouble,
                nifMapperDouble,
                phoneNumberMapperDouble,
                addressMapperDouble,
                emailMapperDouble,
                departmentIDMapperDouble
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> teacherMapperImpl.listToDomain(null)
        );

        assertEquals("Teacher Data Model list cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnListOfTeachersWhenTeacherDataModelListIsValid() {

        // Arrange
        createMapperDoubles();

        TeacherMapperImpl teacherMapper = new TeacherMapperImpl(
                _teacherFactoryDouble,
                _teacherIDMapperDouble,
                _nifMapperDouble,
                _phoneNumberMapperDouble,
                _addressMapperDouble,
                _teacherAcademicEmailMapperDouble,
                _departmentIDMapper
        );

        TeacherDataModel teacherDouble1 = mock(TeacherDataModel.class);
        TeacherDataModel teacherDouble2 = mock(TeacherDataModel.class);

        TeacherIDDataModel tidm1 = mock(TeacherIDDataModel.class);
        NIFDataModel nifDM = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDM = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDM = mock(AddressDataModel.class);
        DepartmentIDDataModel depIDDM = mock(DepartmentIDDataModel.class);

        TeacherID teacherID1 = mock(TeacherID.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Address address = mock(Address.class);
        DepartmentID depID = mock(DepartmentID.class);

        Teacher teacherMock = mock(Teacher.class);

        // Para ambos os TeacherDataModel usares os mesmos mocks (podes duplicar se quiseres mais detalhe)
        for (TeacherDataModel dm : List.of(teacherDouble1, teacherDouble2)) {
            when(dm.getTeacherIDDataModel()).thenReturn(tidm1);
            when(dm.getNif()).thenReturn(nifDM);
            when(dm.getPhoneNumber()).thenReturn(phoneDM);
            when(dm.getAddress()).thenReturn(addressDM);
            when(dm.getDepartmentID()).thenReturn(depIDDM);
            when(dm.getName()).thenReturn("Henrique");
            when(dm.getEmail()).thenReturn("henrique@gmail.com");
            when(dm.getAcademicBackground()).thenReturn("PHD");
        }

        // Mapeamentos
        when(_teacherIDMapperDouble.toDomain(tidm1)).thenReturn(teacherID1);
        when(_nifMapperDouble.dataModelToDomain(nifDM)).thenReturn(nif);
        when(_phoneNumberMapperDouble.dataModelToDomain(phoneDM)).thenReturn(phone);
        when(_addressMapperDouble.toDomain(addressDM)).thenReturn(address);
        when(_departmentIDMapper.toDomainModel(depIDDM)).thenReturn(depID);

        // Endereço (necessário para factory)
        when(address.getStreet()).thenReturn(mock(Street.class));
        when(address.getPostalCode()).thenReturn(mock(PostalCode.class));
        when(address.getLocation()).thenReturn(mock(Location.class));
        when(address.getCountry()).thenReturn(mock(Country.class));

        // TeacherFactory cria sempre o mesmo teacherMock (poderias criar dois diferentes também)
        when(_teacherFactoryDouble.createTeacher(
                eq(teacherID1), any(Name.class), any(Email.class),
                eq(nif), eq(phone), any(AcademicBackground.class),
                any(Street.class), any(PostalCode.class), any(Location.class), any(Country.class),
                eq(depID)
        )).thenReturn(teacherMock);

        // Act
        Iterable<Teacher> result = teacherMapper.listToDomain(List.of(teacherDouble1, teacherDouble2));

        // Assert
        List<Teacher> resultList = new ArrayList<>();
        result.forEach(resultList::add);

        assertEquals(2, resultList.size());
      }
}