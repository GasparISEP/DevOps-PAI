package PAI.factory;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeacherCareerProgressionFactoryImpl implements ITeacherCareerProgressionFactory {

    public TeacherCareerProgression createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID) {

        TeacherCareerProgressionID teacherCareerProgressionId = new TeacherCareerProgressionID();

        return new TeacherCareerProgression(teacherCareerProgressionId, date, teacherCategoryID, workingPercentage, teacherID);
    }

    public TeacherCareerProgression createTeacherCareerProgressionFromDataModel(TeacherCareerProgressionID teacherCareerProgressionId, Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID) {

        return new TeacherCareerProgression(teacherCareerProgressionId, date, teacherCategoryID, workingPercentage, teacherID);
    }
}