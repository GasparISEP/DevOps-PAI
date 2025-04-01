package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.ISchoolYearListFactory;

import java.util.List;
import java.util.Optional;


public class SchoolYearRepository implements ISchoolYearRepository {

    private List<SchoolYear> _schoolYearList;
    private ISchoolYearFactory _schoolYearFactory;
    private ISchoolYearListFactory _schoolYearListFactory;

    public SchoolYearRepository(ISchoolYearFactory schoolYearFactory, ISchoolYearListFactory schoolYearListFactory) {

        if (schoolYearFactory == null) {
            throw new IllegalArgumentException("SchoolYearFactory cannot be null");
        }
        if (schoolYearListFactory == null) {
            throw new IllegalArgumentException("SchoolYearListFactory cannot be null");
        }

        this._schoolYearList = schoolYearListFactory.newArrayList();
        this._schoolYearFactory = schoolYearFactory;
        this._schoolYearListFactory = schoolYearListFactory;
    }

    public boolean addSchoolYear(Description description, Date startDate, Date endDate) throws Exception {

        SchoolYear newSchoolYear = _schoolYearFactory.createSchoolYear(description, startDate, endDate);

        // Check if the school year already exists in the list
        if(schoolYearExists(newSchoolYear)){
            throw new Exception("School year already exists.");
        }
        // Add the school year to the list
        _schoolYearList.add(newSchoolYear);
        return true;
    }

    public boolean schoolYearExists(SchoolYear schoolYear){
        if(schoolYear==null){
            return false;
        }
        for (SchoolYear existingSchoolYear : _schoolYearList) {
            if (existingSchoolYear.isSameSchoolYear(schoolYear)) {
                return true;
            }
        }
        return false;
    }

    public SchoolYear getCurrentSchoolYear() {

        if (_schoolYearList.isEmpty())
            return null;

        Date today = Date.now();

        for (int i = 0; i < _schoolYearList.size(); i++) {
            if (!today.isBefore(_schoolYearList.get(i).getStartDate()) && !today.isAfter(_schoolYearList.get(i).getEndDate()))
                return _schoolYearList.get(i);
        }
        return null;
    }

    public List<SchoolYear> getAllSchoolYears() {
        return _schoolYearListFactory.copySchoolYearArrayList(_schoolYearList);
    }

    @Override
    public SchoolYear save(SchoolYear schoolYear) {
        _schoolYearList.add(schoolYear);
        return schoolYear;
    }

    @Override
    public Iterable<SchoolYear> findAll() {

        if (_schoolYearList.isEmpty()){
            throw new IllegalStateException("Student Grade List is empty.");
        }
        return _schoolYearList;
    }

    @Override
    public Optional<SchoolYear> ofIdentity(SchoolYearID id) {

        return _schoolYearList.stream()
                .filter(sy -> sy.identity().equals(id))
                .findAny();
    }

    @Override
    public boolean containsOfIdentity(SchoolYearID id) {

        if (!ofIdentity(id).isPresent()){
            return false;
        }
        return true;
    }

    }