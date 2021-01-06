package hibernateDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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


@Entity
@Table(name = "category_data")
public class Category implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
    private Integer categoryID;
	
    private String categoryName;
    
    private Set<Question> listQuestion = new HashSet<>(0);
    
    private Set<Submission> submissionList = new HashSet<>(0);
    
    public Category()
    {
    	
    }
    
    public Category(Integer categoryID, String categoryName)
    {
    	super();
    	this.categoryID = categoryID;
    	this.categoryName = categoryName;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryID", unique = true, nullable = false)
    public Integer getCategoryID()
    {
    	return this.categoryID;
    }
    public void setCategoryID(Integer categoryID)
    {
    	this.categoryID = categoryID;
    }
    
    @Column(name = "categoryName")
    public String getCategoryName()
    {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
    
    //, cascade = CascadeType.ALL
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Question> getListQuestion() 
    {
		return listQuestion;
	}
    public void setListQuestion(Set<Question> listQuestion)
    {
    	/*Iterator<Question> iterator = listQuestion.iterator();
    	while(iterator.hasNext())
    	{
    		Question q = (Question)iterator.next();
    		q.setCategory(this);
    	}*/
    	this.listQuestion = listQuestion;
    }
    
    public void addQuestion(Question question)
    {
    	this.listQuestion.add(question);
    	question.setCategory(this);
    }
    
    //, cascade = CascadeType.ALL
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
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
    		q.setCategory(this);
    	}*/
    	this.submissionList = submissionList;
    }
    public void addSubmission(Submission submission)
    {
    	this.submissionList.add(submission);
    }

	/*public Set<Submission> getSubmissionList() {
		return submissionList;
	}*/

	/*public void setSubmissionList(Set<Submission> submissionList) {
		this.submissionList = submissionList;
	}*/

	
    

}


