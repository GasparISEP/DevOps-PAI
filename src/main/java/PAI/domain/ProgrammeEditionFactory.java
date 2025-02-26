package PAI.domain;

public class ProgrammeEditionFactory {

    public ProgrammeEdition createProgrammeEdition(Programme programme, SchoolYear schoolYear) {
        return new ProgrammeEdition(programme, schoolYear);
    }
}
