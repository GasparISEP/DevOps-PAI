package PAI.dto.courseEditionEnrolment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseEditionEnrolmentRequestDtoTest {
    
    @Test
    void shouldCreateCourseEditionEnrolmentRequestDto() {
        // arrange
        String courseEditionID = "1";
        String studentID ="1";
        // act
        CourseEditionEnrolmentRequestDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentRequestDto(courseEditionID, studentID);
        // assert
        assertNotNull(courseEditionEnrolmentRequestDto);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDNull(){
        // arrange
        String courseEditionID = null;
        String studentID ="1";
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentRequestDto(courseEditionID, studentID);
        }); 
    }
    
    @Test
    void shouldThrowExceptionIfStudentIDNull(){
        // arrange
        String courseEditionID = "1";
        String studentID = null;
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentRequestDto(courseEditionID, studentID);
        }); 
    }

    @Test
    void shouldGetCourseEditionId(){
        // arrange
        String courseEditionID = "1";
        String studentID ="1";
        // act
        CourseEditionEnrolmentRequestDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentRequestDto(courseEditionID, studentID);
        // assert
        assertEquals(courseEditionID, courseEditionEnrolmentRequestDto.getCourseEditionId());
    }

    @Test
    void shouldGetStudentId(){
        // arrange
        String courseEditionID = "1";
        String studentID ="1";
        // act
        CourseEditionEnrolmentRequestDto courseEditionEnrolmentRequestDto = new CourseEditionEnrolmentRequestDto(courseEditionID, studentID);
        // assert
        assertEquals(studentID, courseEditionEnrolmentRequestDto.getStudentId());
    }
}
