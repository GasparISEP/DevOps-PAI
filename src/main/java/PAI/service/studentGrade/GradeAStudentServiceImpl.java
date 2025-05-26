package PAI.service.studentGrade;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.studentGrade.IStudentGradeRepository;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.studentGrade.IStudentGradeFactory;
import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.StudentGradeResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;


@Service
public class GradeAStudentServiceImpl {

    private IStudentGradeFactory _studentGradeFactory;
    private IStudentGradeRepository _studentGradeRepo;
    private ICourseEditionRepository _courseEditionRepo;
    private ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepo;
    private IProgrammeEditionRepository _programmeEditionRepo;
    private ISchoolYearRepository _schoolYearRepo;

    public GradeAStudentServiceImpl (IStudentGradeFactory factory, IStudentGradeRepository repo, ICourseEditionRepository ceRepo,
    ICourseEditionEnrolmentRepository ceeRepo, IProgrammeEditionRepository peRepo, ISchoolYearRepository syRepo) {

        if (factory == null)
            throw new IllegalArgumentException("StudentGradeFactory cannot be null.");
        if (repo == null)
            throw new IllegalArgumentException("StudentGradeRepository cannot be null.");
        if (ceRepo == null)
            throw new IllegalArgumentException("CourseEditionRepository cannot be null.");
        if (ceeRepo == null)
            throw new IllegalArgumentException("CourseEditionEnrolmentRepository cannot be null.");
        if (peRepo == null)
            throw new IllegalArgumentException("ProgrammeEditionRepository cannot be null.");
        if (syRepo == null)
            throw new IllegalArgumentException("SchoolYearRepository cannot be null.");

        _studentGradeFactory = factory;
        _studentGradeRepo = repo;
        _courseEditionRepo = ceRepo;
        _courseEditionEnrolmentRepo = ceeRepo;
        _programmeEditionRepo = peRepo;
        _schoolYearRepo = syRepo;
    }
    
    
    public StudentGradeResponseDTO gradeAStudent (GradeAStudentCommand gradeAStudentCommand) throws Exception {

        validateGradeAStudentCommand(gradeAStudentCommand);

        if (!_courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(gradeAStudentCommand.studentID(), gradeAStudentCommand.courseEditionID()))
            throw new BusinessRuleViolationException("Not possible to add grade as student is not enrolled in this course edition");

        if (!isDateGradeInRangeWithSchoolYear(gradeAStudentCommand.courseEditionID(), gradeAStudentCommand.date()))
            throw new BusinessRuleViolationException("Not possible to add grade as it's date is not inside the school year's range");

        if (hasStudentAlreadyGradeAtThisCourseEdition(gradeAStudentCommand.studentID(), gradeAStudentCommand.courseEditionID()))
            throw new BusinessRuleViolationException("Not possible to add grade as student has already been graded in this course edition");

        StudentGrade studentGrade = _studentGradeFactory.newGradeStudent(
            gradeAStudentCommand.grade(), gradeAStudentCommand.date(),
            gradeAStudentCommand.studentID(), gradeAStudentCommand.courseEditionID()
        );

        if (_studentGradeRepo.containsOfIdentity(studentGrade.identity()))
            throw new BusinessRuleViolationException("The provided grade for this Student in this Course Edition already exists.");

        _studentGradeRepo.save(studentGrade);

        int studentUniqueNumber = studentGrade.get_studentID().getUniqueNumber();
        double grade = studentGrade.knowGrade();
        String date = studentGrade.get_date().toString();
        String courseEditionID = studentGrade.get_courseEditionID().toString();
        String programmeEditionID = studentGrade.get_courseEditionID().getProgrammeEditionID().toString();
        String courseInStudyPlanID = studentGrade.get_courseEditionID().getCourseInStudyPlanID().toString();
        String schoolYearID = studentGrade.get_courseEditionID().getProgrammeEditionID().getSchoolYearID().toString();
        String programmeID = studentGrade.get_courseEditionID().getProgrammeEditionID().getProgrammeID().toString();
        String courseID = studentGrade.get_courseEditionID().getCourseInStudyPlanID().getCourseID().toString();
        String studyPlanID = studentGrade.get_courseEditionID().getCourseInStudyPlanID().getStudyPlanID().toString();

        return new StudentGradeResponseDTO(studentUniqueNumber, grade, date, courseEditionID, programmeEditionID,
                courseInStudyPlanID, programmeID, schoolYearID, courseID, studyPlanID);
    }

    /*
    public Iterable<CourseEdition> findAllCourseEditions() {
        return _courseEditionRepo.findAll();
    }
    */
    
    private void validateGradeAStudentCommand(GradeAStudentCommand command) {
        // First validate if the object exists
        if (command == null)
            throw new IllegalArgumentException("GradeAStudent command cannot be null");
        // Now validate the object's content so we don't get NullPointerException
        if (command.grade() == null)
            throw new IllegalArgumentException("Grade cannot be null");
        if (command.date() == null)
            throw new IllegalArgumentException("Date cannot be null");
        if (command.studentID() == null)
            throw new IllegalArgumentException("Student ID cannot be null");
        if (command.courseEditionID() == null)
            throw new IllegalArgumentException("Course Edition ID cannot be null");
    }

    private boolean isDateGradeInRangeWithSchoolYear (CourseEditionID courseEditionID, Date dates) {

        Optional<CourseEdition> courseEdition = _courseEditionRepo.ofIdentity(courseEditionID);
        ProgrammeEditionID programmeEditionID = courseEdition.get().getProgrammeEditionID();
        Optional<ProgrammeEdition> programmeEdition = _programmeEditionRepo.ofIdentity(programmeEditionID);
        SchoolYearID schoolYearID = programmeEdition.get().findSchoolYearIDInProgrammeEdition();
        Optional<SchoolYear> schoolYear = _schoolYearRepo.ofIdentity(schoolYearID);
        LocalDate start = schoolYear.get().getStartDate().getLocalDate();
        LocalDate finalD = schoolYear.get().getEndDate().getLocalDate();
        LocalDate gradeDate = dates.getLocalDate();

        if (gradeDate.isBefore(start) || gradeDate.isAfter(finalD))
            return false;

        return true;
    }

    private boolean hasStudentAlreadyGradeAtThisCourseEdition (StudentID student, CourseEditionID courseEditionID) {
        for ( StudentGrade existingGradeStudent : _studentGradeRepo.findAll()){
            if ( existingGradeStudent.hasThisStudentID(student) && existingGradeStudent.hasThisCourseEditionID(courseEditionID)) return true;
        }
        return false;
    }

    public double knowApprovalRate(CourseEditionID courseEditionID) throws Exception {
        int totalApprovalStudents = 0;
        int totalOfStudents = 0;

        for (StudentGrade studentGrade : _studentGradeRepo.findAll()) {
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

        return ((double) totalApprovalStudents / totalOfStudents) * 100;
    }

    public Double getAverageGrade(CourseEditionID courseEditionID) {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (StudentGrade studentGrade : _studentGradeRepo.findAll()) {
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
}
