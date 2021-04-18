package allcom.example.attensanceapplication;

public class attendancelist {
    String teacherId;
    String teacherName;
    String selectedYear;
    String selectedClass;
    String selectedDivision;
    String selectedSubject;
    String selectedDate;
    String selectedTime;


    public attendancelist(){

    }

    public attendancelist(String teacherId, String teacherName, String selectedYear, String selectedClass, String selectedDivision, String selectedSubject, String selectedDate, String selectedTime) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.selectedYear = selectedYear;
        this.selectedClass = selectedClass;
        this.selectedDivision = selectedDivision;
        this.selectedSubject = selectedSubject;
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public String getSelectedDivision() {
        return selectedDivision;
    }

    public String getSelectedSubject() {
        return selectedSubject;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public String getSelectedTime() {
        return selectedTime;
    }
}
