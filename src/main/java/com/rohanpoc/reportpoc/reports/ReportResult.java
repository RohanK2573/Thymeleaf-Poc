package com.rohanpoc.reportpoc.reports;

public sealed interface ReportResult permits ReportResult.Success, ReportResult.Failure {
    record Success(String html) implements ReportResult {}
    record Failure(String code, String message) implements ReportResult {}
}
