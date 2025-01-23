package PAI.controller;

import PAI.domain.TeacherCategory;
import java.util.ArrayList;
import java.util.List;

public class US01_ConfigureTeacherCategoryController {

    private List<TeacherCategory> categories;

    public US01_ConfigureTeacherCategoryController() {
        this.categories = new ArrayList<>();
    }

    public void addCategory(String categoryName) throws Exception {
        TeacherCategory newCategory = new TeacherCategory(categoryName);
        if (!categories.contains(newCategory)) {
            categories.add(newCategory);
        } else {
            throw new Exception("Category already exists.");
        }
    }

    public TeacherCategory getCategory(String categoryName) throws Exception {
        try {
            TeacherCategory tempCategory = new TeacherCategory(categoryName);
            for (TeacherCategory category : categories) {
                if (category.equals(tempCategory)) {
                    return category;
                }
            }
        } catch (Exception e) {
            throw new Exception("Invalid category name: " + categoryName, e);
        }
        return null;
    }

    public List<TeacherCategory> listCategories() {
        return new ArrayList<>(categories);
    }
}