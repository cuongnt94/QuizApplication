package hibernateDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_data")
public class Feedback implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer feedbackID;
	
	
	private String feedbackText;
	
	
	private Account account;
	
	public Feedback() {}
	
	public Feedback(String feedbackText)
	{
		super();
		this.feedbackText = feedbackText;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackID", unique = true, nullable = false)
	public Integer getFeedbackID()
	{
		return this.feedbackID;
	}
	public void setFeedbackID(Integer feedbackID)
	{
		this.feedbackID = feedbackID;
	}
	
	@Column(name = "feedbackText")
	public String getFeedbackText()
	{
		return this.feedbackText;
	}
	public void setFeedbackText(String feedbackText)
	{
		this.feedbackText = feedbackText;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountID", nullable = false)
	public Account getAccount()
	{
		return this.account;
	}
	public void setAccount(Account account)
	{
		this.account = account;
	}
}
