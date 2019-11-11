package com.desafio.pitang.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.desafio.pitang.model.Phone;
import com.desafio.pitang.model.User;

public class PhoneDAOImpl extends GenericDAOImpl<Phone> implements PhoneDAO {
	
	public PhoneDAOImpl() {
		super(Phone.class);
	}
	
	public List<Phone> findByUser(User user) {
		return ( List<Phone>) getSession().createCriteria(Phone.class).add(Restrictions.eq("user", user)).list();
    }
}
