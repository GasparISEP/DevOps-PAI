package PAI.assembler.programme;

import PAI.VOs.TeacherAcronym;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.Programme.ProgrammeDirectorVOsDTO;
import PAI.dto.teacher.TeacherIdDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgrammeDirectorAssembler implements IProgrammeDirectorAssembler {

    public ProgrammeDirectorResponseDTO fromDomainToDTO(List<Teacher> teachers) {
        List<TeacherIdDTO> teacherDTOs = teachers.stream()
                .map(t -> new TeacherIdDTO(t.getTeacherID().getTeacherAcronym().getAcronym()))
                .collect(Collectors.toList());

        return new ProgrammeDirectorResponseDTO(teacherDTOs);
    }

    public ProgrammeDirectorVOsDTO fromDTOToDomain(ProgrammeDirectorVOsDTO dto) {
        TeacherAcronym teacherAcronym = new TeacherAcronym(dto.teacherID());
        return new ProgrammeDirectorVOsDTO(teacherAcronym.getAcronym());
    }
}