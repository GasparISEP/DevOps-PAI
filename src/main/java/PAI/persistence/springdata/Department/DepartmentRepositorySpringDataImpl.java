package PAI.persistence.springdata.Department;
import PAI.VOs.DepartmentID;

import PAI.domain.Department;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.mapper.department.IDepartmentMapper;

import PAI.persistence.datamodel.department.DepartmentDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.mem.department.IDepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DepartmentRepositorySpringDataImpl implements IDepartmentRepository{
    private final IDepartmentRepositorySpringData jpaRepo;
    private final IDepartmentIDMapper idMapper;
    private final IDepartmentMapper departmentMapper;
    public DepartmentRepositorySpringDataImpl(IDepartmentRepositorySpringData departmentRepository,
                                              IDepartmentIDMapper departmentID,
                                              IDepartmentMapper departmentMapper){
        this.jpaRepo = departmentRepository;
        this.idMapper = departmentID;
        this.departmentMapper = departmentMapper;
    }
    @Override
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
        if (id == null){return false;}
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
}