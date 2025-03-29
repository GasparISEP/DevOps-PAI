package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.*;

public interface ITeacherFactory {
    Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode,
                          String location, String country, IAddressFactory addressFactory, Date date, TeacherCategoryID category, WorkingPercentage workingPercentage, TeacherID teacherID,
                          Department department);
}
