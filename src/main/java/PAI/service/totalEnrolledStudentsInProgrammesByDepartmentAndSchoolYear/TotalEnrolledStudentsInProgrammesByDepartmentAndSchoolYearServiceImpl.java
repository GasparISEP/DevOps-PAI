package PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.VOs.DepartmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl implements  ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService {

    private final IDepartmentRepository departmentRepository;
    private final ISchoolYearRepository schoolYearRepository;
    private final IProgrammeRepository programmeRepository;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;

    public TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(
            IDepartmentRepository departmentRepository,
            ISchoolYearRepository schoolYearRepository,
            IProgrammeRepository programmeRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository) {

        if (departmentRepository == null) throw new IllegalArgumentException("Department Repository cannot be null");
        if (schoolYearRepository == null) throw new IllegalArgumentException("School Year Repository cannot be null");
        if (programmeRepository == null) throw new IllegalArgumentException("Programme Repository cannot be null");
        if (programmeEditionRepository == null) throw new IllegalArgumentException("Programme Edition Repository cannot be null");
        if (programmeEditionEnrolmentRepository == null) throw new IllegalArgumentException("Programme Edition Enrolment Repository cannot be null");

        this.departmentRepository = departmentRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.programmeRepository = programmeRepository;
        this.programmeEditionRepository = programmeEditionRepository;
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
    }

    @Override
    public int getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(TotalEnrolledStudentsCommand command) {

        validateCommand(command);

        DepartmentID departmentID = command.departmentID();
        SchoolYearID schoolYearID = command.schoolYearID();

        if (!isDepartmentIdAndSchoolYearIdValid(departmentID, schoolYearID)) return 0;

        List<ProgrammeID> programmeIDList = programmeRepository.findProgrammesIdByDepartmentId(departmentID);
        if (programmeIDList.isEmpty()) return 0;

        List<ProgrammeEditionID> programmeEditionIDList = programmeEditionRepository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDList);
        if (programmeEditionIDList.isEmpty()) return 0;

        return programmeEditionEnrolmentRepository.countEnrolledStudentsByProgrammeEditionIds(programmeEditionIDList);
    }

    private boolean isDepartmentIdAndSchoolYearIdValid(DepartmentID departmentID, SchoolYearID schoolYearID) {
        return departmentRepository.containsOfIdentity(departmentID) && schoolYearRepository.containsOfIdentity(schoolYearID);
    }

    private void validateCommand(TotalEnrolledStudentsCommand command) {
        if (command == null)
            throw new IllegalArgumentException("TotalEnrolledStudentsCommand must not be null");

        if (command.departmentID() == null)
            throw new IllegalArgumentException("Department ID cannot be null");

        if (command.schoolYearID() == null)
            throw new IllegalArgumentException("School Year ID cannot be null");
    }
}
