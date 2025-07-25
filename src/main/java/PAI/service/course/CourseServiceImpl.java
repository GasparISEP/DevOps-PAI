package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.assembler.course.ICourseAssembler;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;
import PAI.dto.course.CourseDTOCommand;
import PAI.dto.course.CourseIDDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.domain.repositoryInterfaces.course.ICourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    private final ICourseFactory courseFactory;
    private final ICourseRepository courseRepository;
    private final ICourseAssembler courseAssembler;

    public CourseServiceImpl (ICourseFactory icourseFactory, ICourseRepository icourseRepository, ICourseAssembler courseAssembler) {

        if (icourseFactory != null)
            this.courseFactory = icourseFactory;
        else
            throw new IllegalArgumentException("Course Factory cannot be null.");

        if(icourseRepository != null)
            this.courseRepository = icourseRepository;
        else
            throw new IllegalArgumentException("Course Repository cannot be null.");

        if(courseAssembler != null)
            this.courseAssembler = courseAssembler;
        else
            throw new IllegalArgumentException("Course Assembler cannot be null.");
    }

    public Course createAndSaveCourse (Name name, Acronym acronym) throws Exception {
        Course course = this.courseFactory.createCourse(name, acronym);

        if (courseRepository.existsCourseIgnoringCaseByName(name)) {
            throw new BusinessRuleViolationException("A course with this name already exists.");
        }

        if (courseRepository.existsCourseByAcronym(acronym)) {
            throw new BusinessRuleViolationException ("A course with this acronym already exists.");
        }
        return this.courseRepository.save(course);
    }

    public List<CourseIDDTO> getAllCourseIDDTO() {
        List<CourseIDDTO> courseIDDTOs = new ArrayList<>();
        for (Course course : this.courseRepository.findAll()) {
            CourseIDDTO courseIDDTO = this.courseAssembler.toIDDTO(course.identity());
            courseIDDTOs.add(courseIDDTO);
        }
        return courseIDDTOs;
    }

    public Iterable <Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> ofIdentity(CourseID courseID) {
        if(courseID == null) {
            return Optional.empty();
        }
        return courseRepository.ofIdentity(courseID);
    }

    public boolean containsOfIdentity(CourseID courseID) {
       return ofIdentity(courseID).isPresent();
   }

}
