package PAI.controller;

import PAI.domain.Teacher;
import PAI.repository.TeacherRepository;

import java.util.Optional;

public class US15_UpdateTeacherWorkingPercentageController {

    private TeacherRepository _teacherRepository;
    private Teacher _teacher;

    // Constructor
    public US15_UpdateTeacherWorkingPercentageController (TeacherRepository teacherRepository) {

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }

        _teacherRepository = teacherRepository;
    }

    public Optional<Teacher> getTeacherByNIF (String NIF) {

        Optional<Teacher> optT1 = _teacherRepository.getTeacherByNIF(NIF);

        if(optT1.isPresent())
            _teacher = optT1.get();

        return optT1;
    }

    public boolean updateTeacherWorkingPercentageInTeacherCareerProgression (String date, int workingPercentage) {

        if(_teacher == null)
            return false;

        _teacher.updateWorkingPercentageInTeacherCareerProgression(date, workingPercentage);

        return true;
    }
}