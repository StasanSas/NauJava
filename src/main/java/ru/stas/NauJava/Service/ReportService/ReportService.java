package ru.stas.NauJava.Service.ReportService;

public interface ReportService {
    String getContentReport(Long id);
    Long createReport();
    void formationReport(Long id);
}
