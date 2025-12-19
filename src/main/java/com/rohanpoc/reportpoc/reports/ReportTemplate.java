package com.rohanpoc.reportpoc.reports;

import java.util.Map;

public interface ReportTemplate<T> {
    String templateName();
    Map<String, Object> model(T data);
}
