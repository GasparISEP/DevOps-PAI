package PAI.dto.Programme;

import PAI.dto.teacher.TeacherIdDTO;

import java.util.List;

public record ProgrammeDirectorResponseDTO (List<TeacherIdDTO> teachers) {}

