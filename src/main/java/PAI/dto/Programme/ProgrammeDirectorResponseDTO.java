package PAI.dto.Programme;

import java.util.List;

public class ProgrammeDirectorResponseDTO {

    private List<ProgrammeDTO> programmes;
    private List<TeacherDTO> teachers;

    public ProgrammeDirectorResponseDTO() {}

    public ProgrammeDirectorResponseDTO(List<ProgrammeDTO> programmes, List<TeacherDTO> teachers) {
        this.programmes = programmes;
        this.teachers = teachers;
    }

    public List<ProgrammeDTO> getProgrammes() {
        return programmes;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public static class ProgrammeDTO {
        private String programmeName;
        private String programmeAcronym;

        public ProgrammeDTO() {}

        public ProgrammeDTO(String programmeName, String programmeAcronym) {
            this.programmeName = programmeName;
            this.programmeAcronym = programmeAcronym;
        }

        public String getProgrammeName() {
            return programmeName;
        }

        public String getProgrammeAcronym() {
            return programmeAcronym;
        }
    }

    public static class TeacherDTO {
        private String teacherAcronym;

        public TeacherDTO() {}

        public TeacherDTO(String teacherAcronym) {
            this.teacherAcronym = teacherAcronym;
        }

        public String getTeacherAcronym() {
            return teacherAcronym;
        }
    }
}

