package PAI.assembler.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.dto.teacher.RegisterTeacherRequestDTO;
import PAI.dto.teacher.TeacherDTO;
import PAI.dto.teacher.TeacherIdDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TeacherAssemblerImpl implements ITeacherAssembler{

    @Override
    public RegisterTeacherCommandDTO toRegisterTeacherCommandDTO(RegisterTeacherRequestDTO requestDTO) {
        if(requestDTO == null){
            throw new IllegalArgumentException("TeacherRequestDTO cannot be null");
        }
        TeacherID teacherID = new TeacherID(new TeacherAcronym(requestDTO.id()));
        Name name = new Name(requestDTO.name());
        Email email = new Email(requestDTO.email());
        NIF nif = new NIF(requestDTO.nif(), new Country(requestDTO.country()));
        PhoneNumber phoneNumber = new PhoneNumber(requestDTO.countryCode(), requestDTO.phoneNumber());
        AcademicBackground academicBackground = new AcademicBackground(requestDTO.academicBackground());
        Street street = new Street(requestDTO.street());
        PostalCode postalCode = new PostalCode(requestDTO.postalCode());
        Location location = new Location(requestDTO.location());
        Country country = new Country(requestDTO.country());
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(requestDTO.departmentID()));

        return new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
    }

    @Override
    public TeacherDTO toDTO(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        return new TeacherDTO(
                teacher.getTeacherID().getTeacherAcronym().getAcronym(),
                teacher.getName().getName(),
                teacher.getEmail().getEmail(),
                teacher.getNIF().getNIF(),
                teacher.getPhoneNumber().getCountryCode(),
                teacher.getPhoneNumber().getNumber(),
                teacher.getAcademicBackground().getAcademicBackground(),
                teacher.getAddress().getStreet().getStreet(),
                teacher.getAddress().getPostalCode().getPostalCode(),
                teacher.getAddress().getLocation().getLocation(),
                teacher.getAddress().getCountry().getCountryName(),
                teacher.getDepartmentID().getAcronym().getAcronym()
        );
    }

    @Override
    public TeacherIdDTO toIdDTO (Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        return new TeacherIdDTO(teacher.getTeacherID().getTeacherAcronym().getAcronym());
    }

    @Override
    public Iterable<TeacherDTO> toDTOs(Iterable<Teacher> teachers) {
        if (teachers == null) {
            return Collections.emptyList();
        }

        List<TeacherDTO> listDTO = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherDTO teacherDTO = toDTO(teacher);
            listDTO.add(teacherDTO);
        }
        return listDTO;
    }

    @Override
    public TeacherID fromStringToTeacherID(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Teacher ID cannot be null or blank");
        }
        return new TeacherID(new TeacherAcronym(id));
    }
}
