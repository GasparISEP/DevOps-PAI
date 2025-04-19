package PAI.mapper.programmeEdition;

import PAI.domain.programmeEdition.IProgrammeEditionFactory;

public class ProgrammeEditionMapperImpl{

    private final IProgrammeEditionFactory _programmeEditionFactory;

    public ProgrammeEditionMapperImpl(IProgrammeEditionFactory programmeEditionFactory){
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null");
        }
        this._programmeEditionFactory = programmeEditionFactory;
    }
}