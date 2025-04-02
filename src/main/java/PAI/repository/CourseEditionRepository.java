package PAI.repository;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.*;
import PAI.factory.ICourseEditionFactory;
import PAI.factory.ICourseEditionListFactory;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionRepository {

    private List<CourseEditionDDD> _courseEditions;
    private final ICourseEditionFactory _I_courseEditionFactory;

    public CourseEditionRepository(ICourseEditionFactory ICourseEditionFactory, ICourseEditionListFactory ICourseEditionListFactory) {
        _I_courseEditionFactory = ICourseEditionFactory;
        _courseEditions = ICourseEditionListFactory.newArrayList();

    }

    public boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {

        try {
            CourseEditionDDD courseEdition = _I_courseEditionFactory.newCourseEdition(courseInStudyPlanID, programmeEditionID);
            if (isCourseEditionAlreadyInRepository(courseEdition))
                return false;

            _courseEditions.add(courseEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isCourseEditionAlreadyInRepository(CourseEditionDDD courseEdition) {
        return _courseEditions.contains(courseEdition);
    }

    // US20 - returns a list of all course editions stored in the repository
    public List<CourseEditionDDD> getCourseEditions() {
        return new ArrayList<>(_courseEditions); // Retorna uma cópia da lista
    }

//    // US20 - sets the RUC for a specific course edition
//    public boolean setRucInACourseEdition(CourseEdition ce1, Teacher t1) {
//        if (!_courseEditions.contains(ce1)) {
//            throw new IllegalArgumentException("Course edition not found in repository.");
//        }
//        return ce1.setRuc(t1);
//    }

    public ProgrammeEditionID findWhichProgrammeEditionBelongsToACourseEdition(CourseEditionDDD courseEdition) throws Exception {
        for (CourseEditionDDD courseEdition1 : _courseEditions)
            if (courseEdition1.equals(courseEdition)) {
                return courseEdition1.getProgrammeEditionID();
            }

        throw new Exception("The course edition does not belong to the course Edition Repository.");
    }

    public List<CourseEditionDDD> findCourseEditionsByProgrammeEdition(ProgrammeEdition programmeEdition) {
        List<CourseEditionDDD> result = new ArrayList<>();
        for (CourseEditionDDD courseEdition : _courseEditions) {
            if (courseEdition.getProgrammeEditionID().equals(programmeEdition)) {
                result.add(courseEdition);
            }
        }

        return result;
    }
}





