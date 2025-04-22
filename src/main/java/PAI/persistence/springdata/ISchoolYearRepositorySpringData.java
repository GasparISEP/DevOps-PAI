package PAI.persistence.springdata;

import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolYearRepositorySpringData extends JpaRepository<SchoolYearDataModel, Long>{

}
