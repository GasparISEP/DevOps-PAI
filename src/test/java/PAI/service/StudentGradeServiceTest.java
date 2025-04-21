package PAI.service;

import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class StudentGradeServiceTest {

    @Test
    public void shouldCreatConstructor(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);


       StudentGradeService studentGradeService = new StudentGradeService( studentGradeFactory, studentGradeRepository);

        //assert
        assertNotNull(studentGradeService);
    }

}