package hibernateStarter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernateConfig.HibernateConfigUtil;
import hibernateDomain.Account;
import hibernateDomain.Category;
import hibernateDomain.Feedback;
import hibernateDomain.QuestCatResult;
import hibernateDomain.Question;
import hibernateDomain.Submission;


public class ApplicationStarter {
	
	/*
	 * 1: Java
	 * 2: HTML
	 * 3: JavaScript
	 * 4: SpringBoot
	 * 5: C++
	 * 6: SQL
	 */
	
	public ApplicationStarter() {
		
	}
	/*public static void main(String[] args)
	{
		ApplicationStarter database = new ApplicationStarter();
		String[] choice = new String[] {"option1", "option2", "option3", "option4"};
		database.addQuestion(1, "Question 11?", 0, choice);
		database.addQuestion(1, "Question 12?", 0, choice);
		database.addQuestion(1, "Question 13?", 0, choice);
		database.addQuestion(1, "Question 14?", 1, choice);
		database.addQuestion(1, "Question 15?", 1, choice);
		database.addQuestion(1, "Question 16?", 0, choice);
		database.addQuestion(1, "Question 17?", 2, choice);
		database.addQuestion(1, "Question 18?", 0, choice);
		database.addQuestion(1, "Question 19?", 3, choice);
		database.addQuestion(1, "Question 20?", 0, choice);
	}*/
	
	
	/* Method to submit the quiz to database*/
	public void submit(Submission submission)
    {
		Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	try
    	{
    		transaction = session.beginTransaction();
    		session.save(submission);
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	
    }
	
    /* Method to add Question to the database */
    public void addQuestion(Integer categoryID, String questionText, int correctSelection, String[] choice)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	
    	String categoryName = "";
    	switch (categoryID)
    	{
    		case 1:
    			categoryName = "Java";
    			break;
    		case 2:
    			categoryName = "HTML";
    			break;
    		case 3:
    			categoryName = "JavaScript";
    			break;
    		case 4:
    			categoryName = "SpringBoot";
    			break;
    		case 5:
    			categoryName = "C++";
    			break;
    		case 6:
    			categoryName = "SQL";
    			break;
    	}
    	
       try {
    	  transaction = session.beginTransaction();
          Question question = new Question(questionText, choice, correctSelection);
          /*Category category = new Category(categoryID, categoryName);
          Set<Question> questionList = new HashSet<>();
          questionList.add(question);
          category.setListQuestion(questionList);
          session.merge(category);*/
          Category category = (Category) session.get(Category.class, categoryID);
          category.addQuestion(question);
          //session.save(category);
          
          question.setCategory(category);
          session.save(question);
          transaction.commit();
       } 
       catch (HibernateException e) 
       {
          if (transaction!=null)
        	  transaction.rollback();
          e.printStackTrace(); 
       } 
       finally 
       {
          session.close(); 
       }
       
    }

    /* Method to load data from question_data based on given categoryName*/
    public List<QuestCatResult> getQuestionData(String categoryName)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	List<QuestCatResult> results = null;
    	HashMap<String, List<Question>> map = new HashMap<>();
    	try 
    	{
        	transaction = session.beginTransaction();
        	
        	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<QuestCatResult> criteriaQuery = criteriaBuilder.createQuery(QuestCatResult.class);
			
			Root<Question> pRoot = criteriaQuery.from(Question.class);
			pRoot.join("category", JoinType.INNER);
			criteriaQuery.multiselect(				
					pRoot.get("questionID"),
					pRoot.get("questionText"),
					pRoot.get("option1"),
					pRoot.get("option2"),
					pRoot.get("option3"),
					pRoot.get("option4"),
					pRoot.get("correctSelection"),
					pRoot.get("userSelection"),
					pRoot.get("category").get("categoryID"),
					pRoot.get("category").get("categoryName"));
			Predicate predicate = pRoot.get("category").get("categoryName").in(Arrays.asList(categoryName));
			criteriaQuery.where(predicate);
			
			results = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
        
        }
        catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	return results;
    }
    
    
    /* Method to check the login*/
    public boolean checkLogin(String userName, String password)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	try
    	{
    		transaction = session.beginTransaction();
    		List accountData = session.createQuery("from Account").list();
    		for (Iterator iterator = accountData.iterator(); iterator.hasNext();)
    		{
                Account account = (Account) iterator.next(); 
                if(account.getUserName().equals(userName) && account.getUserPassword().equals(password))
                	return true;
            }
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	return false;
    }
    
    /* Method to check the sign up
     * true means it is userName does not exist*/
    public boolean checkSignup(String userName)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	try
    	{
    		transaction = session.beginTransaction();
    		List accountData = session.createQuery("from Account").list();
    		for (Iterator iterator = accountData.iterator(); iterator.hasNext();)
    		{
                Account account = (Account) iterator.next(); 
                if(account.getUserName().equals(userName))
                	return false;
            }
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	return true;
    }
    
    /* Method to get user data based on given userName*/
    public HashMap<String, String> getUserData(String userName)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	HashMap<String, String> map = new HashMap<>();
    	try
    	{
    		transaction = session.beginTransaction();
    		List accountData = session.createQuery("from Account").list();
    		for (Iterator iterator = accountData.iterator(); iterator.hasNext();)
    		{
                Account account = (Account) iterator.next(); 
                if(account.getUserName().equals(userName))
                {
                	map.put("accountID", String.valueOf(account.getAccountID()));
                	map.put("userName", account.getUserName());
                	map.put("userPassword", account.getUserPassword());
                	map.put("birth", account.getBirth());
                	map.put("phone", account.getPhone());
                	map.put("firstName", account.getFirstName());
                	map.put("lastName", account.getLastName());
                	map.put("email", account.getEmail());
                
                }
            }
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	return map;
    }

    
    /* Method to add new user in the sign up page*/
    public void addAccountData(Account account)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	try
    	{
    		transaction = session.beginTransaction();
            session.save(account); 
            transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    }
    
    
    /* Method to load data from submission_data*/
    public List<Submission> getSubmissionData()
    {
    	System.out.println("hey");
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	List<Submission> submissionList = new ArrayList<>();
    	try
    	{
    		transaction = session.beginTransaction();
    		List submissions = session.createQuery("FROM Submission").list(); 
            for (Iterator iterator = submissions.iterator(); iterator.hasNext();){
               Submission temp = (Submission) iterator.next(); 
               submissionList.add(temp);
            }
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	return submissionList;
    }
    
    public void submitFeedback(Feedback feedback)
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	try
    	{
    		transaction = session.beginTransaction();
    		session.save(feedback);
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    }

    
    public List<Feedback> getFeedbackData()
    {
    	Session session = HibernateConfigUtil.openSession();
    	Transaction transaction = null;
    	List<Feedback> feedbackList = new ArrayList<>();
    	try
    	{
    		transaction = session.beginTransaction();
    		List feedbacks = session.createQuery("FROM Feedback").list(); 
            for (Iterator iterator = feedbacks.iterator(); iterator.hasNext();){
            	Feedback temp = (Feedback) iterator.next(); 
            	feedbackList.add(temp);
            }
    		transaction.commit();
    	}
    	catch (HibernateException e) 
        {
           if (transaction!=null)
         	  transaction.rollback();
           e.printStackTrace(); 
        } 
        finally 
        {
           session.close(); 
        }
    	return feedbackList;
    }
    
}
