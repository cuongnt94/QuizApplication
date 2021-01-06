package hibernateDomain;
import java.io.Serializable;


public class QuestCatResult implements Serializable{
	private Integer questionID;
	private String questionText;
	private int correctSelection;
	private int userSelection;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String categoryName;
	private Integer categoryID;
	public QuestCatResult() {}
	private String[] choice;
	public QuestCatResult(Integer questionID, String questionText, String option1,
			String option2, String option3, String option4, int correctSelection, int userSelection, 
			Integer categoryID, String categoryName)
	{
		super();
		this.questionID = questionID;
		this.questionText = questionText;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctSelection = correctSelection;
		this.categoryName = categoryName;
		this.categoryID = categoryID;
		this.userSelection = -2;
		this.choice = new String[] {option1, option2, option3, option4};
	}
	public String getCategoryName()
	{
		return this.categoryName;
	}
	public Integer getQuestionID()
	{
		return this.questionID;
	}
	public Integer getCategoryID()
	{
		return this.categoryID;
	}
	public String getQuestionText()
    {
        return this.questionText;
    }
	public String[] getChoice()
    {
    	String[] choice = new String[4];
    	choice[0] = this.option1;
    	choice[1] = this.option2;
    	choice[2] = this.option3;
    	choice[3] = this.option4;
    	return choice;
    }
	public void setUserSelection(int userSelection)
    {
    	this.userSelection = userSelection;
    }
    public int getUserSelection()
    {
    	return this.userSelection;
    }
    public int getCorrectSelection()
    {
    	return this.correctSelection;
    }
    
}
