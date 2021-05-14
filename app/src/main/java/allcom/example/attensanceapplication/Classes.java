package allcom.example.attensanceapplication;

public class Classes {
    public String teacherName, selectedDivision,selectedSubject,selectedTime,selectedDate,teacherId,selectedYear,selectedClass,sRoll;
    public String studentName;

    public Classes(String studentName,String sRoll) {
        this.studentName = studentName;
        this.sRoll=sRoll;
    }

    public Classes(){
        
    }
}
