package PAI.service;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;

public interface ITeacherCareerProgressionService {

    boolean createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;

    boolean updateTeacherCategoryInTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, TeacherID teacherID) throws Exception;

    boolean updateWorkingPercentageInTeacherCareerProgression(Date date, WorkingPercentage workingPercentage, TeacherID teacherID) throws Exception;
}
