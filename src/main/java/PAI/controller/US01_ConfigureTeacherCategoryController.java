package PAI.controller;

import PAI.service.ITeacherCategoryApplicationService;

public class US01_ConfigureTeacherCategoryController {

    private final ITeacherCategoryApplicationService service;

    public US01_ConfigureTeacherCategoryController(ITeacherCategoryApplicationService service) {
        if (service == null) throw new IllegalArgumentException("Service cannot be null.");
        this.service = service;
    }

    public boolean configureTeacherCategory(String categoryName) throws Exception {
        return service.registerCategory(categoryName);
    }
}
