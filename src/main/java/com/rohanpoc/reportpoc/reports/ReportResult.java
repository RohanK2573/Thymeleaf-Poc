package com.rohanpoc.reportpoc.reports;

public interface ReportResult {
    record Success(String html) implements ReportResult {}
    record Failure(String code, String message) implements ReportResult {}
}
