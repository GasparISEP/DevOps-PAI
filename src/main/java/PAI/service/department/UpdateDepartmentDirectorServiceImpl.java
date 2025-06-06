package PAI.service.department;

import PAI.assembler.department.IDepartmentAssembler;
import PAI.dto.department.DepartmentWithDirectorDTO;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.springframework.stereotype.Service;


@Service
public class UpdateDepartmentDirectorServiceImpl implements IUpdateDepartmentDirectorService {

    private final IDepartmentRepository departmentRepository;
    private final ITeacherRepository teacherRepository;
    private final IDepartmentAssembler departmentAssembler;

    public UpdateDepartmentDirectorServiceImpl(IDepartmentRepository departmentRepository,
                                               ITeacherRepository teacherRepository,
                                               IDepartmentAssembler departmentAssembler) {
        if (departmentRepository == null || teacherRepository == null || departmentAssembler == null) {
            throw new IllegalArgumentException("Dependencies cannot be null.");
        }
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
        this.departmentAssembler = departmentAssembler;
    }

    @Override
    public DepartmentWithDirectorDTO updateDirector(DepartmentID departmentID, TeacherID teacherID) throws Exception {
        if (teacherID == null || departmentID == null) {
            throw new IllegalArgumentException("IDs cannot be null.");
        }

        Department department = departmentRepository.findDepartmentByID(departmentID)
                .orElseThrow(() -> new IllegalArgumentException("Department not found for the given ID."));

        Teacher teacher = teacherRepository.ofIdentity(teacherID)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found for the given ID."));

        if (!teacher.isInDepartment(departmentID)) {
            throw new IllegalArgumentException("Teacher does not belong to Department.");
        }

        department.changeDirector(teacherID);

        try {
            departmentRepository.save(department);
        } catch (Exception e) {
            throw new RuntimeException("Department was not save.", e);
        }

        return departmentAssembler.toDWDDTO(department);
    }

}
