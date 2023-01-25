package com.douzone.mysite.web.mvc.board;


import com.douzone.web2.mvc.Action;
import com.douzone.web2.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("insert".equals(actionName)) {
			//action = new InsertAction();
		}
		else {
			action = new ListFormAction();
		}
		
		return action;
	}




}
