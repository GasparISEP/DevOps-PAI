package PAI.VOs;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US34ResponseTest {

    @Test
    void shouldCreateUS34(){
        //arrange
        ProgrammeEditionEnrolmentID progEditionEnrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        CourseEditionEnrolmentID courseEditionEnrolmentID = mock(CourseEditionEnrolmentID.class);
        List<CourseEditionEnrolmentID> listCourseEditionEnrolmentID = List.of(courseEditionEnrolmentID);

        //act
        US34Response us34Response = new US34Response(progEditionEnrolmentID, listCourseEditionEnrolmentID);

        //assert
        assertNotNull(us34Response);
    }

    @Test
    void shouldGetProgrammeEditionEnrolmentID() {
        //arrange
        ProgrammeEditionEnrolmentID progEditionEnrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        CourseEditionEnrolmentID courseEditionEnrolmentID = mock(CourseEditionEnrolmentID.class);
        List<CourseEditionEnrolmentID> listCourseEditionEnrolmentID = List.of(courseEditionEnrolmentID);

        US34Response us34Response = new US34Response(progEditionEnrolmentID, listCourseEditionEnrolmentID);

        //act
        ProgrammeEditionEnrolmentID res = us34Response.progEditionEnrolment();

        //assert
        assertEquals(res, us34Response.progEditionEnrolment());
    }

    @Test
    void shouldGetListCourseEditionEnrolmentID() {
        //arrange
        ProgrammeEditionEnrolmentID progEditionEnrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        CourseEditionEnrolmentID courseEditionEnrolmentID = mock(CourseEditionEnrolmentID.class);
        List<CourseEditionEnrolmentID> listCourseEditionEnrolmentID = List.of(courseEditionEnrolmentID);

        US34Response us34Response = new US34Response(progEditionEnrolmentID, listCourseEditionEnrolmentID);

        //act
        List<CourseEditionEnrolmentID> res = us34Response.listCourseEditionEnrolment();

        //assert
        assertEquals(res, us34Response.listCourseEditionEnrolment());
    }
}