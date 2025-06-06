package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudyPlanIDMapperImplTest {

    @Test
    void shouldCreateSPIDDataModel() {
        //arrange
        Acronym acronym = mock(Acronym.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        IStudyPlanIDMapper studyPlanIDMapperImpl = new StudyPlanIDMapperImpl(programmeIDMapper);
        LocalDate localDate = mock(LocalDate.class);

        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getAcronym()).thenReturn(acronym);
        when(studyPlanID.getLocalDate()).thenReturn(localDate);

        //act
        StudyPlanIDDataModel studyPlanIDDataModel = studyPlanIDMapperImpl.toDataModel(studyPlanID);

        //assert
        assertNotNull(studyPlanIDDataModel);
    }

    @Test
    void shouldCreateSPIDDomain() {
        //arrange
        String acronym = "GAS";
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        IStudyPlanIDMapper studyPlanIDMapperImpl = new StudyPlanIDMapperImpl(programmeIDMapper);
        LocalDate date = mock(LocalDate.class);

        when(studyPlanIDDataModel.getProgrammeID()).thenReturn(programmeIDDataModel);
        when(programmeIDDataModel.getAcronym()).thenReturn(acronym);
        when(studyPlanIDDataModel.getImplementationDate()).thenReturn(date);

        //act
        StudyPlanID studyPlanID = studyPlanIDMapperImpl.toDomain(studyPlanIDDataModel);

        //assert
        assertNotNull(studyPlanID);
    }

    @Test
    void constructorShouldThrowWhenProgrammeIDMapperIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new StudyPlanIDMapperImpl(null));
    }
}