package PAI.persistence.mem.teacher;

import PAI.domain.teacher.Teacher;

import java.util.List;

public interface ITeacherListFactory {
    List<Teacher> newList();
}
