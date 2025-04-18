package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
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
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        IStudyPlanIDMapper studyPlanIDMapperImpl = new StudyPlanIDMapperImpl();
        LocalDate localDate = mock(LocalDate.class);

        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getName()).thenReturn(name);
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
        String name = "GASPAR";
        String acronym = "GAS";
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        IStudyPlanIDMapper studyPlanIDMapperImpl = new StudyPlanIDMapperImpl();
        LocalDate date = mock(LocalDate.class);

        when(studyPlanIDDataModel.getProgrammeID()).thenReturn(programmeIDDataModel);
        when(programmeIDDataModel.getName()).thenReturn(name);
        when(programmeIDDataModel.getAcronym()).thenReturn(acronym);
        when(studyPlanIDDataModel.getImplementationDate()).thenReturn(date);

        //act
        StudyPlanID studyPlanID = studyPlanIDMapperImpl.toDomain(studyPlanIDDataModel);

        //assert
        assertNotNull(studyPlanID);
    }
}