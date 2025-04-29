package PAI.persistence.mem.programmeEdition;

import PAI.VOs.*;
import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.repository.programmeRepository.IProgrammeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProgrammeRepositoryImpl implements IProgrammeRepository {

    private final List<Programme> _programmeRepo;

    public ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory programmeLisListFactory) {
        _programmeRepo = programmeLisListFactory.newProgrammeArrayList();
    }

    @Override
    public Programme save(Programme entity) {
        _programmeRepo.add(entity);
        return entity;
    }

    @Override
    public Iterable<Programme> findAll() {
        return _programmeRepo;
    }

    @Override
    public Optional<Programme> ofIdentity(ProgrammeID id) {
        for (Programme existingProgramme : _programmeRepo) {
            if (existingProgramme.identity().equals(id)) {
                return Optional.of(existingProgramme);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeID id) {
        return ofIdentity(id).isPresent();
    }

}