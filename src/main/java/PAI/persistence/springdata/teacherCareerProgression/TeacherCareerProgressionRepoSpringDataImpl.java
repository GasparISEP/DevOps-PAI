package PAI.persistence.springdata.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.mapper.TeacherCareerProgression.ITeacherCareerProgressionIDMapper;
import PAI.mapper.TeacherCareerProgression.ITeacherCareerProgressionMapper;
import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import PAI.repository.ITeacherCareerProgressionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherCareerProgressionRepoSpringDataImpl implements ITeacherCareerProgressionRepository {
    private final ITeacherCareerProgressionRepoSpringData iTCPRepo;
    private final ITeacherCareerProgressionMapper iTCPMapper;
    private final ITeacherCareerProgressionIDMapper iTCPIdMapper;

    public TeacherCareerProgressionRepoSpringDataImpl(ITeacherCareerProgressionRepoSpringData iTCPRepo, ITeacherCareerProgressionMapper iTCPMapper, ITeacherCareerProgressionIDMapper iTCPIdMapper) {
        if(iTCPRepo == null) {
            throw new IllegalArgumentException("Repository must not be null");
        }
        if(iTCPMapper == null) {
            throw new IllegalArgumentException("Mapper must not be null");
        }
        if(iTCPIdMapper == null) {
            throw new IllegalArgumentException("ID's Mapper must not be null");
        }

        this.iTCPRepo = iTCPRepo;
        this.iTCPMapper = iTCPMapper;
        this.iTCPIdMapper = iTCPIdMapper;
    }

    @Override
    public Optional<TeacherCareerProgression> findLastTCPFromTeacherID(TeacherID teacherID) {
        String teacherAcronym = teacherID.getTeacherAcronym().getAcronym();
        Optional<TeacherCareerProgressionDataModel> tcpDataModelOpt = iTCPRepo.findTopByTeacherIdOrderByDateDesc(teacherAcronym);

        if (tcpDataModelOpt.isEmpty()) {
            return Optional.empty();
        }
        TeacherCareerProgression tcp = iTCPMapper.toDomain(tcpDataModelOpt.get());
        return Optional.of(tcp);
    }

    @Override
    public TeacherCareerProgression save(TeacherCareerProgression tcp) {

        if (tcp == null) {
           throw new IllegalArgumentException("TCP cannot be null.");
        }
        TeacherCareerProgressionDataModel tcpDataModel = iTCPMapper.toDataModel(tcp);

        iTCPRepo.save(tcpDataModel);

        return iTCPMapper.toDomain(tcpDataModel);
    }



    @Override
    public Iterable<TeacherCareerProgression> findAll() {
        List<TeacherCareerProgression> allTCPs = new ArrayList<>();
        iTCPRepo.findAll().forEach(tcpDataModel -> {
            TeacherCareerProgression tcp = iTCPMapper.toDomain(tcpDataModel);
            if (tcp != null) {
                allTCPs.add(tcp);
            }
        });
        return allTCPs;
    }

    @Override
    public Optional<TeacherCareerProgression> ofIdentity(TeacherCareerProgressionID id) {
        TeacherCareerProgressionIDDataModel idDataModel = iTCPIdMapper.domainToDataModel(id);

        Optional<TeacherCareerProgressionDataModel> result = iTCPRepo.findById(idDataModel);

        if (result.isPresent()) {
            TeacherCareerProgressionDataModel dataModel = result.get();
            TeacherCareerProgression tcp = iTCPMapper.toDomain(dataModel);
            return Optional.of(tcp);
        }

        return Optional.empty();
    }


    @Override
    public boolean containsOfIdentity(TeacherCareerProgressionID id) {
        TeacherCareerProgressionIDDataModel idDataModel =
                iTCPIdMapper.domainToDataModel(id);
        return iTCPRepo.existsById(idDataModel);
    }

}
