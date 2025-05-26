package PAI.assembler.programme;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.TeacherAcronym;
import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeDirectorRequestDTO;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.Programme.ProgrammeDirectorVOsDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgrammeDirectorAssembler implements IProgrammeDirectorAssembler {

    public ProgrammeDirectorResponseDTO fromDomainToDTO(List<Programme> programmes, List<Teacher> teachers) {

        List<ProgrammeDirectorResponseDTO.ProgrammeDTO> programmeDTOs =
                programmes.stream()
                        .map(p -> new ProgrammeDirectorResponseDTO.ProgrammeDTO(
                                p.getProgrammeName().getnameWithNumbersAndSpecialChars(),
                                p.getAcronym().getAcronym()
                        ))
                        .collect(Collectors.toList());

        List<ProgrammeDirectorResponseDTO.TeacherDTO> teacherDTOs =
                teachers.stream()
                        .map(t -> new ProgrammeDirectorResponseDTO.TeacherDTO(
                                t.getTeacherID().getTeacherAcronym().getAcronym()
                        ))
                        .collect(Collectors.toList());

        return new ProgrammeDirectorResponseDTO(programmeDTOs, teacherDTOs);
    }


    public ProgrammeDirectorVOsDTO fromDTOToDomain(ProgrammeDirectorRequestDTO dto) {

        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(dto.getProgrammeName());
        Acronym programmeAcronym = new Acronym(dto.getProgrammeAcronym());
        TeacherAcronym teacherAcronym = new TeacherAcronym(dto.getTeacherAcronym());

        return new ProgrammeDirectorVOsDTO(programmeName, programmeAcronym, teacherAcronym);
    }
}