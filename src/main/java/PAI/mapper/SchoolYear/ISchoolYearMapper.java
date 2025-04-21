package PAI.mapper.SchoolYear;


import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;

public interface ISchoolYearMapper {
    SchoolYear toDomain(SchoolYearDataModel schoolYearDataModel, ISchoolYearFactory schoolYearFactory);
    SchoolYearDataModel toDataModel(SchoolYear schoolYear);
}
