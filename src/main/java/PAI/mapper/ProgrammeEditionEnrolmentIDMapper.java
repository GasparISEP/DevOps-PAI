package PAI.mapper;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProgrammeEditionEnrolmentIDMapper implements IProgrammeEditionEnrolmentIDMapper {

    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final IStudentIDMapper _studentIdMapper;

    public ProgrammeEditionEnrolmentIDMapper(IProgrammeEditionIdMapper programmeEditionIdMapper, IStudentIDMapper studentIdMapper) {
        if (programmeEditionIdMapper == null) {
            throw new IllegalArgumentException("ProgrammeEditionIDMapper cannot be null");
        }

        if (studentIdMapper == null) {
            throw new IllegalArgumentException("StudentIDMapper cannot be null");
        }

        this._programmeEditionIdMapper = programmeEditionIdMapper;
        this._studentIdMapper = studentIdMapper;
    }

    @Override
    public Optional<ProgrammeEditionEnrolmentID> toDomain(ProgrammeEditionEnrolmentIDDataModel dataModel) {
        if (dataModel == null) return Optional.empty();

        try {
            ProgrammeEditionIdDataModel editionDM = dataModel.getProgrammeEditionIdDataModel();
            StudentIDDataModel studentDM = dataModel.getStudentIdDataModel();

            ProgrammeEditionID editionID = _programmeEditionIdMapper.toDomain(editionDM);
            StudentID studentID = _studentIdMapper.dataModelToDomain(studentDM);

            return Optional.of(new ProgrammeEditionEnrolmentID(editionID, studentID));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProgrammeEditionEnrolmentIDDataModel> toDataModel(ProgrammeEditionEnrolmentID domainId) {
        if (domainId == null) return Optional.empty();

        try {
            ProgrammeEditionID editionID = domainId.getProgrammeEditionId();
            StudentID studentID = domainId.getStudentiD();

            ProgrammeEditionIdDataModel editionDM = _programmeEditionIdMapper.toDataModel(editionID);
            StudentIDDataModel studentDM = _studentIdMapper.domainToDataModel(studentID);

            return Optional.of(new ProgrammeEditionEnrolmentIDDataModel(studentDM, editionDM));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
