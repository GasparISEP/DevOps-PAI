package PAI.domain.repositoryInterfaces.teacherCareerProgression;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

import java.util.Optional;

public interface ITeacherCareerProgressionRepository extends IRepository <TeacherCareerProgressionID, TeacherCareerProgression> {

    Optional<TeacherCareerProgression> findLastTCPFromTeacherID(TeacherID teacherID);

}