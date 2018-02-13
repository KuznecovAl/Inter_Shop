package main_pack.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class User {

    private long id;
    private String login;
    private String password;
    private String name;
    private String last_name;
    private String privilege;
    private String email;
    private String phone;
    private String address_city;
    private String address_street;
    private String address_building;
    private String address_flat;
    private String address_index;
    private String lang;
    private String status;
    private LocalDate birthday;


    public User(String login, String password, String name, String last_name, String privilege,
                String email, String phone, String address_city, String address_street, String address_building,
                String address_flat, String address_index, String lang, String status, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.privilege = privilege;
        this.email = email;
        this.phone = phone;
        this.address_city = address_city;
        this.address_street = address_street;
        this.address_building = address_building;
        this.address_flat = address_flat;
        this.address_index = address_index;
        this.lang = lang;
        this.status = status;
        this.birthday = birthday;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
