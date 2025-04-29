package PAI.controller;


import PAI.VOs.DepartmentID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.service.IProgrammeEditionEnrolmentService;
import PAI.service.department.IDepartmentService;
import PAI.service.programme.IProgrammeService;
import PAI.service.schoolYear.ISchoolYearService;

import java.util.List;
import java.util.Set;

public class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController {
    private final IProgrammeService programmeService;
    private final ISchoolYearService schoolYearService;
    private final IDepartmentService departmentService;
    private final IProgrammeEditionEnrolmentService programmeEditionEnrolmentService;

    public US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(
            IProgrammeEditionEnrolmentService programmeEditionEnrolmentService,
            ISchoolYearService schoolYearService,
            IDepartmentService departmentService,
            IProgrammeService programmeService
    ) {
        if (programmeService == null || schoolYearService == null || departmentService == null || programmeEditionEnrolmentService == null){
            throw new IllegalArgumentException("Repositories cannot be null.");
        }
        this.programmeService=programmeService;
        this.schoolYearService=schoolYearService;
        this.departmentService=departmentService;
        this.programmeEditionEnrolmentService =programmeEditionEnrolmentService;
    }

    public int countStudentsInProgrammesFromDepartmentInSchoolYear(DepartmentID departmentID, SchoolYearID schoolYearID) {
        if(departmentID==null || schoolYearID==null){
            throw new  IllegalArgumentException("Department or SchoolYear cannot be null");
        }
        if(!schoolYearService.schoolYearExistsById(schoolYearID)){
            throw new  IllegalArgumentException("SchoolYear does not exist.");
        }
        if(!departmentService.departmentExists(departmentID)){
            throw new  IllegalArgumentException("Department does not exist.");
        }
        List<ProgrammeID> programmeIDs = programmeService.findProgrammeByDepartment(departmentID);

        return programmeEditionEnrolmentService.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearID,programmeIDs);
    }

    public Set<DepartmentID> getAllDepartmentID() {
        return departmentService.getDepartmentIDs();
    }

    public List<SchoolYearID> getAllSchoolYearsIDs() {
        return schoolYearService.getAllSchoolYearsIDs();
    }
}