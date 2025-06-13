package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.programmeEnrolment.IProgrammeEnrolmentFactory;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeEnrolmentServiceImpl implements IProgrammeEnrolmentService {

    private IProgrammeEnrolmentFactory _peFactory;
    private IProgrammeEnrolmentRepository _peRepository;

    public ProgrammeEnrolmentServiceImpl(IProgrammeEnrolmentFactory programmeEnrolmentFactory, IProgrammeEnrolmentRepository programmeEnrolmentRepository){
        if (programmeEnrolmentFactory == null || programmeEnrolmentRepository == null){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        _peFactory = programmeEnrolmentFactory;
        _peRepository = programmeEnrolmentRepository;
    }

    public ProgrammeEnrolment enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws Exception {
        if (studentID == null || accessMethodID == null || programmeID == null || date == null){
            throw new Exception("Not possible to enrol student");
        }

        ProgrammeEnrolment pe = _peFactory.createProgrammeEnrolment(studentID,accessMethodID, programmeID, date);

        ProgrammeEnrolmentID programmeEnrolmentID = pe.getProgrammeEnrolmentID();

        if(_peRepository.containsOfIdentity(programmeEnrolmentID)){
            throw new IllegalArgumentException("Programme Enrolment already exists.");
        }

        return  _peRepository.save(pe);


    }

    @Override
    public ProgrammeEnrolment findEnrolmentByStudentAndProgramme(
            StudentID studentID,
            ProgrammeID programmeID
    ) {
        Optional<ProgrammeEnrolment> opt = _peRepository.findByStudentIDAndProgrammeID(studentID, programmeID);
        return opt.orElse(null);
    }

    @Override
    public List<ProgrammeEnrolment> listOfProgrammesStudentIsEnrolledIn(StudentID studentID) {
        return _peRepository.getProgrammesStudentIsEnrolledIn(studentID);
    }

    public List<ProgrammeID> getProgrammeIDsByProgrammeEnrolment (List<ProgrammeEnrolment> list) {
        List<ProgrammeID> programmeIDList = new ArrayList<>();
        for (ProgrammeEnrolment everyProgEnrol : list) {
            ProgrammeID programmeID = everyProgEnrol.getProgrammeID();
            programmeIDList.add(programmeID);
        }
        return programmeIDList;
    }
}
