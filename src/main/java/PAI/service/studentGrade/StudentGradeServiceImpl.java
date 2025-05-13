package PAI.service.studentGrade;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.studentGrade.IStudentGradeRepository;
import PAI.repository.courseEditionRepository.ICourseEditionRepository;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class StudentGradeServiceImpl implements IStudentGradeService {

    private final IStudentGradeFactory studentGradeFactory;
    private final IStudentGradeRepository studentGradeRepository;
    private final ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository;
    private final ICourseEditionRepository courseEditionRepository ;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final ISchoolYearRepository schoolYearRepository;

    public StudentGradeServiceImpl(IStudentGradeFactory studentGradeFactory, IStudentGradeRepository studentGradeRepository, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, ICourseEditionRepository courseEditionRepository, IProgrammeEditionRepository programmeEditionRepository, ISchoolYearRepository schoolYearRepository) {
        if (studentGradeFactory == null) {
            throw new IllegalArgumentException("Student Grade Factory cannot be null");
        }
        this.studentGradeFactory = studentGradeFactory;

        if (studentGradeRepository == null) {
            throw new IllegalArgumentException("Student Grade Repository cannot be null");
        }
        this.studentGradeRepository = studentGradeRepository;

        if(courseEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("CourseEditionEnrolment cannot be null");
        }
        this.courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;

        if(courseEditionRepository == null){
            throw new IllegalArgumentException("Course Edition cannot be null");
        }
        this.courseEditionRepository = courseEditionRepository;

        if(programmeEditionRepository == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        this.programmeEditionRepository = programmeEditionRepository;

        if(schoolYearRepository == null){
            throw new IllegalArgumentException("School Year Repository cannot be null");
        }
        this.schoolYearRepository = schoolYearRepository;
    }

    public StudentGrade  newStudentGrade (Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID) throws Exception {
        if(!courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID,courseEditionID)){
            throw new Exception("Not possible to addGrade, Student in not enrolled in this CourseEdition");
        }

        if(!isDateGradeInRangeWithSchoolYear(courseEditionID,date)){
            throw new Exception("Not possible to addGrade, Grade is not in the correct range");
        }

        if(!hasStudentAlreadyGradeAtThisCourseEdition(studentID,courseEditionID)){
            throw new Exception("Not possible to addGrade, Student already has a grade in this course edition");
        }
        StudentGrade studentGrade =  studentGradeFactory.newGradeStudent(grade,date,studentID,courseEditionID);
        return studentGradeRepository.save(studentGrade);
    }

    public Double getAverageGrade(CourseEditionID courseEditionID) throws Exception {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (StudentGrade studentGrade : studentGradeRepository.findAll()) {
            if (studentGrade.hasThisCourseEditionID(courseEditionID)) {
                Grade grade1 = studentGrade.get_grade();
                double grade = grade1.knowGrade();
                sumGrade += grade;
                numOfStudent++;
            }
        }
        if (numOfStudent == 0) {
            return null;
        }
        return sumGrade / numOfStudent;
    }


    public double knowApprovalRate(CourseEditionID courseEditionID) throws Exception {
        int totalApprovalStudents = 0;
        int totalOfStudents = 0;

        for (StudentGrade studentGrade : studentGradeRepository.findAll()) {
            if (studentGrade.hasThisCourseEditionID(courseEditionID)) {
                totalOfStudents++;
                Grade grade1 = studentGrade.get_grade();
                if (grade1.knowGrade() >= 10) {
                    totalApprovalStudents++;
                }
            }
        }

        if (totalOfStudents == 0) {
            return 0.0;
        }

        double approvalRate = ((double) totalApprovalStudents / totalOfStudents) * 100;
        return approvalRate;
    }

    public boolean isDateGradeInRangeWithSchoolYear (CourseEditionID courseEditionID, Date dates) throws Exception{
        Optional<CourseEdition> courseEdition = courseEditionRepository.ofIdentity(courseEditionID);
        ProgrammeEditionID programmeEditionID = courseEdition.get().getProgrammeEditionID();
        Optional<ProgrammeEdition> programmeEdition = programmeEditionRepository.ofIdentity(programmeEditionID);
        SchoolYearID schoolYearID = programmeEdition.get().findSchoolYearIDInProgrammeEdition();
        Optional<SchoolYear> schoolYear = schoolYearRepository.ofIdentity(schoolYearID);
        LocalDate start = schoolYear.get().getStartDate().getLocalDate();
        LocalDate finalD = schoolYear.get().getEndDate().getLocalDate();
        LocalDate gradeDate = dates.getLocalDate();
        if (gradeDate.isBefore(start) || gradeDate.isAfter(finalD)) return false;
        return true;
    }

    public boolean hasStudentAlreadyGradeAtThisCourseEdition (StudentID student, CourseEditionID courseEditionID) throws Exception {
        for ( StudentGrade existingGradeStudent : studentGradeRepository.findAll()){
            if ( existingGradeStudent.hasThisStudentID(student) && existingGradeStudent.hasThisCourseEditionID(courseEditionID)) return true;
        }
        return false;
    }
}
