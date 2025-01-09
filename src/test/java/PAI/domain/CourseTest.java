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

    //Equals Method Test
    @Test
    void shouldReturnTrueIfObjectComparedIsTheSameAsThisCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, semester, teacher);
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
        Semester semester = new Semester(1);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, semester, teacher);
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
        Semester semester = new Semester(1);
        Semester semester2 = new Semester(2);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Teacher teacher2 = new Teacher("DSA", "Artur Silva Dias", "dsa@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, semester, teacher);
        Course course2 = new Course("Maths", "INF", 5, semester2, teacher2);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedHasNotTheSameAcronymAsOtherCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Semester semester = new Semester(1);
        Semester semester2 = new Semester(2);
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Teacher teacher2 = new Teacher("DSA", "Artur Silva Dias", "dsa@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, semester, teacher);
        Course course2 = new Course("Maths", "MAT", 5, semester2, teacher2);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertFalse(result);
    }
}