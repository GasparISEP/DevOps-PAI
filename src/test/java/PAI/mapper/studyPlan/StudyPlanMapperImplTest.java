package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.mapper.ProgrammeIDMapper;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

//    @Test
//    void shouldReturnStudyPlanDataModel() {
//
//    }
}