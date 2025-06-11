package PAI.service.courseEdition;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.service.teacher.ITeacherService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefineRucServiceImpl implements IDefineRucService {

    private final ICourseEditionRepository courseEditionRepository;
    private final ITeacherService teacherService;

    public DefineRucServiceImpl(ICourseEditionRepository courseEditionRepository, ITeacherService teacherService) {
        if (teacherService==null)
            throw new IllegalArgumentException("TeacherRepository cannot be null");
        if (courseEditionRepository == null)
            throw new IllegalArgumentException("CourseEditionRepository cannot be null");

        this.teacherService = teacherService;
        this.courseEditionRepository = courseEditionRepository;
    }

    public Iterable<CourseEdition> findAll() {
        return courseEditionRepository.findAll();
    }

    public Iterable <Teacher> findAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @Override
    @Transactional
    public boolean assignRucToCourseEdition(TeacherID teacherId, CourseEditionID courseEditionId) {
        if (!teacherService.existsById(teacherId)) {
            throw new IllegalArgumentException("Teacher with given ID does not exist.");
        }

        Optional<CourseEdition> optionalEdition = courseEditionRepository.ofIdentity(courseEditionId);

        if (optionalEdition.isEmpty()) {
            return false;
        }

        CourseEdition courseEdition = optionalEdition.get();

        if (teacherId.equals(courseEdition.getRuc())) {
            throw new IllegalArgumentException("This teacher is already assigned as the RUC for this course edition.");
        }

        boolean success = courseEdition.setRuc(teacherId);
        if (success) {
            try {
                courseEditionRepository.save(courseEdition);
            } catch (Exception e) {
                throw new RuntimeException("Error when persisting CourseEdition with new RUC", e);
            }
        }
        return success;
    }

    @Override
    public boolean containsOfIdentity(CourseEditionID courseEditionID) {
        if (courseEditionID == null)
            return false;

        return courseEditionRepository.containsOfIdentity(courseEditionID);
    }
 }
