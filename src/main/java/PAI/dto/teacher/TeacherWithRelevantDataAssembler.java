package PAI.dto.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeacherWithRelevantDataAssembler {

    public TeacherWithRelevantDataAssembler() {}

    public TeacherWithRelevantDataDTO toDTOWithNameAndCategory(Teacher teacher, TeacherCategory teacherCategory, TeacherCareerProgression teacherCareerProgression){
        String name = teacher.getName().getName();
        String acronym = teacher.getTeacherID().toString();
        String category = teacherCategory.getName().getName();
        int workingPercentage = teacherCareerProgression.getWorkingPercentage().getValue();
        return new PAI.dto.teacher.TeacherWithRelevantDataDTO(name, acronym, category, workingPercentage);
    }

    public TeacherID toTeacherID (String id) {
        return new TeacherID(new TeacherAcronym(id));
    }

    public Name toName (String name) {
        return new Name(name);
    }

    public Email toEmail (String email) {
        return new Email(email);
    }

    public NIF toNif (String nif, String country) {
        return new NIF(nif, new Country(country));
    }

    public PhoneNumber toPhoneNumber(String countryCode, String phoneNumber) {
        return new PhoneNumber(countryCode, phoneNumber);
    }

    public AcademicBackground toAcademicBackground(String background) {
        return new AcademicBackground(background);
    }

    public Street toStreet(String street){
        return new Street(street);
    }

    public PostalCode toPostalCode(String postalCode) {
        return new PostalCode(postalCode);
    }

    public Location toLocation(String location) {
        return new Location(location);
    }

    public Country toCountry(String country) {
        return new Country(country);
    }

    public DepartmentID toDepartmentID(String departmentID) {
        return new DepartmentID(new DepartmentAcronym(departmentID));
    }

    public Date toDate (String date) {
        return new Date(date);
    }

    public TeacherCategoryID toTeacherCategoryID(String teacherCategoryID) {
        return new TeacherCategoryID(UUID.fromString(teacherCategoryID));
    }

    public WorkingPercentage toWorkingPercentage(String workingpercentage) {
        return new WorkingPercentage(Integer.parseInt(workingpercentage));
    }
}
