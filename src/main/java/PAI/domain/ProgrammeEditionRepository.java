package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeEditionRepository {

    private final ArrayList<ProgrammeEdition> _programmeEditionRepository;
    private final ProgrammeEditionFactory _programmeEditionFactory;

    public ProgrammeEditionRepository (ProgrammeEditionFactory programmeEditionFactory) {
        _programmeEditionRepository = new ArrayList<>();
        _programmeEditionFactory = programmeEditionFactory;
    }

    public boolean createProgrammeEdition(Programme programme, SchoolYear schoolYear) {
        try {
            ProgrammeEdition programmeEdition = _programmeEditionFactory.createProgrammeEdition(programme, schoolYear);
            if (!isProgrammeEditionAlreadyRegistered(programmeEdition)) {
                _programmeEditionRepository.add(programmeEdition);
                return true;
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isProgrammeEditionAlreadyRegistered(ProgrammeEdition programmeEdition) {

        return _programmeEditionRepository.contains(programmeEdition);
    }

    public Optional<ProgrammeEdition> findProgrammeEditionBySchoolYearAndProgramme(
            Programme programme,
            SchoolYear schoolYear) {
        for (ProgrammeEdition programmeEdition : _programmeEditionRepository) {
            if (programmeEdition.findProgrammeInProgrammeEdition().equals(programme) &&
                    programmeEdition.findSchoolYearInProgrammeEdition().equals(schoolYear)) {
                return Optional.of(programmeEdition);
            }
        }
        return Optional.empty();
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository;
    }

    public Programme findProgrammeInProgrammeEdition(ProgrammeEdition programmeEdition) {
        Programme programme = programmeEdition.findProgrammeInProgrammeEdition();
        return programme;
    }
}
