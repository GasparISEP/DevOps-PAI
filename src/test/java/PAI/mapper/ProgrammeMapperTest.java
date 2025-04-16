package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.persistence.datamodel.ProgrammeDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammeMapperTest {

    @Test
    void testToData() throws Exception {
        // Arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Engenharia Informática");
        Acronym acronym = new Acronym("ENI");
        QuantSemesters quantSemesters = new QuantSemesters(6);
        QuantEcts quantEcts = new QuantEcts(30);
        DegreeTypeID degreeTypeID = new DegreeTypeID("MSc");
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("DEP"));
        TeacherID directorID = new TeacherID(new TeacherAcronym("DIR"));

        Programme programme = new Programme(
                name, acronym, quantEcts, quantSemesters,
                degreeTypeID, departmentID, directorID
        );

        // Act
        ProgrammeDataModel dataModel = new ProgrammeMapper().toData(programme);

        // Assert
        assertNotNull(dataModel);
        assertEquals("Engenharia Informática", dataModel.getName());
        assertEquals("ENI", dataModel.getAcronym());
        assertEquals(6, dataModel.getQuantSemesters());
        assertEquals(30, dataModel.getQuantEcts());
        assertEquals("MSc", dataModel.getDegreeTypeID());
        assertEquals("DEP", dataModel.getDepartmentID());
        assertEquals("DIR", dataModel.getProgrammeDirectorID());
    }

    @Test
    void testToDomain() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertNotNull(programme);
    }

    @Test
    void testToDomain_Name() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals("Engenharia Informática", programme.getProgrammeName().getnameWithNumbersAndSpecialChars());
    }

    @Test
    void testToDomain_Acronym() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals("ENI", programme.getAcronym().getAcronym());
    }

    @Test
    void testToDomain_QuantSemesters() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals(6, programme.getQuantSemesters().getQuantityOfSemesters());
    }

    @Test
    void testToDomain_QuantEcts() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals(30, programme.getQuantEcts().getQuantEcts());
    }

    @Test
    void testToDomain_DegreeTypeID() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals("MSc", programme.getDegreeTypeID().getDTID());
    }

    @Test
    void testToDomain_DepartmentID() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals("DEP", programme.getDepartment().getAcronym().getAcronym());
    }

    @Test
    void testToDomain_ProgrammeDirectorID() throws Exception {
        // Arrange
        ProgrammeDataModel dataModel = new ProgrammeDataModel(new Programme(
                new NameWithNumbersAndSpecialChars("Engenharia Informática"),
                new Acronym("ENI"),
                new QuantEcts(30),
                new QuantSemesters(6),
                new DegreeTypeID("MSc"),
                new DepartmentID(new DepartmentAcronym("DEP")),
                new TeacherID(new TeacherAcronym("DIR"))
        ));

        // Act
        Programme programme = new ProgrammeMapper().toDomain(dataModel);

        // Assert
        assertEquals("DIR", programme.getProgrammeDirectorID().getTeacherAcronym().getAcronym());
    }
}
