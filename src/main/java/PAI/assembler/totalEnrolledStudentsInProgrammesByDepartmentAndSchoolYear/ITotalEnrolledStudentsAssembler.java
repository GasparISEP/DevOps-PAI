package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;

public interface ITotalEnrolledStudentsAssembler {

    TotalEnrolledStudentsCommand fromRequestToCommand (TotalEnrolledStudentsRequest request);
}
