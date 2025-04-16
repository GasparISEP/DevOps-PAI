package PAI.persistence.datamodel;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Teacher_Career_Progression")
public class TeacherCareerProgressionDataModel {

    @EmbeddedId
    private TeacherCareerProgressionIDDataModel id;

    @Column(nullable = false)
    private UUID teacherCategoryId;
    @Column(nullable = false)
    private int workingPercentage;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String teacherId;

    public TeacherCareerProgressionDataModel() {}

    public TeacherCareerProgressionDataModel(TeacherCareerProgressionIDDataModel id, UUID teacherCategoryId,
                                             int workingPercentage, LocalDate date, String teacherId){

        this.id = id;
        this.teacherCategoryId = teacherCategoryId;
        this.workingPercentage = workingPercentage;
        this.date = date;
        this.teacherId = teacherId;
    }

    public TeacherCareerProgressionIDDataModel getId() {
        return id;
    }

    public UUID getTeacherCategoryId() {
        return teacherCategoryId;
    }

    public int getWorkingPercentage() {
        return workingPercentage;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTeacherId() {
        return teacherId;
    }
}
