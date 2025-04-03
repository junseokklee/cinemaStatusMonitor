package com.lunchSniper.demo.config;

import com.lunchSniper.demo.entity.CinemaStats;
import com.lunchSniper.demo.repository.CinemaStatsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CinemaStatsRepository repository) {
        return args -> {
            repository.save(new CinemaStats(null, "롯데시네마 서울", "192.168.1.10", 120));
            repository.save(new CinemaStats(null, "롯데시네마 부산", "192.168.1.11", 130));
            repository.save(new CinemaStats(null, "롯데시네마 대구", "192.168.1.12", 100));
            repository.save(new CinemaStats(null, "롯데시네마 광주", "192.168.1.13", 140));
            repository.save(new CinemaStats(null, "롯데시네마 제주", "192.168.1.14", 110));
        };
    }
}
