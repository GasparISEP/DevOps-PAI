package PAI.factory;

import PAI.VOs.*;
import PAI.domain.CourseEdition;
import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.domain.Student;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentGradeFactoryImpl implements IStudentGradeFactory {

    ICourseEditionRepository _courseEditionRepository ;
    IProgrammeEditionRepository _programmeEditionRepository;
    ISchoolYearRepository _schoolYearRepository;

    public StudentGradeFactoryImpl(ICourseEditionRepository _courseEditionRepository, IProgrammeEditionRepository _programmeEditionRepository, ISchoolYearRepository _schoolYearRepository) {
        this._courseEditionRepository = _courseEditionRepository;
        this._programmeEditionRepository = _programmeEditionRepository;
        this._schoolYearRepository = _schoolYearRepository;
    }

    private boolean isDateGradeInRangeWithSchoolYear (CourseEditionID courseEditionID, Date dates) throws Exception{
        Optional<CourseEdition> courseEdition = _courseEditionRepository.ofIdentity(courseEditionID);
        ProgrammeEditionID programmeEditionID = courseEdition.get().getProgrammeEditionID();
        Optional<ProgrammeEdition> programmeEdition = _programmeEditionRepository.ofIdentity(programmeEditionID);
        SchoolYearID schoolYearID = programmeEdition.get().findSchoolYearIDInProgrammeEdition();
        Optional<SchoolYear> schoolYear = _schoolYearRepository.ofIdentity(schoolYearID);
        LocalDate start = schoolYear.get().getStartDate().getLocalDate();
        LocalDate finalD = schoolYear.get().getEndDate().getLocalDate();
        LocalDate gradeDate = dates.getLocalDate();
        if (gradeDate.isBefore(start) || gradeDate.isAfter(finalD)) return false;
        return true;
    }

    public StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception {
        if (grade == null){
            throw new IllegalArgumentException("Grade cannot be null.");
        }
        if (date == null){
            throw new IllegalArgumentException ("Date cannot be null or empty!");
        }
        if (student == null){
            throw  new IllegalArgumentException("Student cannot be null");
        }
        if (courseEditionID == null){
            throw  new IllegalArgumentException("Course Edition cannot be null");
        }

        if (isDateGradeInRangeWithSchoolYear(courseEditionID,date)){
        return new StudentGrade(grade, date, student, courseEditionID);
    }
        throw new IllegalArgumentException("Date is out of Range");
        }
}
