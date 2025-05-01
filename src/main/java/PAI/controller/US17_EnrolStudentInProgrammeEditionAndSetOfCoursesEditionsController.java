package PAI.controller;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.ICourseEditionEnrolmentService;
import PAI.service.IProgrammeEditionEnrolmentService;
import PAI.service.IProgrammeEnrolmentService;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Component
public class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private IProgrammeService _programmeList;
    private ISchoolYearService _schoolYearService;
    private IProgrammeEditionEnrolmentService _programmeEditionEnrolmentService;

    public US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            IProgrammeService programmeService,
            ISchoolYearService schoolYearService,
            IProgrammeEditionEnrolmentService programmeEditionEnrolmentService) {

        this._programmeEditionEnrolmentService = validate(programmeEditionEnrolmentService, "Programme edition enrolment service");
        this._programmeList = validate(programmeService, "Programme service");
        this._schoolYearService = validate(schoolYearService, "School year service");
    }

    public List<ProgrammeID> getAllProgrammesIDs() {
        return _programmeList.getAllProgrammeIDs();
    }

    public List<SchoolYearID> getAllSchoolYearsIDs() {
        return _schoolYearService.getAllSchoolYearsIDs();
    }

    public boolean enrolStudentInProgrammeEditionAndSetOfCoursesEditions(
            StudentID studentId, ProgrammeID programmeId, SchoolYearID schoolYearId ) throws Exception{

        return _programmeEditionEnrolmentService.enrolStudentInProgrammeEditionAndSetOfCoursesEditions(studentId, programmeId, schoolYearId);
    }

    private <T> T validate(T instance, String name) {
        if (instance == null) {
            throw new IllegalStateException(name + " cannot be null.");
        }
        return instance;
    }

}

