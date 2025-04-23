package PAI.mapper.schoolYearID;

import PAI.VOs.SchoolYearID;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchoolYearIDMapperImpl implements ISchoolYearIDMapper {

    public SchoolYearIDDataModel toDataModel(SchoolYearID schoolYearID) {
        if (schoolYearID == null) {
            throw new IllegalArgumentException("SchoolYearID cannot be null");
        }
        SchoolYearIDDataModel schoolYearIDDataModel = new SchoolYearIDDataModel(schoolYearID.toString());

        return schoolYearIDDataModel;
    }

    public SchoolYearID toDomain(SchoolYearIDDataModel schoolYearIDDataModel) {
        if (schoolYearIDDataModel == null) {
            throw new IllegalArgumentException("SchoolYearIDDataModel cannot be null");
        }
        UUID uuid = UUID.fromString(schoolYearIDDataModel.getId());
        SchoolYearID schoolYearID = new SchoolYearID(uuid);
        return schoolYearID;
    }
}