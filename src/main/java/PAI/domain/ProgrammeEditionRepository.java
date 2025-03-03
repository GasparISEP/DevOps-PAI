package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeEditionRepository {

    private final List<ProgrammeEdition> _programmeEditionList;
    private final ProgrammeEditionFactory _programmeEditionFactory;

    public ProgrammeEditionRepository (ProgrammeEditionFactory programmeEditionFactory, ProgrammeEditionListFactory programmeEditionListFactory) {
        _programmeEditionList = programmeEditionListFactory.getProgrammeEditionList();
        _programmeEditionFactory = programmeEditionFactory;
    }

    public boolean createProgrammeEdition(Programme programme, SchoolYear schoolYear) {
        try {
            ProgrammeEdition programmeEdition = _programmeEditionFactory.createProgrammeEdition(programme, schoolYear);
            if (!isProgrammeEditionAlreadyRegistered(programmeEdition)) {
                _programmeEditionList.add(programmeEdition);
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isProgrammeEditionAlreadyRegistered(ProgrammeEdition programmeEdition) {

        return _programmeEditionList.contains(programmeEdition);
    }

    public Optional<ProgrammeEdition> findProgrammeEditionBySchoolYearAndProgramme(
            Programme programme,
            SchoolYear schoolYear) {
        for (ProgrammeEdition programmeEdition : _programmeEditionList) {
            if (programmeEdition.findProgrammeInProgrammeEdition().equals(programme) &&
                    programmeEdition.findSchoolYearInProgrammeEdition().equals(schoolYear)) {
                return Optional.of(programmeEdition);
            }
        }
        return Optional.empty();
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionList;
    }

    public Programme findProgrammeInProgrammeEdition(ProgrammeEdition programmeEdition) {
        Programme programme = programmeEdition.findProgrammeInProgrammeEdition();
        return programme;
    }
}
