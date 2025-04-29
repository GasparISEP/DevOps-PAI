package PAI.mapper.department;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.Teacher;
import PAI.factory.DepartmentFactoryImpl;
import PAI.factory.IDepartmentFactory;
import PAI.persistence.datamodel.TeacherIDDataModel;
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
    void toDataModelWithDirectorID() {
        // Arrange
        String acronym = "ABC";
        String name = "Department";
        String teacherAcronym = "TCH";

        DepartmentAcronym departmentAcronymMock = mock(DepartmentAcronym.class);
        Name nameMock = mock(Name.class);
        TeacherAcronym teacherAcronymMock = mock(TeacherAcronym.class);
        TeacherID teacherIDMock = mock(TeacherID.class);

        when(departmentAcronymMock.getAcronym()).thenReturn(acronym);
        when(nameMock.getName()).thenReturn(name);
        when(teacherAcronymMock.getAcronym()).thenReturn(teacherAcronym);
        when(teacherIDMock.getTeacherAcronym()).thenReturn(teacherAcronymMock);

        Department departmentMock = mock(Department.class);
        when(departmentMock.getAcronym()).thenReturn(departmentAcronymMock);
        when(departmentMock.getName()).thenReturn(nameMock);
        when(departmentMock.getDirectorID()).thenReturn(teacherIDMock);

        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);

        // Act
        DepartmentDataModel departmentDataModel = departmentMapper.toDataModel(departmentMock);

        // Assert
        assertNotNull(departmentDataModel);
        assertEquals(acronym, departmentDataModel.getAcronym());
        assertEquals(name, departmentDataModel.getName());
        assertNotNull(departmentDataModel.getDirectorId());
        assertEquals(teacherAcronym, departmentDataModel.getDirectorId().getTeacherAcronym());
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
    void toDomainWithTeacherID() throws Exception {
        // Arrange
        String acronym = "ABC";
        String name = "Department";
        String acronymTeacher = "ACV";

        DepartmentAcronym expectedAcronym = new DepartmentAcronym(acronym);
        Name expectedName = new Name(name);
        TeacherAcronym teacherAcronym = new TeacherAcronym(acronymTeacher);
        TeacherID expectedTeacherID = new TeacherID(teacherAcronym);

        DepartmentDataModel departmentDataModelMock = mock(DepartmentDataModel.class);
        when(departmentDataModelMock.getAcronym()).thenReturn(acronym);
        when(departmentDataModelMock.getName()).thenReturn(name);

        TeacherIDDataModel teacherIDDataModelMock = mock(TeacherIDDataModel.class);
        when(teacherIDDataModelMock.getTeacherAcronym()).thenReturn(acronymTeacher);
        when(departmentDataModelMock.getDirectorId()).thenReturn(teacherIDDataModelMock);

        Department expectedDepartment = mock(Department.class);
        when(expectedDepartment.getAcronym()).thenReturn(expectedAcronym);
        when(expectedDepartment.getName()).thenReturn(expectedName);
        when(expectedDepartment.getDirectorID()).thenReturn(expectedTeacherID);

        IDepartmentFactory departmentFactoryMock = mock(IDepartmentFactory.class);
        when(departmentFactoryMock.newDepartment(expectedAcronym, expectedName, expectedTeacherID))
                .thenReturn(expectedDepartment);

        DepartmentMapperImpl departmentMapper = new DepartmentMapperImpl(departmentFactoryMock);

        // Act
        Department department = departmentMapper.toDomain(departmentDataModelMock);

        // Assert
        assertNotNull(department);
        assertEquals(acronym, department.getAcronym().getAcronym());
        assertEquals(name, department.getName().getName());
        assertEquals(acronymTeacher, department.getDirectorID().getTeacherAcronym().getAcronym());
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