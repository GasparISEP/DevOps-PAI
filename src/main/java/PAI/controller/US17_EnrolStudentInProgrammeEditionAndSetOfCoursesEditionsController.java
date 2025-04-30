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

public class US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController {

    private IProgrammeEditionService _programmeEditionService;
    private IProgrammeService _programmeList;
    private ICourseEditionEnrolmentService _courseEditionEnrolmentService;
    private ICourseEditionService _courseEditionService;
    private ISchoolYearService _schoolYearService;
    private IProgrammeEnrolmentService _programmeEnrolmentService;
    private IProgrammeEditionEnrolmentService _programmeEditionEnrolmentService;

    public US17_EnrolStudentInProgrammeEditionAndSetOfCoursesEditionsController(
            IProgrammeEditionService programmeEditionService,
            IProgrammeService programmeService,
            ICourseEditionEnrolmentService courseEditionEnrolmentService,
            ICourseEditionService courseEditionService,
            ISchoolYearService schoolYearService,
            IProgrammeEnrolmentService programmeEnrolmentService,
            IProgrammeEditionEnrolmentService programmeEditionEnrolmentService) {

        this._programmeEditionEnrolmentService = validate(programmeEditionEnrolmentService, "Programme edition enrolment service");
        this._programmeEditionService = validate(programmeEditionService, "Programme edition service");
        this._programmeList = validate(programmeService, "Programme service");
        this._courseEditionEnrolmentService = validate(courseEditionEnrolmentService, "Course edition enrolment service");
        this._courseEditionService = validate(courseEditionService, "Course edition service");
        this._schoolYearService = validate(schoolYearService, "School year service");
        this._programmeEnrolmentService = validate(programmeEnrolmentService, "Programme enrolment service");
        this._programmeEditionEnrolmentService = validate(programmeEditionEnrolmentService, "Programme edition enrolment service");
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


