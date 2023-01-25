package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("viewform".equals(actionName)) {
			action = new ViewAction();
		} else if ("modifyform".equals(actionName)) {
			action = new UpdateFormAction();
		} else if ("modify".equals(actionName)) {
			action = new UpdateAction();
		} else if ("delete".equals(actionName)) {
			action = new DelteAction();
		} else {
			action = new ListFormAction();
		}

		return action;
	}

}
