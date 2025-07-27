package com.caf.automation.listeners;

import com.caf.automation.web.utils.ExcelDataReader;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {
    private final ExcelDataReader excelDataReader;

    public MethodInterceptor() {
        this.excelDataReader = new ExcelDataReader();
    }

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

        List<IMethodInstance> result = new ArrayList<>();
        List<Map<String,String>> testDataList = ExcelDataReader.readData();
        if (methods != null)
        {
            for (IMethodInstance method : methods) {
                for (Map<String, String> stringStringMap : testDataList) {
                    if (method.getMethod().getMethodName().equalsIgnoreCase(stringStringMap.get("Test_Name"))) {
                        result.add(method);
                    }
                }
            }
        }
        return result;
    }
}
