package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableCourseInfoTest {
    @Test
    void testCreation() throws Exception {
        Acronym acronym = new Acronym("CSA");
        CourseID id = new CourseID(acronym,new Name("Name"));
        CurricularYear curricularYear = new CurricularYear(1);
        Semester semester = new Semester(1);
        CourseQuantityCreditsEcts ects = new CourseQuantityCreditsEcts(6.0);
        AvailableCourseInfo info = new AvailableCourseInfo(id, ects,curricularYear,semester);

        assertEquals(id, info.courseID());
        assertEquals(ects, info.qtyEcts());
        assertEquals(curricularYear,info.curricularYear());
        assertEquals(semester,info.semester());
    }


}