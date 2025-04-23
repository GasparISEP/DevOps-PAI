package PAI.persistence.springdata;

import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ISchoolYearRepositorySpringData extends JpaRepository<SchoolYearDataModel, Long>{

    @Query("SELECT schoolYear.id FROM SchoolYearDataModel schoolYear WHERE schoolYear.startDate = (SELECT MAX(innerSchoolYear.startDate) FROM SchoolYearDataModel innerSchoolYear)")
    Optional<SchoolYearIDDataModel> findCurrentSchoolYear();
}
