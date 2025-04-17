package PAI.persistence.springdata;

import PAI.persistence.datamodel.TeacherCategoryDataModel;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ITeacherCategoryRepositorySpringData extends JpaRepository<TeacherCategoryDataModel, UUID> {
    Optional<TeacherCategoryDataModel> findByName(String name);
    boolean existsByName(String name);
}
