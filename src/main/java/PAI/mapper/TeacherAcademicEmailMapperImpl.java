package PAI.mapper;

import PAI.VOs.TeacherAcademicEmail;
import PAI.VOs.TeacherAcronym;
import PAI.persistence.datamodel.TeacherAcademicEmailDataModel;
import org.springframework.stereotype.Component;

@Component
public class TeacherAcademicEmailMapperImpl implements ITeacherAcademicEmailMapper {

    public TeacherAcademicEmailDataModel toDataModel(TeacherAcademicEmail teacherAcademicEmail) {

        if (teacherAcademicEmail == null) { return null; }

        String domainEmail = teacherAcademicEmail.getEmailDomain();
        String AcademicEmail = teacherAcademicEmail.getTeacherAcademicEmail();

        return new TeacherAcademicEmailDataModel(domainEmail, AcademicEmail);
    }

    public TeacherAcademicEmail toDomain(TeacherAcademicEmailDataModel teacherAcademicEmailDataModel) throws IllegalArgumentException {

        if (teacherAcademicEmailDataModel == null) { return null; }

        String academicEmail = teacherAcademicEmailDataModel.getTeacherAcademicEmail();

        if (academicEmail == null || !academicEmail.contains("@")) {
            throw new IllegalArgumentException ("Invalid email format - must contain '@'");
        }

        String acronym = academicEmail.substring(0, academicEmail.indexOf("@"));
        TeacherAcronym teacherAcronym = new TeacherAcronym(acronym);

        return new TeacherAcademicEmail(teacherAcronym);
    }
}