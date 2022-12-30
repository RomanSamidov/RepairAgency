package com.myCompany.RepairAgency.model.entity.DTO;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;

import java.util.ArrayList;

public class UserDTOFactory {
    public static UserDTO getUser(User user) {
        return new UserDTO.UserBuilder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setEmail(user.getEmail())
                .setRole(Constants.ROLE.values()[user.getRole_id()].name())
                .setAccount(user.getAccount())
                .build();
    }

    public static ArrayList<UserDTO> getUsers(ArrayList<User> users) {
        ArrayList<UserDTO> answer = new ArrayList<>();
        users.forEach(o -> answer.add(getUser(o)));
        return answer;
    }
}
