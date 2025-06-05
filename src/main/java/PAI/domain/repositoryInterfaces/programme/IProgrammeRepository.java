package PAI.domain.repositoryInterfaces.programme;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.programme.Programme;

import java.util.List;


public interface IProgrammeRepository extends IRepository <ProgrammeID, Programme> {
    List<ProgrammeID> findProgrammeByDepartment(DepartmentID departmentID);
    boolean existsByName(NameWithNumbersAndSpecialChars name);
    boolean existsByAcronym(Acronym acronym);
}
