package POJO;
public class Student {
    private String regId;
    private String name;
    private String address;
    private java.sql.Date dob;  // Use java.sql.Date to map SQL DATE type
    private String email;
    private String mobile;
    private String password;

    // Constructor
    public Student() {
    }

    public Student(String regId, String name, String address, java.sql.Date dob, String email, String mobile, String password) {
        this.regId = regId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    // Getters and Setters
    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getDob() {
        return dob;
    }

    public void setDob(java.sql.Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Override toString() method for easier object representation
    @Override
    public String toString() {
        return "Student{" +
                "regId='" + regId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
