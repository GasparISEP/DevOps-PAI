package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    //Verify if the constructor parameters are valid

    private <T> void validateNotNull(T dependency, String name) {
        if (dependency == null) {
            throw new IllegalArgumentException(name + " cannot be null!");
        }
    }
}
