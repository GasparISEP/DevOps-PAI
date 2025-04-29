package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.programme.IProgrammeService;

import java.util.List;
import java.util.Optional;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final IProgrammeEditionFactory _programmeEditionFactory;
    private final ISchoolYearRepository _schoolYearRepository;
    private final IProgrammeRepository _programmeRepository;
    private final IProgrammeService _programmeService;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(IProgrammeService programmeService, IProgrammeEditionFactory programmeEditionFactory, ISchoolYearRepository schoolYearRepository, IProgrammeRepository programmeRepository) throws Exception {

        if (programmeEditionFactory == null)
            throw new Exception("Programme Edition Repository cannot be null");
        if (schoolYearRepository == null)
            throw new Exception("School Year Repository cannot be null");
        if (programmeRepository == null)
            throw new Exception("Programme Repository cannot be null");

        _programmeEditionFactory = programmeEditionFactory;
        _schoolYearRepository = schoolYearRepository;
        _programmeRepository = programmeRepository;
        _programmeService = programmeService;
    }

    public Iterable<Programme> getAllProgrammes(){

        return _programmeService.findAll();
    }

    public boolean createAProgrammeEditionForTheCurrentSchoolYear(Programme programme) throws Exception {

        ProgrammeID pID = programme.identity();

        Optional<SchoolYear> currentSchoolYear =_schoolYearRepository.getCurrentSchoolYear();
        if(currentSchoolYear.isEmpty())
            return false;

        SchoolYearID sYID = currentSchoolYear.get().identity();

        try {
            ProgrammeEdition programmeEdition = _programmeEditionFactory.createProgrammeEdition(pID, sYID);
            return programmeEdition != null;
        } catch (Exception e) {
            return false;
        }
    }
}
