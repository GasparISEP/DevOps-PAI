package PAI.service.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;
import PAI.repository.courseRepository.ICourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    private final ICourseFactory courseFactory;
    private final ICourseRepository courseRepository;

    public CourseServiceImpl (ICourseFactory icourseFactory, ICourseRepository icourseRepository) {

        if (icourseFactory != null)
            this.courseFactory = icourseFactory;
        else
            throw new IllegalArgumentException("Course Factory cannot be null.");

        if(icourseRepository != null)
            this.courseRepository = icourseRepository;
        else
            throw new IllegalArgumentException("Course Repository cannot be null.");
    }

    public Course newCourse (Name name, Acronym acronym) throws Exception {
        Course course = this.courseFactory.createCourse(name, acronym);
        return this.courseRepository.save(course);
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
