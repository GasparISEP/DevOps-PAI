package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.factory.IStudentFactory;
import PAI.factory.StudentFactoryImpl;
import PAI.persistence.datamodel.*;
import org.apache.commons.lang3.stream.Streams;
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

class StudentMapperTest {

    @Test
    void constructorShouldCreateObject() {
        //arrange
        IStudentFactory studentFactoryDouble = mock(StudentFactoryImpl.class);
        IStudentIDMapper studentIDMapperDouble = mock(StudentIDMapper.class);
        IPhoneNumberMapper phoneNumberMapperDouble = mock(PhoneNumberMapper.class);
        INIFMapper nifMapperDouble = mock(NIFMapper.class);
        IAddressMapper addressMapperDouble = mock(AddressMapper.class);
        IStudentAcademicEmailMapper studentAcademicEmailMapperDouble = mock(StudentAcademicEmailMapper.class);

        //act
        StudentMapper studentMapper = new StudentMapper(studentFactoryDouble, studentIDMapperDouble, phoneNumberMapperDouble,
                nifMapperDouble, addressMapperDouble, studentAcademicEmailMapperDouble);

        //assert
        assertNotNull(studentMapper);
    }

    static Stream<Arguments> testNullInputs() {
        return Streams.of(
                Arguments.of(null, mock(StudentIDMapper.class), mock(PhoneNumberMapper.class), mock(NIFMapper.class), mock(AddressMapper.class), mock(StudentAcademicEmailMapper.class), "Student Factory cannot be null!"),
                Arguments.of(mock(StudentFactoryImpl.class), null, mock(PhoneNumberMapper.class), mock(NIFMapper.class), mock(AddressMapper.class), mock(StudentAcademicEmailMapper.class), "StudentID Mapper cannot be null!"),
                Arguments.of(mock(StudentFactoryImpl.class), mock(StudentIDMapper.class), null, mock(NIFMapper.class), mock(AddressMapper.class), mock(StudentAcademicEmailMapper.class), "PhoneNumber Mapper cannot be null!"),
                Arguments.of(mock(StudentFactoryImpl.class), mock(StudentIDMapper.class), mock(PhoneNumberMapper.class), null, mock(AddressMapper.class), mock(StudentAcademicEmailMapper.class), "NIF Mapper cannot be null!"),
                Arguments.of(mock(StudentFactoryImpl.class), mock(StudentIDMapper.class), mock(PhoneNumberMapper.class), mock(NIFMapper.class), null, mock(StudentAcademicEmailMapper.class), "Address Mapper cannot be null!"),
                Arguments.of(mock(StudentFactoryImpl.class), mock(StudentIDMapper.class), mock(PhoneNumberMapper.class), mock(NIFMapper.class), mock(AddressMapper.class), null, "Student Academic Email Mapper cannot be null!")
        );
    }

    @ParameterizedTest
    @MethodSource("testNullInputs")
    void shouldThrowExceptionIfParametersAreNull(IStudentFactory studentFactoryDouble, IStudentIDMapper studentIDMapperDouble, IPhoneNumberMapper phoneNumberMapperDouble,
                                                 INIFMapper nifMapperDouble, IAddressMapper addressMapperDouble, IStudentAcademicEmailMapper studentAcademicEmailMapperDouble, String expectedMessage) {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new StudentMapper(studentFactoryDouble, studentIDMapperDouble, phoneNumberMapperDouble, nifMapperDouble, addressMapperDouble, studentAcademicEmailMapperDouble));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldCreateStudentDataModelFromStudentObject() {
        //arrange

        //create studentMapper
        IStudentFactory studentFactoryDouble = mock(StudentFactoryImpl.class);
        IStudentIDMapper studentIDMapperDouble = mock(StudentIDMapper.class);
        IPhoneNumberMapper phoneNumberMapperDouble = mock(PhoneNumberMapper.class);
        INIFMapper nifMapperDouble = mock(NIFMapper.class);
        IAddressMapper addressMapperDouble = mock(AddressMapper.class);
        IStudentAcademicEmailMapper studentAcademicEmailMapperDouble = mock(StudentAcademicEmailMapper.class);

        StudentMapper studentMapper = new StudentMapper(studentFactoryDouble, studentIDMapperDouble, phoneNumberMapperDouble,
                nifMapperDouble, addressMapperDouble, studentAcademicEmailMapperDouble);

        //parametersToCreateStudentDataModel
        Student studentDouble = mock(Student.class);
        StudentID studentIDDouble = mock(StudentID.class);
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        Email emailDouble = mock(Email.class);
        Address addressDouble = mock(Address.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmail studentAcademicEmailDouble = mock(StudentAcademicEmail.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        when(studentDouble.getStudentID()).thenReturn(studentIDDouble);
        when(studentIDMapperDouble.domainToDataModel(studentIDDouble)).thenReturn(studentIDDataModelDouble);

        when(studentDouble.getStudentName()).thenReturn(nameDouble);
        when(nameDouble.getName()).thenReturn("Harry Potter");

        when(studentDouble.getStudentNIF()).thenReturn(nifDouble);
        when(nifMapperDouble.domainToDataModel(nifDouble)).thenReturn(nifDataModelDouble);

        when(studentDouble.getStudentPhoneNumber()).thenReturn(phoneNumberDouble);
        when(phoneNumberMapperDouble.domainToDataModel(phoneNumberDouble)).thenReturn(phoneNumberDataModelDouble);

        when(studentDouble.getStudentEmail()).thenReturn(emailDouble);
        when(emailDouble.getEmail()).thenReturn("harrypotter@gmail.com");

        when(studentDouble.getStudentAddress()).thenReturn(addressDouble);
        when(addressMapperDouble.toDataModel(addressDouble)).thenReturn(addressDataModelDouble);

        when(studentDouble.getStudentAcademicEmail()).thenReturn(studentAcademicEmailDouble);
        when(studentAcademicEmailMapperDouble.domainToDataModel(studentAcademicEmailDouble)).thenReturn(studentAcademicEmailDataModelDouble);

        StudentDataModel expected = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble, phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        StudentDataModel result = studentMapper.domainToDataModel(studentDouble);

        //assert
        assertAll(
                () -> assertEquals(expected.getStudentID(), result.getStudentID()),
                () -> assertEquals(expected.getName(), result.getName()),
                () -> assertEquals(expected.getNIF(), result.getNIF()),
                () -> assertEquals(expected.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(expected.getEmail(), result.getEmail()),
                () -> assertEquals(expected.getAddress(), result.getAddress()),
                () -> assertEquals(expected.getAcademicEmail(), result.getAcademicEmail())
        );
    }

    @Test
    void shouldCreateStudentObjectFromStudentDataModel() throws Exception {
        //arrange

        //create studentMapper
        IStudentFactory studentFactoryDouble = mock(StudentFactoryImpl.class);
        IStudentIDMapper studentIDMapperDouble = mock(StudentIDMapper.class);
        IPhoneNumberMapper phoneNumberMapperDouble = mock(PhoneNumberMapper.class);
        INIFMapper nifMapperDouble = mock(NIFMapper.class);
        IAddressMapper addressMapperDouble = mock(AddressMapper.class);
        IStudentAcademicEmailMapper studentAcademicEmailMapperDouble = mock(StudentAcademicEmailMapper.class);

        StudentMapper studentMapper = new StudentMapper(studentFactoryDouble, studentIDMapperDouble, phoneNumberMapperDouble,
                nifMapperDouble, addressMapperDouble, studentAcademicEmailMapperDouble);

        //parametersToCreateStudentObject
        Student studentDouble = mock(Student.class);
        StudentDataModel studentDataModelDouble = mock(StudentDataModel.class);
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        StudentID studentIDDouble = mock(StudentID.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        Address addressDouble = mock(Address.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);
        StudentAcademicEmail studentAcademicEmailDouble = mock(StudentAcademicEmail.class);

        when(studentDataModelDouble.getStudentID()).thenReturn(studentIDDataModelDouble);
        when(studentIDMapperDouble.dataModelToDomain(studentIDDataModelDouble)).thenReturn(studentIDDouble);

        when(studentDataModelDouble.getName()).thenReturn("Harry Potter");

        when(studentDataModelDouble.getNIF()).thenReturn(nifDataModelDouble);
        when(nifMapperDouble.dataModelToDomain(nifDataModelDouble)).thenReturn(nifDouble);

        when(studentDataModelDouble.getPhoneNumber()).thenReturn(phoneNumberDataModelDouble);
        when(phoneNumberMapperDouble.dataModelToDomain(phoneNumberDataModelDouble)).thenReturn(phoneNumberDouble);

        when(studentDataModelDouble.getEmail()).thenReturn("harrypotter@gmail.com");

        when(studentDataModelDouble.getAddress()).thenReturn(addressDataModelDouble);
        when(addressMapperDouble.toDomain(addressDataModelDouble)).thenReturn(addressDouble);

        when(studentDataModelDouble.getAcademicEmail()).thenReturn(studentAcademicEmailDataModelDouble);
        when(studentAcademicEmailMapperDouble.dataModelToDomain(studentAcademicEmailDataModelDouble)).thenReturn(studentAcademicEmailDouble);

        when(studentFactoryDouble.newStudentFromDataModel(eq(studentIDDouble), any(Name.class), eq(nifDouble), eq(phoneNumberDouble), any(Email.class), eq(addressDouble), eq(studentAcademicEmailDouble))).thenReturn(studentDouble);

        //act
        Student result = studentMapper.dataModelToDomain(studentDataModelDouble);

        //assert
        assertEquals(result, studentDouble);
    }
}