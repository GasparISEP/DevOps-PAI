package PAI.repository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.factory.IProgrammeEditionFactory;
import PAI.factory.IProgrammeEditionListFactory;
import PAI.domain.SchoolYear;

import java.util.List;
import java.util.Optional;

public class ProgrammeEditionRepository {

    private final IProgrammeEditionFactory _IprogrammeEditionFactory;
    private final List<ProgrammeEdition> _ProgrammeEditions;

    public ProgrammeEditionRepository (IProgrammeEditionFactory iProgrammeEditionFactory, IProgrammeEditionListFactory iProgrammeEditionListFactory) {

        _IprogrammeEditionFactory = iProgrammeEditionFactory;
        _ProgrammeEditions = iProgrammeEditionListFactory.createProgrammeEditionArrayList();
    }

    public boolean createProgrammeEdition(Programme programme, SchoolYear schoolYear) {
        try {
            ProgrammeEdition programmeEdition = _IprogrammeEditionFactory.createProgrammeEdition(programme, schoolYear);
            if (!isProgrammeEditionAlreadyRegistered(programmeEdition)) {
                _ProgrammeEditions.add(programmeEdition);
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isProgrammeEditionAlreadyRegistered(ProgrammeEdition programmeEdition) {

        return _ProgrammeEditions.contains(programmeEdition);
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _ProgrammeEditions;
    }

    public Programme findProgrammeInProgrammeEdition(ProgrammeEdition programmeEdition) {
        Programme programme = programmeEdition.findProgrammeInProgrammeEdition();
        return programme;
    }
}
