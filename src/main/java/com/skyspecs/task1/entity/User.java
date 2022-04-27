package com.skyspecs.task1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.jboss.logging.annotations.Field;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name="users")
@FilterDef(name="userFilter", parameters={
        @ParamDef( name="withFirstName", type="String" ),
        @ParamDef( name="withEmail", type="String" )
})
@Filters( {
        @Filter(name="userFilter", condition=":withFistName == firstName"),
        @Filter(name="userFilter", condition=":withEmail == email")
} )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String password;
    private String email;

    @JsonIgnore
    @OneToMany
    @JoinTable(name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")})
    private List<Task> tasks;

    @JsonIgnore
    @ManyToOne
    private Organization organization;


    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

    public void addRole(int i, Role role){
        roles.add(i,role);
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
