package PAI.persistence.springdata.schoolYear;


import PAI.VOs.SchoolYearID;
import PAI.domain.schoolYear.SchoolYear;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.SchoolYear.ISchoolYearIDMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import PAI.repository.schoolYear.ISchoolYearRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SchoolYearRepositorySpringDataImpl implements ISchoolYearRepository {

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
        if (schoolYear == null) {
            throw new IllegalArgumentException("SchoolYear cannot be null");
        }
        SchoolYearDataModel schoolYearDataModel = schoolYearMapper.toDataModel(schoolYear);
        schoolYearRepositorySpringData.save(schoolYearDataModel);
        return schoolYear;
    }

    public Iterable<SchoolYear> findAll() {
        List<SchoolYear> allSchoolYears = new ArrayList<>();
        List<SchoolYearDataModel> allSchoolYearDataModels = schoolYearRepositorySpringData.findAll();
        for (SchoolYearDataModel existingSchoolYears : allSchoolYearDataModels) {
            SchoolYear schoolYear = schoolYearMapper.toDomain(existingSchoolYears);
            allSchoolYears.add(schoolYear);
        }
        return allSchoolYears;
    }

    public Optional<SchoolYear> ofIdentity(SchoolYearID id) {
        SchoolYearIDDataModel schoolYearIDDataModel = schoolYearIDMapper.toDataModel(id);
        Optional<SchoolYearDataModel> schoolYearDMOpt = schoolYearRepositorySpringData.findById(schoolYearIDDataModel);

        if (schoolYearDMOpt.isEmpty()) {
            return Optional.empty();
        }
        SchoolYear schoolYear = schoolYearMapper.toDomain(schoolYearDMOpt.get());
        return Optional.of(schoolYear);
    }

    public boolean containsOfIdentity(SchoolYearID id) {
        if (id == null) {
            return false;
        }
        SchoolYearIDDataModel schoolYearIDDataModel = schoolYearIDMapper.toDataModel(id);
        return schoolYearRepositorySpringData.existsById(schoolYearIDDataModel);
    }

    @Override
    public boolean schoolYearExists(SchoolYear schoolYear) {
        if (schoolYear == null) {
            return false;
        }
        SchoolYearID schoolYearID = schoolYear.identity();
        Optional<SchoolYear> foundSchoolYear = ofIdentity(schoolYearID);
        if (foundSchoolYear.isPresent()) {
            SchoolYear year = foundSchoolYear.get();
            return year.isSameSchoolYear(schoolYear);
        } else {
            return false;
        }
    }

    @Override
    public List<SchoolYearID> getAllSchoolYearsIDs() {
        List<SchoolYearID> schoolYearIDs = new ArrayList<>();
        for (SchoolYear schoolYear : findAll()) {
            schoolYearIDs.add(schoolYear.identity());
        }
        return schoolYearIDs;
    }

    //US18
    @Override
    public Optional<SchoolYear> getCurrentSchoolYear() {
        Optional<SchoolYearDataModel> schoolYearDataModelFromCurrentSchoolYear = schoolYearRepositorySpringData.findCurrentSchoolYear();

        //If present return Optional com SchoolYear, else return Empty
        return schoolYearDataModelFromCurrentSchoolYear.map(schoolYearMapper::toDomain);
    }
}
