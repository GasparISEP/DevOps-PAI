package PAI.persistence.springdata.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.mapper.studyPlan.IStudyPlanMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StudyPlanRepositorySpringDataImpl implements IStudyPlanRepository {

    private final IStudyPlanRepositorySpringData iStudyPlanRepositorySpringData;
    private final IStudyPlanIDMapper iStudyPlanIDMapper;
    private final IStudyPlanMapper iStudyPlanMapper;

    public StudyPlanRepositorySpringDataImpl(IStudyPlanRepositorySpringData iStudyPlanRepositorySpringData, IStudyPlanIDMapper iStudyPlanIDMapper, IStudyPlanMapper iStudyPlanMapper) {

        if (iStudyPlanRepositorySpringData == null) {
            throw new IllegalArgumentException("iStudyPlanRepositorySpringData cannot be null");
        }
        this.iStudyPlanRepositorySpringData = iStudyPlanRepositorySpringData;

        if (iStudyPlanIDMapper == null) {
            throw new IllegalArgumentException("iStudyPlanIDMapper cannot be null");
        }
        this. iStudyPlanIDMapper= iStudyPlanIDMapper;

        if (iStudyPlanMapper == null) {
            throw new IllegalArgumentException("iStudyPlanMapper cannot be null");
        }
        this.iStudyPlanMapper = iStudyPlanMapper;

    }

    @Override
    public StudyPlan save(StudyPlan studyPlan) throws Exception {

        if(studyPlan == null) {
            throw new IllegalArgumentException("Study Plan cannot be null");
        }

        StudyPlanDataModel studyPlanDataModel = iStudyPlanMapper.toDataModel(studyPlan);

        StudyPlanDataModel savedStudyPlanDataModel = iStudyPlanRepositorySpringData.save(studyPlanDataModel);

        return iStudyPlanMapper.toDomain(savedStudyPlanDataModel);
    }

    @Override
    public Iterable<StudyPlan> findAll() {
        List<StudyPlan> allStudyPlans = new ArrayList<>();

        iStudyPlanRepositorySpringData.findAll().forEach(dataModel -> {
            if(dataModel != null) {
                try {
                    StudyPlan studyPlan = iStudyPlanMapper.toDomain(dataModel);
                    allStudyPlans.add(studyPlan);

                } catch (Exception e) {
                    throw new RuntimeException("Error mapping StudyPlanDataModel to domain", e);
                }
            }
        });

        return allStudyPlans;
    }

    @Override
    public Optional<StudyPlan> ofIdentity(StudyPlanID id) {
        if (id == null) {
            return Optional.empty();
        }

        StudyPlanIDDataModel studyPlanIDDataModel = iStudyPlanIDMapper.toDataModel(id);

        Optional<StudyPlanDataModel> studyPlanDataModelOpt = iStudyPlanRepositorySpringData.findById(studyPlanIDDataModel);

        return studyPlanDataModelOpt.map(dataModel -> {

            try {
                return iStudyPlanMapper.toDomain(dataModel);
            }
            catch (Exception e) {
                throw new RuntimeException("Error mapping StudyPlanDataModel to domain", e);
            }
        });
    }

    @Override
    public boolean containsOfIdentity(StudyPlanID id) {
        if (id == null) {
            return false;
        }
        StudyPlanIDDataModel studyPlanIDDataModel = iStudyPlanIDMapper.toDataModel(id);
        return iStudyPlanRepositorySpringData.existsById(studyPlanIDDataModel);
    }

    @Override
    public StudyPlanID findLatestByProgrammeID(ProgrammeID programmeID) {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null");
        }

        List<StudyPlan> matchingStudyPlans = new ArrayList<>();

        for (StudyPlan studyPlan : this.findAll()) {
            if (studyPlan.identity().getProgrammeID().equals(programmeID)) {
                matchingStudyPlans.add(studyPlan);
            }
        }

        if (matchingStudyPlans.isEmpty()) {
            throw new IllegalArgumentException("No study plans found for given ProgrammeID");
        }

        return matchingStudyPlans.getLast().identity();
    }

    @Override
    public Optional<StudyPlan> findByGeneratedID(StudyPlanGeneratedID studyPlanUUID) {
        if (studyPlanUUID == null)
            throw new IllegalArgumentException("StudyPlanGeneratedID cannot be null");

        UUID spUuid = studyPlanUUID.getUUID();

        Optional<StudyPlanDataModel> studyPlanOptDataModel = iStudyPlanRepositorySpringData.findByUuid(spUuid);

        return studyPlanOptDataModel.map(dataModel -> {
            try {
                return iStudyPlanMapper.toDomain(dataModel);
            }
                catch (Exception e){
                    throw new RuntimeException("Error mapping StudyPlanDataModel to Domain.", e);
            }
        });
    }
}