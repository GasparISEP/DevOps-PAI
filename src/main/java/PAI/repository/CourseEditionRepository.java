package PAI.repository;

import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.ProgrammeEdition;
import PAI.domain.Teacher;
import PAI.factory.CourseEditionFactoryImpl;
import PAI.factory.CourseEditionListFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionRepository {

    private List<CourseEdition> _courseEditionRepository;
    private final CourseEditionFactoryImpl _courseEditionFactoryImpl;
    private final CourseEditionListFactoryImpl _courseEditionListFactoryImpl;

    public CourseEditionRepository(CourseEditionFactoryImpl courseEditionFactoryImpl, CourseEditionListFactoryImpl courseEditionListFactoryImpl) {
        _courseEditionFactoryImpl = courseEditionFactoryImpl;
        _courseEditionListFactoryImpl = courseEditionListFactoryImpl;
        _courseEditionRepository = courseEditionListFactoryImpl.newArrayList();

    }

    public boolean createAndSaveCourseEdition(Course course, ProgrammeEdition programmeEdition) {

        try {
            CourseEdition courseEdition = _courseEditionFactoryImpl.newCourseEdition(course, programmeEdition);
            if (isCourseEditionAlreadyInRepository(courseEdition))
                return false;

            _courseEditionRepository.add(courseEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isCourseEditionAlreadyInRepository(CourseEdition courseEdition) {
        return _courseEditionRepository.contains(courseEdition);
    }

    // US20 - returns a list of all course editions stored in the repository
    public List<CourseEdition> getCourseEditions() {
        return new ArrayList<>(_courseEditionRepository); // Retorna uma cópia da lista
    }

    // US20 - sets the RUC for a specific course edition
    public boolean setRucInACourseEdition(CourseEdition ce1, Teacher t1) {
        if (!_courseEditionRepository.contains(ce1)) {
            throw new IllegalArgumentException("Course edition not found in repository.");
        }
        return ce1.setRuc(t1);
    }

    public ProgrammeEdition findWhichProgrammeEditionBelongsToACourseEdition(CourseEdition courseEdition) throws Exception {
        for (CourseEdition courseEdition1 : _courseEditionRepository)
            if (courseEdition1.equals(courseEdition)) {
                return courseEdition1.whatProgrammeEditionBelongsThisCourseEdition();
            }

        throw new Exception("The course edition does not belong to the course Edition Repository.");
    }

    public List<CourseEdition> findCourseEditionsByProgrammeEdition(ProgrammeEdition programmeEdition) {
        List<CourseEdition> result = _courseEditionListFactoryImpl.newArrayList();
        for (CourseEdition courseEdition : _courseEditionRepository) {
            if (courseEdition.whatProgrammeEditionBelongsThisCourseEdition().equals(programmeEdition)) {
                result.add(courseEdition);
            }
        }

        return result;
    }
}





