package voting.app.models;

import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "VOTE_CASTING_DETAILS")
@Component
public class VoteCastingDetails {
    @Id
    @GeneratedValue
    private int transactionId;

    @Column
    private Date voteCastDate;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "voterId", nullable = false)
    private Voter voter;

    @ManyToOne(cascade= CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "candidateId", nullable = false)
    private Candidate candidate;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

//    @Column(unique = true)
//    private String voterId;
//
//    @Column
//    private String candidateId;

    public VoteCastingDetails() {
    }

    public VoteCastingDetails(Date voteCastDate, Candidate candidate, Voter voter) {
        this.voteCastDate = voteCastDate;
        this.candidate = candidate;
        this.voter = voter;
    }

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


    public Date getVoteCastDate() {
        return voteCastDate;
    }

    public void setVoteCastDate(Date voteCastDate) {
        this.voteCastDate = voteCastDate;
    }

//    public String getVoterId() {
//        return voterId;
//    }
//
//    public void setVoterId(String voterId) {
//        this.voterId = voterId;
//    }

//    public String getCandidateId() {
//        return candidateId;
//    }
//
//    public void setCandidateId(String candidateId) {
//        this.candidateId = candidateId;
//    }
}
