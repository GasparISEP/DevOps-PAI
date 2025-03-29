package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.TeacherCareerProgression;

public class TeacherCareerProgressionFactoryImpl implements ITeacherCareerProgressionFactory {

    public TeacherCareerProgression createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID) throws IllegalArgumentException {

        return new TeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherID);
    }
}