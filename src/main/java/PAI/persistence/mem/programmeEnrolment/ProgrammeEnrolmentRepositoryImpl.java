package PAI.persistence.mem.programmeEnrolment;

import PAI.VOs.ProgrammeEnrolmentGeneratedID;
import PAI.VOs.ProgrammeEnrolmentID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;

import java.util.ArrayList;
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

    @Override
    public List<ProgrammeID> findProgrammesByStudent(StudentID studentID) {
        List<ProgrammeID> programmes = new ArrayList<>();
        for (ProgrammeEnrolment enrolment : _programmeEnrolmentList) {
            if (enrolment.hasSameStudent(studentID)) {
                programmes.add(enrolment.getProgrammeID());
            }
        }
        return programmes;
    }

    @Override
    public Optional<ProgrammeEnrolment> findByStudentIDAndProgrammeID(StudentID studentID, ProgrammeID programmeID) {
        return _programmeEnrolmentList.stream()
                .filter(pe -> pe.hasSameStudent(studentID) && pe.hasSameProgramme(programmeID))
                .findFirst();
    }

    @Override
    public Optional<ProgrammeEnrolment> findByGeneratedID(ProgrammeEnrolmentGeneratedID gid) {
        return _programmeEnrolmentList.stream()
                .filter(pe -> pe.getProgrammeEnrolmentGeneratedID().equals(gid))
                .findFirst();
    }

    @Override
    public List<ProgrammeEnrolment> listOfProgrammesStudentIsEnrolledIn(StudentID studentID) {
        List<ProgrammeEnrolment> programmes = new ArrayList<>();
        for (ProgrammeEnrolment existingEnrolment : _programmeEnrolmentList) {
            if (existingEnrolment.hasSameStudent(studentID)) {
                programmes.add(existingEnrolment);
            }
        }
        return programmes;
    }
}
