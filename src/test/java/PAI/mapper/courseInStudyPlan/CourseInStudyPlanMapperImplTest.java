package PAI.mapper.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanFactoryImpl;
import PAI.mapper.CourseID.ICourseIDMapper;
import PAI.mapper.CourseID.CourseIDMapperImpl;
import PAI.mapper.ProgrammeIDMapper;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanMapperImplTest {

    private ICourseInStudyPlanMapper mapper;
    private ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper;
    private ICourseIDMapper courseIDMapper;
    private IStudyPlanIDMapper studyPlanIDMapper;
    private ICourseInStudyPlanFactory courseInStudyPlanFactory;

    @BeforeEach
    void setUp() throws Exception {
        courseIDMapper = new CourseIDMapperImpl();
        studyPlanIDMapper = new StudyPlanIDMapperImpl(new ProgrammeIDMapper());
        // Passar sempre o StudyPlanIDMapper e o CourseIDMapper ao construtor
        courseInStudyPlanIDMapper = new CourseInStudyPlanIDMapperImpl(
                studyPlanIDMapper,
                courseIDMapper
        );
        courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        mapper = new CourseInStudyPlanMapperImpl(
                courseIDMapper,
                studyPlanIDMapper,
                courseInStudyPlanIDMapper,
                courseInStudyPlanFactory
        );
    }

    @Test
    void constructorShouldThrowWhenStudyPlanIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanMapperImpl(
                        new CourseIDMapperImpl(),
                        null,
                        new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapper()), new CourseIDMapperImpl()),
                        new CourseInStudyPlanFactoryImpl()
                )
        );
        assertEquals("StudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenCourseIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanMapperImpl(
                        null,
                        new StudyPlanIDMapperImpl(new ProgrammeIDMapper()),
                        new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapper()), new CourseIDMapperImpl()),
                        new CourseInStudyPlanFactoryImpl()
                )
        );
        assertEquals("CourseIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenCourseInStudyPlanIDMapperIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanMapperImpl(
                        new CourseIDMapperImpl(),
                        new StudyPlanIDMapperImpl(new ProgrammeIDMapper()),
                        null,
                        new CourseInStudyPlanFactoryImpl()
                )
        );
        assertEquals("CourseInStudyPlanIDMapper cannot be null", ex.getMessage());
    }

    @Test
    void constructorShouldThrowWhenFactoryIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanMapperImpl(
                        new CourseIDMapperImpl(),
                        new StudyPlanIDMapperImpl(new ProgrammeIDMapper()),
                        new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapper()), new CourseIDMapperImpl()),
                        null
                )
        );
        assertEquals("CourseInStudyPlanFactory cannot be null", ex.getMessage());
    }

    @Test
    void toDataModelShouldMapDomainToDataModel() {
        // Arrange
        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("ProgrammeName"),
                new Acronym("PN")
        );
        Date date = new Date("12-03-2005");
        CourseID courseIDValueObject = new CourseID(
                new Acronym("ACR"),
                new Name("NAME")
        );
        StudyPlanID studyPlanIDValueObject = new StudyPlanID(programmeID, date);
        Semester semesterVO = new Semester(2);
        CurricularYear yearVO = new CurricularYear(3);

        CourseInStudyPlan domain = courseInStudyPlanFactory
                .newCourseInStudyPlan(semesterVO, yearVO, courseIDValueObject, studyPlanIDValueObject);

        // Act
        CourseInStudyPlanDataModel dataModel = mapper.toDataModel(domain);

        // Assert
        assertNotNull(dataModel);

        // ID composto
        CourseInStudyPlanIDDataModel expectedIdDM =
                courseInStudyPlanIDMapper.toDataModel(domain.identity());
        assertEquals(expectedIdDM, dataModel.getCourseInStudyPlanIDDataModel());

        // Semestre e ano curricular
        assertEquals(semesterVO.toInt(), dataModel.getSemester());
        assertEquals(yearVO.toInt(), dataModel.getCurricularYear());

        // Mapeamento dos VOs internos
        CourseIDDataModel expectedCourseDM = courseIDMapper.toDataModel(domain.getCourseID());
        StudyPlanIDDataModel expectedStudyPlanDM = studyPlanIDMapper.toDataModel(domain.getStudyplanID());
        assertEquals(expectedCourseDM,   dataModel.getCourseIDDataModel());
        assertEquals(expectedStudyPlanDM, dataModel.getStudyPlanIDDataModel());
    }

    @Test
    void toDomainShouldMapDataModelToDomain() {
        // Arrange: criar VOs e DataModels
        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("ProgrammeName"),
                new Acronym("PN")
        );
        Date date = new Date("12-03-2005");
        CourseID courseIDValueObject = new CourseID(
                new Acronym("ACR"),
                new Name("NAME")
        );
        StudyPlanID studyPlanIDValueObject = new StudyPlanID(programmeID, date);
        Semester semesterVO = new Semester(2);
        CurricularYear yearVO = new CurricularYear(3);

        CourseIDDataModel courseIDDataModel = courseIDMapper.toDataModel(courseIDValueObject);
        StudyPlanIDDataModel studyPlanIDDataModel = studyPlanIDMapper.toDataModel(studyPlanIDValueObject);
        CourseInStudyPlanIDDataModel compositeIDDataModel =
                courseInStudyPlanIDMapper.toDataModel(
                        courseInStudyPlanFactory
                                .newCourseInStudyPlan(semesterVO, yearVO, courseIDValueObject, studyPlanIDValueObject)
                                .identity()
                );

        CourseInStudyPlanDataModel dataModel = new CourseInStudyPlanDataModel(
                compositeIDDataModel,
                studyPlanIDDataModel,
                courseIDDataModel,
                semesterVO.toInt(),
                yearVO.toInt()
        );

        // Act
        CourseInStudyPlan domain = mapper.toDomain(dataModel);

        // Assert: valores simples
        assertNotNull(domain);
        assertEquals(semesterVO,               domain.getSemester());
        assertEquals(yearVO,                   domain.getCurricularYear());
        assertEquals(courseIDValueObject,      domain.getCourseID());
        assertEquals(studyPlanIDValueObject,   domain.getStudyplanID());

        // Assert: identidade composta
        assertEquals(courseIDValueObject,     domain.identity().getCourseID());
        assertEquals(studyPlanIDValueObject,  domain.identity().getStudyPlanID());
    }
}