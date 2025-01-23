package PAI.domain;

import java.util.ArrayList;
import java.util.Optional;

public class ProgrammeEditionRepository {

    private ArrayList<ProgrammeEdition> _programmeEditionRepository = new ArrayList<>();

    public boolean createProgrammeEdition (Programme programme, SchoolYear schoolYear){
        try {
            ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
            if(!isProgrammeEditionAlreadyRegistered(programmeEdition)){
                _programmeEditionRepository.add(programmeEdition);
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }


       private boolean isProgrammeEditionAlreadyRegistered (ProgrammeEdition programmeEdition){

        return _programmeEditionRepository.contains(programmeEdition);
    }
}
