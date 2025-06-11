package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.SchoolYearID;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TotalEnrolledStudentsAssemblerImpl implements ITotalEnrolledStudentsAssembler{

    @Override
    public TotalEnrolledStudentsCommand fromRequestToCommand(String departmentID, String schoolYearID) {
        if (departmentID == null)
            throw new IllegalArgumentException("departmentID cannot be null");
        if (schoolYearID == null)
            throw new IllegalArgumentException("schoolYearID cannot be null");

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(departmentID);
        DepartmentID departmentIdDomain = new DepartmentID(departmentAcronym);

        UUID uuidSchoolYearID = UUID.fromString(schoolYearID);
        SchoolYearID schoolYearIDDomain = new SchoolYearID(uuidSchoolYearID);

        return new TotalEnrolledStudentsCommand(departmentIdDomain, schoolYearIDDomain);
    }
}
