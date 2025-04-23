package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.factory.ITeacherCareerProgressionFactory;
import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionMapperTest {

    private final ITeacherCareerProgressionIDMapper idMapper = mock(ITeacherCareerProgressionIDMapper.class);

    @Test
    void shouldReturnNonNullDomainObject() {
        // arrange
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 50;
        LocalDate date = LocalDate.of(2022, 4, 17);
        String teacherId = "ABC";

        TeacherCareerProgressionDataModel dataModel = new TeacherCareerProgressionDataModel(
                new TeacherCareerProgressionIDDataModel(UUID.randomUUID()), teacherCategoryId, workingPercentage, date, teacherId
        );

        ITeacherCareerProgressionFactory tcpFactory = mock(ITeacherCareerProgressionFactory.class);
        TeacherCareerProgressionMapper mapper = new TeacherCareerProgressionMapper(tcpFactory,idMapper);

        TeacherCareerProgression tcp = new TeacherCareerProgression(
                new Date(date),
                new TeacherCategoryID(teacherCategoryId),
                new WorkingPercentage(workingPercentage),
                new TeacherID(new TeacherAcronym(teacherId))
        );

        when(tcpFactory.createTeacherCareerProgression(
                new Date(date),
                new TeacherCategoryID(teacherCategoryId),
                new WorkingPercentage(workingPercentage),
                new TeacherID(new TeacherAcronym(teacherId))
        )).thenReturn(tcp);

        // act
        TeacherCareerProgression domain = mapper.toDomain(dataModel);

        // assert
        assertNotNull(domain);
    }

    @Test
    void shouldCreateTCPDomainObject() {
        // arrange
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 50;
        LocalDate date = LocalDate.of(2022, 4, 17);
        String teacherId = "ABC";

        TeacherCareerProgressionDataModel dataModel = new TeacherCareerProgressionDataModel(
                new TeacherCareerProgressionIDDataModel(UUID.randomUUID()), teacherCategoryId, workingPercentage, date, teacherId
        );

        ITeacherCareerProgressionFactory tcpFactory = mock(ITeacherCareerProgressionFactory.class);
        TeacherCareerProgressionMapper mapper = new TeacherCareerProgressionMapper(tcpFactory,idMapper);

        TeacherCareerProgression tcp = new TeacherCareerProgression(
                new Date(date),
                new TeacherCategoryID(teacherCategoryId),
                new WorkingPercentage(workingPercentage),
                new TeacherID(new TeacherAcronym(teacherId))
        );

        when(tcpFactory.createTeacherCareerProgression(
                new Date(date),
                new TeacherCategoryID(teacherCategoryId),
                new WorkingPercentage(workingPercentage),
                new TeacherID(new TeacherAcronym(teacherId))
        )).thenReturn(tcp);

        // act
        TeacherCareerProgression domain = mapper.toDomain(dataModel);

        // assert
        assertEquals(date, domain.getDate().getLocalDate());
        assertEquals(teacherCategoryId, domain.getTeacherCategoryID().getValue());
        assertEquals(workingPercentage, domain.getWorkingPercentage().getValue());
        assertEquals(teacherId, domain.getTeacherID().getTeacherAcronym().getAcronym());
    }
    @Test
    void shouldReturnNonNullDataModelObject() {
        // arrange
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 50;
        LocalDate date = LocalDate.of(2022, 4, 17);
        String teacherId = "ABC";

        Date domainDate = new Date(date);
        TeacherCategoryID domainTeacherCategoryId = new TeacherCategoryID(teacherCategoryId);
        WorkingPercentage domainWorkingPercentage = new WorkingPercentage(workingPercentage);
        TeacherID domainTeacherID = new TeacherID(new TeacherAcronym(teacherId));

        TeacherCareerProgression domain = new TeacherCareerProgression(
                domainDate, domainTeacherCategoryId, domainWorkingPercentage, domainTeacherID
        );

        TeacherCareerProgressionMapper mapper = new TeacherCareerProgressionMapper(mock(ITeacherCareerProgressionFactory.class), mock(ITeacherCareerProgressionIDMapper.class));

        // act
        TeacherCareerProgressionDataModel dataModel = mapper.toDataModel(domain);

        // assert
        assertNotNull(dataModel);
    }

    @Test
    void shouldCreateTCPDataModel() {
        // arrange
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 50;
        LocalDate date = LocalDate.of(2022, 4, 17);
        String teacherId = "ABC";

        Date domainDate = new Date(date);
        TeacherCategoryID domainTeacherCategoryId = new TeacherCategoryID(teacherCategoryId);
        WorkingPercentage domainWorkingPercentage = new WorkingPercentage(workingPercentage);
        TeacherID domainTeacherID = new TeacherID(new TeacherAcronym(teacherId));

        TeacherCareerProgression domain = new TeacherCareerProgression(
                domainDate, domainTeacherCategoryId, domainWorkingPercentage, domainTeacherID
        );

        TeacherCareerProgressionIDDataModel mockedTcpID = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());
        ITeacherCareerProgressionIDMapper tcpIDMapper = mock(ITeacherCareerProgressionIDMapper.class);
        when(tcpIDMapper.domainToDataModel(domain.identity())).thenReturn(mockedTcpID);

        TeacherCareerProgressionMapper mapper = new TeacherCareerProgressionMapper(
                mock(ITeacherCareerProgressionFactory.class), tcpIDMapper
        );

        // act
        TeacherCareerProgressionDataModel dataModel = mapper.toDataModel(domain);

        // assert
        assertEquals(mockedTcpID.getIDValue(), dataModel.getId().getIDValue());
        assertEquals(date, dataModel.getDate());
        assertEquals(teacherCategoryId, dataModel.getTeacherCategoryId());
        assertEquals(workingPercentage, dataModel.getWorkingPercentage());
        assertEquals(teacherId, dataModel.getTeacherId());
    }
}