package PAI.repository.programmeRepository;

import PAI.VOs.DepartmentID;
import PAI.VOs.ProgrammeID;
import PAI.ddd.IRepository;
import PAI.domain.programme.Programme;

import java.util.List;


public interface IProgrammeRepository extends IRepository <ProgrammeID, Programme> {
    List<ProgrammeID> findProgrammeByDepartment(DepartmentID departmentID);
}
