package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ITeacherRepository;

import java.util.Optional;

public class CourseEditionApplicationService implements ICourseEditionApplicationService {

    private final ICourseEditionRepository courseEditionRepository;
    private final ITeacherRepository teacherRepository;

    public CourseEditionApplicationService(ICourseEditionRepository courseEditionRepository,
                                           ITeacherRepository teacherRepository) {
        this.courseEditionRepository = courseEditionRepository;
        this.teacherRepository = teacherRepository;
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Iterable<CourseEdition> getAllCourseEditions() {
        return courseEditionRepository.findAll();
    }

    public boolean assignRucToCourseEdition(TeacherID teacherID, CourseEditionID courseEditionID) {
        if (teacherID == null || courseEditionID == null) return false;

        Optional<CourseEdition> courseOpt = courseEditionRepository.ofIdentity(courseEditionID);
        Optional<Teacher> teacherOpt = teacherRepository.ofIdentity(teacherID);

        if (courseOpt.isEmpty() || teacherOpt.isEmpty()) return false;

        CourseEdition course = courseOpt.get();
        Teacher teacher = teacherOpt.get();

        boolean success = course.setRuc(teacher.identity());
        if (success) {
            courseEditionRepository.save(course); // Persist updated aggregate
        }

        return success;
    }
}
