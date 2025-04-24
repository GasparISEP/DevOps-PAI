package PAI.persistence.springdata.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.mapper.DegreeType.DegreeTypeMapper;
import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
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
        return dtRepoJPA.findById(new DegreeTypeIDDataModel(id.getDTID()))
                .map(mapper::toDomainModel);
    }

    @Override
    public boolean containsOfIdentity(DegreeTypeID id) {
        return dtRepoJPA.existsById(new DegreeTypeIDDataModel(id.getDTID()));
    }

    @Override
    public DegreeType save(DegreeType degreeType) {
        DegreeTypeDataModel dm = mapper.toDataModel(degreeType);
        DegreeTypeDataModel saved = dtRepoJPA.save(dm);
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
        DegreeTypeIDDataModel idDataModel = new DegreeTypeIDDataModel(degreeTypeID.getDTID());

        if (dtRepoJPA.existsById(idDataModel))
            return false;

        DegreeTypeDataModel dm = new DegreeTypeDataModel(idDataModel, name.getName(), maxEcts.getMaxEcts());
        dtRepoJPA.save(dm);
        return true;
    }

    @Override
    public List<DegreeType> getAllDegreeTypes() {
        return findAll();
    }
}