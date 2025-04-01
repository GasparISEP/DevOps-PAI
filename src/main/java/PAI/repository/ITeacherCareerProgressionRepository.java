package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCareerProgression;

import java.util.Optional;

public interface ITeacherCareerProgressionRepository extends IRepository <TeacherCareerProgressionID, TeacherCareerProgression> {

    boolean createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;

    Optional<TeacherCareerProgression> findLastTCPFromTeacherID(TeacherID teacherID);

    boolean updateWorkingPercentageInTeacherCareerProgression(Date date, WorkingPercentage workingPercentage, TeacherID teacherID) throws Exception;

    boolean updateTeacherCategoryInTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, TeacherID teacherID) throws Exception;

}