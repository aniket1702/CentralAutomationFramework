package com.caf.automation.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;


    private static final Faker faker = new Faker();
    private static final User user = new User();

    private User setPassword()
    {
        user.setPassword(faker.internet().password());
        return this;
    }

    private User setName()
    {
        user.setName(faker.name().firstName());
        return this;
    }

    private User setEmail()
    {
        user.setEmail(faker.internet().emailAddress());
        return this;
    }

    public static User createUser()
    {
        return user.setName().setEmail().setPassword();
    }

}
