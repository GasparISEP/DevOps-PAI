package PAI.domain;

public class ProgrammeEditionFactory {

    public ProgrammeEdition createProgrammeEdition(Programme programme, SchoolYear schoolYear) throws Exception {
        return new ProgrammeEdition(programme, schoolYear);
    }
}
