
package model;

public class Student {
    private String StudentID;
    private String FullName;
    private String BirthDate;
    private String Gender;
    private String E1;
    private String E2;
    private String E3;
    private String E4;
    private String SpecId;

    public Student() {
    }

    public Student(String StudentID, String FullName, String BirthDate, String Gender, String E1, String E2, String E3, String E4, String SpecId) {
        this.StudentID = StudentID;
        this.FullName = FullName;
        this.BirthDate = BirthDate;
        this.Gender = Gender;
        this.E1 = E1;
        this.E2 = E2;
        this.E3 = E3;
        this.E4 = E4;
        this.SpecId = SpecId;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getE1() {
        return E1;
    }

    public void setE1(String E1) {
        this.E1 = E1;
    }

    public String getE2() {
        return E2;
    }

    public void setE2(String E2) {
        this.E2 = E2;
    }

    public String getE3() {
        return E3;
    }

    public void setE3(String E3) {
        this.E3 = E3;
    }

    public String getE4() {
        return E4;
    }

    public void setE4(String E4) {
        this.E4 = E4;
    }

    public String getSpecId() {
        return SpecId;
    }

    public void setSpecId(String SpecId) {
        this.SpecId = SpecId;
    }

    @Override
    public String toString() {
        return "Student{" + "StudentID=" + StudentID + ", FullName=" + FullName + ", BirthDate=" + BirthDate + ", Gender=" + Gender + ", E1=" + E1 + ", E2=" + E2 + ", E3=" + E3 + ", E4=" + E4 + ", SpecId=" + SpecId + '}';
    }

    
    
    
    
    
}
