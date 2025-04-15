package PAI.persistence.springdata;

import PAI.persistence.datamodel.StudentGradeDM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentGradeRepositorySpringData extends JpaRepository<StudentGradeDM, Long> {
}
