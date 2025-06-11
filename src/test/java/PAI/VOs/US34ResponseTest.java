package PAI.VOs;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US34ResponseTest {

    @Test
    void shouldCreateUS34(){
        //arrange
        ProgrammeEditionEnrolment progEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        CourseEditionEnrolment courseEditionEnrolment = mock(CourseEditionEnrolment.class);
        List<CourseEditionEnrolment> listCourseEditionEnrolment = List.of(courseEditionEnrolment);

        //act
        US34Response us34Response = new US34Response(progEditionEnrolment, listCourseEditionEnrolment);

        //assert
        assertNotNull(us34Response);
    }

    @Test
    void shouldGetProgrammeEditionEnrolmentID() {
        //arrange
        ProgrammeEditionEnrolment progEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        CourseEditionEnrolment courseEditionEnrolment = mock(CourseEditionEnrolment.class);
        List<CourseEditionEnrolment> listCourseEditionEnrolment = List.of(courseEditionEnrolment);

        US34Response us34Response = new US34Response(progEditionEnrolment, listCourseEditionEnrolment);

        //act
        ProgrammeEditionEnrolment res = us34Response.progEditionEnrolment();

        //assert
        assertEquals(res, us34Response.progEditionEnrolment());
    }

    @Test
    void shouldGetListCourseEditionEnrolmentID() {
        //arrange
        ProgrammeEditionEnrolment progEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        CourseEditionEnrolment courseEditionEnrolment = mock(CourseEditionEnrolment.class);
        List<CourseEditionEnrolment> listCourseEditionEnrolment = List.of(courseEditionEnrolment);

        US34Response us34Response = new US34Response(progEditionEnrolment, listCourseEditionEnrolment);

        //act
        List<CourseEditionEnrolment> res = us34Response.listCourseEditionEnrolment();

        //assert
        assertEquals(res, us34Response.listCourseEditionEnrolment());
    }
}