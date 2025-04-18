package PAI.mapper.courseInStudyPlan;

import PAI.VOs.*;
import PAI.mapper.CourseID.CourseIDMapperImpl;
import PAI.mapper.courseInStudyPlanID.CourseInStudyPlanIDMapperImpl;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.CourseIDDataModel;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CourseInStudyPlanIDMapperImplTest {

    @Test
    void toDataModel_deveMapearDominioParaDataModel() {
        // Arrange: criação do domínio
        NameWithNumbersAndSpecialChars nameProg = new NameWithNumbersAndSpecialChars("ProgrammeName");
        Acronym acronymProg = new Acronym("PN");
        ProgrammeID programmeID = new ProgrammeID(nameProg, acronymProg);
        Date date = new Date(LocalDate.of(2025, 4, 18));
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, date);

        Name nameCourse = new Name("CourseName");
        Acronym acronymCourse = new Acronym("CCC");
        CourseID courseID = new CourseID(acronymCourse, nameCourse);

        CourseInStudyPlanID domain = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseInStudyPlanIDMapperImpl mapper = new CourseInStudyPlanIDMapperImpl();

        // Act
        CourseInStudyPlanIDDataModel dm = mapper.toDataModel(domain);

        // Assert: verifica StudyPlanID
        StudyPlanIDDataModel spDM = dm.getStudyPlanIDDataModel();
        ProgrammeIDDataModel progDM = spDM.getProgrammeID();
        assertEquals("ProgrammeName", progDM.getName());
        assertEquals("PN", progDM.getAcronym());
        assertEquals(LocalDate.of(2025, 4, 18), spDM.getImplementationDate());

        // Assert: verifica CourseID
        CourseIDDataModel cDM = dm.getCourseID();
        assertEquals("CCC", cDM.getAcronym());
        assertEquals("CourseName", cDM.getName());
    }


    @Test
    void toDomain_deveMapearDataModelParaDominio() {
        // Arrange: criação do datamodel
        ProgrammeIDDataModel progDM = new ProgrammeIDDataModel("ProgrammeName", "PN");
        StudyPlanIDDataModel spDM = new StudyPlanIDDataModel(progDM, LocalDate.of(2025, 4, 18));
        CourseIDDataModel cDM = new CourseIDDataModel("CCC", "CourseName");

        CourseInStudyPlanIDDataModel dataModel = new CourseInStudyPlanIDDataModel(spDM, cDM);
        CourseInStudyPlanIDMapperImpl mapper = new CourseInStudyPlanIDMapperImpl();

        // Act
        CourseInStudyPlanID domain = mapper.toDomain(dataModel);

        // Assert: verifica StudyPlanID
        StudyPlanID spDomain = domain.getStudyPlanID();
        ProgrammeID progDomain = spDomain.getProgrammeID();
        assertEquals("ProgrammeName", progDomain.getName().getnameWithNumbersAndSpecialChars());
        assertEquals("PN", progDomain.getAcronym().getAcronym());
        assertEquals(LocalDate.of(2025, 4, 18), spDomain.getLocalDate());

        // Assert: verifica CourseID
        CourseID cDomain = domain.getCourseID();
        assertEquals("CCC", cDomain.getAcronym().getAcronym());
        assertEquals("CourseName", cDomain.getName().getName());
    }

}