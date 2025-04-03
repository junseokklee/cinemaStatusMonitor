package com.lunchSniper.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MssqlService {

    private final JdbcTemplate jdbcTemplate;

    public MssqlService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HashMap<String, String> fetchData() {
        String query = "SELECT column1, column2 FROM your_table"; // SQL 쿼리 작성
        List<HashMap<String, String>> results = jdbcTemplate.query(query, (rs, rowNum) -> {
            HashMap<String, String> row = new HashMap<>();
            row.put("column1", rs.getString("column1"));
            row.put("column2", rs.getString("column2"));
            return row;
        });

        // 첫 번째 결과만 반환 (단순화를 위해)
        return results.isEmpty() ? new HashMap<>() : results.get(0);
    }
}
