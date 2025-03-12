package PAI.repository;

import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.ProgrammeEdition;
import PAI.domain.Teacher;
import PAI.factory.CourseEditionFactory;
import PAI.factory.CourseEditionListFactory;
import PAI.factory.CourseEditionListFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionRepository {

    private List<CourseEdition> _courseEditions;
    private final CourseEditionFactory _courseEditionFactory;

    public CourseEditionRepository(CourseEditionFactory courseEditionFactory, CourseEditionListFactory courseEditionListFactory) {
        _courseEditionFactory = courseEditionFactory;
        _courseEditions = courseEditionListFactory.newArrayList();

    }

    public boolean createAndSaveCourseEdition(Course course, ProgrammeEdition programmeEdition) {

        try {
            CourseEdition courseEdition = _courseEditionFactory.newCourseEdition(course, programmeEdition);
            if (isCourseEditionAlreadyInRepository(courseEdition))
                return false;

            _courseEditions.add(courseEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isCourseEditionAlreadyInRepository(CourseEdition courseEdition) {
        return _courseEditions.contains(courseEdition);
    }

    // US20 - returns a list of all course editions stored in the repository
    public List<CourseEdition> getCourseEditions() {
        return new ArrayList<>(_courseEditions); // Retorna uma cópia da lista
    }

    // US20 - sets the RUC for a specific course edition
    public boolean setRucInACourseEdition(CourseEdition ce1, Teacher t1) {
        if (!_courseEditions.contains(ce1)) {
            throw new IllegalArgumentException("Course edition not found in repository.");
        }
        return ce1.setRuc(t1);
    }

    public ProgrammeEdition findWhichProgrammeEditionBelongsToACourseEdition(CourseEdition courseEdition) throws Exception {
        for (CourseEdition courseEdition1 : _courseEditions)
            if (courseEdition1.equals(courseEdition)) {
                return courseEdition1.whatProgrammeEditionBelongsThisCourseEdition();
            }

        throw new Exception("The course edition does not belong to the course Edition Repository.");
    }

    public List<CourseEdition> findCourseEditionsByProgrammeEdition(ProgrammeEdition programmeEdition) {
        List<CourseEdition> result = new ArrayList<>();
        for (CourseEdition courseEdition : _courseEditions) {
            if (courseEdition.whatProgrammeEditionBelongsThisCourseEdition().equals(programmeEdition)) {
                result.add(courseEdition);
            }
        }

        return result;
    }
}





