package PAI.assembler.studentGrade;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentGradeEnrolmentAssemblerImpl implements IStudentGradeEnrolmentAssembler {

    private final ICourseEditionEnrolmentRepository enrolmentRepository;
    private final ICourseEditionRepository courseEditionRepository;

    public StudentGradeEnrolmentAssemblerImpl(ICourseEditionEnrolmentRepository enrolmentRepository, ICourseEditionRepository courseEditionRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.courseEditionRepository = courseEditionRepository;
    }

    @Override
    public CourseEditionEnrolmentMinimalDTO toMinimalDTO(CourseEditionEnrolment enrolment) {
        CourseEdition courseEdition = courseEditionRepository.ofIdentity(enrolment.knowCourseEdition())
                .orElseThrow(() -> new IllegalArgumentException("CourseEdition not found for ID: " + enrolment.knowCourseEdition()));
        
        return new CourseEditionEnrolmentMinimalDTO(
                courseEdition.getCourseEditionGeneratedID().getCourseEditionGeneratedID(), // Use CourseEditionGeneratedID
                enrolment.knowCourseEdition().courseName().getName()
        );
    
    }

    @Override
    public GradeAStudentCommand toCommand(GradeAStudentRequestMinimalDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null.");
        }

        StudentID studentID = new StudentID(dto.getStudentUniqueNumber());

        Grade grade;
        try {
            grade = new Grade(dto.getGrade());
        } catch (Exception e) {
            throw new RuntimeException("Invalid grade provided: " + dto.getGrade(), e);
        }

        Date date = Date.now();

        UUID uuid = UUID.fromString(dto.getCourseEditionGeneratedID());
        CourseEditionEnrolmentGeneratedID enrolmentGeneratedID = new CourseEditionEnrolmentGeneratedID(uuid);

        CourseEditionEnrolment enrolment = enrolmentRepository.findByGeneratedID(enrolmentGeneratedID)
                .orElseThrow(() -> new IllegalArgumentException("Enrolment not found for generated ID: " + enrolmentGeneratedID));

        CourseEditionID courseEditionID = enrolment.knowCourseEdition();

        return new GradeAStudentCommand(grade, date, studentID, courseEditionID);
    }

}
