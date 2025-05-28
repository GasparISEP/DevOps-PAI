package PAI.service.department;
import PAI.dto.department.DepartmentWithDirectorDTO;
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
    public DepartmentWithDirectorDTO updateDirector(DepartmentID departmentID, TeacherID teacherID) throws Exception {
        if (teacherID == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null.");
        }

        if (departmentID == null) {
            throw new IllegalArgumentException("Department ID cannot be null.");
        }

        // Check if the department exists

        Optional<Department> optionalDepartment = departmentRepository.findDepartmentByID(departmentID);
        if (optionalDepartment.isEmpty()) {
            throw new IllegalArgumentException("Department not found for the given ID.");
        }

        Department department = optionalDepartment.get();

        // Check if the teacher belongs to the department

        Optional <Teacher> teacherOpt = teacherRepository.ofIdentity(teacherID);
        if (teacherOpt.isEmpty()) {
            throw new IllegalArgumentException("Teacher not found for the given ID.");
        }
        Teacher teacher = teacherOpt.get();
        if (teacher.isInDepartment(departmentID)){
            department.changeDirector(teacherID);
            departmentRepository.save(department);
        }

        // DepartmentWithDirectorDTO result = domainToDtoResponse(department);
        // Extrai os dados necessários do departamento
        String id = department.identity().toString();
        String name = department.getName().toString();       // assumes getName()
        String acronym = department.getAcronym().toString();
        String director = department.getDirectorID().toString(); // assumes getAcronym()

        return new DepartmentWithDirectorDTO(id, name, acronym, director);
    }


}