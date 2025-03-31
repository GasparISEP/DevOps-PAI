package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;


public class TeacherCareerProgression implements AggregateRoot<TeacherCareerProgressionID> {

    private TeacherCareerProgressionID _tcpID;
    private Date _date;
    private TeacherCategoryID _teacherCategoryID;
    private WorkingPercentage _workingPercentage;
    private TeacherID _teacherID;

    //constructor
    public TeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID) throws IllegalArgumentException {

        _tcpID = new TeacherCareerProgressionID();

        if(date == null)
            throw new IllegalArgumentException("Date cannot be null!");

        _date = date;

        if(teacherCategoryID == null)
            throw new IllegalArgumentException("Teacher Category cannot be null!");

        this._teacherCategoryID = teacherCategoryID;

        if(workingPercentage == null)
            throw new IllegalArgumentException("Working Percentage cannot be null!");

        _workingPercentage = workingPercentage;

        if(teacherID == null)
            throw new IllegalArgumentException("Teacher ID cannot be null!");

        _teacherID = teacherID;
    }

    public TeacherCategoryID getTeacherCategoryID () {

        return _teacherCategoryID;
    }

    public WorkingPercentage getWorkingPercentage () {

        return _workingPercentage;
    }

    public Date getDate() {

        return _date;
    }

    public TeacherID getTeacherID() {

        return _teacherID;
    }

    public boolean isLastDateEqualOrBeforeNewDate(Date newDate) {

        return !_date.getLocalDate().isAfter(newDate.getLocalDate());
    }

    @Override
    public TeacherCareerProgressionID identity() {
        return _tcpID;
    }

    @Override
    public boolean sameAs(Object object) {

        if(object instanceof TeacherCareerProgression) {

            TeacherCareerProgression tcp = (TeacherCareerProgression) object;

            if (_teacherID.sameAs(tcp._teacherID) && _date.equals(tcp._date))
                return true;
        }

        return false;
    }

}
