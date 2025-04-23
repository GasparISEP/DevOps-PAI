package PAI.persistence.springdata;

import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentGradeRepositorySpringData extends JpaRepository<StudentGradeDM, StudentGradeIDDataModel> {
}
