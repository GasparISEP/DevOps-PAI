package PAI.persistence.springdata.programme;

import PAI.VOs.Acronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProgrammeRepositorySpringData extends JpaRepository<ProgrammeDataModel, ProgrammeIDDataModel> {

    boolean existsByName (String name);

    boolean existsByAcronym (String acronym);

    @Query("SELECT p.programmeID FROM ProgrammeDataModel p WHERE p.departmentID = :departmentId")
    List<ProgrammeIDDataModel> findProgrammesIdByDepartmentId(@Param("departmentId") DepartmentIDDataModel departmentId);
}
