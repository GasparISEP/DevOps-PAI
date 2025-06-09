package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionGeneratedIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionGeneratedIDMapperImpl implements IProgrammeEditionGeneratedIDMapper {

    @Override
    public ProgrammeEditionGeneratedID toDomain(ProgrammeEditionGeneratedIDDataModel dataModel) throws Exception {
        if (dataModel == null) {
            throw new IllegalArgumentException("ProgrammeEditionGeneratedIDDataModel cannot be null");
        }
        return new ProgrammeEditionGeneratedID(dataModel.getGeneratedId());
    }

    @Override
    public ProgrammeEditionGeneratedIDDataModel toDataModel(ProgrammeEditionGeneratedID domain) throws Exception {
        if (domain == null) {
            throw new IllegalArgumentException("ProgrammeEditionGeneratedID cannot be null");
        }
        return new ProgrammeEditionGeneratedIDDataModel(domain.getProgrammeEditionGID());
    }
}

