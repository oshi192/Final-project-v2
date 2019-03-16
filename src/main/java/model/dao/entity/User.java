package model.dao.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class User {
    private int id;
    private int sum;
    private String email;
    private String phoneNumber;
    private String password;
    private String name;
    private String surname;
    private Role role;//todo ?

}
