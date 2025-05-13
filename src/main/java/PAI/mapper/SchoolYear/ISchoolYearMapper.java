package PAI.mapper.SchoolYear;


import PAI.domain.schoolYear.SchoolYear;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;

public interface ISchoolYearMapper {
    SchoolYear toDomain(SchoolYearDataModel schoolYearDataModel);
    SchoolYearDataModel toDataModel(SchoolYear schoolYear);
}
