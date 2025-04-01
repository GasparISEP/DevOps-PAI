package PAI.repository;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.StudentID;
import PAI.ddd.AggregateRoot;
import PAI.domain.*;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.factory.IProgrammeEditionEnrolmentListFactory;



import java.util.*;

public class ProgrammeEditionEnrolmentRepository implements IProgrammeEditionEnrolmentRepository {


    private final Set<ProgrammeEditionEnrolment> _programmeEditionEnrolments;
    private final IProgrammeEditionEnrolmentFactory _iProgrammeEditionEnrolmentFactory;

    public ProgrammeEditionEnrolmentRepository(IProgrammeEditionEnrolmentFactory iProgrammeEditionEnrolmentFactory,
                                               IProgrammeEditionEnrolmentListFactory iProgrammeEditionEnrolmentListFactory) {

        _iProgrammeEditionEnrolmentFactory = iProgrammeEditionEnrolmentFactory;
        _programmeEditionEnrolments = iProgrammeEditionEnrolmentListFactory.newListProgrammeEditionEnrolment();
    }
    @Override
    public boolean enrolStudentInProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
            if (programmeEdition == null || student == null) {
                throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
            }

            ProgrammeEditionEnrolment programmeEditionEnrol = _iProgrammeEditionEnrolmentFactory.newProgrammeEditionEnrolment(student, programmeEdition);

            return _programmeEditionEnrolments.add(programmeEditionEnrol);
    }
    @Override
    public boolean isStudentEnrolledInThisProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
        if(student == null || programmeEdition == null) {
            return false;
        }
        for (ProgrammeEditionEnrolment enrollment : _programmeEditionEnrolments) {
            if (enrollment.hasSameStudent(student) && enrollment.hasSameProgrammeEdition(programmeEdition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear) {
        Set<StudentID> studentIDs = new HashSet<>();

        for (ProgrammeEditionEnrolment enrollment : _programmeEditionEnrolments) {
            if (enrollment.isEnrolmentAssociatedToDepartmentAndSchoolYear(department, schoolYear)) {
                StudentID studentID = enrollment.getStudentID();
                studentIDs.add(studentID);
                }
        }
        return studentIDs.size();
    }

    //US21 - Get The Number Of Students Enrolled In A Programme Edition
    @Override
    public int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){
        int numberOfStudents = 0;

        for(ProgrammeEditionEnrolment programmeEditionEnrolment : _programmeEditionEnrolments)
            if(programmeEditionEnrolment.findProgrammeEditionInEnrolment().equals(programmeEdition)){
                numberOfStudents++;
            }

        return numberOfStudents;
    }

    @Override
    public List<ProgrammeEdition> findProgrammeEditionsThatStudentIsEnrolled(Student student){
        List<ProgrammeEdition> list = new ArrayList<>();

        for(ProgrammeEditionEnrolment programmeEditionEnrolment : _programmeEditionEnrolments){
            if(programmeEditionEnrolment.findStudentInProgrammeEdition().equals(student)){
                ProgrammeEdition programmeEdition = programmeEditionEnrolment.findProgrammeEditionInEnrolment();
                list.add(programmeEdition);
            }
        }
        return list;
    }


    @Override
    public ProgrammeEditionEnrolment save(ProgrammeEditionEnrolment entity) {
        _programmeEditionEnrolments.add(entity);
        return entity;
    }

    @Override
    public Iterable<ProgrammeEditionEnrolment> findAll() {
        return new ArrayList<>(_programmeEditionEnrolments);
    }

    @Override
    public Optional<ProgrammeEditionEnrolment> ofIdentity(ProgrammeEditionEnrolmentID id) {
        return _programmeEditionEnrolments.stream()
                .filter(enrolment -> enrolment.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionEnrolmentID id) {
        return _programmeEditionEnrolments.stream()
                .anyMatch(enrolment -> enrolment.identity().equals(id));
    }
}