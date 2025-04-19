package PAI.persistence.springdata;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.mapper.ITeacherCategoryMapper;
import PAI.mapper.TeacherCategoryIDMapper;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
import PAI.repository.ITeacherCategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TeacherCategoryRepositoryJpaAdapter implements ITeacherCategoryRepository {

    private final ITeacherCategoryRepositorySpringData jpaRepository;
    private final ITeacherCategoryMapper mapper;
    private final ITeacherCategoryFactory factory;
    private final TeacherCategoryIDMapper idMapper;

    public TeacherCategoryRepositoryJpaAdapter(
            ITeacherCategoryRepositorySpringData jpaRepository,
            ITeacherCategoryMapper mapper,
            ITeacherCategoryFactory factory,
            TeacherCategoryIDMapper idMapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
        this.factory = factory;
        this.idMapper = idMapper;
    }

    @Override
    public boolean registerTeacherCategory(Name name) {
        if (jpaRepository.existsByName(name.getName())) {
            return false;
        }

        TeacherCategory category = factory.createTeacherCategory(name);
        jpaRepository.save(mapper.toDataModel(category));
        return true;
    }

    @Override
    public boolean existsByName(Name name) {
        return jpaRepository.existsByName(name.getName());
    }

    @Override
    public TeacherCategory save(TeacherCategory entity) {
        jpaRepository.save(mapper.toDataModel(entity));
        return entity;
    }

    @Override
    public Optional<TeacherCategory> ofIdentity(TeacherCategoryID id) {
        TeacherCategoryIDDataModel idDataModel = idMapper.toDataModel(id);
        return jpaRepository.findById(idDataModel)
                .map(mapper::toDomainModel);
    }

    @Override
    public boolean containsOfIdentity(TeacherCategoryID id) {
        TeacherCategoryIDDataModel idDataModel = idMapper.toDataModel(id);
        return jpaRepository.existsById(idDataModel);
    }

    @Override
    public Iterable<TeacherCategory> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherCategory> getTeacherCategoryList() {
        return (List<TeacherCategory>) findAll();
    }

    @Override
    public Optional<TeacherCategory> findByName(Name name) {
        return jpaRepository.findByName(name.getName())
                .map(mapper::toDomainModel);
    }

    @Override
    public Optional<TeacherCategoryID> getTeacherCategoryIDFromName(Name name) {
        return jpaRepository.findByName(name.getName())
                .map(data -> idMapper.toDomainModel(data.getId()));
    }
}
