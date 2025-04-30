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
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.schoolYear.ISchoolYearService;

import java.util.List;
import java.util.Optional;

public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final IProgrammeEditionService _programmeEditionService;
    private final IProgrammeService _programmeService;
    private final ISchoolYearService _schoolYearService;

    private final IProgrammeEditionFactory _programmeEditionFactory;
    private final ISchoolYearRepository _schoolYearRepository;


    public US18_CreateProgrammeEditionForCurrentSchoolYearController(IProgrammeEditionService programmeEditionService, IProgrammeService programmeService, ISchoolYearService schoolYearService, IProgrammeEditionFactory programmeEditionFactory, ISchoolYearRepository schoolYearRepository) throws Exception {

        if (programmeEditionService == null)
            throw new Exception("Programme Edition Service cannot be null");
        if (programmeService == null)
            throw new Exception("Programme Service cannot be null");
        if (schoolYearService == null)
            throw new Exception("School Year Service cannot be null");
        if (programmeEditionFactory == null)
            throw new Exception("Programme Edition Repository cannot be null");
        if (schoolYearRepository == null)
            throw new Exception("School Year Repository cannot be null");

        this._programmeEditionService = programmeEditionService;
        this._programmeService = programmeService;
        this._schoolYearService = schoolYearService;
        this._programmeEditionFactory = programmeEditionFactory;
        this._schoolYearRepository = schoolYearRepository;
    }

    public Iterable<Programme> getAllProgrammes(){

        return _programmeService.findAll();
    }

    protected SchoolYearID getCurrentSchoolYear(){

        Optional<SchoolYearID> currentSchoolYear = _schoolYearService.getCurrentSchoolYearID();
        if(currentSchoolYear.isEmpty()){
            return null;
        }
        return currentSchoolYear.get();
    }

    public boolean createAProgrammeEditionForTheCurrentSchoolYear(Programme programme, SchoolYearID sYID) throws Exception {

        if(programme == null){
            throw new Exception("Programme cannot be null");
        }
        if(sYID == null){
            throw new Exception("School Year ID cannot be null");
        }

        ProgrammeID pID = programme.identity();

        try {
            ProgrammeEdition programmeEdition = _programmeEditionService.createProgrammeEdition(pID, sYID);
            Optional<ProgrammeEdition> programmeEditionOptional = _programmeEditionService.saveProgrammeEdition(programmeEdition);
            if(programmeEditionOptional.isPresent()){
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
