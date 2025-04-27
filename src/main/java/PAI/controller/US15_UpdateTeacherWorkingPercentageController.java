package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.service.ITeacherCareerProgressionService;
import PAI.service.ITeacherService;

public class US15_UpdateTeacherWorkingPercentageController {

    private ITeacherService _teacherService;
    private ITeacherCareerProgressionService _teacherCareerProgressionService;


    // Constructor
    public US15_UpdateTeacherWorkingPercentageController (ITeacherService teacherService, ITeacherCareerProgressionService teacherCareerProgressionService) {

        if (teacherService == null || teacherCareerProgressionService == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        _teacherService = teacherService;
        _teacherCareerProgressionService = teacherCareerProgressionService;
    }

    public Iterable<Teacher> findAll() throws Exception {

        return _teacherService.getAllTeachers();
    }

    public boolean updateWorkingPercentageInTeacherCareerProgression (String date, int workingPercentage, String teacherAcronym) throws Exception {

        Date dateVO = new Date(date);

        WorkingPercentage workingPercentageVO = new WorkingPercentage(workingPercentage);

        TeacherAcronym acronymVO = new TeacherAcronym(teacherAcronym);

        TeacherID teacherID = new TeacherID(acronymVO);

        if(!_teacherService.existsById(teacherID))
            return false;

        return _teacherCareerProgressionService.updateWorkingPercentageInTeacherCareerProgression(dateVO, workingPercentageVO, teacherID);
    }
}

