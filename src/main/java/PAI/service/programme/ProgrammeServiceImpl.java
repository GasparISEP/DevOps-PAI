package PAI.service.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeFactory;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements IProgrammeService {

    private final IProgrammeFactory _programmeFactory;
    private final IProgrammeRepository _programmeRepository;

    public ProgrammeServiceImpl(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository) {
        if (programmeFactory == null) {
            throw new IllegalArgumentException("Programme Factory cannot be null");
        }
        _programmeFactory = programmeFactory;

        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme Repository cannot be null");
        }
        _programmeRepository = programmeRepository;
    }

    public boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {
        Programme programme = _programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
        Programme savedProgramme = _programmeRepository.save(programme);

        if (savedProgramme == null) {
            throw new IllegalArgumentException("Failed to save Programme.");
        }
        return true;
    }

    public boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID programmeDirectorID) throws Exception {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null");
        }
        if (programmeDirectorID == null) {
            throw new IllegalArgumentException("ProgrammeDirectorID cannot be null");
        }

        Programme programme = _programmeRepository.ofIdentity(programmeID)
                .orElseThrow(() -> new IllegalArgumentException("Programme not found"));

        programme.newProgrammeDirector(programmeDirectorID);

        _programmeRepository.save(programme);

        return true;
    }

    public List<ProgrammeID> findProgrammeByDepartment(DepartmentID id) {
        List<ProgrammeID> programmesWithDepartment = new ArrayList<>();
        for (Programme programme : _programmeRepository.findAll()) {
            if(programme.isInDepartment(id)){
                programmesWithDepartment.add(programme.identity());
            }
        }
        return programmesWithDepartment;
    }

    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID id) throws Exception {
        return _programmeRepository.getProgrammesByDegreeTypeID(id);
    }

    public Optional<ProgrammeID> findProgrammeIdByProgramme(Programme prog) throws Exception {
        return _programmeRepository.findProgrammeIdByProgramme(prog);
    }

    public List<Programme> getAllProgrammes() {
        return _programmeRepository.getAllProgrammes();
    }

    public Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name) {
        return  _programmeRepository.getProgrammeByName(name);
    }

    public Programme getProgrammeByAcronym(Acronym acronym) {
        return _programmeRepository.getProgrammeByAcronym(acronym);
    }

    public List<ProgrammeID> getAllProgrammeIDs() {
        return _programmeRepository.getAllProgrammesIDs();
    }
}
