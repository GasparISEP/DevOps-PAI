package PAI.initializer;

import PAI.VOs.*;
import PAI.domain.repositoryInterfaces.studentGrade.IStudentGradeRepository;
import PAI.domain.studentGrade.StudentGrade;
import PAI.domain.studentGrade.StudentGradeFactoryImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.UUID;

@Component
@Order(16)
@Profile("studentGrade-direct")
public class StudentGradeInitializer {

    @Autowired
    private IStudentGradeRepository studentGradeRepository;

    private final StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl();

    @PostConstruct
    public void init() {
        System.out.println("🔥 INIT: StudentGradeDirectInitializer foi chamado!");

        try (InputStream is = getClass().getResourceAsStream("/StudentGrade.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                System.out.println("❌ Ficheiro StudentGrade.csv não encontrado!");
                return;
            }

            String line = reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);

                try {
                    // ORDEM DO CSV:
                    // 0: DATE (data da nota)
                    // 1: GRADE
                    // 2: LOCAL_DATE (data da course edition / study plan)
                    // 3: STUDENT_ID
                    // 4: STUDENT_GRADE_GENERATED_ID (ignorado)
                    // 5: COURSEID_ACRONYM
                    // 6: COURSENAME
                    // 7: EDITION_PROGRAMME_ACRONYM
                    // 8: EDITION_SCHOOL_YEAR
                    // 9: PROGRAMME_ACRONYM

                    Grade grade = new Grade(Double.parseDouble(values[1].trim()));
                    Date date = new Date(LocalDate.parse(values[0].trim())); // data da nota
                    Date studyPlanDate = new Date(LocalDate.parse(values[2].trim())); // LOCAL_DATE da course edition

                    StudentID studentID = new StudentID(Integer.parseInt(values[3].trim()));
                    Acronym programmeAcronym = new Acronym(values[9].trim());
                    ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                    SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(values[8].trim()));
                    ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

                    Acronym courseAcronym = new Acronym(values[5].trim());
                    Name courseName = new Name(values[6].trim());
                    CourseID courseID = new CourseID(courseAcronym, courseName);

                    StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
                    CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
                    CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

                    StudentGrade sg = factory.createGradeStudent(grade, date, studentID, courseEditionID);
                    studentGradeRepository.save(sg);
                    System.out.println("✅ Nota inserida diretamente para estudante: " + studentID);

                } catch (Exception e) {
                    System.out.println("❌ Erro ao processar linha: " + line);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Erro geral no initializer:");
            e.printStackTrace();
        }
    }
}
