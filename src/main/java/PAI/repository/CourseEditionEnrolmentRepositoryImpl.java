package PAI.repository;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.ICourseEditionEnrolmentFactory;
import PAI.factory.ICourseEditionEnrolmentListFactory;

import java.util.*;

public class CourseEditionEnrolmentRepositoryImpl implements ICourseEditionEnrolmentRepository {

    private Set<CourseEditionEnrolment> _courseEditionEnrolments;

    private final ICourseEditionEnrolmentFactory _courseEditionEnrolmentFactory;

    public CourseEditionEnrolmentRepositoryImpl(ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory, ICourseEditionEnrolmentListFactory courseEditionEnrolmentListFactory) {

        _courseEditionEnrolments = courseEditionEnrolmentListFactory.getCourseEditionEnrolmentList();
        _courseEditionEnrolmentFactory = courseEditionEnrolmentFactory;
    }

    public boolean enrolStudentInACourseEdition(StudentID studentId, CourseEditionID courseEditionId) {
        try {
            CourseEditionEnrolment cee1 = _courseEditionEnrolmentFactory.createCourseEditionEnrolment(studentId, courseEditionId);

            return _courseEditionEnrolments.add(cee1);

        } catch (Exception e) {
            return false;
        }
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

    // Method to remove (deactivate) an enrolment
    public boolean removeEnrolment(StudentID studentID, CourseEditionID courseEditionID) {
        Optional<CourseEditionEnrolment> enrollment = findByStudentAndEdition(studentID, courseEditionID);
        if (enrollment.isPresent()) {
            CourseEditionEnrolment cee = enrollment.get();
            // Deactivate the enrolment if it's active
            if (cee.isEnrolmentActive()) {  // Only deactivates if the enrolment is currently active
                cee.deactivateEnrolment();
                return true; // Returns true indicating that the enrolment was deactivated
            }
        }
        return false; // Returns false if the enrolment was already inactive or did not exist
    }


    public void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions){

        for (CourseEditionID courseEditionId : courseEditions) {
            Optional<CourseEditionEnrolment> existingEnrollment = findByStudentAndEdition(studentId, courseEditionId);
            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrolment is already in the list.");
            }
            enrolStudentInACourseEdition(studentId, courseEditionId);
        }
    }

    @Override
    public CourseEditionEnrolment save(CourseEditionEnrolment entity) {

        if(entity == null){
            throw new IllegalArgumentException("Entity cannot be null");
        }
        _courseEditionEnrolments.add(entity);
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
        if (!ofIdentity(id).isPresent()){
            return false;
        }
        return true;
    }
}


