package PAI.factory;

import PAI.domain.TeacherCategory;

// Define the interface
    public interface TeacherCategoryFactory {
        TeacherCategory createTeacherCategory(String categoryName) throws Exception;
    }

