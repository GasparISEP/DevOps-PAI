package PAI.mapper.programmeEditionEnrolment;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProgrammeEditionEnrolmentIDMapperImpl implements IProgrammeEditionEnrolmentIDMapper {

    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final IStudentIDMapper _studentIdMapper;

    public ProgrammeEditionEnrolmentIDMapperImpl(IProgrammeEditionIdMapper programmeEditionIdMapper, IStudentIDMapper studentIdMapper) {
        this._programmeEditionIdMapper = validate(programmeEditionIdMapper, "ProgrammeEditionIDMapper");
        this._studentIdMapper = validate(studentIdMapper, "StudentIDMapper");
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

    private <T> T validate(T instance, String name) {
        if (instance == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
        return instance;
    }
}
