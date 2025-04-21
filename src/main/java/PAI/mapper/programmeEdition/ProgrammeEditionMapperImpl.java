package PAI.mapper.programmeEdition;

import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;

import java.util.Optional;

public class ProgrammeEditionMapperImpl implements  IProgrammeEditionMapper{

    private IProgrammeEditionFactory _programmeEditionFactory;

    public ProgrammeEditionMapperImpl(IProgrammeEditionFactory programmeEditionFactory){
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null");
        }
        this._programmeEditionFactory = programmeEditionFactory;
    }

    @Override
    public Optional<ProgrammeEditionDataModel> toDataModel(ProgrammeEdition programmeEdition) {
        return Optional.empty();
    }

    @Override
    public Optional<ProgrammeEdition> toDomain(ProgrammeEditionDataModel programmeEditionDataModel) {
        return Optional.empty();
    }
}