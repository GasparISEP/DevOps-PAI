package PAI.domain;

import java.util.ArrayList;

public class TeacherCategoryRepository {

    private ArrayList<TeacherCategory> _teacherCategoryRepository;

    //CONSTRUTOR
    public TeacherCategoryRepository () {

        _teacherCategoryRepository = new ArrayList<>();
    }
    //VALIDAÇÕES

    public boolean registerTeacherCategory (String name) throws Exception {

        TeacherCategory teacherCategory = new TeacherCategory (name);

        if (isTeacherCategoryRegistered(teacherCategory))
            return false;

        _teacherCategoryRepository.add(teacherCategory);
        return true;
    }
    public boolean isTeacherCategoryRegistered(TeacherCategory teacherCategory) {

        return _teacherCategoryRepository.contains(teacherCategory);
    }

}
