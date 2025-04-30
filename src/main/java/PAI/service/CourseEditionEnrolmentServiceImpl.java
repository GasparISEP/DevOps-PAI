package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseEditionEnrolmentServiceImpl implements ICourseEditionEnrolmentService {

    private final ICourseEditionEnrolmentFactory _courseEditionEnrolmentFactoryInterface;
    private final ICourseEditionEnrolmentRepository _ceeRepositoryInterface;
    private final IProgrammeEditionEnrolmentRepository _peeRepositoryInterface;
    private final ICourseEditionRepository _courseEditionRepositoryInterface;


    public CourseEditionEnrolmentServiceImpl(
            ICourseEditionEnrolmentFactory ceeFactoryInterface, ICourseEditionEnrolmentRepository ceeRepositoryInterface,
            IProgrammeEditionEnrolmentRepository peeRepositoryInterface, ICourseEditionRepository courseEditionRepositoryInterface) {

        validateNotNull(ceeFactoryInterface, "Course Edition Enrolment Factory Interface");
        validateNotNull(ceeRepositoryInterface, "Course Edition Enrolment Repository Interface");
        validateNotNull(peeRepositoryInterface, "Programme Edition Enrolment Repository Interface");
        validateNotNull(courseEditionRepositoryInterface, "Course Edition Repository Interface");

        this._courseEditionEnrolmentFactoryInterface = ceeFactoryInterface;
        this._ceeRepositoryInterface = ceeRepositoryInterface;
        this._peeRepositoryInterface = peeRepositoryInterface;
        this._courseEditionRepositoryInterface = courseEditionRepositoryInterface;
    }

    //show a list of programme editions that student is enrolled
    public List<ProgrammeEditionID> findProgrammeEditionIDsThatStudentIsEnrolled(StudentID studentId) {

        if (studentId == null) {
            return Collections.emptyList();
        }

        return _peeRepositoryInterface.findProgrammeEditionsThatStudentIsEnrolled (studentId);
    }

    //show a list of course editions that belongs to a course edition for student choose a course edition
    public List<CourseEditionID> findCourseEditionIDsByProgrammeEdition(ProgrammeEditionID programmeEditionID) {
        return _courseEditionRepositoryInterface.findCourseEditionsByProgrammeEditionID(programmeEditionID);
    }

    //create a course edition enrolment with factory
    private CourseEditionEnrolment createCourseEditionEnrolment (StudentID studentId, CourseEditionID courseEditionId){
        return _courseEditionEnrolmentFactoryInterface.createCourseEditionEnrolment (studentId, courseEditionId);
    }

    //enrol a student in a course edition
    public boolean enrolStudentInACourseEdition(StudentID studentId, CourseEditionID courseEditionId) {
        try {
            CourseEditionEnrolment cee = createCourseEditionEnrolment(studentId, courseEditionId);

            if(cee == null){
                return false;
            }

            if (_ceeRepositoryInterface.isStudentEnrolledInCourseEdition(studentId, courseEditionId)) {
                return false;
            }

            _ceeRepositoryInterface.save (cee);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeCourseEditionEnrolment(StudentID studentID, CourseEditionID courseEditionID) throws Exception {
        // 1. Retrieve the enrolment
        Optional<CourseEditionEnrolment> enrolmentOpt = _ceeRepositoryInterface.findByStudentAndEdition(studentID, courseEditionID);

        if (enrolmentOpt.isEmpty()) {
            return false;
        }

        CourseEditionEnrolment enrolment = enrolmentOpt.get();

        // 2. Check if it's active
        if (!enrolment.isEnrolmentActive()) {
            // If already inactive, no need to modify
            return false;
        }

        // 3. Deactivate enrolment
        enrolment.deactivateEnrolment();

        // 4. Save changes
        _ceeRepositoryInterface.save(enrolment);

        // 5. Check if the student still has active enrolments in this programme
        if (!hasOtherActiveCourseEditionEnrolments(studentID, courseEditionID.getProgrammeEditionID())) {
            // If not, also remove the ProgrammeEditionEnrolment
            removeProgrammeEditionEnrolment(studentID, courseEditionID.getProgrammeEditionID());
        }

        return true;
    }

    private boolean hasOtherActiveCourseEditionEnrolments(StudentID studentID, ProgrammeEditionID programmeEditionID) {
        Iterable<CourseEditionEnrolment> enrolments = _ceeRepositoryInterface.findAll();

        for (CourseEditionEnrolment enrolment : enrolments) {
            if (enrolment.hasStudent(studentID) &&
                    enrolment.isEnrolmentActive() &&
                    enrolment.knowCourseEdition().getProgrammeEditionID().equals(programmeEditionID)) {
                return true;
            }
        }
        return false;
    }

    private void removeProgrammeEditionEnrolment(StudentID studentID, ProgrammeEditionID programmeEditionID) throws Exception {
        Optional<ProgrammeEditionEnrolment> peEnrolmentOpt = _peeRepositoryInterface.findByStudentAndProgrammeEdition(studentID, programmeEditionID);

        if (peEnrolmentOpt.isPresent()) {
            ProgrammeEditionEnrolment peEnrolment = peEnrolmentOpt.get();
            peEnrolment.deactivateEnrolment();
            // Possible future consideration/alternative: rather than automatically auto-deactivating the enrolment,
            // we might introduce an "IRREGULAR" status (e.g., peEnrolment.markAsIrregular()).
            // This would involve changing the status from a boolean to an enum: ACTIVE, INACTIVE or IRREGULAR.

            _peeRepositoryInterface.save(peEnrolment);
        }
    }

    //Verify if the constructor parameters are valid

    private <T> void validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null!");
        }
    }
}
