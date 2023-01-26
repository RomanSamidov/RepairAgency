package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public static boolean addToUserAccount(long userId, int increment) throws MyDBException {
        try {
            iUserRepository repo = ModelManager.getInstance().getUserRepository();
            User user = repo.getById(userId);
            if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
                user.setAccount(user.getAccount() + increment);
                repo.update(user);

                SendEmailService.forAddToUserAccount(user, increment);
                return true;
            }
        } catch (MyDBException e) {
            logger.debug("Catch MyDBException");
        }
        return false;
    }

    public static boolean checkUserExistence(String login) throws MyDBException {
        iUserRepository userRepository = ModelManager.getInstance().getUserRepository();
        return userRepository.getByLogin(login) != null;
    }

    public static boolean loginUser(String login, String password, HttpServletRequest request) throws MyDBException {
        User user = ModelManager.getInstance().getUserRepository().getByLogin(login);
        if (user == null) {
            request.getSession().setAttribute("errorLoginPassMessage", "message.login_not_exist_error");
            return false;
        }

        String userPassword = user.getPassword();
        if (userPassword.equals(Encrypt.encrypt(password))) {
            InitSessionAttributesService.initUserSessionAttributes(request, user);
            SendEmailService.forLogin(user);
            return true;
        } else {
            request.getSession().setAttribute("errorLoginPassMessage", "message.login_error");
            return false;
        }
    }

    public static User get(String login) throws MyDBException {
        iUserRepository userRepository = ModelManager.getInstance().getUserRepository();
        return userRepository.getByLogin(login);
    }

    public static User get(long id) throws MyDBException {
        iUserRepository userRepository = ModelManager.getInstance().getUserRepository();
        return userRepository.getById(id);
    }

    public static void changePassword(User user, String password) throws MyDBException {
        user.setPassword(Encrypt.encrypt(password));
        ModelManager.getInstance().getUserRepository().update(user);
        SendEmailService.forChangePassword(user);
    }

    public static void changeEmail(User user, String email) throws MyDBException {
        user.setEmail(email);
        user.setConfirmed(false);
        ModelManager.getInstance().getUserRepository().update(user);
    }

    public static void registerNewUser(String login, String password, String email, int role_id) throws MyDBException {
        String userPassword = Encrypt.encrypt(password);

        User user = new User.UserBuilder().setLogin(login)
                .setPassword(userPassword)
                .setEmail(email)
                .setAllow_letters(true)
                .setConfirmed(false)
                .setRole_id(role_id)
                .build();

        iUserRepository userRepository = ModelManager.getInstance().getUserRepository();
        userRepository.insert(user);
        SendEmailService.forSignup(user);
    }


    public static ArrayList<User> getByRole(int roleId, int skip, int quantity) throws MyDBException {
        return ModelManager.getInstance().getUserRepository().getByRole(roleId, skip, quantity);
    }

    public static void update(User user) throws MyDBException {
        ModelManager.getInstance().getUserRepository().update(user);
    }

    public static void delete(User user) {
        ModelManager.getInstance().getUserRepository().delete(user);
    }
}
