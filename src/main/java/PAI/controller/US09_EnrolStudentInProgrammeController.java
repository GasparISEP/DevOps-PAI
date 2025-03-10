package PAI.controller;

import PAI.domain.*;
import PAI.repository.AccessMethodRepository;
import PAI.repository.ProgrammeRepository;
import PAI.repository.StudentRepository;

import java.util.Optional;

public class US09_EnrolStudentInProgrammeController {
    private final StudentRepository _studentRepository;
    private final AccessMethodRepository _accessMethodRepository;
    private final ProgrammeRepository _programmeList;
    private final ProgrammeEnrolmentRepository _programmeEnrolmentRepository;

    //Constructor
    public US09_EnrolStudentInProgrammeController(StudentRepository studentRepository, AccessMethodRepository accessMethodRepository,
                                                  ProgrammeRepository programmeList, ProgrammeEnrolmentRepository programmeEnrolmentRepository) {
        if (studentRepository == null) {
            throw new IllegalArgumentException("studentRepository cannot be null.");
        }
        if (accessMethodRepository == null) {
            throw new IllegalArgumentException("accessMethodRepository cannot be null.");
        }
        if (programmeList == null) {
            throw new IllegalArgumentException("programmeList cannot be null.");
        }
        if (programmeEnrolmentRepository == null) {
            throw new IllegalArgumentException("enrolmentRepository cannot be null.");
        }

        this._studentRepository = studentRepository;
        this._accessMethodRepository = accessMethodRepository;
        this._programmeList = programmeList;
        this._programmeEnrolmentRepository = programmeEnrolmentRepository;
    }

    public Optional<Student> getStudentByUniqueNumber(int uniqueNumber) {
        return _studentRepository.getStudentByUniqueNumber(uniqueNumber);
    }

    public Optional<AccessMethod> getAccessMethodByName(String accessMethod) {
        return _accessMethodRepository.getAccessMethodByName(accessMethod);
    }

    public Optional<Programme> getProgrammeByName(String programmeName) {
        return _programmeList.getProgrammeByName(programmeName);
    }

    public boolean enrolStudent(Student s1, AccessMethod am1, Programme p1, String date) throws Exception {
        validateEnrolmentParameters(s1, am1, p1, date);
        _programmeEnrolmentRepository.enrolStudents(s1, am1, p1, date);
        return true;
    }

    private void validateEnrolmentParameters(Student s1, AccessMethod am1, Programme p1, String date) throws Exception {
        if (s1 == null) {
            throw new Exception("Student cannot be null");
        }
        if (am1 == null) {
            throw new Exception("Access method cannot be null");
        }
        if (p1 == null) {
            throw new Exception("Programme cannot be null");
        }
        if (date == null || date.isEmpty()) {
            throw new Exception("Date cannot be null or empty");
        }
    }
}