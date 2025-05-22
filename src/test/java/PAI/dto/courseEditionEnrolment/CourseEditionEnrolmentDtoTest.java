package PAI.dto.courseEditionEnrolment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class CourseEditionEnrolmentDtoTest {
    
    @Test
    void shouldCreateCourseEditionEnrolmentRequestDto() {
        // arrange
        String courseEditionID = "1";
        String studentID ="1";
        // act
        CourseEditionEnrolmentDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentDto(courseEditionID, studentID);
        // assert
        assertNotNull(courseEditionEnrolmentRequestDto);
    }

    @Test
    void shouldGetNullIfCourseEditionIDNull(){
        // arrange
        String courseEditionID = null;
        String studentID ="1";
        // act
        CourseEditionEnrolmentDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentDto(courseEditionID, studentID);
        // assert
        assertNull(courseEditionEnrolmentRequestDto.courseEditionId());
    }

    @Test
    void shouldGetNullIfStudentIDNull(){
        // arrange
        String courseEditionID = "1";
        String studentID = null;
        // act
        CourseEditionEnrolmentDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentDto(courseEditionID, studentID);
        // assert
        assertNull(courseEditionEnrolmentRequestDto.studentId());
    }   

    @Test
    void shouldGetCourseEditionID(){
        // arrange
        String courseEditionID = "1";
        String studentID ="1";
        // act
        CourseEditionEnrolmentDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentDto(courseEditionID, studentID);
        // assert
        assertEquals(courseEditionID, courseEditionEnrolmentRequestDto.courseEditionId());
    }   

    @Test
    void shouldGetStudentID(){
        // arrange
        String courseEditionID = "1";
        String studentID ="1";
        // act
        CourseEditionEnrolmentDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentDto(courseEditionID, studentID);
        // assert
        assertEquals(studentID, courseEditionEnrolmentRequestDto.studentId());
    }       

}
