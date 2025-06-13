package PAI.initializer;

import PAI.VOs.*;
import PAI.domain.studentGrade.StudentGrade;
import PAI.domain.repositoryInterfaces.studentGrade.IStudentGradeRepository;
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
public class StudentGradeInitializer {

    @Bean
    public CommandLineRunner loadStudentGrades(IStudentGradeRepository repository) {
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

                    System.out.println("📄 Linha do CSV: " + line);

                    String[] fields = line.split(",");

                    if (fields.length == 9) {
                        try {
                            // Lê os campos do CSV
                            int gradeValue = Integer.parseInt(fields[0].trim());
                            LocalDate gradeDate = LocalDate.parse(fields[1].trim());
                            int studentNumber = Integer.parseInt(fields[2].trim());
                            Acronym programmeAcronym = new Acronym(fields[3].trim());
                            UUID schoolYearUUID = UUID.fromString(fields[4].trim());
                            int studyPlanStartYear = Integer.parseInt(fields[5].trim());
                            Acronym courseAcronym = new Acronym(fields[6].trim());
                            Name courseName = new Name(fields[7].trim());
                            UUID studentGradeGeneratedUUID = UUID.fromString(fields[8].trim());

                            // Cria os Value Objects
                            Grade grade = new Grade(gradeValue);
                            Date date = new Date(gradeDate);
                            StudentID studentID = new StudentID(studentNumber);
                            ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
                            SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);
                            Date studyPlanDate = new Date(LocalDate.of(studyPlanStartYear, 1, 1));
                            StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
                            CourseID courseID = new CourseID(courseAcronym, courseName);
                            CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
                            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
                            CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

                            StudentGradeID studentGradeID = new StudentGradeID(studentID, courseEditionID);
                            StudentGradeGeneratedID studentGradeGeneratedID = new StudentGradeGeneratedID(studentGradeGeneratedUUID);

                            // Cria e guarda a entidade
                            StudentGrade studentGrade = new StudentGrade(
                                    grade,
                                    date,
                                    studentID,
                                    courseEditionID,
                                    studentGradeID,
                                    studentGradeGeneratedID
                            );

                            repository.save(studentGrade);

                        } catch (Exception e) {
                            System.err.println("Erro ao processar linha: " + line);
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Linha inválida (esperado 9 campos): " + line);
                    }
                }

            } catch (Exception e) {
                System.err.println("Erro ao carregar StudentGrades.csv:");
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\n✅ StudentGrades carregados em " + (endTime - startTime) + " ms\n");
        };
    }
}
