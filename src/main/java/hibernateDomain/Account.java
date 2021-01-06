package hibernateDomain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "account_data")
public class Account implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
    private Integer accountID;
	
	
    private String userName;
    
	
    private String userPassword;
    
	
    private String email;
    
	
    private String phone;
    
	
    private String birth;
    
	
	private String firstName;
    
	
	private String lastName;
    
	
	private Set<Submission> submissionList = new HashSet<>(0);
	
	
	private Set<Feedback> feedbackList = new HashSet<>(0);
	
	public Account() {
		
	}
    public Account(String userName, String userPassword, String email, String phone, String birth,
                   String firstName, String lastName)
    {
    	super();
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountID", unique = true, nullable = false)
    public Integer getAccountID()
    {
        return this.accountID;
    }
    public void setAccountID(Integer accountID)
    {
        this.accountID = accountID;
    }
    
    
    public void addSubmission(Submission submission)
    {
    	this.submissionList.add(submission);
    	submission.setAccount(this);
    }
    
    public void addFeedback(Feedback feedback)
    {
    	this.feedbackList.add(feedback);
    	feedback.setAccount(this);
    }
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    public Set<Submission> getSubmissionList()
    {
        return this.submissionList;
    }
    public void setSubmissionList(Set<Submission> submissionList)
    {
    	/*Iterator<Submission> iterator = submissionList.iterator();
    	while(iterator.hasNext())
    	{
    		Submission q = (Submission)iterator.next();
    		q.setAccount(this);
    	}*/
        this.submissionList = submissionList;
    }
    
    public void setFeedbackList(Set<Feedback> feedbackList)
    {
    	/*Iterator<Feedback> iterator = feedbackList.iterator();
    	while(iterator.hasNext())
    	{
    		Feedback q = (Feedback)iterator.next();
    		q.setAccount(this);
    	}*/
    	this.feedbackList = feedbackList;
    }
    
    //, cascade = CascadeType.ALL
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    public Set<Feedback> getFeedbackList()
    {
    	return this.feedbackList;
    }
    
    @Column(name = "userName")
    public String getUserName()
    {
        return this.userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    @Column(name = "email")
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @Column(name = "phone")
    public String getPhone()
    {
        return this.phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public void setBirth(String birth)
    {
        this.birth = birth;
    }
    
    @Column(name = "birth")
    public String getBirth()
    {
        return this.birth;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    @Column(name = "firstName")
    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    @Column(name = "lastName")
    public String getLastName()
    {
        return this.lastName;
    }
    
    @Column(name = "userPassword")
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
