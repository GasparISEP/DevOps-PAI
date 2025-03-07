package PAI.controller;

import PAI.domain.Teacher;
import PAI.repository.TeacherRepository;

import java.util.Optional;

public class US15_UpdateTeacherWorkingPercentageController {

    private TeacherRepository _teacherRepository;

    // Constructor
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

        teacher.updateWorkingPercentageInTeacherCareerProgression(date, workingPercentage);

        return true;
    }
}