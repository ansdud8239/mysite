package com.douzone.mysite.web.mvc.guestbook;


import com.douzone.web2.mvc.Action;
import com.douzone.web2.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("insert".equals(actionName)) {
			action = new InsertAction();
		}else if("deleteform".equals(actionName)) { 
			action = new DeleteFormAction();
		}else if("delete".equals(actionName)) { 
			action = new DeleteAction();
		}
		else {
			action = new ListFormAction();
		}
		
		return action;
	}




}
