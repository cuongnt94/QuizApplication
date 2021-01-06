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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question_data")
public class Question implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
    private Integer questionID;
    
    
    private String questionText;
    
    
    private int correctSelection;
    
    
    private int userSelection;
    
    
    private String option1;
    
    
    private String option2;
    
    
    private String option3;
    
    
    private String option4;
    
    
    private Category category;
    
    
    public Question() {}

    
    public Question(String questionText, String[] choice, int correctSelection)
    {
    	super();
        this.questionText = questionText;
        this.correctSelection = correctSelection;
        this.userSelection = -2;
        this.option1 = choice[0];
        this.option2 = choice[1];
        this.option3 = choice[2];
        this.option4 = choice[3];
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "questionID", unique = true, nullable = false)
    public Integer getQuestionID()
    {
        return this.questionID;
    }
    public void setQuestionID(Integer questionID)
    {
        this.questionID = questionID;
    }
    
    @Column(name = "questionText")
    public String getQuestionText()
    {
        return this.questionText;
    }
    public void setQuestionText(String questionText)
    {
    	this.questionText = questionText;
    }
    
    /*public String[] getChoice()
    {
    	String[] choice = new String[4];
    	choice[0] = this.option1;
    	choice[1] = this.option2;
    	choice[2] = this.option3;
    	choice[3] = this.option4;
    	return choice;
    }*/
    
    @Column(name = "option1")
    public String getOption1()
    {
    	return this.option1;
    }
    public void setOption1(String option1)
    {
    	this.option1 = option1;
    }
    
    @Column(name = "option2")
    public String getOption2()
    {
    	return this.option2;
    }
    public void setOption2(String option2)
    {
    	this.option2 = option2;
    }
    
    @Column(name = "option3")
    public String getOption3()
    {
    	return this.option3;
    }
    public void setOption3(String option3)
    {
    	this.option3 = option3;
    }
    
    @Column(name = "option4")
    public String getOption4()
    {
    	return this.option4;
    }
    public void setOption4(String option4)
    {
    	this.option4 = option4;
    }
    
    @Column(name = "questionAnswer")
    public int getCorrectSelection()
    {
        return this.correctSelection;
    }
    public void setCorrectSelection(int correctSelection)
    {
    	this.correctSelection = correctSelection;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryID", nullable = false)
    public Category getCategory()
    {
    	return this.category;
    }
    public void setCategory(Category category)
    {
    	this.category = category;
    }
    
    @Column(name = "userSelection")
    public int getUserSelection()
    {
    	return this.userSelection;
    }
    public void setUserSelection(int userSelection)
    {
    	this.userSelection = userSelection;
    }
}