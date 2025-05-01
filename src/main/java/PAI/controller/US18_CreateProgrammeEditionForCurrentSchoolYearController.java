package PAI.controller;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class US18_CreateProgrammeEditionForCurrentSchoolYearController {

    private final IProgrammeEditionService _programmeEditionService;
    private final IProgrammeService _programmeService;
    private final ISchoolYearService _schoolYearService;

    public US18_CreateProgrammeEditionForCurrentSchoolYearController(IProgrammeEditionService programmeEditionService, IProgrammeService programmeService, ISchoolYearService schoolYearService) throws Exception {

        if (programmeEditionService == null)
            throw new Exception("Programme Edition Service cannot be null");
        if (programmeService == null)
            throw new Exception("Programme Service cannot be null");
        if (schoolYearService == null)
            throw new Exception("School Year Service cannot be null");

        this._programmeEditionService = programmeEditionService;
        this._programmeService = programmeService;
        this._schoolYearService = schoolYearService;
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
