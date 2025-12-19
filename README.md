# Thymeleaf Report POC (Java 17 + Spring Boot)

This is a senior-style POC for generating **HTML reports** using **Thymeleaf** with a reusable, extensible architecture.

## What you get
- A reusable `ThymeleafReportEngine` (doesn't change when new reports are added)
- A `ReportTemplate<T>` contract (new report = implement this + add HTML)
- Java 17 features:
  - `record` models
  - `sealed` result type + `switch` handling
- Endpoints:
  - `GET /reports/invoice/html` -> renders invoice HTML
  - `GET /reports/invoice/download` -> downloads invoice HTML as a file

## Run
```bash
mvn spring-boot:run
```

## Try
- http://localhost:8080/reports/invoice/html
- http://localhost:8080/reports/invoice/download

## Add a new report
1) Create a new data model (record/class)
2) Create a Thymeleaf template under `src/main/resources/templates/<name>.html`
3) Implement `ReportTemplate<YourModel>` and return:
   - `templateName()` -> `<name>`
   - `model(data)` -> Map of template variables
4) Add a controller endpoint (or reuse a generic one)
