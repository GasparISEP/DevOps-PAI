package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.ITeacherService;

import java.util.Objects;

/**
 * Controller de domínio para orquestrar a atribuição de um RUC (coordenador)
 * a uma edição de curso, usando serviços de CourseEdition e Teacher.
 */
public class US20_DefineRucForCourseEditionController {

    private final ICourseEditionService courseService;
    private final ITeacherService       teacherService;

    /**
     * @param courseService  serviço para operações em CourseEdition
     * @param teacherService serviço para operações em Teacher
     */
    public US20_DefineRucForCourseEditionController(
            ICourseEditionService courseService,
            ITeacherService       teacherService
    ) {
        this.courseService  = Objects.requireNonNull(courseService,  "courseService não pode ser nulo");
        this.teacherService = Objects.requireNonNull(teacherService, "teacherService não pode ser nulo");
    }

    /**
     * Retorna todos os professores disponíveis.
     *
     * @return lista de Teacher
     */
    public Iterable<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    /**
     * Retorna todas as edições de curso existentes.
     *
     * @return lista de CourseEdition
     */
    public Iterable<CourseEdition> getAllCourseEditions() {
        return courseService.findAll();
    }

    /**
     * Define um RUC (coordenador) para a edição de curso identificada.
     *
     * @param courseEditionID ID da edição de curso
     * @param teacherID       ID do professor a ser atribuído como RUC
     * @return true se a atribuição for bem-sucedida; false caso o registro não exista
     * @throws IllegalArgumentException se os IDs forem nulos ou não encontrados
     */
    public boolean defineRucForCourseEdition(
            CourseEditionID courseEditionID,
            TeacherID       teacherID
    ) {
        Objects.requireNonNull(courseEditionID, "courseEditionID não pode ser nulo");
        Objects.requireNonNull(teacherID,       "teacherID não pode ser nulo");

        // 1. Valida pré-existência
        if (!courseService.containsOfIdentity(courseEditionID)) {
            throw new IllegalArgumentException(
                    "Edição de curso não encontrada: " + courseEditionID);
        }
        if (!teacherService.existsById(teacherID)) {
            throw new IllegalArgumentException(
                    "Professor não encontrado: " + teacherID);
        }

        // 2. Delegação ao serviço de domínio
        return courseService.assignRucToCourseEdition(teacherID, courseEditionID);
    }
}
