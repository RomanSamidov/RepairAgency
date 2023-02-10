package com.myCompany.RepairAgency.servlet.service;

import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.exception.MyDBException;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iUserRepository;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.util.Encrypt;
import com.myCompany.RepairAgency.servlet.util.InitSessionAttributes;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;

class UserServiceTest {

    @Test
    void addToUserAccount1() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            Mockito.when(user.getRole_id()).thenReturn(5);

            UserService.addToUserAccount(1L, 1);
        }
    }

    @Test
    void addToUserAccount2() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            Mockito.when(user.getRole_id()).thenReturn(4);

            UserService.addToUserAccount(1L, 1);
        }
    }

    @Test
    void addToUserAccount3() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getById(Mockito.anyLong())).thenThrow(new MyDBException());

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.addToUserAccount(1L, 1);
        }
    }

    @Test
    void checkUserExistence() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getByLogin(Mockito.anyString())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.checkUserExistence("user");
        }
    }

    @Test
    void loginUser1() {

        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getByLogin(Mockito.anyString())).thenReturn(null);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = new MockHttpSession();
            request.setSession(session);

            UserService.loginUser("login", "password", request);
        }
    }

    @Test
    void loginUser2() {

        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<InitSessionAttributes> ignored3 = Mockito.mockStatic(InitSessionAttributes.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getByLogin(Mockito.anyString())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            Mockito.when(user.getPassword()).thenReturn("password");

            Mockito.when(Encrypt.encrypt(Mockito.anyString())).thenReturn("password");

            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = new MockHttpSession();
            request.setSession(session);

            UserService.loginUser("login", "password", request);
        }
    }

    @Test
    void loginUser3() {

        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<InitSessionAttributes> ignored3 = Mockito.mockStatic(InitSessionAttributes.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getByLogin(Mockito.anyString())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            Mockito.when(user.getPassword()).thenReturn("wrong_password");

            Mockito.when(Encrypt.encrypt(Mockito.anyString())).thenReturn("password");

            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpSession session = new MockHttpSession();
            request.setSession(session);

            UserService.loginUser("login", "password", request);
        }
    }

    @Test
    void get1() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getByLogin(Mockito.anyString())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.get("login");
        }
    }

    @Test
    void get2() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(user);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.get(0L);
        }
    }


    @Test
    void changePassword() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class);
             MockedStatic<Encrypt> ignored4 = Mockito.mockStatic(Encrypt.class)) {

            Mockito.when(Encrypt.encrypt(Mockito.anyString())).thenReturn("password");

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.doNothing().when(userRepository).update(Mockito.any());

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.changePassword(user, "password");
        }
    }

    @Test
    void changeEmail() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.doNothing().when(userRepository).update(Mockito.any());

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.changeEmail(user, "email");
        }
    }

    @Test
    void registerNewUser() {
        User.UserBuilder builder = Mockito.mock(User.UserBuilder.class);

        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class);
             MockedConstruction<User.UserBuilder> ignored4 =
                     Mockito.mockConstructionWithAnswer(User.UserBuilder.class,
                             invocation -> builder);
             MockedStatic<Encrypt> ignored5 = Mockito.mockStatic(Encrypt.class)) {

            Mockito.when(Encrypt.encrypt(Mockito.anyString())).thenReturn("password");

            User user = Mockito.mock(User.class);

            Mockito.when(builder.build()).thenReturn(user);
            Mockito.when(builder.setLogin(Mockito.anyString())).thenReturn(builder);
            Mockito.when(builder.setPassword(Mockito.anyString())).thenReturn(builder);
            Mockito.when(builder.setEmail(Mockito.anyString())).thenReturn(builder);
            Mockito.when(builder.setAllow_letters(Mockito.anyBoolean())).thenReturn(builder);
            Mockito.when(builder.setConfirmed(Mockito.anyBoolean())).thenReturn(builder);
            Mockito.when(builder.setRole_id(Mockito.anyInt())).thenReturn(builder);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.registerNewUser("login", "password", "email", 0);
        }
    }

    @Test
    void update() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.doNothing().when(userRepository).update(Mockito.any());

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.update(user);
        }
    }

    @Test
    void delete() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            User user = Mockito.mock(User.class);

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.doNothing().when(userRepository).delete(Mockito.any());

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.delete(user);
        }
    }

    @Test
    void countWhereRoleIs() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.countWhereRoleIs(Mockito.anyLong())).thenReturn(10L);

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.countWhereRoleIs(1L);
        }
    }

    @Test
    void getByRole() {
        try (MockedStatic<SendEmail> ignored1 = Mockito.mockStatic(SendEmail.class);
             MockedStatic<ModelManager> ignored0 = Mockito.mockStatic(ModelManager.class)) {

            iUserRepository userRepository = Mockito.mock(iUserRepository.class);
            Mockito.when(userRepository.getByRole(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                    .thenReturn(new ArrayList<>());

            ModelManager manager = Mockito.mock(ModelManager.class);
            Mockito.when(ModelManager.getInstance()).thenReturn(manager);
            Mockito.when(manager.getUserRepository()).thenReturn(userRepository);

            UserService.getByRole(0L, 10, 10);
        }
    }
}