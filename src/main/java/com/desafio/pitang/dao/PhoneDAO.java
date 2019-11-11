package com.desafio.pitang.dao;

import java.util.List;

import com.desafio.pitang.model.Phone;
import com.desafio.pitang.model.User;

public interface PhoneDAO extends GenericDAO<Phone> {
	
	public List<Phone> findByUser(User user);

}
