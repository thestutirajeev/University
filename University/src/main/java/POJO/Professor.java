package POJO;

public class Professor {
    private String profId;
    private String profName;
    private String email;
    private String mobile;
    private String deptId;
    private String crsId;
    private String password;

    // Constructor
    public Professor() {
    }

    public Professor(String profId, String profName, String email, String mobile, String deptId, String crsId, String password) {
        this.profId = profId;
        this.profName = profName;
        this.email = email;
        this.mobile = mobile;
        this.deptId = deptId;
        this.crsId = crsId;
        this.password = password;
    }

    // Getters and Setters
    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCrsId() {
        return crsId;
    }

    public void setCrsId(String crsId) {
        this.crsId = crsId;
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
        return "Professor{" +
                "profId='" + profId + '\'' +
                ", profName='" + profName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", deptId='" + deptId + '\'' +
                ", crsId='" + crsId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
