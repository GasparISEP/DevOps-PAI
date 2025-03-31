package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.*;

public class TeacherFactoryImpl implements ITeacherFactory {

    private ITeacherCareerProgressionFactory _teacherCareerProgressionFactory;
    private ITeacherCareerProgressionListFactory _teacherCareerProgressionListFactory;

    public TeacherFactoryImpl(TeacherCareerProgressionFactoryImpl tcpFact, TeacherCareerProgressionListFactoryImpl tcplFact){
        _teacherCareerProgressionFactory = tcpFact;
        _teacherCareerProgressionListFactory = tcplFact;
    }

    public Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber,
                                 String academicBackground, String street, String postalCode, String location,
                                 String country, IAddressFactory addressFactory, Date date, TeacherCategoryID category, WorkingPercentage workingPercentage, TeacherID teacherID,
                                 Department department) throws IllegalArgumentException {

        return new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode,
                location, country, addressFactory, date, category, workingPercentage, teacherID, department, _teacherCareerProgressionFactory, _teacherCareerProgressionListFactory);
    }
}
