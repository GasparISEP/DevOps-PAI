package PAI.persistence.springdata.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.mapper.programmeEditionEnrolment.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.programmeEditionEnrolment.IProgrammeEditionEnrolmentMapper;
import PAI.mapper.Student.IStudentIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.Student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.repository.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
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

    public int countStudentsInProgrammesFromDepartmentInSchoolYear(SchoolYearID schoolYear, List<ProgrammeID> programmeIDS) {
        Set<StudentID> studentIDs = new HashSet<>();
        List<ProgrammeEditionEnrolment> enrollmentList = findAll();
        for (ProgrammeEditionEnrolment enrollment : enrollmentList) {
            if (enrollment.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear, programmeIDS)) {
                StudentID studentID = enrollment.findStudentInProgrammeEdition();
                studentIDs.add(studentID);
            }
        }
        return studentIDs.size();
    }


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
    public List<ProgrammeEditionEnrolment> findAll() {
        return this._peeRepositorySpringData.findAll().stream()
                .map(peeDataModel -> this._peeMapper.toDomain(peeDataModel)
                        .orElseThrow(() -> new IllegalStateException("Could not map ProgrammeEditionEnrolmentDataModel to domain")))
                .collect(Collectors.toList());
    }

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

}

