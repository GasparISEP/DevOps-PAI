package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.StudentCountDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentCountAssemblerTest {

    @Test
    void shouldCreateStudentCountDTOFromGivenValue() {
        //arrange
        int studentCount = 22;
        StudentCountAssembler studentCountAssembler = new StudentCountAssembler();

        //act
        StudentCountDTO result = studentCountAssembler.fromDomainToDTO(studentCount);

        //assert
        assertEquals(studentCount, result.studentCount());
    }

}