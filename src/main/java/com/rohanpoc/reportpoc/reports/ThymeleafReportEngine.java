package com.rohanpoc.reportpoc.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
public class ThymeleafReportEngine {
    private static final Logger log = LoggerFactory.getLogger(ThymeleafReportEngine.class);

    private final TemplateEngine templateEngine;

    public ThymeleafReportEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public <T> ReportResult renderHtml(ReportTemplate<T> template, T data) {
        try {
            Context ctx = new Context(Locale.ENGLISH);
            template.model(data).forEach(ctx::setVariable);
            String html = templateEngine.process(template.templateName(), ctx);
            return new ReportResult.Success(html);
        } catch (Exception e) {
            log.error("Report render failed for template={}", template.templateName(), e);
            return new ReportResult.Failure("REPORT_RENDER_FAILED", e.getMessage());
        }
    }
}
