package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.factory.IProgrammeEnrolmentListFactory;

import java.util.List;
import java.util.Optional;

public class ProgrammeEnrolmentRepositoryImpl implements IProgrammeEnrolmentRepository {

    private List<ProgrammeEnrolment> _programmeEnrolmentList;
    private IProgrammeEnrolmentFactory _programmeEnrolmentFactory;

    public ProgrammeEnrolmentRepositoryImpl(IProgrammeEnrolmentFactory programmeEnrolmentFactory, IProgrammeEnrolmentListFactory programmeEnrolmentList){

        if(programmeEnrolmentFactory == null || programmeEnrolmentList == null)
            throw new IllegalArgumentException("Factory cannot be null!");

        _programmeEnrolmentList = programmeEnrolmentList.newArrayList();
        _programmeEnrolmentFactory = programmeEnrolmentFactory;
    }

    public boolean enrolStudents(StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date enrolmentDate) throws Exception {

        ProgrammeEnrolment newProgrammeEnrolment = _programmeEnrolmentFactory.createProgrammeEnrolment(studentID, accessMethodID, programmeID, enrolmentDate);

        if(!isEnrolmentRepeated(newProgrammeEnrolment)) {
            _programmeEnrolmentList.add(newProgrammeEnrolment);
            return true;
        } else
            throw new Exception("Student is already enrolled in the programme.");
    }

    private boolean isEnrolmentRepeated(ProgrammeEnrolment newEnrolment) {
        for (ProgrammeEnrolment enrolment : _programmeEnrolmentList) {
            if (enrolment.hasSameEnrolment(newEnrolment)) {
                return true; // Return true if a repeated enrolment is found
            }
        }
        return false; // Return false if no repeated enrolment is found
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
