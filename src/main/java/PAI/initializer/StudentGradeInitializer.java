package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US22_IWantToGradeAStudentInACourseEditionController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Configuration
@Profile("student-grades")
public class StudentGradeInitializer {

    @Bean
    public CommandLineRunner loadStudentGrades(US22_IWantToGradeAStudentInACourseEditionController controller) {
        return args -> {
            long startTime = System.currentTimeMillis();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(getClass().getResourceAsStream("/StudentGrades.csv"))
                    )
            )) {
                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    line = line.replace("\uFEFF", "").trim();
                    if (line.isEmpty()) continue;

                    if (line.endsWith(";")) {
                        line = line.substring(0, line.length() - 1);
                    }

                    String[] fields = line.split(",");

                    if (fields.length == 8) {
                        try {
                            int studentNum = Integer.parseInt(fields[0].trim());
                            int gradeValue = Integer.parseInt(fields[1].trim());
                            Acronym programmeAcronym = new Acronym(fields[2].trim());
                            UUID schoolYearUUID = UUID.fromString(fields[3].trim());
                            int studyPlanStartYear = Integer.parseInt(fields[4].trim());
                            Acronym courseAcronym = new Acronym(fields[5].trim());
                            Name courseName = new Name(fields[6].trim());
                            LocalDate gradeDate = LocalDate.parse(fields[7].trim());

                            // Construção dos objetos do domínio
                            StudentID studentID = new StudentID(studentNum);
                            ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                            SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);
                            Date studyPlanYear = new Date(LocalDate.of(studyPlanStartYear, 1, 1));
                            StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanYear);
                            CourseID courseID = new CourseID(courseAcronym, courseName);
                            CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
                            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                            CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
                            Grade grade = new Grade(gradeValue);
                            Date date = new Date(gradeDate);

                            // Registar a nota
                            controller.registerStudentGrade(grade, date, studentID, courseEditionID);

                        } catch (Exception e) {
                            System.err.println("Erro ao processar linha: " + line);
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Formato inválido da linha (esperado 8 campos): " + line);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nStudent grades loaded in " + (endTime - startTime) + " ms\n");
        };
    }
}

