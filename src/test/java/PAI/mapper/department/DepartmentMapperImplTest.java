package PAI.mapper.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.domain.Department;
import PAI.factory.DepartmentFactoryImpl;
import PAI.factory.IDepartmentFactory;
import PAI.persistence.datamodel.department.DepartmentDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentMapperImplTest {
    @Test
    void ShouldReturnValidDepartmentMapperConstructor(){
        //arrange
        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);
        //assert + act
        assertNotNull(departmentMapper);
    }
    @Test
    void toDataModel(){
        //arrange
        String acronym = "ABC";
        String name = "Department";

        DepartmentAcronym departmentAcronymMock = mock(DepartmentAcronym.class);
        Name nameMock = mock(Name.class);

        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);
        Department departmentMock = mock(Department.class);

        when(departmentAcronymMock.getAcronym()).thenReturn(acronym);
        when(nameMock.getName()).thenReturn(name);
        when(departmentMock.getAcronym()).thenReturn(departmentAcronymMock);
        when(departmentMock.getName()).thenReturn(nameMock);

        //act
        DepartmentDataModel departmentDataModel =departmentMapper.toDataModel(departmentMock) ;
        //assert
        assertNotNull(departmentDataModel);
        assertEquals(acronym,departmentDataModel.getAcronym());
        assertEquals(name,departmentDataModel.getName());
    }
    @Test
    void toDomain() throws Exception {
        // Arrange
        String acronym = "ABC";
        String name = "Department";

        DepartmentAcronym expectedAcronym = new DepartmentAcronym(acronym);
        Name expectedName = new Name(name);

        DepartmentDataModel departmentDataModelMock = mock(DepartmentDataModel.class);
        when(departmentDataModelMock.getAcronym()).thenReturn(acronym);
        when(departmentDataModelMock.getName()).thenReturn(name);

        Department expectedDepartment = mock(Department.class);
        when(expectedDepartment.getAcronym()).thenReturn(expectedAcronym);
        when(expectedDepartment.getName()).thenReturn(expectedName);

        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        when(departmentFactoryMock.newDepartment(expectedAcronym, expectedName))
                .thenReturn(expectedDepartment);

        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);

        // Act
        Department department = departmentMapper.toDomain(departmentDataModelMock);

        // Assert
        assertNotNull(department);
        assertEquals(acronym, department.getAcronym().getAcronym());
        assertEquals(name, department.getName().getName());
    }
    @Test
    void ShouldThrowAnExceptionWhenTheDepartmentIsNull(){
        //arrange
        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> departmentMapper.toDataModel(null));
        assertEquals("department Cannot be null", exception.getMessage());
    }
    @Test
    void ShouldThrowAnExceptionWhenTheDepartmentDataModelIsNull(){
        //arrange
        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> departmentMapper.toDomain(null));
        assertEquals("departmentDataModel Cannot be null", exception.getMessage());
    }
}