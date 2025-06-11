package PAI.service.student;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeAndCoursesEnrolmentServiceImpl {
    private final IProgrammeEditionEnrolmentFactory _enrolmentFactory;
    private final IProgrammeEditionEnrolmentRepository _enrolmentRepository;
    private final ICourseEditionEnrolmentFactory _courseEditionEnrolmentFactory;
    private final ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    private final IAvailableCoursesService _availableCoursesService;
    private final ICourseEditionRepository _courseEditionRepository;

    public ProgrammeAndCoursesEnrolmentServiceImpl(IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory, IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
                                                   ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, IAvailableCoursesService availableCoursesService,ICourseEditionRepository courseEditionRepository) {
        if (programmeEditionEnrolmentFactory == null || programmeEditionEnrolmentRepository == null || courseEditionEnrolmentFactory == null || courseEditionEnrolmentRepository == null || availableCoursesService == null || courseEditionRepository == null)
            throw new IllegalArgumentException("Cannot be null");
        _enrolmentFactory = programmeEditionEnrolmentFactory;
        _enrolmentRepository = programmeEditionEnrolmentRepository;
        _courseEditionEnrolmentFactory = courseEditionEnrolmentFactory;
        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
        _availableCoursesService = availableCoursesService;
        _courseEditionRepository = courseEditionRepository;
    }

    public ProgrammeEditionEnrolment createProgrammeEditionEnrolment(StudentID studentID, ProgrammeEditionID programmeEditionID) {
        return _enrolmentFactory.newProgrammeEditionEnrolment(studentID,programmeEditionID);
    }

    private boolean isCourseIdInCourseInStudyPlan(CourseID courseID, CourseInStudyPlan studyPlan){
        return studyPlan.getCourseID().equals(courseID);
    }

    private boolean isCourseIdInListOfCourseInStudyPlan(List<CourseID> list, CourseInStudyPlan csp) {
        for (CourseID existingCourseID : list) {
            if (isCourseIdInCourseInStudyPlan(existingCourseID, csp)) {
                return true;
            }
        }
        return false;
    }

    private List<CourseInStudyPlan> filterMatchingCourseInStudyPlans(List<CourseID> courseIDS, ProgrammeEditionID programmeEditionID) {
        List<CourseInStudyPlan> allCSP = _availableCoursesService.getByIdentity(_availableCoursesService.allCoursesInStudyFromProgrammeEdition(_availableCoursesService.allCourseEditionIdsFromProgrammeEdition(programmeEditionID)));
        List<CourseInStudyPlan> result = new ArrayList<>();
        for (CourseInStudyPlan existingCSP : allCSP) {
            if (isCourseIdInListOfCourseInStudyPlan(courseIDS, existingCSP)) {
                result.add(existingCSP);
            }
        }
        return result;
    }

    private List<CourseInStudyPlanID> getListOfCourseInStudyPlanID (List<CourseInStudyPlan> list){
        List<CourseInStudyPlanID> result = new ArrayList<>();
        for (CourseInStudyPlan existingCSP : list){
            result.add(existingCSP.identity());
        }
        return result;
    }

    private List<CourseEditionID> getCourseEditionIDsFromProgrammeAndCISP(ProgrammeEditionID programmeEditionID, List<CourseInStudyPlanID> courseInStudyPlanIDs) throws Exception {

        List<CourseEditionID> courseEditionIDs = new ArrayList<>();

        for (CourseInStudyPlanID cspID : courseInStudyPlanIDs) {
            List<CourseEditionID> found = _courseEditionRepository.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, cspID);

            courseEditionIDs.addAll(found);
        }

        return courseEditionIDs;
    }

    private List<CourseEditionEnrolment> createCourseEditions(StudentID studentID,CourseEditionEnrolmentGeneratedID id, List<CourseEditionID> courseEditionIDS, Date date){
        List<CourseEditionEnrolment> result = new ArrayList<>();

        if (courseEditionIDS == null || courseEditionIDS.isEmpty()) {
            return result;
        }

        for (CourseEditionID existingCEID : courseEditionIDS) {
            result.add(_courseEditionEnrolmentFactory.createCourseEditionEnrolment(studentID,existingCEID));
        }

        return result;
    }

    private List<CourseEditionEnrolment> saveAllCourseEditionEnrolments(List<CourseEditionEnrolment> list) throws Exception{
        List<CourseEditionEnrolment> saved = new ArrayList<>();
        for (CourseEditionEnrolment existingCEE : list ){
            saved.add(_courseEditionEnrolmentRepository.save(existingCEE));
        }
        return saved;
    }

}
