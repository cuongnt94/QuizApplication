package DAO;
import java.util.*;

import hibernateDomain.Question;
import hibernateDomain.Submission;
import hibernateStarter.ApplicationStarter;
import hibernateDomain.Account;
import hibernateDomain.Category;
import hibernateDomain.QuestCatResult;

public class QuizDAO {
	
	private ApplicationStarter applicationStarter;
	
	public QuizDAO (){
		applicationStarter = new ApplicationStarter();
		
		//HashMap<String, List<Question>> map = database.getListOfQuestion();
		
		
		/*for(Map.Entry element: map.entrySet())
		{
			List<Question> listQuestion = (List<Question>) element.getValue();
			//System.out.println("CategoryID: " + element.getKey());
			//System.out.println("Size: " + listQuestion.size());
			Category tempQuiz = new Category(3, (String) element.getKey());
			tempQuiz.setListQuestion(listQuestion);
			quiz.add(tempQuiz);
		}*/
		
	}
	
	public List<QuestCatResult> getQuestionList(String categoryName)
	{
		List<QuestCatResult> list = applicationStarter.getQuestionData(categoryName);
		Collections.shuffle(list);
		
		List<QuestCatResult> ans = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			ans.add(list.get(i));
		}
		return ans;
	}
	
	public void submit(Submission submission)
	{
		applicationStarter.submit(submission);
	}
	
	/*public Category getCategory(String categoryName)
	{
		List<QuestCatResult> list = applicationStarter.getQuestionData(categoryName);
		for(int i = 0; i < list.size(); i++)
		{
			QuestCatResult temp = list.get(i);
			Question q = new Question(temp.getQuestionText(), temp.getChoice(), temp.getCorrectSelection());
			q.setCategory(category);
		}
	}*/
	
	public void addQuestion(Integer categoryID, String questionText, int correctSelection, String[] choice)
	{
		applicationStarter.addQuestion(categoryID, questionText, correctSelection, choice);
	}
}
