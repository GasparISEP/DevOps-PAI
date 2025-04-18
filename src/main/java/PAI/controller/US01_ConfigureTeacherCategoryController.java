package PAI.controller;

import PAI.service.TeacherCategoryApplicationService;

public class US01_ConfigureTeacherCategoryController {

    private final TeacherCategoryApplicationService service;

    public US01_ConfigureTeacherCategoryController(TeacherCategoryApplicationService service) {
        if (service == null) throw new IllegalArgumentException("Service cannot be null.");
        this.service = service;
    }

    public boolean configureTeacherCategory(String categoryName) throws Exception {
        return service.registerCategory(categoryName);
    }
}