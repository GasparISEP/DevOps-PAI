package PAI.mapper.schoolYearID;


import PAI.VOs.SchoolYearID;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;

public interface ISchoolYearIDMapper {
    SchoolYearID toDomain(SchoolYearIDDataModel schoolYearIDDataModel);

    SchoolYearIDDataModel toDataModel(SchoolYearID schoolYearID);
}
