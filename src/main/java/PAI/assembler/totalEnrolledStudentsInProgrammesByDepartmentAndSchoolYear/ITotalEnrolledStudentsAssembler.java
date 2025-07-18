package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;

public interface ITotalEnrolledStudentsAssembler {

    TotalEnrolledStudentsCommand fromRequestToCommand (String departmentID, String schoolYearID) throws IllegalArgumentException;
}
