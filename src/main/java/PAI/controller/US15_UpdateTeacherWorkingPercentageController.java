package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.repository.ITeacherCareerProgressionRepository;
import PAI.repository.ITeacherRepository;

public class US15_UpdateTeacherWorkingPercentageController {

    private ITeacherRepository _teacherRepository;
    private ITeacherCareerProgressionRepository _teacherCareerProgressionRepository;


    // Constructor
    public US15_UpdateTeacherWorkingPercentageController (ITeacherRepository teacherRepository, ITeacherCareerProgressionRepository teacherCareerProgressionRepository) {

        if (teacherRepository == null || teacherCareerProgressionRepository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }

        _teacherRepository = teacherRepository;
        _teacherCareerProgressionRepository = teacherCareerProgressionRepository;
    }

    public Iterable<Teacher> findAll() {

        return _teacherRepository.findAll();
    }

    public boolean updateWorkingPercentageInTeacherCareerProgression (String date, int workingPercentage, String teacherAcronym) throws Exception {

        Date dateVO = new Date(date);

        WorkingPercentage workingPercentageVO = new WorkingPercentage(workingPercentage);

        TeacherAcronym acronymVO = new TeacherAcronym(teacherAcronym);

        TeacherID teacherID = new TeacherID(acronymVO);

        if(!_teacherRepository.containsOfIdentity(teacherID))
            return false;

        return _teacherCareerProgressionRepository.updateWorkingPercentageInTeacherCareerProgression(dateVO, workingPercentageVO, teacherID);
    }
}