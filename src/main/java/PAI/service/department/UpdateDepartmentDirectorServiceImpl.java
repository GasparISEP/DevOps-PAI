package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDepartmentDirectorServiceImpl implements IUpdateDepartmentDirectorService {

    private final IDepartmentRepository departmentRepository;
    private final ITeacherRepository teacherRepository;

    public UpdateDepartmentDirectorServiceImpl(IDepartmentRepository departmentRepository,
                                               ITeacherRepository teacherRepository) {
        if (departmentRepository == null || teacherRepository == null) {
            throw new IllegalArgumentException("Repositories cannot be null.");
        }
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Department updateDirector(DepartmentID departmentID, TeacherID teacherID) throws Exception {
        if (teacherID == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null.");
        }

        if (departmentID == null) {
            throw new IllegalArgumentException("Department ID cannot be null.");
        }

        Optional<Department> optionalDepartment = departmentRepository.findDepartmentByID(departmentID);
        if (optionalDepartment.isEmpty()) {
            throw new IllegalArgumentException("Department not found for the given ID.");
        }


        Department department = optionalDepartment.get();
        department.setDirectorID(teacherID);
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Iterable<Department> listDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Iterable<Teacher> listTeachersByDepartment(DepartmentID departmentID) {
        if (departmentID == null) {
            throw new IllegalArgumentException("Department ID cannot be null.");
        }
        return teacherRepository.findAllByDepartmentId(departmentID);
    }
}
