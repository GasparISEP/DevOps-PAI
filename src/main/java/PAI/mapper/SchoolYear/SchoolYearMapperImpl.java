package PAI.mapper.SchoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchoolYearMapperImpl implements ISchoolYearMapper {

    public SchoolYearDataModel toDataModel(SchoolYear schoolYear) {

        if (schoolYear == null) {
            throw new IllegalArgumentException("School Year cannot be null");
        }
        SchoolYearIDDataModel schoolYearIDDataModel = new SchoolYearIDDataModel(schoolYear.identity().toString());

        SchoolYearDataModel schoolYearDataModel = new SchoolYearDataModel(schoolYearIDDataModel, schoolYear.getDescription().getDescription(),
                schoolYear.getStartDate().getLocalDate(), schoolYear.getEndDate().getLocalDate());
        return schoolYearDataModel;
    }

    public SchoolYear toDomain(SchoolYearDataModel schoolYearDataModel, ISchoolYearFactory schoolYearFactory) {
        if (schoolYearDataModel == null || schoolYearFactory == null) {
            throw new IllegalArgumentException("School Year DataModel and/or Factory cannot be null");
        }

        UUID uuid = UUID.fromString(schoolYearDataModel.getId().getId());
        Description description = new Description(schoolYearDataModel.getDescription());
        Date startDate = new Date(schoolYearDataModel.getStartDate());
        Date endDate = new Date(schoolYearDataModel.getEndDate());
        SchoolYear schoolYear = schoolYearFactory.createSchoolYear(uuid, description, startDate, endDate);
        return schoolYear;
    }

}
