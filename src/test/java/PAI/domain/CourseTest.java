package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testValidCourseCreation() throws Exception {
        // Arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        // Act
        Course course1 = new Course("Informatics", "INF", 6, semester, teacher);

        // Assert
        assertNotNull(course1);
    }

    @Test
    void testCourseCreationTestingInvalidName() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("", "INF", 6, semester, teacher));
    }

    @Test
    void testCourseCreationTestingNullName() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course(null, "INF", 6, semester, teacher));
    }

    @Test
    void testCourseCreationTestingNullAcronym() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", null, 6, semester, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidAcronym() throws Exception {
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "", 6, semester, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidQuantityOfEctsLower() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 0, semester, teacher));
    }

    @Test
    void testCourseCreationTestingInvalidQuantityOfEctsHigher() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com", 
        "238310710", "A123", address, teacherCategory, department);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 181, semester, teacher));
    }
}