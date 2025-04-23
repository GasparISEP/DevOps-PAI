package PAI.persistence.springdata;


import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//TODO "implements ISchoolYearRepository"

@Repository
public class SchoolYearRepositorySpringDataImpl {

    private ISchoolYearRepositorySpringData schoolYearRepositorySpringData;
    private ISchoolYearMapper schoolYearMapper;
    private ISchoolYearIDMapper schoolYearIDMapper;


    public SchoolYearRepositorySpringDataImpl(ISchoolYearRepositorySpringData schoolYearRepositorySpringData, ISchoolYearMapper schoolYearMapper, ISchoolYearIDMapper schoolYearIDMapper) {
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

    public Iterable<SchoolYear> findAll(ISchoolYearFactory factory){
        List<SchoolYear> allSchoolYears = new ArrayList<>();
        List<SchoolYearDataModel> allSchoolYearDataModels = schoolYearRepositorySpringData.findAll();
        for(SchoolYearDataModel existingSchoolYears : allSchoolYearDataModels){
            try {
                SchoolYear schoolYear = schoolYearMapper.toDomain(existingSchoolYears, factory);
                allSchoolYears.add(schoolYear);
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }
        return allSchoolYears;
    }
}
