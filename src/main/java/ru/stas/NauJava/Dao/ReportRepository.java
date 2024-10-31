package ru.stas.NauJava.Dao;

import org.springframework.data.repository.CrudRepository;
import ru.stas.NauJava.Entity.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {

}
