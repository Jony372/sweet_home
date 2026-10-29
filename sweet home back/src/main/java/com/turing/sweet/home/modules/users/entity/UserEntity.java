package com.turing.sweet.home.modules.users.entity;

import com.turing.sweet.home.audit.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email = null;

    @Column(unique = true)
    private String phone = null;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_admin")
    private Boolean admin = false;

    public UserEntity() {
    }

    public UserEntity(String name, String lastName, String username, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        if(email != null && !email.isEmpty()) {
            this.email = email;
        }
        if(phone != null && !phone.isEmpty()) {
            this.phone = phone;
        }
    }

    @Override
    public void delete() {
        super.delete();
        this.setUsername(null);
        this.setEmail(null);
        this.setPhone(null);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
