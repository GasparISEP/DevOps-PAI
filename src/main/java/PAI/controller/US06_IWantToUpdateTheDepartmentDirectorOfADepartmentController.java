package PAI.controller;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.repository.IDepartmentRepository;
import PAI.repository.ITeacherRepository;
import java.util.Optional;
import java.util.Set;

public class US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController {

    private final ITeacherRepository _teacherRepository;
    private final IDepartmentRepository _departmentRepository;

    public US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(IDepartmentRepository departmentRepository, ITeacherRepository teacherRepository) {

        validateTeacherRepository(teacherRepository);
        validateDepartmentRepository(departmentRepository);

        this._departmentRepository = departmentRepository;
        this._teacherRepository = teacherRepository;
    }

    public Set<DepartmentID> getAllDepartmentID() {
        return _departmentRepository.getDepartmentIDs();
    }

    public boolean updateOfDepartmentDirector(DepartmentID departmentId, TeacherID teacherId) {
        if (departmentId == null || teacherId == null) {
            return false;
        }

        Optional<Teacher> teacherOptional = _teacherRepository.ofIdentity(teacherId);

        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();

            if (teacher.isInDepartment(departmentId)) {
                return _departmentRepository.updateOfDepartmentDirector(departmentId, teacherId);
            }
        }
        return false;
    }

    private void validateTeacherRepository(ITeacherRepository teacherRepository) {
        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null!");
        }
    }

    private void validateDepartmentRepository(IDepartmentRepository iDepartmentRepository) {
        if (iDepartmentRepository == null) {
            throw new IllegalArgumentException("Department Repository cannot be null!");
        }
    }
}


