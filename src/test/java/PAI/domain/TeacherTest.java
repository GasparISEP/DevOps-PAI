package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    @Test
    void shouldReturnTeacherWhenAllFieldsAreValid() throws Exception {
        //arrange
        Address address = new Address("Street One", "1234-678", "Porto", "Portugal");
        TeacherCategory category = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");
        //act
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", address, category, department);
        //assert
        assertNotNull(teacher);
    }

}