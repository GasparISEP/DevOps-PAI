package PAI.assembler.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class CourseEditionAssemblerImpl implements ICourseEditionAssembler {
    @Override
    public CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CourseEditionRequestDTO cannot be null");
        }
        return new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(dto.programmeName()),
                new Acronym(dto.programmeAcronym()),
                new SchoolYearID(dto.schoolYearID()),
                new Acronym(dto.courseAcronym()),
                new Name(dto.courseName()),
                new Date(dto.studyPlanImplementationDate())
        );
    }

    @Override
    public CourseEditionResponseDTO toResponseDTO(CourseEdition courseEdition) {
        if (courseEdition == null) {
            throw new IllegalArgumentException("CourseEdition cannot be null");
        }
        CourseInStudyPlanID cspID = courseEdition.getCourseInStudyPlanID();
        ProgrammeEditionID peID = courseEdition.getProgrammeEditionID();

        ProgrammeID programmeID = peID.getProgrammeID();
        SchoolYearID schoolYearID = peID.getSchoolYearID();

        String formattedID = URLEncoder.encode(
                programmeID.getProgrammeAcronym() + "-" +
                        schoolYearID.getSchoolYearID() + "_" +
                        cspID.getCourseID().getCourseAcronymValue() + "-" +
                        cspID.getCourseID().getCourseNameValue() + "-" +
                        cspID.getStudyPlanID().getProgrammeID().getProgrammeAcronym() + "-" +
                        cspID.getStudyPlanID().getLocalDate().toString(), // yyyy-MM-dd
                StandardCharsets.UTF_8
        );

        System.out.println("formattedID: " + formattedID);

        return new CourseEditionResponseDTO(
                programmeID.getProgrammeAcronym(),
                schoolYearID.getSchoolYearID(),
                cspID.getCourseID().getCourseAcronymValue(),
                cspID.getCourseID().getCourseNameValue(),
                cspID.getStudyPlanID().getLocalDate(),
                formattedID
                );
    }

    @Override
    public List<CourseEditionResponseDTO> toResponseDTOList(List<CourseEditionID> courseEditionIDs){
        if (courseEditionIDs == null) {
            throw new IllegalArgumentException("CourseEditionIDs cannot be null");
        }
        List<CourseEditionResponseDTO> courseEditionResponseDTOs = new ArrayList<>();
        for (CourseEditionID courseEditionID : courseEditionIDs) {
            String courseEditionIDToDto = courseEditionID.toString();
            String programmeAcronym = courseEditionID.getProgrammeEditionID().getProgrammeID().getProgrammeAcronym();
            UUID schoolYearID = courseEditionID.getProgrammeEditionID().getSchoolYearID().getSchoolYearID();
            String courseName = courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseNameValue();
            String courseAcronym = courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseAcronymValue();
            LocalDate studyPlanImplementationDate = courseEditionID.getCourseInStudyPlanID().getStudyPlanID().getLocalDate();
            CourseEditionResponseDTO  courseEditionResponseDTO = new CourseEditionResponseDTO(programmeAcronym, schoolYearID, courseAcronym, courseName, studyPlanImplementationDate, courseEditionIDToDto);
            courseEditionResponseDTOs.add(courseEditionResponseDTO);
        }
        return courseEditionResponseDTOs;
    }

    public TeacherID createTeacherID (String teacherID) {
        return new TeacherID(new TeacherAcronym(teacherID));
    }
    public CourseEditionGeneratedID fromDtoToCourseEditionGeneratedID (SelectedCourseEditionGeneratedIdDTO courseEditionDTO) {
        return  new CourseEditionGeneratedID(courseEditionDTO.courseEditionID());
    }
    public CourseEditionID fromDtoToCourseEditionID (SelectedCourseEditionIdDTO courseEditionDTO) throws Exception{
        ProgrammeID programmeID= new ProgrammeID(
                new Acronym(courseEditionDTO.programmeAcronym()));
        SchoolYearID schoolYearID= new SchoolYearID(courseEditionDTO.schoolYearID());
        ProgrammeEditionID programmeEditionID= new ProgrammeEditionID(programmeID, schoolYearID);

        CourseID courseID= new CourseID(
                new Acronym(courseEditionDTO.courseAcronym()),
                new Name(courseEditionDTO.courseName()));

        LocalDate localDate = courseEditionDTO.studyPlanImplementationDate();

        Date studyPlanDate = new Date(localDate);

        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);

        CourseInStudyPlanID courseInStudyPlanID= new CourseInStudyPlanID(courseID, studyPlanID);

        return new CourseEditionID(programmeEditionID, courseInStudyPlanID);
    }

    @Override
    public ProgrammeEditionID toProgrammeEditionID(CourseEditionRequestDTO courseEditionRequestDTO) throws Exception {
        if(courseEditionRequestDTO == null) {
            throw new Exception("CourseEditionRequestDTO cannot be null");
        }
        ProgrammeID programmeID = new ProgrammeID(
                new Acronym(courseEditionRequestDTO.programmeAcronym()));
        SchoolYearID schoolYearID = new SchoolYearID(courseEditionRequestDTO.schoolYearID());
        return new ProgrammeEditionID(programmeID, schoolYearID);
    }

    @Override
    public CourseInStudyPlanID toCourseInStudyPlanID(CourseEditionRequestDTO courseEditionRequestDTO) throws Exception {
        if(courseEditionRequestDTO == null) {
            throw new Exception("CourseEditionRequestDTO cannot be null");
        }
        ProgrammeID programmeID = new ProgrammeID(
                new Acronym(courseEditionRequestDTO.programmeAcronym()));
        CourseID courseID = new CourseID(
                new Acronym(courseEditionRequestDTO.courseAcronym()),
                new Name(courseEditionRequestDTO.courseName()));
        Date studyPlanDate = new Date(courseEditionRequestDTO.studyPlanImplementationDate());
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        return new CourseInStudyPlanID(courseID, studyPlanID);
    }
    
}

