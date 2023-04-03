package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.ifsp.dmos5.criptografia.MD5;
import br.edu.ifsp.dmos5.model.User;

public class UserDAO {

    private static UserDAO instance;
    private List<User> users = new ArrayList<>();
    private UserDAO(){};

    public static UserDAO getInstance(){
        if (instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findByUsername(String username) {
        for (User user : users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public boolean verificaSenha(String username, String password) {
        for (User user: users){
            if (username.equals(user.getUsername()) && MD5.criptografar(password).equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
}
