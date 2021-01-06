package DAO;

import java.util.HashMap;
import java.util.List;

import hibernateDomain.Account;
import hibernateDomain.Feedback;
import hibernateDomain.Submission;
import hibernateStarter.ApplicationStarter;

public class AccountDAO {
	private ApplicationStarter applicationStarter;
	
	public AccountDAO()
	{
		applicationStarter = new ApplicationStarter();
	}
	
	public Account getAccount(String userName)
	{
		HashMap<String, String> map = applicationStarter.getUserData(userName);
		Account account = new Account(map.get("userName"), map.get("userPassword"), map.get("email"),
									map.get("phone"), map.get("birth"), map.get("firstName"), map.get("lastName"));
		account.setAccountID(Integer.parseInt(map.get("accountID")));
		return account;
	}
	public Integer getAccountID(String userName)
	{
		HashMap<String, String> map = applicationStarter.getUserData(userName);
		return Integer.parseInt(map.get("accountID"));
	}
	
	public List<Submission> getSubmissionList()
	{
		return applicationStarter.getSubmissionData();
	}
	
	public void submitFeedback(Feedback feedback)
	{
		applicationStarter.submitFeedback(feedback);
	}
	
	public List<Feedback> getFeedbackList()
	{
		return applicationStarter.getFeedbackData();
	}
	public void addAccount(Account account)
	{
		applicationStarter.addAccountData(account);
	}
}
