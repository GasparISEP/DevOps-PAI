package PAI.dto.Programme;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.TeacherAcronym;

public class ProgrammeDirectorVOsDTO {
    private final NameWithNumbersAndSpecialChars programmeName;
    private final Acronym programmeAcronym;
    private final TeacherAcronym teacherAcronym;

    public ProgrammeDirectorVOsDTO(NameWithNumbersAndSpecialChars programmeName, Acronym programmeAcronym, TeacherAcronym teacherAcronym) {
        this.programmeName = programmeName;
        this.programmeAcronym = programmeAcronym;
        this.teacherAcronym = teacherAcronym;
    }

    public NameWithNumbersAndSpecialChars getProgrammeName() {
        return programmeName;
    }

    public Acronym getProgrammeAcronym() {
        return programmeAcronym;
    }

    public TeacherAcronym getTeacherAcronym() {
        return teacherAcronym;
    }
}
