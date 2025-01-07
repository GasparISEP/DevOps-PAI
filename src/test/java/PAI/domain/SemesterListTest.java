package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SemesterListTest {
    @Test
    void createSemesterList() throws Exception {
        // arrange + act + assert
        Semester semester1 = new Semester(1);
    }


    @Test
    void addSemester() throws Exception {
        // arrange
        SemesterList list = new SemesterList();

        Semester semester1 = new Semester(1);

        // act
        list.addSemester(semester1);
        List<Semester> SemesterList = list.getSemestersList();

        // assert
        assertTrue(SemesterList.contains(semester1));

    }

}