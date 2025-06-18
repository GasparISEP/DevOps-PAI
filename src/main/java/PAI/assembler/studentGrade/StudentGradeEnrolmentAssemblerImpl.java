package PAI.assembler.studentGrade;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentGradeEnrolmentAssemblerImpl implements IStudentGradeEnrolmentAssembler {

    private final ICourseEditionEnrolmentRepository enrolmentRepository;

    public StudentGradeEnrolmentAssemblerImpl(ICourseEditionEnrolmentRepository enrolmentRepository) {
        this.enrolmentRepository = enrolmentRepository;
    }

    @Override
    public CourseEditionEnrolmentMinimalDTO toMinimalDTO(CourseEditionEnrolment enrolment) {
        return new CourseEditionEnrolmentMinimalDTO(
                enrolment.getGeneratedID().id(),
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
