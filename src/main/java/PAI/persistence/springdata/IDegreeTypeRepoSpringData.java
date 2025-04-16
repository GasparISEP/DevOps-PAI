package PAI.persistence.springdata;

import PAI.persistence.datamodel.DegreeTypeDM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDegreeTypeRepoSpringData extends JpaRepository<DegreeTypeDM, String> {
}
