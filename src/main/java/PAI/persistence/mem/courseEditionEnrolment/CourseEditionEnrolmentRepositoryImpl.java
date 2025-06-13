package PAI.persistence.mem.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentGeneratedID;
import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;

import java.util.*;

public class CourseEditionEnrolmentRepositoryImpl implements ICourseEditionEnrolmentRepository {

    private Set<CourseEditionEnrolment> _courseEditionEnrolments;

    public CourseEditionEnrolmentRepositoryImpl(ICourseEditionEnrolmentListFactory courseEditionEnrolmentListFactory) {

        _courseEditionEnrolments = courseEditionEnrolmentListFactory.getCourseEditionEnrolmentList();
    }

    public boolean isStudentEnrolledInCourseEdition(StudentID student, CourseEditionID courseEdition) {
        for (CourseEditionEnrolment enrollment : _courseEditionEnrolments) {
            if (enrollment.knowStudent().equals(student) &&
                    enrollment.knowCourseEdition().equals(courseEdition) &&
                    enrollment.isEnrolmentActive()) {
                return true;
            }
        }
        return false;
    }

    public Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID student, CourseEditionID courseEdition) {
        if (student == null || courseEdition == null) {
            return Optional.empty();
        }
        for (CourseEditionEnrolment courseEEnrollment : _courseEditionEnrolments)
            if (courseEEnrollment.hasStudent(student) && courseEEnrollment.hasCourseEdition(courseEdition)) {
                return Optional.of(courseEEnrollment);
            }
        return Optional.empty();
    }

    @Override
    public Optional<CourseEditionEnrolment> findByGeneratedID(CourseEditionEnrolmentGeneratedID id) {
        if (id == null) {
            return Optional.empty();
        }

        UUID targetUUID = id.getCourseEditionEnrolmentGeneratedID();

        for (CourseEditionEnrolment ceEnrolment : _courseEditionEnrolments) {
            CourseEditionEnrolmentGeneratedID currentID = ceEnrolment.getGeneratedID();
            if (currentID != null && currentID.isEquals(targetUUID)) {
                return Optional.of(ceEnrolment);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsByGeneratedID(CourseEditionEnrolmentGeneratedID id) {
        if (id == null) return false;

        UUID targetUUID = id.getCourseEditionEnrolmentGeneratedID();

        for (CourseEditionEnrolment ceEnrolment : _courseEditionEnrolments) {
            CourseEditionEnrolmentGeneratedID currentID = ceEnrolment.getGeneratedID();
            if (currentID != null && currentID.isEquals(targetUUID)) {
                return true;
            }
        }
        return false;
    }

    //US24
    public int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception {

        int count = 0;
        for (CourseEditionEnrolment enrollment : _courseEditionEnrolments) {
            if (enrollment.knowCourseEdition().equals(courseEditionId)) {
                count++;
            }
        }
        return count;
    }


    public void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions) {

        for (CourseEditionID courseEditionId : courseEditions) {
            Optional<CourseEditionEnrolment> existingEnrollment = findByStudentAndEdition(studentId, courseEditionId);
            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrolment is already in the list.");
            }

            CourseEditionEnrolment newEnrolment = new CourseEditionEnrolment(studentId, courseEditionId);
            save(newEnrolment);
        }
    }

    @Override
    public Set<CourseEditionEnrolment> getInternalSet() {
        return this._courseEditionEnrolments;
    }


    @Override
    public CourseEditionEnrolment save(CourseEditionEnrolment entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        boolean isCourseEditionEnrolmentSaved = _courseEditionEnrolments.add(entity);

        if (!isCourseEditionEnrolmentSaved){
            throw new IllegalStateException("This course edition enrolment is already in the list.");
        }

        return entity;
    }

    @Override
    public Iterable<CourseEditionEnrolment> findAll() {
        return new ArrayList<>(_courseEditionEnrolments);
    }

    @Override
    public Optional<CourseEditionEnrolment> ofIdentity(CourseEditionEnrolmentID id) {
        return _courseEditionEnrolments.stream()
                .filter(enrolment -> enrolment.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(CourseEditionEnrolmentID id) {
        if (!ofIdentity(id).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public List<CourseEditionEnrolment> findByStudentID(StudentID studentID) {
        return _courseEditionEnrolments.stream()
                .filter(e -> e.knowStudent().equals(studentID))
                .toList();
    }


}


