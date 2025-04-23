package PAI.mapper;

import PAI.VOs.*;
import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.mapper.department.DepartmentIDMapperImpl;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.ProgrammeDataModel;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammeMapperTest {

    @Test
    void shouldMakeANotNullConstructor() {
        //arrange
        ProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        TeacherIDMapper teacherIDMapper = mock(TeacherIDMapper.class);
        DepartmentIDMapperImpl departmentIDMapper = mock(DepartmentIDMapperImpl.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);

        //act
        ProgrammeMapper programmeMapper = new ProgrammeMapper(programmeIDMapper,teacherIDMapper,departmentIDMapper,factory);

        //assert
        assertNotNull(programmeMapper);
    }

    @Test
    void testToData() {
        // Arrange
        Programme programme = mock(Programme.class);

        ProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        TeacherIDMapper teacherIDMapper = mock(TeacherIDMapper.class);
        DepartmentIDMapperImpl departmentIDMapper = mock(DepartmentIDMapperImpl.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        TeacherID progDirectorID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);

        ProgrammeMapper programmeMapper = new ProgrammeMapper(programmeIDMapper,teacherIDMapper,departmentIDMapper,factory);

        when(programme.getProgrammeName()).thenReturn(name);
        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("name");

        when(programme.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("OLA");

        when(programme.getQuantEcts()).thenReturn(quantEcts);
        when(quantEcts.getQuantEcts()).thenReturn(30);

        when(programme.getQuantSemesters()).thenReturn(quantSemesters);
        when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

        when(programme.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getName()).thenReturn(name);
        when(programmeID.getAcronym()).thenReturn(acronym);

        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
        when(degreeTypeID.getDTID()).thenReturn("id");

        when(programme.getDepartment()).thenReturn(departmentID);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("LEI");

        when(programme.getProgrammeDirectorID()).thenReturn(progDirectorID);
        when(progDirectorID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("PTP");

        // Act
        ProgrammeDataModel res = programmeMapper.toData(programme);

        // Assert
        assertNotNull(res);

    }

    @Test
    void testToDomain() {
        // Arrange
        ProgrammeDataModel dataModel = mock(ProgrammeDataModel.class);

        ProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        TeacherIDMapper teacherIDMapper = mock(TeacherIDMapper.class);
        DepartmentIDMapperImpl departmentIDMapper = mock(DepartmentIDMapperImpl.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);

        ProgrammeMapper programmeMapper = new ProgrammeMapper(programmeIDMapper,teacherIDMapper,departmentIDMapper,factory);

        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);

        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID progDirectorID = mock(TeacherID.class);
        Programme programme = mock(Programme.class);

        when(factory.registerProgramme(
                any(NameWithNumbersAndSpecialChars.class),
                any(Acronym.class),
                any(QuantEcts.class),
                any(QuantSemesters.class),
                any(DegreeTypeID.class),
                eq(departmentID),
                eq(progDirectorID)
        )).thenReturn(programme);

        when(teacherIDMapper.toDomain(teacherIDDataModel)).thenReturn(progDirectorID);
        when(departmentIDMapper.toDomainModel(departmentIDDataModel)).thenReturn(departmentID);

        when(dataModel.getName()).thenReturn("name");

        when(dataModel.getAcronym()).thenReturn("OLA");

        when(dataModel.getQuantEcts()).thenReturn(30);

        when(dataModel.getQuantSemesters()).thenReturn(6);

        when(dataModel.getProgID()).thenReturn(programmeIDDataModel);
        when(programmeIDDataModel.getAcronym()).thenReturn("OLA");
        when(programmeIDDataModel.getName()).thenReturn("name");

        when(dataModel.getDegreeTypeID()).thenReturn("id");

        when(dataModel.getDepartmentID()).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn("LEI");

        when(dataModel.getProgrammeDirectorID()).thenReturn(teacherIDDataModel);
        when(teacherIDDataModel.getTeacherAcronym()).thenReturn("PTP");

        // Act
        Programme res = programmeMapper.toDomain(dataModel);

        // Assert
        assertNotNull(res);
    }

}
