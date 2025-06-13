package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableCourseInfoTest {
    @Test
    void testCreation() throws Exception {
        Acronym acronym = new Acronym("CSA");
        CourseID id = new CourseID(acronym,new Name("Name"));
        CourseQuantityCreditsEcts ects = new CourseQuantityCreditsEcts(6.0);
        AvailableCourseInfo info = new AvailableCourseInfo(id, ects);

        assertEquals(id, info.courseID());
        assertEquals(ects, info.qtyEcts());
    }


}