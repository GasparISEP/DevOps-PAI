package PAI.service.courseEdition;
import PAI.VOs.CourseEditionGeneratedID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.teacher.Teacher;
import PAI.exception.AlreadyAssignedRUCException;
import PAI.exception.CourseEditionPersistenceException;
import PAI.exception.TeacherNotFoundException;
import PAI.service.teacher.ITeacherService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static PAI.utils.ValidationUtils.validateNotNull;

@Service
public class DefineRucServiceImpl implements IDefineRucService {

    private final ICourseEditionRepository courseEditionRepository;
    private final ITeacherService teacherService;

    public DefineRucServiceImpl(ICourseEditionRepository courseEditionRepository, ITeacherService teacherService) {

        this.teacherService = validateNotNull(teacherService, "TeacherService");
        this.courseEditionRepository = validateNotNull(courseEditionRepository, "CourseEditionRepository");
    }

    public Iterable<CourseEdition> findAll() {
        return courseEditionRepository.findAll();
    }

    public Iterable <Teacher> findAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @Override
    @Transactional
    public boolean assignRucToCourseEdition(TeacherID teacherId, CourseEditionGeneratedID courseEditionId) throws Exception {
        if (!teacherService.existsById(teacherId)) {
            throw new TeacherNotFoundException("Teacher with given ID does not exist.");
        }

        Optional<CourseEdition> optionalEdition = courseEditionRepository.findCourseEditionByGeneratedId(courseEditionId);
        if (optionalEdition.isEmpty()) {
            return false;
        }

        CourseEdition courseEdition = optionalEdition.get();

        if (teacherId.equals(courseEdition.getRuc())) {
            throw new AlreadyAssignedRUCException("This teacher is already assigned as the RUC for this course edition.");
        }

        boolean success = courseEdition.setRuc(teacherId);
        if (success) {
            try {
                courseEditionRepository.save(courseEdition);
            } catch (Exception e) {
                throw new CourseEditionPersistenceException("Error when persisting CourseEdition with new RUC");
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
