package PAI.factory;

import PAI.domain.TeacherCategory;

// Define the interface
    public interface TeacherCategoryFactoryInterface {
        TeacherCategory createTeacherCategory(String categoryName) throws Exception;
    }

