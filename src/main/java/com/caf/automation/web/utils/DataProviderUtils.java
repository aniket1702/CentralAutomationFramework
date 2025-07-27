package com.caf.automation.web.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {
private DataProviderUtils(){}

    private static List<Map<String, String>> list = new ArrayList<>();

    @DataProvider(parallel = true)
    public static Object[] getTestData(Method m) {
        String testName = m.getName();

        if (list.isEmpty()) {
            list = ExcelDataReader.readData();
        }

        List<Map<String, String>> filteredList = new ArrayList<>();

        for (Map<String, String> stringStringMap : list) {
            if (stringStringMap.get("Test_Name").equalsIgnoreCase(testName)
                    && (stringStringMap.get("Test_Execute").equalsIgnoreCase("Y"))) {

                filteredList.add(stringStringMap);

            }
        }
        //	list.removeAll(filteredList); ----> use only when necessary
        return filteredList.toArray();

    }


}
