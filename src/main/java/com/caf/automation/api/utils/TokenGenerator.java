package com.caf.automation.api.utils;


import com.caf.automation.api.pojo.LoginDAO;
import com.caf.automation.api.pojo.User;
import com.caf.automation.config.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public final class TokenGenerator
{

    private TokenGenerator(){}
    private static final User registerUser = User.createUser();

    public static String registerAndGetToken(String registerAPI, String loginAPI) {
        registerUser(registerAPI);
        return loginUserAndGetToken(loginAPI);
    }

    private static void registerUser(String registerAPI)
    {

        RestAssured
                .given()
                .header("Content-Type", ContentType.JSON)
                .baseUri(ConfigFactory.getConfig().baseURLs())
                .when()
                .body(registerUser)
                .and()
                .post(registerAPI)
                .then()
                .extract().response();
    }
    private static String loginUserAndGetToken(String loginAPI) {

        LoginDAO loginDAO = new LoginDAO();
        loginDAO.setEmail(registerUser.getEmail());
        loginDAO.setPassword(registerUser.getPassword());

        Response response = RestAssured
                .given()
                .header("Content-Type", ContentType.JSON)
                .baseUri(ConfigFactory.getConfig().baseURLs())
                .when()
                .body(loginDAO)
                .and()
                .post(loginAPI)
                .then()
                .extract().response();

        return response.jsonPath().getJsonObject("data.token");

    }

}
