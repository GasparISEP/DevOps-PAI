package PAI.service.teacher;

import PAI.VOs.*;
import PAI.domain.department.Department;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacher.TeacherWithRelevantDataDTO;

import java.util.Optional;

public interface ITeacherWithRelevantDataService {

    TeacherWithRelevantDataDTO registerTeacherWithRelevantData (TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                                                Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID, Date date,
                                                                TeacherCategoryID teacherCategoryID, WorkingPercentage wp) throws Exception;

    Optional<Teacher> registerTeacher(TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                      Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) throws Exception;

    Optional<TeacherCareerProgression> createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;

    Optional<TeacherCategory> getTeacherCategory(TeacherCareerProgression teacherCareerProgression);

    Iterable<Department> findAll();

    Iterable<TeacherCategory> getAllTeacherCategories();
}
