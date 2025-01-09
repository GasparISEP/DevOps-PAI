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
        Course course1 = new Course("Informatics", "INF", 6, 1, teacher);

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
        assertThrows(Exception.class, () -> new Course("", "INF", 6, 1, teacher));
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
        assertThrows(Exception.class, () -> new Course(null, "INF", 6,1, teacher));
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
        assertThrows(Exception.class, () -> new Course("Informatics", null, 6, 1, teacher));
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
        assertThrows(Exception.class, () -> new Course("Informatics", "", 6, 1,  teacher));
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
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 0, 1, teacher));
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
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 181, 1, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidSemesterLower() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 6, 0, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidSemesterHigher() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 6, 7, teacher));
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
        Course course1 = new Course("Informatics", "INF", 10, 1, teacher);
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
        Course course1 = new Course("Informatics", "INF", 10, 1, teacher);
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
        Course course1 = new Course("Informatics", "INF", 10, 1, teacher);
        Course course2 = new Course("Maths", "INF", 5, 1, teacher2);
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
        Course course1 = new Course("Informatics", "INF", 10, 1, teacher);
        Course course2 = new Course("Maths", "MAT", 5, 1, teacher2);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertFalse(result);
    }
}