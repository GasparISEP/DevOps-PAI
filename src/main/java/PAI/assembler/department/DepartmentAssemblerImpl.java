package PAI.assembler.department;


import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.domain.course.Course;
import PAI.domain.department.Department;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import PAI.dto.department.RegisterDepartmentRequestVOs;
import PAI.dto.department.RegisterDepartmentRequest;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
@Component
public class DepartmentAssemblerImpl implements IDepartmentAssembler {

    @Override
    public RegisterDepartmentRequestVOs toRegisterDepartmentRequestVOs(RegisterDepartmentRequest registerDepartmentRequest) {
        if (registerDepartmentRequest == null) {
            throw new IllegalArgumentException("RegisterDepartmentRequest cannot be null");
        }
        Name name = new Name(registerDepartmentRequest.name());
        DepartmentAcronym acronym = new DepartmentAcronym(registerDepartmentRequest.acronym());

        return new RegisterDepartmentRequestVOs(name, acronym);
    }

    @Override
    public DepartmentDTO toDTO (Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        return new DepartmentDTO(
                department.identity().getAcronym().getAcronym(),
                department.getName().getName(),
                department.getAcronym().getAcronym()
        );
    }
    @Override
    public Iterable<DepartmentDTO> toDTOs(Iterable<Department> listDepartment) {
        if (listDepartment == null) {
            return Collections.emptyList(); // evita null pointer exception e retorna uma lista vazia
        }

        List<DepartmentDTO> listDTO = new ArrayList<>();
        for (Department department : listDepartment) {
            DepartmentDTO departmentDTO = toDTO(department);
            listDTO.add(departmentDTO);
        }
        return listDTO;
    }
    @Override
    public DepartmentWithDirectorDTO toDWDDTO (Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        return new DepartmentWithDirectorDTO(
                department.identity().getAcronym().getAcronym(),
                department.getName().getName(),
                department.getAcronym().getAcronym(),
                department.getDirectorID().getTeacherAcronym().getAcronym()
        );
    }

    @Override
    public Iterable<DepartmentWithDirectorDTO> toDWDDTOs(Iterable<Department> listDepartment) {
        if (listDepartment == null) {
            return Collections.emptyList(); // evita null pointer exception e retorna uma lista vazia
        }

        List<DepartmentWithDirectorDTO> listDTO = new ArrayList<>();
        for (Department department : listDepartment) {
            DepartmentWithDirectorDTO departmentWithDirectorDTO = toDWDDTO(department);
            listDTO.add(departmentWithDirectorDTO);
        }
        return listDTO;
    }

    public Department updateDepartmentWithDirector(Department department, TeacherAcronym teacherID) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        if (teacherID == null ) {
            throw new IllegalArgumentException("TeacherID cannot be null or blank");
        }

        TeacherID directorID = new TeacherID(teacherID);
        department.setDirectorID(directorID);
        return department;
    }


}
