package PAI.mapper.courseInStudyPlan;

import PAI.VOs.*;
import PAI.mapper.courseID.CourseIDMapperImpl;
import PAI.mapper.programme.ProgrammeIDMapperImpl;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CourseInStudyPlanIDMapperImplTest {

    private CourseInStudyPlanIDMapperImpl mapper;

    @BeforeEach
    void setUp() {
        // Instancia os mappers necessários
        mapper = new CourseInStudyPlanIDMapperImpl(
                new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()),
                new CourseIDMapperImpl()
        );
    }

    @Test
    void constructorShouldThrowWhenStudyPlanIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanIDMapperImpl(null, new CourseIDMapperImpl())
        );
        assertEquals("StudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenCourseIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()), null)
        );
        assertEquals("CourseIDMapper cannot be null", ex.getMessage());
    }


    @Test
    void toDataModelShouldMapDomainToDataModel() {
        // Arrange: criação do domínio
        NameWithNumbersAndSpecialChars progName = new NameWithNumbersAndSpecialChars("ProgrammeName");
        Acronym progAcronym = new Acronym("PN");
        ProgrammeID programmeID = new ProgrammeID(progName, progAcronym);
        Date planDate = new Date(LocalDate.of(2025, 4, 18));
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, planDate);

        Name courseName = new Name("CourseName");
        Acronym courseAcr = new Acronym("CCC");
        CourseID courseID = new CourseID(courseAcr, courseName);

        CourseInStudyPlanID domainId = new CourseInStudyPlanID(courseID, studyPlanID);

        // Act
        CourseInStudyPlanIDDataModel dm = mapper.toDataModel(domainId);

        // Assert: verifica StudyPlanIDDataModel
        StudyPlanIDDataModel spDM = dm.getStudyPlanIDDataModel();
        ProgrammeIDDataModel progDM = spDM.getProgrammeID();
        assertEquals("ProgrammeName", progDM.getName());
        assertEquals("PN",           progDM.getAcronym());
        assertEquals(LocalDate.of(2025, 4, 18), spDM.getImplementationDate());

        // Assert: verifica CourseIDDataModel
        CourseIDDataModel cDM = dm.getCourseID();
        assertEquals("CCC",         cDM.getAcronym());
        assertEquals("CourseName",  cDM.getName());
    }

    @Test
    void toDomainShouldMapDataModelToDomain() {
        // Arrange: criação do DataModel
        ProgrammeIDDataModel progDM = new ProgrammeIDDataModel("ProgrammeName", "PN");
        StudyPlanIDDataModel spDM = new StudyPlanIDDataModel(progDM, LocalDate.of(2025, 4, 18));
        CourseIDDataModel cDM = new CourseIDDataModel("CCC", "CourseName");

        CourseInStudyPlanIDDataModel dataModel = new CourseInStudyPlanIDDataModel(spDM, cDM);

        // Act
        CourseInStudyPlanID domainId = mapper.toDomain(dataModel);

        // Assert: verifica StudyPlanID
        StudyPlanID spDomain = domainId.getStudyPlanID();
        ProgrammeID progDomain = spDomain.getProgrammeID();
        assertEquals("ProgrammeName",
                progDomain.getName().getnameWithNumbersAndSpecialChars());
        assertEquals("PN",
                progDomain.getAcronym().getAcronym());
        assertEquals(LocalDate.of(2025, 4, 18),
                spDomain.getLocalDate());

        // Assert: verifica CourseID
        CourseID cDomain = domainId.getCourseID();
        assertEquals("CCC",
                cDomain.getAcronym().getAcronym());
        assertEquals("CourseName",
                cDomain.getName().getName());
    }
}