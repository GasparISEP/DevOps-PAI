package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.programmeEnrolment.IProgrammeEnrolmentFactory;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.service.programme.IProgrammeService;
import PAI.service.student.IStudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgrammeEnrolmentServiceImpl implements IProgrammeEnrolmentService {

    private IProgrammeEnrolmentFactory _peFactory;
    private IProgrammeEnrolmentRepository _peRepository;
    private IStudentService _sService;
    private IProgrammeService _progService;

    public ProgrammeEnrolmentServiceImpl(IProgrammeEnrolmentFactory programmeEnrolmentFactory, IProgrammeEnrolmentRepository programmeEnrolmentRepository,
                                         IStudentService sService, IProgrammeService progService){
        if (programmeEnrolmentFactory == null || programmeEnrolmentRepository == null || sService == null || progService == null){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        _peFactory = programmeEnrolmentFactory;
        _peRepository = programmeEnrolmentRepository;
        _sService = sService;
        _progService = progService;
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
    public US34ListOfProgrammes getProgrammesStudentIsEnrolled(StudentID studentID) {
       List<ProgrammeEnrolment> enrolments = _peRepository.getProgrammesStudentIsEnrolledIn(studentID);
       List<ProgrammeID> programmeIDs = getProgrammeIDsByProgrammeEnrolment(enrolments);
       List<Programme> programmes = _progService.getProgrammesByProgrammeIDs(programmeIDs);
       Name name = _sService.getNameByStudentID(studentID);

       List<ProgrammeSummary> programmeSummaries = mappingVOsIntoRecord(enrolments,programmes);

       return new US34ListOfProgrammes(programmeSummaries, name);
    }

    @Override
    public List<ProgrammeID> getProgrammeIDsByProgrammeEnrolment (List<ProgrammeEnrolment> list) {
        List<ProgrammeID> programmeIDList = new ArrayList<>();
        for (ProgrammeEnrolment everyProgEnrol : list) {
            ProgrammeID programmeID = everyProgEnrol.getProgrammeID();
            programmeIDList.add(programmeID);
        }
        return programmeIDList;
    }

    @Override
    public List<ProgrammeSummary> mappingVOsIntoRecord(List<ProgrammeEnrolment> enrolments, List<Programme> programmes) {
        Map<ProgrammeID, Programme> programmeMap = programmes.stream()
                .collect(Collectors.toMap(Programme::getProgrammeID, programme -> programme));

        List<ProgrammeSummary> programmeSummaries = new ArrayList<>();

        for (ProgrammeEnrolment enrolment : enrolments) {

            ProgrammeID programmeID = enrolment.getProgrammeID();

            Programme programme = programmeMap.get(programmeID);

            NameWithNumbersAndSpecialChars programmeName = programme.getProgrammeName();

            ProgrammeEnrolmentGeneratedID enrolmentID = enrolment.getProgrammeEnrolmentGeneratedID();

            ProgrammeSummary summary = new ProgrammeSummary(programmeID, programmeName, enrolmentID);

            programmeSummaries.add(summary);
        }
        return  programmeSummaries;
    }
}
