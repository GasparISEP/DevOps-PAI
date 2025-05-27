package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US11_RegisterProgrammeInTheSystemController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ProgrammeInitializerTest {

    @Mock
    private US11_RegisterProgrammeInTheSystemController controller;

    @InjectMocks
    private ProgrammeInitializer initializer;

    private DepartmentID departmentID1;
    private TeacherID teacherID1;
    private DegreeTypeID degreeTypeID1;

    private DepartmentID departmentID2;
    private TeacherID teacherID2;
    private DegreeTypeID degreeTypeID2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        TeacherAcronym teacherAcronym1 = new TeacherAcronym("HPS");
        DepartmentAcronym departmentAcronym1 = new DepartmentAcronym("INF");

        TeacherAcronym teacherAcronym2 = new TeacherAcronym("LKY");
        DepartmentAcronym departmentAcronym2 = new DepartmentAcronym("INF");


        // Create IDs from VOs
        teacherID1 = new TeacherID(teacherAcronym1);
        departmentID1 = new DepartmentID(departmentAcronym1);
        degreeTypeID1 = new DegreeTypeID("BSC");

        teacherID2 = new TeacherID(teacherAcronym2);
        departmentID2 = new DepartmentID(departmentAcronym2);
        degreeTypeID2 = new DegreeTypeID("MSC");
    }

    @Test
    void shouldInitializeAndRegisterProgrammesFromCsv() throws Exception {
        // act
        initializer.init();

        // assert
        verify(controller).registerProgramme(
                "Computer Sci", "CSD", 30, 6,
                degreeTypeID1, departmentID1, teacherID1
        );

        verify(controller).registerProgramme(
                "AI & Robotics", "AIR", 28, 6,
                degreeTypeID2, departmentID2, teacherID2
        );
    }
}