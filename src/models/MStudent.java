package models;

public class MStudent {
    private String studentID;
    private String studentName;
    private float score;
    private String image;
    private String address;
    private String description;

    public MStudent() {
        this.studentID = "";
        this.studentName = "";
        this.score = 0;
        this.image = "";
        this.address = "";
        this.description = "";
    }

    public MStudent(String studentID, String studentName, float score, String image, String address, String description) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.score = score;
        this.image = image;
        this.address = address;
        this.description = description;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
