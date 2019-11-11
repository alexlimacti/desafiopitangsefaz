package com.desafio.pitang.manegedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.desafio.pitang.dao.UserDAO;
import com.desafio.pitang.dao.UserDAOImpl;
import com.desafio.pitang.model.User;

@ManagedBean(name = "userManagedBean")
@ViewScoped
public class UserManagedBean {

    private User user = new User();
    private UserDAO userDAO = new UserDAOImpl();
    private List<User> list = new ArrayList<User>();
    
    
    @PostConstruct
    public void init() {
        try {
			list = userDAO.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String save() {
    	userDAO = new UserDAOImpl();
        userDAO.save(user);
        list.add(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
        return "usuario-salvo";
    }

    public String remover() {
        try {
        	userDAO = new UserDAOImpl();
            userDAO.delete(user);
            list.remove(user);
            user = new User();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));

        } catch (Exception e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Informação: ", "Existem telefones para o usuario!"));
            }else {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String newUser() {
        user = new User();
        return "";
    }

    public List<User> getList() {
        return list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
