package com.lunchSniper.demo.repository;

import com.lunchSniper.demo.entity.CinemaStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaStatsRepository extends JpaRepository<CinemaStats, Long> {
}
