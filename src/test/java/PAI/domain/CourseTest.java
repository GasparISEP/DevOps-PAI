package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testValidCourseCreation() throws Exception {
        // Arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        // Act
        Course course1 = new Course("Informatics", "INF", 6, teacher);

        // Assert
        assertNotNull(course1);
    }

    @Test
    void testCourseCreationTestingInvalidName() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("", "INF", 6, teacher));
    }

    @Test
    void testCourseCreationTestingNullName() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course(null, "INF", 6, teacher));
    }

    @Test
    void testCourseCreationTestingNullAcronym() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", null, 6, teacher));
    }

    @Test
    void testCourseCreationTestingBlankAcronym() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "", 6, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidAcronym() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "^AZ", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "A^Z", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "AZ^", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "áBD", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "BÁD", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "BDá", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "A ZD", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "AZ D", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "AZD ", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", " AZD", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "AZ!", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "!ZD", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "A!Z", 6, teacher));
        assertThrows(Exception.class, () -> new Course("Informatics", "asd", 6, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidQuantityOfEctsLower() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 0, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidQuantityOfEctsHigher() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 181, teacher));
    }

    //Equals Method Test
    @Test
    void shouldReturnTrueIfObjectComparedIsTheSameAsThisCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, teacher);
        Object compare = course1;
        //act
        boolean result = course1.equals(compare);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedIsNotAnInstanceOfCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, teacher);
        Object compare = teacher;
        //act
        boolean result = course1.equals(compare);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectComparedHasTheSameAcronymAsOtherCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Teacher teacher2 = new Teacher("DSA", "Artur Silva Dias", "dsa@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, teacher);
        Course course2 = new Course("Maths", "INF", 5, teacher2);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedHasNotTheSameAcronymAsOtherCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Teacher teacher2 = new Teacher("DSA", "Artur Silva Dias", "dsa@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, teacher);
        Course course2 = new Course("Maths", "MAT", 5, teacher2);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertFalse(result);
    }
}