package PAI.assembler.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.SelectedCourseEditionIdDTO;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;

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
                dto.programmeName(),
                dto.programmeAcronym(),
                dto.schoolYearID(),
                dto.courseAcronym(),
                dto.courseName(),
                dto.studyPlanImplementationDate()
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

        return new CourseEditionResponseDTO(
                courseEdition.identity().toString(),

                programmeID.getProgrammeName(),
                programmeID.getProgrammeAcronym(),
                schoolYearID.getSchoolYearID(),

                cspID.getCourseID().getCourseAcronymValue(),
                cspID.getCourseID().getCourseNameValue(),
                cspID.getStudyPlanID().getLocalDate()
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
            String programmeName = courseEditionID.getProgrammeEditionID().getProgrammeID().getProgrammeName();
            String programmeAcronym = courseEditionID.getProgrammeEditionID().getProgrammeID().getProgrammeAcronym();
            UUID schoolYearID = courseEditionID.getProgrammeEditionID().getSchoolYearID().getSchoolYearID();
            String courseName = courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseNameValue();
            String courseAcronym = courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseAcronymValue();
            LocalDate studyPlanImplementationDate = courseEditionID.getCourseInStudyPlanID().getStudyPlanID().getLocalDate();
            CourseEditionResponseDTO  courseEditionResponseDTO = new CourseEditionResponseDTO(courseEditionIDToDto, programmeName, programmeAcronym, schoolYearID, courseAcronym, courseName, studyPlanImplementationDate); 
            courseEditionResponseDTOs.add(courseEditionResponseDTO);
        }
        return courseEditionResponseDTOs;
    }

    public TeacherID createTeacherID (String teacherID) {
        return new TeacherID(new TeacherAcronym(teacherID));
    }

    public CourseEditionID fromDtoToCourseEditionID (SelectedCourseEditionIdDTO courseEditionDTO) throws Exception{
        ProgrammeID programmeID= new ProgrammeID(
                new NameWithNumbersAndSpecialChars(courseEditionDTO.programmeName()),
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
}

