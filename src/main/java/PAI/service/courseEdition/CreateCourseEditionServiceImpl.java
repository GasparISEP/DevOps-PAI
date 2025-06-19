package PAI.service.courseEdition;

import PAI.VOs.*;
import PAI.assembler.courseEdition.CourseEditionServiceAssemblerImpl;
import PAI.assembler.courseEdition.ICourseEditionServiceAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.courseEdition.CourseEditionServiceResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.CourseEditionCreationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateCourseEditionServiceImpl implements ICreateCourseEditionService {

    private final ICourseEditionRepository courseEditionRepository;
    private final ICourseEditionFactory courseEditionFactory;
    private final IDegreeTypeRepository degreeTypeRepository;
    private final IProgrammeRepository programmeRepository;
    private final IStudyPlanRepository studyPlanRepository;
    private final ICourseInStudyPlanRepository courseInStudyPlanRepository;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final ICourseEditionServiceAssembler courseEditionAssembler;

    public CreateCourseEditionServiceImpl(ICourseEditionFactory courseEditionFactory, ICourseEditionRepository courseEditionRepository,
                                          IDegreeTypeRepository degreeTypeRepository, IProgrammeRepository programmeRepository,
                                          IStudyPlanRepository studyPlanRepository, ICourseInStudyPlanRepository courseInStudyPlanRepository,
                                          IProgrammeEditionRepository programmeEditionRepository, ICourseEditionServiceAssembler courseEditionAssembler) {

        if (courseEditionFactory == null)
            throw new IllegalArgumentException("courseEditionFactory cannot be null");
        if (courseEditionRepository == null)
            throw new IllegalArgumentException("courseEditionRepository cannot be null");
        if (degreeTypeRepository == null)
            throw new IllegalArgumentException("degreeTypeRepository cannot be null");
        if (programmeRepository == null)
            throw new IllegalArgumentException("programmeRepository cannot be null");
        if (studyPlanRepository == null)
            throw new IllegalArgumentException("studyPlanRepository cannot be null");
        if (courseInStudyPlanRepository == null)
            throw new IllegalArgumentException("courseInStudyPlanRepository cannot be null");
        if (programmeEditionRepository == null)
            throw new IllegalArgumentException("programmeEditionRepository cannot be null");
        if (courseEditionAssembler == null)
            throw new IllegalArgumentException("courseEditionAssembler cannot be null");

        this.courseEditionFactory = courseEditionFactory;
        this.courseEditionRepository = courseEditionRepository;
        this.degreeTypeRepository = degreeTypeRepository;
        this.programmeRepository = programmeRepository;
        this.studyPlanRepository = studyPlanRepository;
        this.courseInStudyPlanRepository = courseInStudyPlanRepository;
        this.programmeEditionRepository = programmeEditionRepository;
        this.courseEditionAssembler = courseEditionAssembler;

    }

    @Override
    public CourseEditionServiceResponseDTO createCourseEditionForRestApi(CreateCourseEditionCommand command) {
        validateCommand(command);

        CourseInStudyPlanID courseInStudyPlanID = buildCourseInStudyPlanID(command);
        ProgrammeEditionID programmeEditionID = buildProgrammeEditionID(command);

        CourseEdition courseEdition = courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID);
        CourseEditionID courseEditionID = courseEdition.identity();

        checkIfAlreadyRegistered(courseEditionID);

        return saveAndConvertToDTO(courseEdition);
    }

    private void validateCommand(CreateCourseEditionCommand command) {
        if (command == null)
            throw new IllegalArgumentException("CreateCourseEditionCommand cannot be null.");
    }

    private CourseInStudyPlanID buildCourseInStudyPlanID(CreateCourseEditionCommand command) {
        ProgrammeID programmeID = new ProgrammeID(command.programmeAcronym());
        Date studyPlanDate = command.studyPlanImplementationDate();
        return new CourseInStudyPlanID(
                new CourseID(command.courseAcronym(), command.courseName()),
                new StudyPlanID(programmeID, studyPlanDate)
        );
    }

    private ProgrammeEditionID buildProgrammeEditionID(CreateCourseEditionCommand command) {
        ProgrammeID programmeID = new ProgrammeID(command.programmeAcronym());
        return new ProgrammeEditionID(
                programmeID,
                command.schoolYearID()
        );
    }

    private void checkIfAlreadyRegistered(CourseEditionID courseEditionID) {
        if (courseEditionRepository.containsOfIdentity(courseEditionID))
            throw new AlreadyRegisteredException("CourseEdition");
    }

    private CourseEditionServiceResponseDTO saveAndConvertToDTO(CourseEdition courseEdition) {
        try {
            CourseEdition saved = courseEditionRepository.save(courseEdition);
            return courseEditionAssembler.toServiceResponseDTO(saved);
        } catch (Exception e) {
            throw new CourseEditionCreationException("Failed to create CourseEdition.", e);
        }
    }


    @Override
    public CourseEdition createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        if (courseInStudyPlanID == null || programmeEditionID == null)
            return null;

        try {
            CourseEdition courseEdition = courseEditionFactory.createCourseEditionToDomain(courseInStudyPlanID, programmeEditionID);
            CourseEdition courseEditionSaved = null;
            if (!courseEditionRepository.containsOfIdentity(courseEdition.identity()))
                courseEditionSaved = courseEditionRepository.save(courseEdition);
            return courseEditionSaved;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DegreeType> getAllDegreeTypes() {
        return degreeTypeRepository.getAllDegreeTypes();
    }


    @Override
    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID id) {
        List<Programme> programmeList = new ArrayList<>();
        for (Programme programme : programmeRepository.findAll()) {
            if (programme.hasThisDegreeTypeID(id))
                programmeList.add(programme);
        }
        return programmeList;
    }


    @Override
    public List<StudyPlan> getStudyPlansByProgrammeID(ProgrammeID programmeID) {
        List<StudyPlan> result = new ArrayList<>();
        for (StudyPlan c : studyPlanRepository.findAll()) {
            if (c.identity().getProgrammeID().equals(programmeID)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID) {
        List<StudyPlan> list = getStudyPlansByProgrammeID(programmeID);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No study plans found for given ProgrammeID");
        }
        return list.getLast().identity();
    }

    @Override
    public List<CourseInStudyPlan> getCoursesByStudyPlanId(StudyPlanID studyPlanID) throws Exception {
        List<CourseInStudyPlan> result = new ArrayList<>();
        for (CourseInStudyPlan c : courseInStudyPlanRepository.findAll()) {
            if (c.identity().getStudyPlanID().equals(studyPlanID)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception {
        if (programmeID == null) {
            return List.of();
        }

        return programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID);
    }

    @Override
    public List<CourseEditionServiceResponseDTO> findAll() {
        Iterable<CourseEdition> allCourseEditions = courseEditionRepository.findAll();

        List<CourseEditionServiceResponseDTO> dtoList = new ArrayList<>();

        for (CourseEdition courseEdition : allCourseEditions) {
            dtoList.add(courseEditionAssembler.toServiceResponseDTO(courseEdition));
        }
        return dtoList;
    }

    @Override
    public CourseEditionServiceResponseDTO findById(CourseEditionGeneratedID courseEditionGeneratedID) throws Exception {
        if (courseEditionGeneratedID == null) {
            throw new IllegalArgumentException("CourseEditionID cannot be null");
        }

        Optional<CourseEdition> courseEdition = courseEditionRepository.findCourseEditionByGeneratedId(courseEditionGeneratedID);
        if (courseEdition.isEmpty()) {
            throw new BusinessRuleViolationException("Course Edition not found for ID: " + courseEditionGeneratedID);
        }

        return courseEditionAssembler.toServiceResponseDTO(courseEdition.get());

    }

}



