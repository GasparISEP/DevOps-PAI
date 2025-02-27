package PAI.domain;

public class TeacherRepositoryFactory {

    public TeacherRepository newTeacherRepository(TeacherFactory teacherFactory) {
        return new TeacherRepository(teacherFactory);
    }
}
