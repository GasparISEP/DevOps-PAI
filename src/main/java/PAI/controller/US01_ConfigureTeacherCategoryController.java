package PAI.controller;

import PAI.service.ITeacherCategoryService;

public class US01_ConfigureTeacherCategoryController {

    private final ITeacherCategoryService service;

    public US01_ConfigureTeacherCategoryController(ITeacherCategoryService service) {
        if (service == null) throw new IllegalArgumentException("Service cannot be null.");
        this.service = service;
    }

    public boolean configureTeacherCategory(String categoryName) throws Exception {
        return service.registerCategory(categoryName);
    }
}
