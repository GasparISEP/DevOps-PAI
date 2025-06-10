package PAI.assembler.studentGrade;

import PAI.VOs.*;
import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentGradeAssembler implements IStudentGradeAssembler {

    public GradeAStudentCommand toDomain (GradeAStudentRequestDTO requestDTO) throws Exception {

        Grade grade = createGrade(requestDTO.grade());
        Date date = createDate(requestDTO.date());
        StudentID studentID = createStudentID(requestDTO.studentUniqueNumber());
        ProgrammeID programmeID = createProgrammeID(requestDTO.programmeAcronym());
        SchoolYearID schoolYearID = createSchoolYearID(requestDTO.schoolYearId());
        CourseID courseID = createCourseID(requestDTO.courseAcronym(), requestDTO.courseName());
        StudyPlanID studyPlanID = createStudyPlanID(requestDTO.studyPlanImplementationDate(), programmeID);
        CourseInStudyPlanID courseInStudyPlanID = createCourseInStudyPlanID(courseID, studyPlanID);
        ProgrammeEditionID programmeEditionID = createProgrammeEditionID(programmeID, schoolYearID);
        CourseEditionID courseEditionID = createCourseEditionID(programmeEditionID, courseInStudyPlanID);

        return new GradeAStudentCommand(grade, date, studentID, courseEditionID);
    }

    public GradeAStudentResponseDTO toDTO(
            StudentGrade studentGrade, String programmeID, String schoolYearID, String courseID,
            String studyPlanID, String courseInStudyPlanID, String programmeEditionID, String courseEditionID) {

        int uniqueNumber = studentGrade.getStudentID().getUniqueNumber();
        double grade = studentGrade.getGrade().knowGrade();
        String date = studentGrade.getDate().toString();

        return new GradeAStudentResponseDTO(uniqueNumber, grade, date, courseEditionID, programmeEditionID, courseInStudyPlanID, programmeID, schoolYearID, courseID, studyPlanID);
    }

    private Grade createGrade (double grade) throws Exception {
        return new Grade(grade);
    }

    private Date createDate (String date) {
        return new Date(date);
    }

    private StudentID createStudentID (int uniqueNumber) {
        return new StudentID(uniqueNumber);
    }

    private CourseEditionID createCourseEditionID(ProgrammeEditionID programmeEditionID, CourseInStudyPlanID courseInStudyPlanID) {
        return new CourseEditionID(programmeEditionID, courseInStudyPlanID);
    }

    // The methods below serve the method above
    private ProgrammeID createProgrammeID(String programmeAcronym) {
        Acronym acronym = new Acronym(programmeAcronym);
        return new ProgrammeID(acronym);
    }

    private SchoolYearID createSchoolYearID(String schoolYearId) {
        UUID uuid = UUID.fromString(schoolYearId);
        return new SchoolYearID(uuid);
    }

    private CourseID createCourseID(String courseAcronym, String courseName) {
        Acronym acronym = new Acronym(courseAcronym);
        Name name = new Name(courseName);
        return new CourseID(acronym, name);
    }

    private StudyPlanID createStudyPlanID(String studyPlanDate, ProgrammeID programmeID) {
        Date date = new Date(studyPlanDate);
        return new StudyPlanID(programmeID, date);
    }

    private ProgrammeEditionID createProgrammeEditionID(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        return new ProgrammeEditionID(programmeID, schoolYearID);
    }

    private CourseInStudyPlanID createCourseInStudyPlanID(CourseID courseID, StudyPlanID studyPlanID) {
        return new CourseInStudyPlanID(courseID, studyPlanID);
    }
}