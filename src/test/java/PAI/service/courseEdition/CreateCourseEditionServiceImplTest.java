package PAI.service.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;
import PAI.domain.studyPlan.StudyPlan;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateCourseEditionServiceImplTest {

    @Test
    public void testCreateAndSaveCourseEdition_Success() throws Exception {
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IDegreeTypeRepository degreeTypeRepository = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepository = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                factory, courseEditionRepository, degreeTypeRepository,
                programmeRepository, studyPlanRepository,
                courseInStudyPlanRepository, programmeEditionRepository
        );

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(factory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(false);
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);

        CourseEdition result = service.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        assertNotNull(result);
        verify(courseEditionRepository).save(courseEdition);
    }

    @Test
    public void testCreateAndSaveCourseEdition_Duplicate_ReturnsNull() throws Exception {
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IDegreeTypeRepository degreeTypeRepository = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepository = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                factory, courseEditionRepository, degreeTypeRepository,
                programmeRepository, studyPlanRepository,
                courseInStudyPlanRepository, programmeEditionRepository
        );

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(factory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);
        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(true);

        CourseEdition result = service.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

        assertNull(result);
    }

    @Test
    public void testGetAllDegreeTypes() {
        IDegreeTypeRepository degreeTypeRepository = mock(IDegreeTypeRepository.class);
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepository = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);

        DegreeType degreeType = mock(DegreeType.class);
        when(degreeTypeRepository.getAllDegreeTypes()).thenReturn(List.of(degreeType));

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                factory, courseEditionRepository, degreeTypeRepository,
                programmeRepository, studyPlanRepository,
                courseInStudyPlanRepository, programmeEditionRepository
        );

        List<DegreeType> result = service.getAllDegreeTypes();

        assertEquals(1, result.size());
    }

    @Test
    public void testGetProgrammesByDegreeTypeID() {
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IDegreeTypeRepository degreeTypeRepository = mock(IDegreeTypeRepository.class);
        IStudyPlanRepository studyPlanRepository = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);

        when(programmeRepository.findAll()).thenReturn(List.of(programme1, programme2));
        when(programme1.hasThisDegreeTypeID(degreeTypeID)).thenReturn(true);
        when(programme2.hasThisDegreeTypeID(degreeTypeID)).thenReturn(false);

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                factory, courseEditionRepository, degreeTypeRepository,
                programmeRepository, studyPlanRepository,
                courseInStudyPlanRepository, programmeEditionRepository
        );

        List<Programme> result = service.getProgrammesByDegreeTypeID(degreeTypeID);

        assertEquals(1, result.size());
        assertTrue(result.contains(programme1));
    }

    @Test
    public void testGetStudyPlansByProgrammeID() {
        IStudyPlanRepository studyPlanRepository = mock(IStudyPlanRepository.class);
        ICourseEditionFactory factory = mock(ICourseEditionFactory.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IDegreeTypeRepository degreeTypeRepository = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        StudyPlan studyPlan = mock(StudyPlan.class);

        when(studyPlan.identity()).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(studyPlanRepository.findAll()).thenReturn(List.of(studyPlan));

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                factory, courseEditionRepository, degreeTypeRepository,
                programmeRepository, studyPlanRepository,
                courseInStudyPlanRepository, programmeEditionRepository
        );

        List<StudyPlan> result = service.getStudyPlansByProgrammeID(programmeID);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetLatestStudyPlanIDByProgrammeID_ThrowsIfEmpty() {
        IStudyPlanRepository studyPlanRepository = mock(IStudyPlanRepository.class);
        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                mock(ICourseEditionFactory.class), mock(ICourseEditionRepository.class),
                mock(IDegreeTypeRepository.class), mock(IProgrammeRepository.class),
                studyPlanRepository, mock(ICourseInStudyPlanRepository.class),
                mock(IProgrammeEditionRepository.class)
        );

        when(studyPlanRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> {
            service.getLatestStudyPlanIDByProgrammeID(mock(ProgrammeID.class));
        });
    }

    @Test
    public void testGetCoursesByStudyPlanId() throws Exception {
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlan course = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID courseID = mock(CourseInStudyPlanID.class);

        when(course.identity()).thenReturn(courseID);
        when(courseID.getStudyPlanID()).thenReturn(studyPlanID);
        when(courseInStudyPlanRepository.findAll()).thenReturn(List.of(course));

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                mock(ICourseEditionFactory.class), mock(ICourseEditionRepository.class),
                mock(IDegreeTypeRepository.class), mock(IProgrammeRepository.class),
                mock(IStudyPlanRepository.class), courseInStudyPlanRepository,
                mock(IProgrammeEditionRepository.class)
        );

        List<CourseInStudyPlan> result = service.getCoursesByStudyPlanId(studyPlanID);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetProgrammeEditionsByProgrammeID() throws Exception {
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEdition edition = mock(ProgrammeEdition.class);

        when(programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID)).thenReturn(List.of(edition));

        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                mock(ICourseEditionFactory.class), mock(ICourseEditionRepository.class),
                mock(IDegreeTypeRepository.class), mock(IProgrammeRepository.class),
                mock(IStudyPlanRepository.class), mock(ICourseInStudyPlanRepository.class),
                programmeEditionRepository
        );

        List<ProgrammeEdition> result = service.getProgrammeEditionsByProgrammeID(programmeID);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetProgrammeEditionsByProgrammeID_Null_ReturnsEmptyList() throws Exception {
        CreateCourseEditionServiceImpl service = new CreateCourseEditionServiceImpl(
                mock(ICourseEditionFactory.class), mock(ICourseEditionRepository.class),
                mock(IDegreeTypeRepository.class), mock(IProgrammeRepository.class),
                mock(IStudyPlanRepository.class), mock(ICourseInStudyPlanRepository.class),
                mock(IProgrammeEditionRepository.class)
        );

        List<ProgrammeEdition> result = service.getProgrammeEditionsByProgrammeID(null);

        assertTrue(result.isEmpty());
    }
}
