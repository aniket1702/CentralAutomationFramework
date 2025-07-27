package com.caf.automation.annotation;


import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for Allure reporting in web automation tests.
 * Specifies test-related details such as epic, story, description, owner, and severity level.
 *
 * <p>{@code AllureTest} allows defining the following information:
 * <ul>
 *     <li>{@code epic}: Specifies the epic related to the test. Default value: "WEB AUTOMATION - Regression And Smoke Suites".</li>
 *     <li>{@code story}: Specifies the story related to the test.</li>
 *     <li>{@code description}: Specifies a description of the test.</li>
 *     <li>{@code owner}: Specifies the owner of the test. Default value: "Aniket Maurya".</li>
 *     <li>{@code severity}: Specifies the severity level of the test. Default value: {@link SeverityLevel#NORMAL}.</li>
 * </ul>
 *
 * <p>This annotation is used in combination with Allure reporting to provide detailed information
 * about the tests, allowing for better categorization and reporting of test results.
 *
 * @see Epic
 * @see Severity
 * @see SeverityLevel
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Epic("WEB AUTOMATION")
@Severity(SeverityLevel.NORMAL)
public @interface AllureTest {

    /**
     * Specifies the epic related to the test.
     *
     * @return The epic related to the test.
     */
    String epic() default "WEB AUTOMATION - Regression And Smoke Suites";

    /**
     * Specifies the story related to the test.
     *
     * @return The story related to the test.
     */
    String story() default "";

    /**
     * Specifies a description of the test.
     *
     * @return The description of the test.
     */
    String description() default "";


    /**
     * Specifies the owner of the test.
     *
     * @return The owner of the test.
     */
    String owner() default "Aniket Maurya";


    /**
     * Specifies the severity level of the test.
     *
     * @return The severity level of the test.
     */
    SeverityLevel severity() default SeverityLevel.NORMAL;


}
