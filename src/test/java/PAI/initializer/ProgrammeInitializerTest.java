package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US11_RegisterProgrammeInTheSystemController;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class ProgrammeInitializerTest {

    @Mock
    private US11_RegisterProgrammeInTheSystemController controller;

    @Mock
    private IDegreeTypeRepository degreeTypeRepository;

    @Mock
    private DegreeType bachelorDegreeType;

    @Mock
    private DegreeType masterDegreeType;

    @Mock
    private DegreeType integratedMasterDegreeType;

    @InjectMocks
    private ProgrammeInitializer initializer;

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/ProgrammeData.csv";

        // Mock DegreeType names and ids
        when(bachelorDegreeType.getName()).thenReturn(new PAI.VOs.Name("Bachelor"));
        when(bachelorDegreeType.getId()).thenReturn(new DegreeTypeID("1"));

        when(masterDegreeType.getName()).thenReturn(new PAI.VOs.Name("Master"));
        when(masterDegreeType.getId()).thenReturn(new DegreeTypeID("2"));

        when(integratedMasterDegreeType.getName()).thenReturn(new PAI.VOs.Name("Integrated Master"));
        when(integratedMasterDegreeType.getId()).thenReturn(new DegreeTypeID("3"));

        when(degreeTypeRepository.findAll())
                .thenReturn(List.of(bachelorDegreeType, masterDegreeType, integratedMasterDegreeType));
    }

    @Test
    void shouldInitializeAndRegisterProgrammesFromCsv() throws Exception {
        // Expected VOs for DepartmentID and TeacherID from CSV entries
        DepartmentID deptAAA = new DepartmentID(new DepartmentAcronym("AAA"));
        TeacherID teacherAAA = new TeacherID(new TeacherAcronym("AAA"));

        DepartmentID deptAAF = new DepartmentID(new DepartmentAcronym("AAF"));
        TeacherID teacherAAB = new TeacherID(new TeacherAcronym("AAB"));

        DepartmentID deptAAR = new DepartmentID(new DepartmentAcronym("AAR"));
        TeacherID teacherAAC = new TeacherID(new TeacherAcronym("AAC"));

        // Run initializer to process CSV and call controller
        initializer.loadProgramme(controller, degreeTypeRepository, _csvPath);

        // Verify controller called with exact expected parameters
        verify(controller).registerProgramme("Computer Sci", "CSD", 180, 6, new DegreeTypeID("1"), deptAAA, teacherAAA);
        verify(controller).registerProgramme("Information Eng", "IEE", 120, 4, new DegreeTypeID("2"), deptAAF, teacherAAB);
        verify(controller).registerProgramme("Data Science", "DSD", 240, 8, new DegreeTypeID("3"), deptAAR, teacherAAC);

        verify(controller, atLeast(3)).registerProgramme(anyString(), anyString(), anyInt(), anyInt(),
                any(DegreeTypeID.class), any(DepartmentID.class), any(TeacherID.class));
    }
}