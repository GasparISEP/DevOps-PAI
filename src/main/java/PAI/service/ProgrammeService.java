package PAI.service;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeFactory;
import PAI.repository.ITeacherRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService implements IProgrammeService {

    private final IProgrammeFactory _programmeFactory;
    private final IProgrammeRepository _programmeRepository;
    private final ITeacherRepository _teacherRepository;

    public ProgrammeService(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository, ITeacherRepository teacherRepository) {
        if (programmeFactory == null) {
            throw new IllegalArgumentException("Programme Factory cannot be null");
        }
        _programmeFactory = programmeFactory;

        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme Repository cannot be null");
        }
        _programmeRepository = programmeRepository;

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }
        _teacherRepository = teacherRepository;
    }

    public Programme registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {
        Programme programme = _programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
        return _programmeRepository.save(programme);
    }

    public boolean changeProgrammeDirector(Programme programme, Teacher programmeDirector) throws Exception {
        if (programme == null) {
            throw new IllegalArgumentException("Programme cannot be null");
        }
        if (programmeDirector == null) {
            throw new IllegalArgumentException("Programme Director cannot be null");
        }

        ProgrammeID programmeID = programme.identity();
        TeacherID programmeDirectorID = programmeDirector.identity();

        return _programmeRepository.changeProgrammeDirector(programmeID,programmeDirectorID);
    }
}
