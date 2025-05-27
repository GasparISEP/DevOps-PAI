package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.SchoolYearID;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TotalEnrolledStudentsAssemblerImpl implements ITotalEnrolledStudentsAssembler{

    @Override
    public TotalEnrolledStudentsCommand fromRequestToCommand(TotalEnrolledStudentsRequest request) {
        if (request == null)
            throw new IllegalArgumentException("TotalEnrolledStudentsRequest cannot be null");

        String depID = request.departmentID();
        String syID = request.schoolYearID();

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(depID);
        DepartmentID departmentID = new DepartmentID(departmentAcronym);

        UUID uuidSchoolYearID = UUID.fromString(syID);
        SchoolYearID schoolYearID = new SchoolYearID(uuidSchoolYearID);

        return new TotalEnrolledStudentsCommand(departmentID, schoolYearID);
    }
}
