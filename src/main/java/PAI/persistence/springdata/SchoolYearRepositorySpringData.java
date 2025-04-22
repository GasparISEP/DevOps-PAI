package PAI.persistence.springdata;


import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.mapper.IStudentGradeIDMapper;
import PAI.mapper.IStudentGradeMapper;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.repository.ISchoolYearRepository;
import org.springframework.stereotype.Repository;


//TODO "implements ISchoolYearRepository"

@Repository
public class SchoolYearRepositorySpringData {

    private ISchoolYearRepositorySpringData schoolYearRepositorySpringData;
    private ISchoolYearMapper schoolYearMapper;
    private ISchoolYearIDMapper schoolYearIDMapper;


    public SchoolYearRepositorySpringData(ISchoolYearRepositorySpringData schoolYearRepositorySpringData, ISchoolYearMapper schoolYearMapper, ISchoolYearIDMapper schoolYearIDMapper) {
        if (schoolYearRepositorySpringData == null || schoolYearMapper == null || schoolYearIDMapper == null) {
        throw new IllegalArgumentException("Spring Data Repository or SchoolYear/SchoolYearID mappers cannot be null");
        }
            this.schoolYearRepositorySpringData = schoolYearRepositorySpringData;
            this.schoolYearMapper = schoolYearMapper;
            this.schoolYearIDMapper = schoolYearIDMapper;

    }

    public SchoolYear save(SchoolYear schoolYear) {
        if(schoolYear==null){
            throw new IllegalArgumentException("SchoolYear cannot be null");
        }
        SchoolYearDataModel schoolYearDataModel = schoolYearMapper.toDataModel(schoolYear);
        schoolYearRepositorySpringData.save(schoolYearDataModel);
        return schoolYear;
    }
}
