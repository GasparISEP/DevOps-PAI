package PAI.controller;

import PAI.VOs.Name;
import PAI.service.teacherCategory.ITeacherCategoryService;
import org.springframework.stereotype.Component;

@Component
public class US01_ConfigureTeacherCategoryController {

    private final ITeacherCategoryService service;

    public US01_ConfigureTeacherCategoryController(ITeacherCategoryService service) {
        if (service == null) throw new IllegalArgumentException("Service cannot be null.");
        this.service = service;
    }

    public boolean configureTeacherCategory(Name categoryName) throws Exception {
        service.configureTeacherCategory(categoryName);
        return true;
    }
}
