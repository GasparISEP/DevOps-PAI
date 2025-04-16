package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
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

}
