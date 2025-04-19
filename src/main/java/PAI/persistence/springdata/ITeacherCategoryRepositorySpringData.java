package PAI.persistence.springdata;

import PAI.persistence.datamodel.TeacherCategoryDataModel;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherCategoryRepositorySpringData extends JpaRepository<TeacherCategoryDataModel, TeacherCategoryIDDataModel> {
    Optional<TeacherCategoryDataModel> findByName(String name);
    boolean existsByName(String name);
}
