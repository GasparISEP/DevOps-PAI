package PAI.service.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.VOs.US35EnrolledCourseDetails;
import PAI.domain.courseEdition.CourseEdition;
import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.*;

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

    //save course edition enrolment
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

    //save course edition enrolment from persistence
    public boolean enrolStudentInACourseEditionFromPersistence(CourseEditionEnrolmentGeneratedID uuid, StudentID studentId, CourseEditionID courseEditionId, Date enrolmentDate, EnrolmentStatus status) {
        try {
            CourseEditionEnrolment cee = _courseEditionEnrolmentFactoryInterface.createWithEnrolmentDateAndUUID(uuid, studentId, courseEditionId, enrolmentDate, status);

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

        // 4. Remove the old version from the internal set (workaround for Set behavior)
        if (_ceeRepositoryInterface instanceof CourseEditionEnrolmentRepositoryImpl repoImpl) {
            repoImpl.getInternalSet().remove(enrolment);
        }

        // 5. Save changes
        _ceeRepositoryInterface.save(enrolment);

        // 6. Check if the student still has active enrolments in this programme
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

            // 4. Remove the old version from the internal set (workaround for Set behavior)
            if (_peeRepositoryInterface instanceof ProgrammeEditionEnrolmentRepositoryImpl repoImpl) {
                repoImpl.getInternalSet().remove(peEnrolment);
            }

            _peeRepositoryInterface.save(peEnrolment);
        }
    }

    //Verify if the constructor parameters are valid

    private <T> void validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null!");
        }
    }

    public int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception {

        return _ceeRepositoryInterface.numberOfStudentsEnrolledInCourseEdition(courseEditionId);
    }

    @Override
    public List<CourseEditionEnrolment> findByStudentID(int studentUniqueNumber) {
        StudentID studentID = new StudentID(studentUniqueNumber);
        return _ceeRepositoryInterface.findByStudentID(studentID);
    }


    @Override
    public List<US35EnrolledCourseDetails> findEnrolledCourseEditionsForStudent(StudentID studentID) {
        if (studentID == null) {
            return Collections.emptyList();
        }

        List<US35EnrolledCourseDetails> results = new ArrayList<>();
        List<CourseEditionEnrolment> studentEnrolments = _ceeRepositoryInterface.findActiveEnrolmentsByStudentID(studentID);

        for (CourseEditionEnrolment enrolment : studentEnrolments) {
            CourseEditionID courseEditionIdVO = enrolment.knowCourseEdition();

            Optional<CourseEdition> courseEditionOptional;
            try {
                courseEditionOptional = _courseEditionRepositoryInterface.ofIdentity(courseEditionIdVO);

            } catch (Exception e) {
                System.err.println("Error getting CourseEdition by ID " + courseEditionIdVO + " for enrolment " +
                        enrolment.getGeneratedID() + ": " + e.getMessage()); //We check which Enrolment has thrown an exception
                continue;
            }

            if (courseEditionOptional.isPresent()) {
                CourseEdition actualCourseEdition = courseEditionOptional.get();

                results.add(new US35EnrolledCourseDetails(actualCourseEdition, enrolment.getGeneratedID()));

            } else {
                System.err.println("Course Edition not found for CourseEditionID " + courseEditionIdVO +
                        " referenced by enrolment ID " + enrolment.getGeneratedID());
            }
        }
        return results;
    }
}
