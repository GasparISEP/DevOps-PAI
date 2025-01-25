package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionRepository {

    private ArrayList<CourseEdition> _courseEditionRepository;

    public CourseEditionRepository() {

        _courseEditionRepository = new ArrayList<>();

    }

    public boolean createCourseEdition(Course course, ProgrammeEdition programmeEdition) throws Exception {

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

    private boolean isCourseEditionAlreadyInRepository(CourseEdition courseEdition) {

        return _courseEditionRepository.contains(courseEdition);
    }

    public boolean contains(CourseEdition courseEdition) {
        return _courseEditionRepository.contains(courseEdition);
    }

    public boolean setRucInACourseEdition(CourseEdition ce1, Teacher t1) {
        if (ce1.setRuc(t1))
            return true;
        return false;
    }

    public List<CourseEdition> getCourseEditions() {
        return _courseEditionRepository;
    }
}





