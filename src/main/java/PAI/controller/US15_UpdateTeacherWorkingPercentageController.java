package PAI.controller;

import PAI.domain.Teacher;
import PAI.domain.TeacherRepository;

import java.util.Optional;

public class US15_UpdateTeacherWorkingPercentageController {

    private TeacherRepository _teacherRepository;

    //constructor
    public US15_UpdateTeacherWorkingPercentageController (TeacherRepository teacherRepository) {

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }

        _teacherRepository = teacherRepository;
    }

    public Optional<Teacher> getTeacherByNIF (String NIF) {

        Optional<Teacher> optT1 = _teacherRepository.getTeacherByNIF(NIF);

        return optT1;
    }

    public boolean updateTeacherWorkingPercentageInTeacherCareerProgression (Teacher teacher, String date, int workingPercentage) {

        return teacher.updateWorkingPercentageInTeacherCareerProgression(date, workingPercentage);
    }
}