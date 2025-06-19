package PAI.persistence.springdata.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.mapper.programmeEditionEnrolment.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.programmeEditionEnrolment.IProgrammeEditionEnrolmentMapper;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProgrammeEditionEnrolmentRepositorySpringDataImpl implements IProgrammeEditionEnrolmentRepository {

    private final IProgrammeEditionEnrolmentRepositorySpringData _peeRepositorySpringData;
    private final IProgrammeEditionEnrolmentMapper _peeMapper;
    private final IProgrammeEditionEnrolmentIDMapper _peeIDMapper;
    private final IStudentIDMapper studentIdMapper;
    private final IProgrammeEditionIdMapper programmeEditionIdMapper;

    public ProgrammeEditionEnrolmentRepositorySpringDataImpl(
            IProgrammeEditionEnrolmentRepositorySpringData peeRepositorySpringData,
            IProgrammeEditionEnrolmentMapper peeMapper,
            IProgrammeEditionEnrolmentIDMapper peeIDMapper,
            IStudentIDMapper studentIdMapper,
            IProgrammeEditionIdMapper programmeEditionIdMapper) {

        this._peeRepositorySpringData = validate(peeRepositorySpringData, "ProgrammeEditionEnrolmentRepositorySpringData");
        this._peeMapper = validate(peeMapper, "ProgrammeEditionEnrolmentMapper");
        this._peeIDMapper = validate(peeIDMapper, "ProgrammeEditionEnrolmentIDMapper");
        this.studentIdMapper = validate(studentIdMapper, "StudentIDMapper");
        this.programmeEditionIdMapper = validate(programmeEditionIdMapper, "ProgrammeEditionIdMapper");
    }

    @Override
    public boolean isStudentEnrolledInThisProgrammeEdition(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        if (studentId == null || programmeEditionId == null)
            return false;

        List<ProgrammeEditionEnrolmentDataModel> peeDataModels = this._peeRepositorySpringData.findAll();

        for (ProgrammeEditionEnrolmentDataModel dataModel : peeDataModels) {
            ProgrammeEditionEnrolment enrolment = _peeMapper.toDomain(dataModel)
                    .orElseThrow(() -> new IllegalStateException("Could not map ProgrammeEditionEnrolmentDataModel to domain"));

            if (enrolment.hasSameStudent(studentId) && enrolment.hasSameProgrammeEdition(programmeEditionId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ProgrammeEditionID> findProgrammeEditionsThatStudentIsEnrolled(StudentID studentId) {
        List<ProgrammeEditionID> ProgrammeEditionsThatStudentIsEnrolled = new ArrayList<>();

        List<ProgrammeEditionEnrolmentDataModel> peeDataModels = this._peeRepositorySpringData.findAll();

        for (ProgrammeEditionEnrolmentDataModel dataModel : peeDataModels) {
            ProgrammeEditionEnrolment enrolment = _peeMapper.toDomain(dataModel)
                    .orElseThrow(() -> new IllegalStateException("Could not map data model to domain"));

            if (enrolment.findStudentInProgrammeEdition().equals(studentId) && enrolment.isEnrolmentActive()) {
                ProgrammeEditionID programmeEditionId = enrolment.findProgrammeEditionInEnrolment();
                ProgrammeEditionsThatStudentIsEnrolled.add(programmeEditionId);
            }
        }

        return ProgrammeEditionsThatStudentIsEnrolled;
    }

    @Override
    public int countEnrolledStudentsByProgrammeEditionIds(List<ProgrammeEditionID> programmeEditionIDs) {
        if (programmeEditionIDs == null || programmeEditionIDs.isEmpty()) return 0;

        List<String> acronyms = extractAcronyms(programmeEditionIDs);
        List<String> schoolYears = extractSchoolYears(programmeEditionIDs);
        Set<String> validPairs = buildValidPairs(programmeEditionIDs);

        List<Object[]> results = _peeRepositorySpringData.countEnrolledByAcronymAndSchoolYear(acronyms, schoolYears);

        return sumValidEnrolments(results, validPairs);
    }

    private List<String> extractAcronyms(List<ProgrammeEditionID> ids) {
        List<String> acronyms = new ArrayList<>();
        for (ProgrammeEditionID id : ids) {
            acronyms.add(id.getProgrammeID().getProgrammeAcronym());
        }
        return acronyms;
    }

    private List<String> extractSchoolYears(List<ProgrammeEditionID> ids) {
        List<String> schoolYears = new ArrayList<>();
        for (ProgrammeEditionID id : ids) {
            schoolYears.add(id.getSchoolYearID().toString());
        }
        return schoolYears;
    }

    private Set<String> buildValidPairs(List<ProgrammeEditionID> ids) {
        Set<String> validPairs = new HashSet<>();
        for (ProgrammeEditionID id : ids) {
            String acronym = id.getProgrammeID().getProgrammeAcronym();
            String schoolYear = id.getSchoolYearID().toString();
            validPairs.add(acronym + "_" + schoolYear);
        }
        return validPairs;
    }

    private int sumValidEnrolments(List<Object[]> results, Set<String> validPairs) {
        int total = 0;
        for (Object[] result : results) {
            String acronym = (String) result[0];
            String schoolYear = (String) result[1];
            Number count = (Number) result[2];

            String key = acronym + "_" + schoolYear;
            if (validPairs.contains(key)) {
                total += count.intValue();
            }
        }
        return total;
    }

    @Override
    public List<ProgrammeEditionEnrolment> getAllProgrammeEditionsEnrollmentByProgrammeEditionID(ProgrammeEditionID programmeEditionId) throws Exception {
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIdMapper.toDataModel(programmeEditionId);
        List<ProgrammeEditionEnrolmentDataModel> allProgrammeEditionEnrolmentsDataModel = _peeRepositorySpringData.findAllById_ProgrammeEditionIdDataModel(programmeEditionIdDataModel);
        List<ProgrammeEditionEnrolment> allProgrammeEditionEnrolments = new ArrayList<>();
        for (ProgrammeEditionEnrolmentDataModel programmeEditionEnrolmentDataModel : allProgrammeEditionEnrolmentsDataModel) {
            Optional<ProgrammeEditionEnrolment> programmeEditionEnrolment = _peeMapper.toDomain(programmeEditionEnrolmentDataModel);
            programmeEditionEnrolment.ifPresent(allProgrammeEditionEnrolments::add);
        }
        return allProgrammeEditionEnrolments;
    }

    @Override
    public Optional<ProgrammeEditionEnrolment> findByStudentAndProgrammeEdition(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        try {
            StudentIDDataModel studentIDDataModel = studentIdMapper.domainToDataModel(studentId);
            ProgrammeEditionIdDataModel programmeEditionIDDataModel = programmeEditionIdMapper.toDataModel(programmeEditionId);

            Optional<ProgrammeEditionEnrolmentDataModel> dataModel =
                    _peeRepositorySpringData.findById_StudentIdDataModelAndId_ProgrammeEditionIdDataModel(studentIDDataModel, programmeEditionIDDataModel);

            if (dataModel.isEmpty()) return Optional.empty();

            return _peeMapper.toDomain(dataModel.get());

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Set<ProgrammeEditionEnrolment> getInternalSet() {
        Set<ProgrammeEditionEnrolment> internalSet = new HashSet<>();

        try {
            Iterable<ProgrammeEditionEnrolmentDataModel> dataModels = _peeRepositorySpringData.findAll();

            for (ProgrammeEditionEnrolmentDataModel dataModel : dataModels) {
                Optional<ProgrammeEditionEnrolment> domainEntity = _peeMapper.toDomain(dataModel);
                domainEntity.ifPresent(internalSet::add);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving the set of programme edition enrolments", e);
        }

        return internalSet;
    }

    @Override
    public ProgrammeEditionEnrolment save(ProgrammeEditionEnrolment enrolment) {
        if (enrolment == null) {
            throw new IllegalArgumentException("ProgrammeEditionEnrolment cannot be null");
        }

        ProgrammeEditionEnrolmentDataModel peeDataModel = this._peeMapper.toDataModel(enrolment)
                .orElseThrow(() -> new IllegalStateException("Cannot map ProgrammeEditionEnrolment to data model"));

        ProgrammeEditionEnrolmentDataModel saved = this._peeRepositorySpringData.save(peeDataModel);

        return this._peeMapper.toDomain(saved)
                .orElseThrow(() -> new IllegalStateException("Failed to map saved entity back to domain"));
    }

    @Override
    public Optional<ProgrammeEditionEnrolment> ofIdentity(ProgrammeEditionEnrolmentID peeID) {
        if (peeID == null) return Optional.empty();

        Optional<ProgrammeEditionEnrolmentIDDataModel> optionalDataModelID = this._peeIDMapper.toDataModel(peeID);

        if (optionalDataModelID.isEmpty()) return Optional.empty();

        Optional<ProgrammeEditionEnrolmentDataModel> optionalDataModel =
                this._peeRepositorySpringData.findById(optionalDataModelID.get());

        if (optionalDataModel.isEmpty()) return Optional.empty();

        Optional<ProgrammeEditionEnrolment> optionalDomain =
                this._peeMapper.toDomain(optionalDataModel.get());

        return optionalDomain;
    }


    // For each item in the list, turn it into a domain and give me a new list at the end
    @Override
    public List<ProgrammeEditionEnrolment> findAll() {
        return this._peeRepositorySpringData.findAll().stream()
                .map(peeDataModel -> this._peeMapper.toDomain(peeDataModel)
                        .orElseThrow(() -> new IllegalStateException("Could not map ProgrammeEditionEnrolmentDataModel to domain")))
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionEnrolmentID peeID) {
        if (peeID == null)
            return false;

        return this._peeIDMapper.toDataModel(peeID)
                .map(this._peeRepositorySpringData::existsById)
                .orElse(false);
    }

    private <T> T validate(T instance, String name) {
        if (instance == null) {
            throw new IllegalArgumentException(name + " cannot be null.");
        }
        return instance;
    }

    @Override
    public boolean existsByID(ProgrammeEditionEnrolmentID id) {
        try {
            Optional<ProgrammeEditionEnrolmentIDDataModel> idDataModelOpt = _peeIDMapper.toDataModel(id);
            return idDataModelOpt.isPresent() && _peeRepositorySpringData.existsById(idDataModelOpt.get());
        } catch (Exception e) {
            return false;
        }
    }


}

