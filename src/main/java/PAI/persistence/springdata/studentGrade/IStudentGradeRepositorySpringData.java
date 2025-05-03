package PAI.persistence.springdata.studentGrade;

import PAI.persistence.datamodel.studentGrade.StudentGradeDM;
import PAI.persistence.datamodel.studentGrade.StudentGradeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentGradeRepositorySpringData extends JpaRepository<StudentGradeDM, StudentGradeIDDataModel> {
}
