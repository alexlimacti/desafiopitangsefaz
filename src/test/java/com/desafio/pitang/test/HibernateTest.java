package com.desafio.pitang.test;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.desafio.pitang.dao.PhoneDAOImpl;
import com.desafio.pitang.dao.UserDAOImpl;
import com.desafio.pitang.model.Phone;
import com.desafio.pitang.model.User;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void testeHibernateUtil() {

        UserDAOImpl userDAO =  new UserDAOImpl();
        PhoneDAOImpl phoneDAO =  new PhoneDAOImpl();
        User user = new User();

        // Seta todas as propriedades do objeto       
        user.setName("Paulo");
        user.setPassword("123");
        user.setMail("teste@teste.com");
        userDAO.save(user);// Chama o salvar para gravar no banco de dados.
    }

    @Test
    public void testeBuscar() {
        UserDAOImpl userDAO = new UserDAOImpl();// Inicia o DAO
        User user = new User();// Inicia o Objeto 
        user.setId(2L);// Precisamos apenas do ID
        user = userDAO.findById(user.getId()); // Envia para pesquisa
        System.out.println(user); // Imprime objeto para conferir
    }

    @Test
    public void testeBuscar2() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findById(1L);
        System.out.println(user);
    }

    @Test
    public void testeUpdate() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findById(1L);
        user.setName("Nome atualizado Hibernate");
        user.setPassword("sd4s5d4s4d");
        userDAO.save(user);
        System.out.println(user);
    }

    @Test
    public void testeDelete() {
        UserDAOImpl userDAO = new UserDAOImpl();// Instancia o DAO
        User user = userDAO.findById(3L);// Consulta o objeto antes de remover
        try {
            userDAO.delete(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// Invoca o método de remoção do banco de dados

    }

    @Test
    public void testeConsultar() throws Exception {
        UserDAOImpl userDAO = new UserDAOImpl();
        List<User> list = userDAO.findAll();
        for (User user : list) {
            System.out.println(user);
            System.out.println("--------------------------------------------------");
        }

    }

    @Test
    public void testeGravaTelefone(){
        PhoneDAOImpl phoneDAO = new PhoneDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        User user =  (User) userDAO.findById(28L);
        Phone phone = new Phone();
        phone.setType("Casa");
        phone.setNumber("8778987987");
        phone.setUser(user);
        phoneDAO.save(phone);
    }

    @Test
    public void testeConsultaTelefones(){
        UserDAOImpl userDAO = new UserDAOImpl();
        User user =  (User) userDAO.findById(24L);
        for (Phone fone : user.getPhones()) {
            System.out.println(fone.getNumber());
            System.out.println(fone.getType());
            System.out.println(fone.getUser().getName());
            System.out.println("----------------------------------");
        }

    }
}
