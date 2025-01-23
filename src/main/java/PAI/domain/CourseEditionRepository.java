package PAI.domain;

import java.util.ArrayList;

public class CourseEditionRepository {

    private ArrayList<CourseEdition> _courseEditionRepository;

    public CourseEditionRepository() {

        _courseEditionRepository = new ArrayList<>();

    }


    public boolean createCourseEdition (Course course, ProgrammeEdition programmeEdition) throws Exception {

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
}
