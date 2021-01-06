package hibernateDomain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "submission_data")
public class Submission implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer submissionID;
	
	
	private String startTime;
	
	
	private String endTime;
	
	
	private double grade;
	
	
	private Account account;
	
    private Category category;
	
	public Submission(){}
	
	public Submission(String startTime, String endTime, double grade)
	{
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.grade = grade;
	}
	
	@Column(name = "startTime")
	public String getStartTime()
	{
		return this.startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	
	@Column(name = "endTime")
	public String getEndTime()
	{
		return this.endTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "submissionID", unique = true, nullable = false)
	public Integer getSubmissionID() {
		return this.submissionID;
	}
	public void setSubmissionID(Integer submissionID)
	{
		this.submissionID = submissionID;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryID", nullable = false)
	public Category getCategory()
	{
		return this.category;
	}
	public void setCategory(Category category)
	{
		this.category = category;
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
	
	@Column(name = "grade")
	public double getGrade()
	{
		return this.grade;
	}
	public void setGrade(double grade)
	{
		this.grade = grade;
	}
	
}

