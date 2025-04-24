package PAI.persistence.springdata;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.mapper.DegreeType.DegreeTypeMapper;
import PAI.persistence.datamodel.DegreeTypeDM;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DegreeTypeRepoSpringData implements IDegreeTypeRepository {

    private final IDegreeTypeRepoSpringData dtRepoJPA;
    private final DegreeTypeMapper mapper;

    public DegreeTypeRepoSpringData(IDegreeTypeRepoSpringData dtRepoJPA, DegreeTypeMapper mapper) {
        this.dtRepoJPA = dtRepoJPA;
        this.mapper = mapper;
    }

    @Override
    public Optional<DegreeType> ofIdentity(DegreeTypeID id) {
        return dtRepoJPA.findById(id.getDTID())
                .map(mapper::toDomainModel);
    }

    @Override
    public boolean containsOfIdentity(DegreeTypeID id) {
        return dtRepoJPA.existsById(id.getDTID());
    }

    @Override
    public DegreeType save(DegreeType degreeType) {
        DegreeTypeDM dm = mapper.toDataModel(degreeType);
        DegreeTypeDM saved = dtRepoJPA.save(dm);
        return mapper.toDomainModel(saved);
    }

    @Override
    public List<DegreeType> findAll() {
        return dtRepoJPA.findAll().stream()
                .map(mapper::toDomainModel)
                .toList();
    }

    @Override
    public boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        if (dtRepoJPA.existsById(degreeTypeID.getDTID()))
            return false;

        DegreeTypeDM dm = new DegreeTypeDM(degreeTypeID.getDTID(), name.getName(), maxEcts.getMaxEcts());
        dtRepoJPA.save(dm);
        return true;
    }

    @Override
    public List<DegreeType> getAllDegreeTypes() {
        return findAll();
    }
}