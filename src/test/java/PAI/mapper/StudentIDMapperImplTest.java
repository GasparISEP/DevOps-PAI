package PAI.mapper;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentIDMapperImplTest {

    @Test
    void shouldReturnDataModelFromDomain() {
        //arrange
        StudentIDMapperImpl studentIDMapperImpl = new StudentIDMapperImpl();
        StudentID studentID = mock(StudentID.class);

        when(studentID.getUniqueNumber()).thenReturn(1000001);

        //act
        StudentIDDataModel result = studentIDMapperImpl.domainToDataModel(studentID);

        //assert
        assertEquals(1000001, result.getUniqueNumber());
    }

    @Test
    void shouldReturnDomainFromDataModel() {
        //arrange
        StudentIDMapperImpl studentIDMapperImpl = new StudentIDMapperImpl();
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        when(studentIDDataModel.getUniqueNumber()).thenReturn(1000001);

        //act
        StudentID result = studentIDMapperImpl.dataModelToDomain(studentIDDataModel);

        //assert
        assertEquals(1000001, result.getUniqueNumber());
    }

}