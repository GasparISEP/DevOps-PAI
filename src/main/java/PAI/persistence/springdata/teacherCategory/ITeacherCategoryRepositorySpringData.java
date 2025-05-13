package PAI.persistence.springdata.teacherCategory;

import PAI.persistence.datamodel.teacherCategory.TeacherCategoryDataModel;
import PAI.persistence.datamodel.teacherCategoryID.TeacherCategoryIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherCategoryRepositorySpringData extends JpaRepository<TeacherCategoryDataModel, TeacherCategoryIDDataModel> {
    Optional<TeacherCategoryDataModel> findByName(String name);
    boolean existsByName(String name);
}
