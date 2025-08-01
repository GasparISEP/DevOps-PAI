package PAI.persistence.mem.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;


import java.util.*;
import java.util.stream.Collectors;

public class ProgrammeEditionEnrolmentRepositoryImpl implements IProgrammeEditionEnrolmentRepository {


    private final Set<ProgrammeEditionEnrolment> _programmeEditionEnrolments;

    public ProgrammeEditionEnrolmentRepositoryImpl(IProgrammeEditionEnrolmentListFactory iProgrammeEditionEnrolmentListFactory) {

        _programmeEditionEnrolments = iProgrammeEditionEnrolmentListFactory.newListProgrammeEditionEnrolment();
    }

    @Override
    public boolean isStudentEnrolledInThisProgrammeEdition(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        if(studentId == null || programmeEditionId == null) {
            return false;
        }
        for (ProgrammeEditionEnrolment enrollment : _programmeEditionEnrolments) {
            if (enrollment.hasSameStudent(studentId) && enrollment.hasSameProgrammeEdition(programmeEditionId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countEnrolledStudentsByProgrammeEditionIds(List<ProgrammeEditionID> programmeEditionIDs) {
//        Set<StudentID> studentIDs = new HashSet<>();
//        for (ProgrammeEditionEnrolment enrollment : _programmeEditionEnrolments) {
//            if (enrollment.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear, programmeEditionIDs)) {
//                StudentID studentID = enrollment.findStudentInProgrammeEdition();
//                studentIDs.add(studentID);
//            }
//        }
//        return studentIDs.size();
        return 0;
    }

    //US21 - Get The Number Of Students Enrolled In A Programme Edition
    @Override
    public List<ProgrammeEditionEnrolment> getAllProgrammeEditionsEnrollmentByProgrammeEditionID(ProgrammeEditionID programmeEditionId){
        List<ProgrammeEditionEnrolment> allProgrammeEditionEnrolments = new ArrayList<>();

        for(ProgrammeEditionEnrolment programmeEditionEnrolment : _programmeEditionEnrolments)
            if(programmeEditionEnrolment.findProgrammeEditionInEnrolment().equals(programmeEditionId)){
                allProgrammeEditionEnrolments.add(programmeEditionEnrolment);
            }
        return allProgrammeEditionEnrolments;
    }

    @Override
    public List<ProgrammeEditionID> findProgrammeEditionsThatStudentIsEnrolled(StudentID studentId){
        return _programmeEditionEnrolments.stream()
                .filter(enrolment -> enrolment.findStudentInProgrammeEdition().equals(studentId) && enrolment.isEnrolmentActive())
                .map(ProgrammeEditionEnrolment::findProgrammeEditionInEnrolment)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProgrammeEditionEnrolment> findByStudentAndProgrammeEdition(StudentID studentID, ProgrammeEditionID programmeEditionID) {
        if (studentID == null || programmeEditionID == null) {
            return Optional.empty();
        }

        for (ProgrammeEditionEnrolment enrolment : _programmeEditionEnrolments) {
            if (enrolment.hasSameStudent(studentID) &&
                    enrolment.hasSameProgrammeEdition(programmeEditionID)) {
                return Optional.of(enrolment);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<ProgrammeEditionEnrolment> getInternalSet() {
        return this._programmeEditionEnrolments;
    }

    @Override
    public ProgrammeEditionEnrolment save(ProgrammeEditionEnrolment entity) {
        if (entity == null ){
            throw new IllegalArgumentException("Entity cannot be null");
        }
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
            if (!ofIdentity(id).isPresent()){
                return false;
            }
            return true;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentRepositoryImpl that = (ProgrammeEditionEnrolmentRepositoryImpl) o;
        return Objects.equals(_programmeEditionEnrolments, that._programmeEditionEnrolments);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_programmeEditionEnrolments);
    }

    @Override
    public boolean existsByID(ProgrammeEditionEnrolmentID id) {
        return _programmeEditionEnrolments.stream()
                .anyMatch(enrolment -> enrolment.identity().equals(id));
    }

}