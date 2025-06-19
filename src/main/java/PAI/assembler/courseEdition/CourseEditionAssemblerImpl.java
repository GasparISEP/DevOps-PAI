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

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class CourseEditionAssemblerImpl implements ICourseEditionAssembler {
    @Override
    public CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto) {
        validateNotNull(dto, "CourseEditionRequestDTO");
        return new CreateCourseEditionCommand(
                new Acronym(dto.programmeAcronym()),
                new SchoolYearID(dto.schoolYearID()),
                new Acronym(dto.courseAcronym()),
                new Name(dto.courseName()),
                new Date(dto.studyPlanImplementationDate())
        );
    }

    @Override
    public CourseEditionResponseDTO toResponseDTO(CourseEditionServiceResponseDTO courseEditionServiceDTO) {
        validateNotNull(courseEditionServiceDTO, "CourseEditionServiceResponseDTO");


        return new CourseEditionResponseDTO(
                courseEditionServiceDTO.courseEditionGeneratedID(),
                courseEditionServiceDTO.programmeAcronym(),
                courseEditionServiceDTO.schoolYearID(),
                courseEditionServiceDTO.courseAcronym(),
                courseEditionServiceDTO.courseName(),
                courseEditionServiceDTO.studyPlanImplementationDate(),
                courseEditionServiceDTO.courseEditionID(),
                courseEditionServiceDTO.teacherID() != null ? courseEditionServiceDTO.teacherID() : null

                );
    }

    @Override
    public List<CourseEditionResponseIDDTO> toResponseIDDTOList(List<CourseEditionID> courseEditionIDs){
        if (courseEditionIDs == null) {
            throw new IllegalArgumentException("CourseEditionIDs cannot be null");
        }
        List<CourseEditionResponseIDDTO> courseEditionResponseIDDTOs = new ArrayList<>();
        for (CourseEditionID courseEditionID : courseEditionIDs) {
            String courseEditionIDToDto = courseEditionID.toString();
            String programmeAcronym = courseEditionID.getProgrammeEditionID().getProgrammeID().getProgrammeAcronym();
            UUID schoolYearID = courseEditionID.getProgrammeEditionID().getSchoolYearID().getSchoolYearID();
            String courseName = courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseNameValue();
            String courseAcronym = courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseAcronymValue();
            LocalDate studyPlanImplementationDate = courseEditionID.getCourseInStudyPlanID().getStudyPlanID().getLocalDate();
            CourseEditionResponseIDDTO  courseEditionResponseIDDTO = new CourseEditionResponseIDDTO(programmeAcronym, schoolYearID, courseAcronym, courseName, studyPlanImplementationDate, courseEditionIDToDto);
            courseEditionResponseIDDTOs.add(courseEditionResponseIDDTO);
        }
        return courseEditionResponseIDDTOs;
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

    @Override
    public CourseEditionResponseIDDTO toResponseIDDTO(CourseEditionServiceResponseDTO serviceResponseDTO) {
        if (serviceResponseDTO == null) {
            throw new IllegalArgumentException("CourseEditionServiceResponseDTO cannot be null");
        }

        return new CourseEditionResponseIDDTO(
                serviceResponseDTO.programmeAcronym(),
                serviceResponseDTO.schoolYearID(),
                serviceResponseDTO.courseAcronym(),
                serviceResponseDTO.courseName(),
                serviceResponseDTO.studyPlanImplementationDate(),
                serviceResponseDTO.courseEditionID()
        );
    }

    @Override
    public List<CourseEditionResponseDTO> toCourseEditionResponseDTOList(Iterable<CourseEditionServiceResponseDTO> courseEditionServiceResponseDTOList) {
        List<CourseEditionResponseDTO> dtoList = new ArrayList<>();

        courseEditionServiceResponseDTOList.forEach(serviceDTO -> {
            CourseEditionResponseDTO responseDTO = new CourseEditionResponseDTO(
                    serviceDTO.courseEditionGeneratedID(),
                    serviceDTO.programmeAcronym(),
                    serviceDTO.schoolYearID(),
                    serviceDTO.courseAcronym(),
                    serviceDTO.courseName(),
                    serviceDTO.studyPlanImplementationDate(),
                    serviceDTO.courseEditionID(),
                    serviceDTO.teacherID()
            );
            dtoList.add(responseDTO);
        });

        return dtoList;
    }
}

