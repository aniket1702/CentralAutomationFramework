package com.caf.automation.web.driver.entity;


import com.caf.automation.web.enums.Browser;
import com.caf.automation.web.enums.RemoteMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverData
{
    private Browser browserType;
    private RemoteMode remoteModeType;

}
