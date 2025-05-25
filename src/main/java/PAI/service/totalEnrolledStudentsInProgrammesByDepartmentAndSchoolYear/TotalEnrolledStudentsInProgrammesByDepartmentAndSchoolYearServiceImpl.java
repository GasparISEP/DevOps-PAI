package PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.springframework.stereotype.Service;

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

        this.departmentRepository = departmentRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.programmeRepository = programmeRepository;
        this.programmeEditionRepository = programmeEditionRepository;
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
    }

    @Override
    public int getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(TotalEnrolledStudentsCommand command) {
        return 0;
    }
}
