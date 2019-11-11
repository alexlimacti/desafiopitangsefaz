package com.desafio.pitang.dao;

import com.desafio.pitang.model.User;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
	
	public UserDAOImpl() {
		super(User.class);
	}
	

}
