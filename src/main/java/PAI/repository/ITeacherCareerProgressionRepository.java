package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCareerProgression;

import java.util.Optional;

public interface ITeacherCareerProgressionRepository extends IRepository <TeacherCareerProgressionID, TeacherCareerProgression> {

    Optional<TeacherCareerProgression> findLastTCPFromTeacherID(TeacherID teacherID);

}