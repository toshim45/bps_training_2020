package com.bps.employee.repository;

import com.bps.employee.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void store(Employee employee){
        String query = "MERGE dbo.employee AS [Target]" +
                " USING (SELECT ? AS id) AS [Source]" +
                " ON [Target].id = [Source].id" +
                " WHEN MATCHED THEN  UPDATE SET [Target].reward = [Target].reward+1, [Target].updated = GetDate()" +
                " WHEN NOT MATCHED THEN  INSERT (reward) VALUES (1);";
        jdbcTemplate.update(query, employee.getId());
        logger.debug("employee {} incremented ", employee.getId());
    }
}
