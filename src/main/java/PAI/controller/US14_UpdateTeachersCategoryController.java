//package PAI.controller;
//
//import PAI.VOs.*;
//import PAI.domain.Teacher;
//import PAI.domain.TeacherCategory;
//import PAI.repository.ITeacherCareerProgressionRepository;
//import PAI.repository.ITeacherCategoryRepository;
//import PAI.repository.ITeacherRepository;
//
//import java.util.Optional;
//
//public class US14_UpdateTeachersCategoryController {
//
//    private ITeacherRepository _teacherRepository;
//    private ITeacherCategoryRepository _teacherCategoryRepository;
//    private ITeacherCareerProgressionRepository _teacherCareerProgressionRepository;
//
//    public US14_UpdateTeachersCategoryController(ITeacherRepository teacherRepository, ITeacherCategoryRepository teacherCategoryRepository, ITeacherCareerProgressionRepository teacherCareerProgressionRepository) {
//
//        if (teacherRepository == null) {
//            throw new IllegalArgumentException("Teacher Repository cannot be null");
//        }
//
//        _teacherRepository = teacherRepository;
//
//        if (teacherCategoryRepository == null) {
//            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
//        }
//
//        _teacherCategoryRepository = teacherCategoryRepository;
//
//        if (teacherCareerProgressionRepository == null) {
//            throw new IllegalArgumentException("Teacher Career Progression Repository cannot be null");
//        }
//
//        _teacherCareerProgressionRepository = teacherCareerProgressionRepository;
//    }
//
//    public Iterable<Teacher> findAllTeachers() throws Exception {
//
//        return _teacherRepository.findAll();
//    }
//
//    public Iterable<TeacherCategory> findAllTeacherCategories() throws Exception {
//
//        return _teacherCategoryRepository.findAll();
//    }
//
//
//    public boolean updateTeacherCategoryInTeacherCareerProgression (String date, String teacherCategory, String teacherAcronym) throws Exception {
//
//        Date dateVO = new Date(date);
//
//        Name teacherCategoryName = new Name (teacherCategory);
//
//        Optional<TeacherCategoryID> optionalTeacherCategoryID = _teacherCategoryRepository.getTeacherCategoryIDFromName(teacherCategoryName);
//
//        if (optionalTeacherCategoryID.isEmpty())
//            return false;
//
//        TeacherCategoryID teacherCategoryID = optionalTeacherCategoryID.get();
//
//        TeacherAcronym acronymVO = new TeacherAcronym(teacherAcronym);
//
//        TeacherID teacherID = new TeacherID(acronymVO);
//
//        if(!_teacherRepository.containsOfIdentity(teacherID))
//            return false;
//
//        return _teacherCareerProgressionRepository.updateTeacherCategoryInTeacherCareerProgression(dateVO, teacherCategoryID, teacherID);
//    }
//}
