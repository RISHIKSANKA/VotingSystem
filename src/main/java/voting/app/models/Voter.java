package voting.app.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table
@Component
public class Voter {
    @Column
    private int zipCode;
    @Column
    private String name;
    @Column
    private String surname;
    //    @Column
//    private String gender;
    @Column(unique = true)
    private String emailId;
    @Column(unique = true)
    private long dLNum;
    @Column
    private long mobileNum;
    @Id
//    @Column(name = "Id", updatable = false)
    private String loginId;

//    @OneToOne(mappedBy = "voter", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Voter voter;

    @Column
    private String password;
    @Column
    private String hintQuestion;
    @Column
    private String hintAnswer;
    @Column(name = "dob")
    private Date dob;
    @Column
    private boolean approvalStatus;

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHintQuestion() {
        return hintQuestion;
    }

    public void setHintQuestion(String hintQuestion) {
        this.hintQuestion = hintQuestion;
    }

    public String getHintAnswer() {
        return hintAnswer;
    }

    public void setHintAnswer(String hintAnswer) {
        this.hintAnswer = hintAnswer;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public long getdLNum() {
        return dLNum;
    }

    public void setdLNum(long dLNum) {
        this.dLNum = dLNum;
    }
//    public String getGender() {
//        return gender;
//    }
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    @Override
    public String toString() {
        return "Voters [zipCode=" + zipCode + ", name=" + name + ", surname=" + surname + ", emailId="
                + emailId + ", loginId=" + loginId + ", password=" + password + ", hintQuestion=" + hintQuestion
                + ", hintAnswer=" + hintAnswer + ", dob=" + dob + "]";
    }

    public boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public long getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
    }
}
