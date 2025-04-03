package PAI.controller;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.ProgrammeEnrolmentRepository;
import PAI.repository.StudentRepository;
import PAI.repository.accessMethodRepository.AccessMethodRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;

import java.util.Optional;

public class US09_EnrolStudentInProgrammeController {
    private final StudentRepository _studentRepository;
    private final AccessMethodRepositoryImpl _accessMethodRepository;
    private final ProgrammeDDDRepositoryImpl _programmeRepository;
    private final ProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    //Constructor
    public US09_EnrolStudentInProgrammeController(StudentRepository studentRepository, AccessMethodRepositoryImpl accessMethodRepository,
                                                  ProgrammeDDDRepositoryImpl programmeRepository, ProgrammeEnrolmentRepository programmeEnrolmentRepository) {
        if (studentRepository == null) {
            throw new IllegalArgumentException("studentRepository cannot be null.");
        }
        if (accessMethodRepository == null) {
            throw new IllegalArgumentException("accessMethodRepository cannot be null.");
        }
        if (programmeRepository == null) {
            throw new IllegalArgumentException("programmeList cannot be null.");
        }
        if (programmeEnrolmentRepository == null) {
            throw new IllegalArgumentException("enrolmentRepository cannot be null.");
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

    public Optional<ProgrammeDDD> getProgrammeByName(NameWithNumbersAndSpecialChars programmeName) {
        return _programmeRepository.getProgrammeByName(programmeName);
    }

    public Optional<ProgrammeDDD> getProgrammeByID(ProgrammeID programmeID) {
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