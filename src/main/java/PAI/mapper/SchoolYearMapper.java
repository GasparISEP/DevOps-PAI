package PAI.mapper;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.persistence.datamodel.SchoolYearDataModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchoolYearMapper {

    public SchoolYearDataModel toDataModel(SchoolYear schoolYear) {

        if (schoolYear == null){
            throw new IllegalArgumentException("School Year cannot be null");
        }

        SchoolYearDataModel schoolYearDataModel = new SchoolYearDataModel(
                schoolYear.identity().toString(), schoolYear.getDescription().getDescription(),
                schoolYear.getStartDate().getLocalDate(), schoolYear.getEndDate().getLocalDate());
        return schoolYearDataModel;
    }

    public SchoolYear toDomain(SchoolYearDataModel schoolYearDataModel, ISchoolYearFactory schoolYearFactory) {
        if (schoolYearDataModel == null || schoolYearFactory == null){
            throw new IllegalArgumentException("School Year DataModel and/or Factory cannot be null");
        }

        UUID uuid = UUID.fromString(schoolYearDataModel.getId());
        Description description = new Description(schoolYearDataModel.getDescription());
        Date startDate = new Date(schoolYearDataModel.getStartDate());
        Date endDate = new Date(schoolYearDataModel.getEndDate());
        SchoolYear schoolYear = schoolYearFactory.createSchoolYear(uuid, description, startDate, endDate);
        return schoolYear;
    }

}
