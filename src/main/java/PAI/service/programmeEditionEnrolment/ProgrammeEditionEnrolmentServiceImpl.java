package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeEditionEnrolmentServiceImpl implements IProgrammeEditionEnrolmentService {

    private final IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository;
    private final ICourseEditionRepository courseEditionRepository;
    private final ISchoolYearRepository schoolYearRepository;
    private final IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    private final IProgrammeRepository programmeRepository;
    private final IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory;

    @Autowired
    public ProgrammeEditionEnrolmentServiceImpl(
            IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository,
            ICourseEditionRepository courseEditionRepository,
            ISchoolYearRepository schoolYearRepository,
            IProgrammeEnrolmentRepository programmeEnrolmentRepository,
            IProgrammeRepository programmeRepository,
            IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory) {

        this.programmeEditionEnrolmentRepository = validate(programmeEditionEnrolmentRepository, "Programme edition enrolment repository");
        this.programmeEditionRepository = validate(programmeEditionRepository, "Programme edition repository");
        this.courseEditionEnrolmentRepository = validate(courseEditionEnrolmentRepository, "Course edition enrolment repository");
        this.courseEditionRepository = validate(courseEditionRepository, "Course edition repository");
        this.schoolYearRepository = validate(schoolYearRepository, "School year repository");
        this.programmeEnrolmentRepository = validate(programmeEnrolmentRepository, "Enrolment repository");
        this.programmeRepository = validate(programmeRepository, "Programme repository");
        this.programmeEditionEnrolmentFactory = validate(programmeEditionEnrolmentFactory, "~ProgrammeEdition enrolment factory");

    }

    @Override
    public boolean enrolStudentInProgrammeEdition(StudentID studentId, ProgrammeEditionID programmeEditionId) throws Exception {
        if (programmeEditionId == null || studentId == null) {
            throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
        }

        if (programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(studentId, programmeEditionId)) {
            throw new IllegalStateException("Student is already enrolled in this programme edition.");
        }

        ProgrammeEditionEnrolment programmeEditionEnrol = programmeEditionEnrolmentFactory.newProgrammeEditionEnrolment(studentId, programmeEditionId);

        programmeEditionEnrolmentRepository.save(programmeEditionEnrol);

        return true;
    }


    public boolean enrolStudentInProgrammeEditionAndSetOfCoursesEditions(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeID)) {
            return false;
        }

        Optional<ProgrammeEditionID> optionalProgrammeEdition =
                programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID, schoolYearID);

        if (optionalProgrammeEdition.isEmpty()) {
            return false;
        }

        ProgrammeEditionID programmeEditionId = optionalProgrammeEdition.get();

        if (programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(studentID, programmeEditionId)) {
            return false;
        }

        ProgrammeEditionEnrolment enrolment = programmeEditionEnrolmentFactory.newProgrammeEditionEnrolment(studentID, programmeEditionId);

        programmeEditionEnrolmentRepository.save(enrolment);

        List<CourseEditionID> courseEditions =
                courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionId);

        courseEditionEnrolmentRepository.enrolStudentInProgrammeCourseEditions(studentID, courseEditions);

        return true;
    }

//    @Override
//    public List<ProgrammeID> getAllProgrammesIDs() {
//
//        return programmeRepository.getAllProgrammesIDs();
//    }

    @Override
    public List<SchoolYearID> getAllSchoolYearIDs() {

        return schoolYearRepository.getAllSchoolYearsIDs();
    }

    @Override
    public int totalStudentsInProgrammeEdition(ProgrammeEditionID programmeEditionID) throws Exception {
        List<ProgrammeEditionEnrolment> allProgrammeEditionEnrolment = programmeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID);
        return allProgrammeEditionEnrolment.size();
    }

    private <T> T validate(T instance, String name) {
        if (instance == null) {
            throw new IllegalArgumentException(name + " cannot be null.");
        }
        return instance;
    }
    @Override
    public int countStudentsInProgrammesFromDepartmentInSchoolYear(SchoolYearID schoolYearID,List<ProgrammeID> programmeIDs){
        int result;
        if(schoolYearID == null || programmeIDs == null || programmeIDs.isEmpty()){
            result=0;
        }else{
            result=programmeEditionEnrolmentRepository.countStudentsInProgrammesFromDepartmentInSchoolYear(schoolYearID,programmeIDs);
        } return result;
    }

    @Override
    public List<ProgrammeEditionID> getProgrammeEditionEnrolmentsByStudentID(StudentID studentID) throws Exception {
        if (studentID == null) {
            throw new IllegalArgumentException("StudentID cannot be null");
        }
        List<ProgrammeEditionID> listOfProgrammeEditionEnrolments = programmeEditionEnrolmentRepository.findProgrammeEditionsThatStudentIsEnrolled(studentID);
        return listOfProgrammeEditionEnrolments;
    }
}
