package PAI.persistence.springdata;


import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
    public Optional<SchoolYear> ofIdentity(SchoolYearID id, SchoolYearFactoryImpl schoolYearFactory) {
        Optional<SchoolYearDataModel> schoolYearDMOpt = schoolYearRepositorySpringData.findById(id.toString());

        if (schoolYearDMOpt.isEmpty()) {
            return Optional.empty();
        }
        try {
            SchoolYear schoolYear = schoolYearMapper.toDomain(schoolYearDMOpt.get(), schoolYearFactory);
            return Optional.of(schoolYear);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean containsOfIdentity(SchoolYearID id) {
        if(id ==null){
            return false;
        }
        return schoolYearRepositorySpringData.existsById(id.toString());
    }

    //US18
    public Optional<SchoolYearID> getCurrentSchoolYear() {
        Optional<SchoolYearIDDataModel> schoolYearIDDataModelFromCurrentSchoolYear = schoolYearRepositorySpringData.findCurrentSchoolYear();

        //If present return Optional com ProgrammeID, else return Empty
        return schoolYearIDDataModelFromCurrentSchoolYear.map(schoolYearIDMapper::toDomain);
    }


}
