package PAI.assembler.teacher;

import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.TeacherDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TeacherAssemblerImpl implements ITeacherAssembler{

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
}
