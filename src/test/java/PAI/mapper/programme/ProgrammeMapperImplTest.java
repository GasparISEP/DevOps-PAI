package PAI.mapper.programme;

import PAI.VOs.*;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.mapper.degreeType.DegreeTypeIDMapper;
import PAI.mapper.department.DepartmentIDMapperImpl;
import PAI.mapper.teacher.TeacherIDMapperImpl;
import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgrammeMapperImplTest {

    @Test
    void shouldMakeANotNullConstructor() {
        //arrange
        ProgrammeIDMapperImpl programmeIDMapper = mock(ProgrammeIDMapperImpl.class);
        TeacherIDMapperImpl teacherIDMapper = mock(TeacherIDMapperImpl.class);
        DepartmentIDMapperImpl departmentIDMapper = mock(DepartmentIDMapperImpl.class);
        DegreeTypeIDMapper degreeTypeIDMapper = mock(DegreeTypeIDMapper.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);

        //act
        ProgrammeMapperImpl programmeMapper = new ProgrammeMapperImpl(programmeIDMapper,teacherIDMapper,departmentIDMapper,degreeTypeIDMapper,factory);

        //assert
        assertNotNull(programmeMapper);
    }

    @Test
    void testToData() {
        // Arrange
        Programme programme = mock(Programme.class);

        ProgrammeIDMapperImpl programmeIDMapper = mock(ProgrammeIDMapperImpl.class);
        TeacherIDMapperImpl teacherIDMapper = mock(TeacherIDMapperImpl.class);
        DepartmentIDMapperImpl departmentIDMapper = mock(DepartmentIDMapperImpl.class);
        DegreeTypeIDMapper degreeTypeIDMapper = mock(DegreeTypeIDMapper.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        MaxEcts maxEcts = mock(MaxEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        TeacherID progDirectorID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);

        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);

        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        ProgrammeMapperImpl programmeMapper = new ProgrammeMapperImpl(programmeIDMapper,teacherIDMapper,departmentIDMapper,degreeTypeIDMapper,factory);

        when(programme.getProgrammeName()).thenReturn(name);
        when(name.getNameWithNumbersAndSpecialChars()).thenReturn("name");

        when(programme.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("OLA");

        when(programme.getMaxEcts()).thenReturn(maxEcts);
        when(maxEcts.getMaxEcts()).thenReturn(30);

        when(programme.getQuantSemesters()).thenReturn(quantSemesters);
        when(quantSemesters.getQuantityOfSemesters()).thenReturn(6);

        when(programme.getProgrammeID()).thenReturn(programmeID);
        when(name.getNameWithNumbersAndSpecialChars()).thenReturn("name");
        when(programmeID.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("OLA");

        when(degreeTypeIDMapper.toDataModel(degreeTypeID)).thenReturn(degreeTypeIDDataModel);
        when(degreeTypeIDDataModel.getDegreeTypeID()).thenReturn("id");

        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);
        when(degreeTypeID.getDTID()).thenReturn("id");

        when(departmentIDMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn("id");

        when(programme.getDepartment()).thenReturn(departmentID);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("LEI");

        when(teacherIDMapper.toDataModel(progDirectorID)).thenReturn(teacherIDDataModel);
        when(teacherIDDataModel.getTeacherAcronym()).thenReturn("PSN");

        when(programmeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);
        when(programmeIDDataModel.getAcronym()).thenReturn("PSN");

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

        ProgrammeIDMapperImpl programmeIDMapper = mock(ProgrammeIDMapperImpl.class);
        TeacherIDMapperImpl teacherIDMapper = mock(TeacherIDMapperImpl.class);
        DepartmentIDMapperImpl departmentIDMapper = mock(DepartmentIDMapperImpl.class);
        DegreeTypeIDMapper degreeTypeIDMapper = mock(DegreeTypeIDMapper.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);

        ProgrammeMapperImpl programmeMapper = new ProgrammeMapperImpl(programmeIDMapper,teacherIDMapper,departmentIDMapper,degreeTypeIDMapper,factory);

        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);

        DegreeTypeIDDataModel degreeTypeIDDataModel = mock(DegreeTypeIDDataModel.class);

        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID progDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme programme = mock(Programme.class);

        when(factory.reregisterProgramme(
                any(NameWithNumbersAndSpecialChars.class),
                any(Acronym.class),
                any(MaxEcts.class),
                any(QuantSemesters.class),
                eq(degreeTypeID),
                eq(departmentID),
                eq(progDirectorID),
                eq(programmeID)
        )).thenReturn(programme);

        when(teacherIDMapper.toDomain(teacherIDDataModel)).thenReturn(progDirectorID);
        when(departmentIDMapper.toDomainModel(departmentIDDataModel)).thenReturn(departmentID);
        when(degreeTypeIDMapper.toDomain(degreeTypeIDDataModel)).thenReturn(degreeTypeID);
        when(programmeIDMapper.toDomain(programmeIDDataModel)).thenReturn(programmeID);

        when(dataModel.getName()).thenReturn("name");

        when(dataModel.getAcronym()).thenReturn("OLA");

        when(dataModel.getMaxEcts()).thenReturn(30);

        when(dataModel.getQuantSemesters()).thenReturn(6);

        when(dataModel.getProgID()).thenReturn(programmeIDDataModel);
        when(programmeIDDataModel.getAcronym()).thenReturn("OLA");

        when(dataModel.getDegreeTypeID()).thenReturn(degreeTypeIDDataModel);
        when(degreeTypeIDDataModel.getDegreeTypeID()).thenReturn("id");

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
