package PAI.service.department;
import PAI.factory.IDepartmentFactory;
import PAI.repository.IDepartmentRepository;

public class DepartmentServiceImpl {

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
}
