package PAI.service.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionService {

    public CourseEdition createAndSaveCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

    public Iterable<CourseEdition> findAll();

    public List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);

    public Optional<CourseEdition> ofIdentity(CourseEditionID courseEditionID);

    public boolean containsOfIdentity(CourseEditionID courseEditionID);

    boolean assignRucToCourseEdition(TeacherID teacherID, CourseEditionID courseEditionID);
}
