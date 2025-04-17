package PAI.controller;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.programme.Programme;
import PAI.repository.ProgrammeEnrolmentRepository;
import PAI.repository.StudentRepository;
import PAI.persistence.mem.accessMethod.AccessMethodRepositoryImpl;
import PAI.persistence.mem.ProgrammeRepositoryImpl;

import java.util.Optional;

public class US09_EnrolStudentInProgrammeController {
    private final StudentRepository _studentRepository;
    private final AccessMethodRepositoryImpl _accessMethodRepository;
    private final ProgrammeRepositoryImpl _programmeRepository;
    private final ProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    //Constructor
    public US09_EnrolStudentInProgrammeController(StudentRepository studentRepository, AccessMethodRepositoryImpl accessMethodRepository,
                                                  ProgrammeRepositoryImpl programmeRepository, ProgrammeEnrolmentRepository programmeEnrolmentRepository) {
        if (studentRepository == null) {
            throw new IllegalArgumentException("Student Repository cannot be null.");
        }
        if (accessMethodRepository == null) {
            throw new IllegalArgumentException("Access Method Repository cannot be null.");
        }
        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme List cannot be null.");
        }
        if (programmeEnrolmentRepository == null) {
            throw new IllegalArgumentException("Enrolment Repository cannot be null.");
        }

        this._studentRepository = studentRepository;
        this._accessMethodRepository = accessMethodRepository;
        this._programmeRepository = programmeRepository;
        this._programmeEnrolmentRepository = programmeEnrolmentRepository;
    }

    public Optional<Student> getStudentByUniqueNumber(StudentID studentID) {
        return _studentRepository.getStudentByID(studentID);
    }

    public Optional<AccessMethod> getAccessMethodByName(NameWithNumbersAndSpecialChars accessMethod) {
        return _accessMethodRepository.getAccessMethodByName(accessMethod);
    }

    public Optional<AccessMethod> getAccessMethodByID(AccessMethodID accessMethodID) {
        return _accessMethodRepository.ofIdentity(accessMethodID);
    }

    public Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars programmeName) {
        return _programmeRepository.getProgrammeByName(programmeName);
    }

    public Optional<Programme> getProgrammeByID(ProgrammeID programmeID) {
        return _programmeRepository.ofIdentity(programmeID);
    }

    public boolean enrolStudentInProgramme(StudentID s1, AccessMethodID am1, ProgrammeID p1, Date date) throws Exception {
        validateEnrolmentParameters(s1, am1, p1, date);
        _programmeEnrolmentRepository.enrolStudents(s1, am1, p1, date);
        return true;
    }

    private void validateEnrolmentParameters(StudentID s1, AccessMethodID am1, ProgrammeID p1, Date date) throws Exception {
        if (s1 == null) {
            throw new Exception("Student cannot be null");
        }
        if (am1 == null) {
            throw new Exception("Access method cannot be null");
        }
        if (p1 == null) {
            throw new Exception("Programme cannot be null");
        }
        if (date == null) {
            throw new Exception("Date cannot be null or empty");
        }
    }
}