package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.mapper.programme.ProgrammeIDMapperImpl;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanMapperImplTest {

    @Test
    void shouldCreateStudyPlanMapperImpl() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = mock(IStudyPlanIDMapper.class);
        IStudyPlanFactory studyPlanFactory = mock(IStudyPlanFactory.class);
        //act
        StudyPlanMapperImpl result = new StudyPlanMapperImpl(studyPlanIDMapper, studyPlanFactory);
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldNotCreateStudyPlanMapperImplWithNullSPIDMAPPER() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = null;
        IStudyPlanFactory studyPlanFactory = mock(IStudyPlanFactory.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanMapperImpl(studyPlanIDMapper, studyPlanFactory);
        });
    }

    @Test
    void shouldNotCreateStudyPlanMapperImplWithNullSPFACTORY() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = mock(IStudyPlanIDMapper.class);
        IStudyPlanFactory studyPlanFactory = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanMapperImpl(studyPlanIDMapper, studyPlanFactory);
        });
    }

    @Test
    void shouldReturnStudyPlanDataModel() throws Exception {
        //arrange
        Acronym progacronym = new Acronym("PRO");
        ProgrammeID progID = new ProgrammeID(progacronym);

        Date date = mock(Date.class);
        DurationInYears durationInYears = new DurationInYears(4);
        MaxEcts maxEcts = new MaxEcts(30);
        StudyPlanID spID = new StudyPlanID(progID, date);
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(UUID.randomUUID());

        StudyPlan studyPlan = new StudyPlan(progID, date, durationInYears, maxEcts, spID, generatedID);

        ProgrammeIDMapperImpl progIDMapper = new ProgrammeIDMapperImpl();
        IStudyPlanIDMapper spIDmapper = new StudyPlanIDMapperImpl(progIDMapper);
        StudyPlanFactoryImpl spFac = new StudyPlanFactoryImpl();
        StudyPlanMapperImpl spMapper = new StudyPlanMapperImpl(spIDmapper, spFac);
        //act
        StudyPlanDataModel result = spMapper.toDataModel(studyPlan);
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnDomainStudyPlan() throws Exception {
        // Arrange
        ProgrammeIDDataModel progIDdm = new ProgrammeIDDataModel("PRO");
        LocalDate implementationDate = mock(LocalDate.class);
        StudyPlanIDDataModel spIDdm = new StudyPlanIDDataModel(progIDdm, implementationDate);
        MaxEcts maxEcts = new MaxEcts(30);
        DurationInYears durationInYears = new DurationInYears(8);

        StudyPlanDataModel spDM = new StudyPlanDataModel(spIDdm, UUID.randomUUID(), maxEcts, durationInYears);

        ProgrammeIDMapperImpl progIDMapper = new ProgrammeIDMapperImpl();
        IStudyPlanIDMapper spIDmapper = new StudyPlanIDMapperImpl(progIDMapper);
        StudyPlanFactoryImpl spFac = new StudyPlanFactoryImpl();
        StudyPlanMapperImpl spMapper = new StudyPlanMapperImpl(spIDmapper, spFac);

        // Act
        StudyPlan result = spMapper.toDomain(spDM);

        // Assert
        assertNotNull(result);
    }
}