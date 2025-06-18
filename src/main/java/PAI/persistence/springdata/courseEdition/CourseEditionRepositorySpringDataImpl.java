package PAI.persistence.springdata.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionGeneratedIDMapper;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseEditionRepositorySpringDataImpl implements ICourseEditionRepository {

    private final ICourseEditionRepositorySpringData courseEditionRepositorySpringData;
    private final ICourseEditionMapper courseEditionMapper;
    private final ICourseEditionIDMapper courseEditionIDMapper;
    private final IProgrammeEditionIdMapper programmeEditionIdMapper;
    private final ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper;
    private final ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper;

    public CourseEditionRepositorySpringDataImpl(ICourseEditionRepositorySpringData courseEditionReposSD, ICourseEditionMapper courseEditionMapper, ICourseEditionIDMapper courseEditionIDMapper, IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper) {

        if (courseEditionReposSD == null)
            throw new IllegalArgumentException("CourseEditionRepositorySpringData cannot be null");
        if (courseEditionMapper == null)
            throw new IllegalArgumentException("CourseEditionMapper cannot be null");
        if (courseEditionIDMapper == null)
            throw new IllegalArgumentException("CourseEditionIDMapper cannot be null");
        if (programmeEditionIdMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionIdMapper cannot be null");
        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("CourseInStudyPlanIDMapper cannot be null");
        this.courseEditionGeneratedIDMapper = courseEditionGeneratedIDMapper;
        this.courseEditionRepositorySpringData = courseEditionReposSD;
        this.courseEditionMapper = courseEditionMapper;
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.programmeEditionIdMapper = programmeEditionIdMapper;
        this.courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;
    }

    @Override
    public List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId) {
        if (programmeEditionId == null)
            return new ArrayList<>();

        try {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIdMapper.toDataModel(programmeEditionId);
            List<CourseEditionIDDataModel> courseEditionIDsDataModel = courseEditionRepositorySpringData.findCourseEditionIDByProgrammeEditionIDDataModel(programmeEditionIdDataModel);
            List<CourseEditionID> courseEditionIDs = new ArrayList<>();
            for (CourseEditionIDDataModel courseEditionIDDataModel : courseEditionIDsDataModel) {
                CourseEditionID toAdd = courseEditionIDMapper.toDomain(courseEditionIDDataModel);
                if (toAdd != null)
                    courseEditionIDs.add(toAdd);
            }
            return courseEditionIDs;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public CourseEdition save(CourseEdition entity) {

        if (entity == null)
            throw new IllegalArgumentException("CourseEdition cannot be null");

        CourseEditionDataModel courseEditionDataModel = courseEditionMapper.toDataModel(entity);
        courseEditionRepositorySpringData.save(courseEditionDataModel);
        return courseEditionMapper.toDomain(courseEditionDataModel);

    }

    @Override
    public Iterable<CourseEdition> findAll() {

        Iterable<CourseEditionDataModel> courseEditionDataModels = courseEditionRepositorySpringData.findAll();
        List<CourseEdition> courseEditions = new ArrayList<>();
        for (CourseEditionDataModel courseEditionDataModel : courseEditionDataModels) {
            try {
                CourseEdition courseEdition = courseEditionMapper.toDomain(courseEditionDataModel);
                if (courseEdition != null)
                    courseEditions.add(courseEdition);
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return courseEditions;
    }

    @Override
    public Optional<CourseEdition> ofIdentity(CourseEditionID id) {
        if (id == null)
            return Optional.empty();
        try {
            CourseEditionIDDataModel courseEditionIDDataModel = courseEditionIDMapper.toDataModel(id);
            Optional<CourseEditionDataModel> courseEditionDataModelOptional = courseEditionRepositorySpringData.findById(courseEditionIDDataModel);
            if (courseEditionDataModelOptional.isPresent())
                return Optional.of(courseEditionMapper.toDomain(courseEditionDataModelOptional.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(CourseEditionID id) {
        if (id == null)
            return false;
        try {
            return courseEditionRepositorySpringData.existsById(courseEditionIDMapper.toDataModel(id));
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public List<CourseEditionID> findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(ProgrammeEditionID programmeEditionId, CourseInStudyPlanID courseInStudyPlanId) throws Exception {        
        if(programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEditionID cannot be null");
        }
        if(courseInStudyPlanId == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID cannot be null");
        }
        List<CourseEditionID> courseEditionIDs = new ArrayList<>();
        try {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIdMapper.toDataModel(programmeEditionId);
            CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = courseInStudyPlanIDMapper.toDataModel(courseInStudyPlanId);
            List<CourseEditionDataModel> courseEditionsDataModel = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionIDDataModelAndCourseInStudyPlanIDDataModel(programmeEditionIdDataModel, courseInStudyPlanIDDataModel);
            return addCourseEditionToCourseEditionIDs(courseEditionsDataModel, courseEditionIDs);
        } catch (Exception e) {
            throw new Exception("Error trying to find CourseEditions by ProgrammeEditionID and CourseInStudyPlanID", e);
        }
    }

    private List<CourseEditionID> addCourseEditionToCourseEditionIDs(List<CourseEditionDataModel> courseEditionsDataModel, List<CourseEditionID> courseEditionIDs) throws Exception {
        for (CourseEditionDataModel courseEditionDataModel : courseEditionsDataModel) {
            CourseEdition courseEdition = courseEditionMapper.toDomain(courseEditionDataModel);
            if (courseEdition != null) {
                courseEditionIDs.add(courseEdition.identity());
            }
        }
        return courseEditionIDs;
    }
    @Override
    public Optional<CourseEdition> findCourseEditionByGeneratedId(CourseEditionGeneratedID id) throws Exception {
        CourseEditionGeneratedIDDataModel IDDatamodel =  courseEditionGeneratedIDMapper.toDataModel(id);
        CourseEditionDataModel courseEditionDataModel = courseEditionRepositorySpringData.findCourseEditionByGeneratedId(IDDatamodel);

        if (courseEditionDataModel != null) {
            CourseEdition courseEdition = courseEditionMapper.toDomain(courseEditionDataModel);
            return Optional.of(courseEdition);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<CourseEdition> findByProgrammeIDAndCourseID(ProgrammeID programmeID, CourseID courseID) {
        List<CourseEdition> result = new ArrayList<>();
        Iterable<CourseEditionDataModel> courseEditionDataModels = courseEditionRepositorySpringData.findAll();
        for (CourseEditionDataModel dataModel : courseEditionDataModels) {
            try {
                CourseEdition courseEdition = courseEditionMapper.toDomain(dataModel);
                if (courseEdition.getProgrammeEditionID().getProgrammeID().equals(programmeID) &&
                    courseEdition.getCourseInStudyPlanID().getCourseID().equals(courseID)) {
                    result.add(courseEdition);
                }
            } catch (Exception ignored) {
            }
        }
        return result;
    }

}
