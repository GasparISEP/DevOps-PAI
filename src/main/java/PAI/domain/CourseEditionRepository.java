package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionRepository {

    private ArrayList<CourseEdition> _courseEditionRepository;

    public CourseEditionRepository() {

        _courseEditionRepository = new ArrayList<>();

    }

    public boolean createCourseEdition(Course course, ProgrammeEdition programmeEdition) {

        try {
            CourseEdition courseEdition = new CourseEdition(course, programmeEdition);
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

    //US16 - return the programmeEdition that belongs to a course Edition, if the course edition exists in this repository
    public ProgrammeEdition findWhichProgrammeEditionBelongsToACourseEdition(CourseEdition courseEdition) throws Exception {
        for (CourseEdition courseEdition1 : _courseEditionRepository)
            if (courseEdition1.equals(courseEdition)) {
                return courseEdition1.whatProgrammeEditionBelongsThisCourseEdition();
            }

        throw new Exception("The course edition does not belong to the course Edition Repository.");
    }

    public List<CourseEdition> findCourseEditionsByProgrammeEdition(ProgrammeEdition programmeEdition) {
        List<CourseEdition> result = new ArrayList<>();
        for (CourseEdition courseEdition : _courseEditionRepository) {
            if (courseEdition.whatProgrammeEditionBelongsThisCourseEdition().equals(programmeEdition)) {
                result.add(courseEdition);
            }
        }

        return result;
    }
}





