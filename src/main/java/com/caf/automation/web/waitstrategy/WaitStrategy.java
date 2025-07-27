package com.caf.automation.web.waitstrategy;


import com.caf.automation.web.driver.DriverManager;
import com.caf.automation.web.enums.WaitType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class WaitStrategy {

    private WaitStrategy() {
    }

    private static final Map<WaitType, Function<By, Function<WebDriver, WebElement>>> MAP = new EnumMap<>(WaitType.class);
    private static final Map<WaitType, Function<By, Function<WebDriver, List<WebElement>>>> LIST_MAP = new EnumMap<>(WaitType.class);

    static {
        MAP.put(WaitType.CLICKABLE, ExpectedConditions::elementToBeClickable);
        MAP.put(WaitType.VISIBLE, ExpectedConditions::visibilityOfElementLocated);
        MAP.put(WaitType.PRESENCE, ExpectedConditions::presenceOfElementLocated);
        MAP.put(WaitType.NONE, by -> driver -> driver.findElement(by));

        LIST_MAP.put(WaitType.VISIBLE, ExpectedConditions::visibilityOfAllElementsLocatedBy);
    }

    public static WebElement performExplicitWait(WaitType waitType, By by) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Function<By, Function<WebDriver, WebElement>> conditionFactory = MAP.get(waitType);

        if (conditionFactory == null) {
            throw new IllegalArgumentException("Invalid wait type: " + waitType);
        }
        Function<WebDriver, WebElement> condition = conditionFactory.apply(by);

        if (condition == null) {
            return driver.findElement(by);
        }


        return wait.until(condition);
    }


    public static List<WebElement> performExplicitWaitOnListOfWebElements(WaitType waitType, By by) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Function<By, Function<WebDriver, List<WebElement>>> conditionFactory = LIST_MAP.get(waitType);

        if (conditionFactory == null) {
            throw new IllegalArgumentException("Invalid wait type: " + waitType);
        }
        Function<WebDriver, List<WebElement>> condition = conditionFactory.apply(by);

        if (condition == null) {
            return driver.findElements(by);
        }


        return wait.until(condition);
    }
}
