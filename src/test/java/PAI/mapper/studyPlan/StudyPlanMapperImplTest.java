package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.ProgrammeIDMapper;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanMapperImplTest {

    @Test
    void shouldCreateStudyPlanMapperImpl() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = mock(IStudyPlanIDMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        IStudyPlanFactory studyPlanFactory = mock(IStudyPlanFactory.class);
        //act
        StudyPlanMapperImpl result = new StudyPlanMapperImpl(studyPlanIDMapper, programmeIDMapper, studyPlanFactory);
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldNotCreateStudyPlanMapperImplWithNullSPIDMAPPER() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = null;
        IProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        IStudyPlanFactory studyPlanFactory = mock(IStudyPlanFactory.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanMapperImpl(studyPlanIDMapper, programmeIDMapper, studyPlanFactory);
        });
    }

    @Test
    void shouldNotCreateStudyPlanMapperImplWithNullPROGIDMAPPER() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = mock(StudyPlanIDMapperImpl.class);
        IProgrammeIDMapper programmeIDMapper = null;
        IStudyPlanFactory studyPlanFactory = mock(IStudyPlanFactory.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanMapperImpl(studyPlanIDMapper, programmeIDMapper, studyPlanFactory);
        });
    }

    @Test
    void shouldNotCreateStudyPlanMapperImplWithNullSPFACTORY() throws IllegalArgumentException {
        //arrange
        IStudyPlanIDMapper studyPlanIDMapper = mock(IStudyPlanIDMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        IStudyPlanFactory studyPlanFactory = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new StudyPlanMapperImpl(studyPlanIDMapper, programmeIDMapper, studyPlanFactory);
        });
    }

    @Test
    void shouldReturnStudyPlanDataModel() throws Exception {
        //arrange
        NameWithNumbersAndSpecialChars progname = new NameWithNumbersAndSpecialChars("Programme");
        Acronym progacronym = new Acronym("PRO");
        ProgrammeID progID = new ProgrammeID(progname, progacronym);

        Date date = mock(Date.class);
        DurationInYears durationInYears = new DurationInYears(4);
        MaxEcts maxEcts = new MaxEcts(30);
        StudyPlanID spID = new StudyPlanID(progID, date);
        StudyPlan studyPlan = new StudyPlan(progID, date, durationInYears, maxEcts);

        ProgrammeIDMapper progIDMapper = new ProgrammeIDMapper();
        IStudyPlanIDMapper spIDmapper = new StudyPlanIDMapperImpl(progIDMapper);
        IProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        IStudyPlanFactory spFac = new StudyPlanFactoryImpl();

        IStudyPlanMapper spMapper = new StudyPlanMapperImpl(spIDmapper,  programmeIDMapper, spFac);

        //act
        StudyPlanDataModel result = spMapper.toDataModel(studyPlan);
        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnDomainStudyPlan() throws Exception {
        ProgrammeIDDataModel progIDdm = new ProgrammeIDDataModel("Programme", "PRO");
        LocalDate implementationdate = mock(LocalDate.class);
        StudyPlanIDDataModel spIDdm = new StudyPlanIDDataModel(progIDdm, implementationdate);
        MaxEcts maxEcts = new MaxEcts(30);
        DurationInYears durationInYears = new DurationInYears(8);

        StudyPlanDataModel spDM = new StudyPlanDataModel(spIDdm, maxEcts, durationInYears);

        ProgrammeIDMapper progIDMapper = new ProgrammeIDMapper();
        IStudyPlanIDMapper spIDmapper = new StudyPlanIDMapperImpl(progIDMapper);
        IProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapper.class);
        IStudyPlanFactory spFac = new StudyPlanFactoryImpl();
        StudyPlanMapperImpl spMapper = new StudyPlanMapperImpl(spIDmapper, programmeIDMapper, spFac);

        StudyPlan result = spMapper.toDomain(spDM);

        assertNotNull(result);
    }
}