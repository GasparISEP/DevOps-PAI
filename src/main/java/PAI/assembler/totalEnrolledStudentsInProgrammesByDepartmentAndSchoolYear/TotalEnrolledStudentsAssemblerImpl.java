package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import org.springframework.stereotype.Component;

@Component
public class TotalEnrolledStudentsAssemblerImpl implements ITotalEnrolledStudentsAssembler{

    @Override
    public TotalEnrolledStudentsCommand fromRequestToCommand(TotalEnrolledStudentsRequest request) {
        return null;
    }
}
