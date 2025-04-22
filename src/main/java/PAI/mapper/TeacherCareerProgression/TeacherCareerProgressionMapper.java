package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.mapper.ITeacherCareerProgressionIDMapper;
import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;

import java.time.LocalDate;
import java.util.UUID;

public class TeacherCareerProgressionMapper implements ITeacherCareerProgressionMapper {

    private final ITeacherCareerProgressionFactory tcpFactory;
    private final ITeacherCareerProgressionIDMapper tcpIDMapper;

    public TeacherCareerProgressionMapper(ITeacherCareerProgressionFactory tcpFactory, ITeacherCareerProgressionIDMapper tcpIDMapper) {
        this.tcpFactory = tcpFactory;
        this.tcpIDMapper = tcpIDMapper;
    }

    @Override
    public TeacherCareerProgression toDomain(TeacherCareerProgressionDataModel tcpDataModel) {
        Date date = new Date(tcpDataModel.getDate());
        TeacherCategoryID teacherCategory = new TeacherCategoryID(tcpDataModel.getTeacherCategoryId());
        WorkingPercentage workingPercentage = new WorkingPercentage(tcpDataModel.getWorkingPercentage());
        TeacherID teacherID = new TeacherID(new TeacherAcronym(tcpDataModel.getTeacherId()));

        return tcpFactory.createTeacherCareerProgression(date, teacherCategory, workingPercentage, teacherID);
    }

    @Override
    public TeacherCareerProgressionDataModel toDataModel(TeacherCareerProgression teacherCareerProgression) {

        TeacherCareerProgressionIDDataModel tcpID = tcpIDMapper.domainToDataModel(teacherCareerProgression.identity());

        LocalDate date = teacherCareerProgression.getDate().getLocalDate();
        UUID teacherCategory = teacherCareerProgression.getTeacherCategoryID().getValue();
        int workingPercentage = teacherCareerProgression.getWorkingPercentage().getValue();
        String teacherID = teacherCareerProgression.getTeacherID().getTeacherAcronym().getAcronym();


        return new TeacherCareerProgressionDataModel (tcpID, teacherCategory, workingPercentage, date, teacherID);
    }
}
