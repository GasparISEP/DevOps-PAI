package PAI.assembler.courseEditionEnrolment;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;


import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.VOs.Acronym;
import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.Date;
import PAI.VOs.Name;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.VOs.StudentID;
import PAI.VOs.StudyPlanID;

@Component
public class CourseEditionEnrolmentAssemblerImpl implements ICourseEditionEnrolmentAssembler {
    
    @Override
    public CourseEditionID toCourseEditionID(CourseEditionEnrolmentDto courseEditionEnrolmentDto) throws Exception {
        ProgrammeID programmeID = createProgrammeID(courseEditionEnrolmentDto.programmeAcronym());
        SchoolYearID schoolYearID = createSchoolYearID(courseEditionEnrolmentDto.schoolYearId());
        CourseID courseID = createCourseID(courseEditionEnrolmentDto.courseAcronym(), courseEditionEnrolmentDto.courseName());
        StudyPlanID studyPlanID = createStudyPlanID(courseEditionEnrolmentDto.studyPlanDate(), programmeID);
        CourseInStudyPlanID courseInStudyPlanID = createCourseInStudyPlanID(courseID, studyPlanID);
        ProgrammeEditionID programmeEditionID = createProgrammeEditionID(programmeID, schoolYearID);
        return createCourseEditionID(programmeEditionID, courseInStudyPlanID);
    }

    @Override
    public StudentID toStudentID(int studentUniqueNumber) throws Exception {
        return createStudentID(studentUniqueNumber);
    }

    private StudentID createStudentID(int studentUniqueNumber) {
        return new StudentID(studentUniqueNumber);
    }

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

    private CourseEditionID createCourseEditionID(ProgrammeEditionID programmeEditionID, CourseInStudyPlanID courseInStudyPlanID) throws Exception {
        return new CourseEditionID(programmeEditionID, courseInStudyPlanID);
    }
}
