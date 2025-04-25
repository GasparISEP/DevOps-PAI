package PAI.persistence.springdata.Department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.domain.Department;
import PAI.mapper.ITeacherIDMapper;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.mapper.department.IDepartmentMapper;
import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.persistence.datamodel.department.DepartmentDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.repository.IDepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DepartmentRepositorySpringDataImpl implements IDepartmentRepository{
    private final IDepartmentRepositorySpringData jpaRepo;
    private final IDepartmentIDMapper idMapper;
    private final IDepartmentMapper departmentMapper;
    private final ITeacherIDMapper directorIDMapper;
    public DepartmentRepositorySpringDataImpl(IDepartmentRepositorySpringData departmentRepository,
                                              IDepartmentIDMapper departmentID,
                                              IDepartmentMapper departmentMapper,
                                              ITeacherIDMapper teacherIDMapper){
        this.jpaRepo = departmentRepository;
        this.idMapper = departmentID;
        this.departmentMapper = departmentMapper;
        this.directorIDMapper = teacherIDMapper;
    }
    @Override
    @Transactional
    public Department save(Department department) {
        try {
            DepartmentDataModel departmentDataModel = departmentMapper.toDataModel(department);
            DepartmentDataModel saved = jpaRepo.save(departmentDataModel);
            return departmentMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save department: " + department.getName().getName(), e);
        }
    }

    @Override
    public boolean registerDepartment(DepartmentAcronym acronym, Name name) throws Exception {
        return false;
    }

    @Override
    public List<Department> findAll(){
        List<Department> departments = new ArrayList<>();
        Iterable<DepartmentDataModel> departmentDataModels = jpaRepo.findAll();

        for (DepartmentDataModel DepartmentDatamodel : departmentDataModels) {
            try {
                departments.add(departmentMapper.toDomain(DepartmentDatamodel));
            } catch (Exception e) {
                throw new RuntimeException("Failed to map department data model." , e);
            }
        }
        return departments;
    }

    @Override
    public Optional<Department> ofIdentity(DepartmentID id) {
        return findDepartmentByID(id);
    }

    @Override
    public boolean containsOfIdentity(DepartmentID id) {
        DepartmentIDDataModel departmentIDDataModel = idMapper.toDataModel(id);
        return jpaRepo.existsById(departmentIDDataModel.getDepartmentID());
    }
    @Override
    public Set<DepartmentID> getDepartmentIDs(){
        List<Department> departments = findAll();
        if (departments.isEmpty()) {
            throw new IllegalStateException("No departments found in repository.");
        }
        return departments.stream()
                .map(Department::identity)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Department> findDepartmentByID(DepartmentID departmentID) {
        if (departmentID == null) {
            throw new IllegalArgumentException("Department ID cannot be null.");
        }

        DepartmentIDDataModel dataModelId = idMapper.toDataModel(departmentID);

        return jpaRepo.findById(dataModelId.getDepartmentID())
                .map(dataModel -> {
                    try {
                        return departmentMapper.toDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to map DepartmentDataModel to domain for ID: " + departmentID.getAcronym(), e);
                    }
                });
    }


    @Override
    public boolean departmentExists(DepartmentID departmentID)  {
        if(departmentID == null){return false;}
        Optional <Department> opDepartment=findDepartmentByID(departmentID);
        return opDepartment.isPresent();
    }

    @Override
    @Transactional
    public boolean updateOfDepartmentDirector(DepartmentID departmentId, TeacherID teacherId) {

        if(teacherId == null){
            throw new IllegalArgumentException("Teacher ID cannot be null.");
        }
        if(departmentId == null){
            throw new IllegalArgumentException("Department ID cannot be null.");
        }

        TeacherIDDataModel teacherIDDataModel = directorIDMapper.toDataModel(teacherId);
       Optional<Department> opDepartment = findDepartmentByID(departmentId);
        if (opDepartment.isEmpty()) {
            return false;
        }
        Department department = opDepartment.get();

        DepartmentDataModel departmentDataModel = departmentMapper.toDataModel(department);
        departmentDataModel.setDirectorId(teacherIDDataModel);
        jpaRepo.save(departmentDataModel);
        return true;
    }

}