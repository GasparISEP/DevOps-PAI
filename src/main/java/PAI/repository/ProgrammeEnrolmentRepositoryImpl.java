package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.IProgrammeEnrolmentListFactory;

import java.util.List;
import java.util.Optional;

public class ProgrammeEnrolmentRepositoryImpl implements IProgrammeEnrolmentRepository {

    private List<ProgrammeEnrolment> _programmeEnrolmentList;

    public ProgrammeEnrolmentRepositoryImpl(IProgrammeEnrolmentListFactory programmeEnrolmentList){

        if(programmeEnrolmentList == null)
            throw new IllegalArgumentException("List cannot be null!");

        _programmeEnrolmentList = programmeEnrolmentList.newArrayList();

    }

    public boolean isStudentEnrolled(StudentID studentID, ProgrammeID programmeID) {
        for (ProgrammeEnrolment existingEnrolment : _programmeEnrolmentList) {
            if (existingEnrolment.hasSameStudent(studentID) && existingEnrolment.hasSameProgramme(programmeID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ProgrammeEnrolment save(ProgrammeEnrolment entity) {
        _programmeEnrolmentList.add(entity);
        return entity;
    };

    @Override
    public Iterable<ProgrammeEnrolment> findAll() {
        if(_programmeEnrolmentList.isEmpty())
            throw new IllegalArgumentException("Programme Enrolment must not be empty.");
        return _programmeEnrolmentList;
    };

    @Override
    public Optional<ProgrammeEnrolment> ofIdentity(ProgrammeEnrolmentID peID) {
        return _programmeEnrolmentList.stream()
                .filter(pe -> pe.identity().equals(peID))
                .findAny();
    };

    @Override
    public boolean containsOfIdentity(ProgrammeEnrolmentID peID) {
        if (!ofIdentity(peID).isPresent()){
            return false;
        }
        return true;
    };
}
