package PAI.service.department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.repository.IDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import java.util.Set;


@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private final IDepartmentFactory _departmentFactory;
    private final IDepartmentRepository _departmentRepo;

    public DepartmentServiceImpl (IDepartmentFactory departmentFactory, IDepartmentRepository departmentRepo){
        if(departmentFactory==null){
             throw new IllegalArgumentException("DepartmentFactory cannot be null");
        }

        if(departmentRepo==null){
            throw new IllegalArgumentException("DepartmentRepo cannot be null");
        }

        this._departmentFactory=departmentFactory;
        this._departmentRepo=departmentRepo;
    }

    public boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception {
        DepartmentID id= new DepartmentID(acronym);

        if(_departmentRepo.containsOfIdentity(id)){
            return false;
        }

        Department department= _departmentFactory.newDepartment(acronym,name);

        _departmentRepo.save(department);
        return true;
    }

    public boolean updateOfDepartmentDirector(DepartmentID departmentID, TeacherID furtherDirectorID) {
        return _departmentRepo.updateOfDepartmentDirector(departmentID,furtherDirectorID);
    }

    public boolean containsOfIdentity(DepartmentID id) {
        return _departmentRepo.containsOfIdentity(id);
    }

    public Iterable<Department> findAll() {
        return _departmentRepo.findAll();
    }

    public boolean departmentExists(DepartmentID id) {
        boolean result;
        if(id==null){
            result = false;
        }else{
           result= _departmentRepo.containsOfIdentity(id);
        }
        return result;
    }

    public Set<DepartmentID> getDepartmentIDs (){
        return _departmentRepo.getDepartmentIDs();
    }
}
