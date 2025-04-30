package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.mapper.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.IProgrammeEditionEnrolmentMapper;
import PAI.mapper.IStudentIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProgrammeEditionEnrolmentRepositorySpringData implements IProgrammeEditionEnrolmentRepository {

    private final IProgrammeEditionEnrolmentRepositorySpringData _peeRepositorySpringData;
    private final IProgrammeEditionEnrolmentMapper _peeMapper;
    private final IProgrammeEditionEnrolmentIDMapper _peeIDMapper;
    private final IStudentIDMapper studentIdMapper;
    private final IProgrammeEditionIdMapper programmeEditionIdMapper;

    public ProgrammeEditionEnrolmentRepositorySpringData(
            IProgrammeEditionEnrolmentRepositorySpringData peeRepositorySpringData,
            IProgrammeEditionEnrolmentMapper peeMapper,
            IProgrammeEditionEnrolmentIDMapper peeIDMapper,
            IStudentIDMapper studentIdMapper,
            IProgrammeEditionIdMapper programmeEditionIdMapper) {
        if (peeRepositorySpringData == null)
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentRepositorySpringData cannot be null");
        if (peeMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentMapper cannot be null");
        if (peeIDMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentIDMapper cannot be null");
        if (studentIdMapper == null)
            throw new IllegalArgumentException("StudentIDMapper cannot be null!");
        if (programmeEditionIdMapper == null) {
            throw new IllegalArgumentException("ProgrammeEditionIdMapper cannot be null");
        }
        this.programmeEditionIdMapper = programmeEditionIdMapper;
        this._peeRepositorySpringData = peeRepositorySpringData;
        this._peeMapper = peeMapper;
        this.studentIdMapper = studentIdMapper;
        this._peeIDMapper = peeIDMapper;
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

            if (enrolment.findStudentInProgrammeEdition().equals(studentId)) {
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
        List<ProgrammeEditionEnrolmentDataModel> allProgrammeEditionEnrolmentsDataModel = _peeRepositorySpringData.findAllBy_id_ProgrammeEditionIdDataModel(programmeEditionIdDataModel);
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
                    _peeRepositorySpringData.findByStudentIDAndProgrammeEditionID(studentIDDataModel, programmeEditionIDDataModel);

            if (dataModel.isEmpty()) return Optional.empty();

            return _peeMapper.toDomain(dataModel.get());

        } catch (Exception e) {
            return Optional.empty();
        }
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
}

