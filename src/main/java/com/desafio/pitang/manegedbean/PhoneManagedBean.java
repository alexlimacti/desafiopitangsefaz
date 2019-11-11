package com.desafio.pitang.manegedbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.desafio.pitang.dao.PhoneDAO;
import com.desafio.pitang.dao.PhoneDAOImpl;
import com.desafio.pitang.dao.UserDAO;
import com.desafio.pitang.dao.UserDAOImpl;
import com.desafio.pitang.model.Phone;
import com.desafio.pitang.model.User;

@ManagedBean(name = "phoneManagedBean")
@ViewScoped
public class PhoneManagedBean {

    private User user = new User();
    private UserDAO userDAO = new UserDAOImpl();
    private PhoneDAO phoneDAO = new PhoneDAOImpl();

    private Phone phone = new Phone();

    @PostConstruct
    public void init() {
        String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
                .get("codigouser");
        user = userDAO.findById(Long.parseLong(coduser));
        user.setPhones(phoneDAO.findByUser(user));
    }

    public String save(){
        phone.setUser(user);
        phoneDAO = new PhoneDAOImpl();
        phoneDAO.save(phone);
        phone = new Phone();
        user = userDAO.findById(user.getId());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));

        return "";
    }

    public String deletePhone() throws Exception{
    	phoneDAO = new PhoneDAOImpl();
        phoneDAO.delete(phone);
        userDAO = new UserDAOImpl();
        user = userDAO.findById(user.getId());
        phone = new Phone();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Telefone Removido!"));
        return "";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public void setPhoneDAO(PhoneDAOImpl phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
