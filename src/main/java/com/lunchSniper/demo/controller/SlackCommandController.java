package com.lunchSniper.demo.controller;

import com.lunchSniper.demo.entity.CinemaStats;
import com.lunchSniper.demo.repository.CinemaStatsRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/slack")
public class SlackCommandController {

    private final CinemaStatsRepository cinemaStatsRepository;

    public SlackCommandController(CinemaStatsRepository cinemaStatsRepository) {
        this.cinemaStatsRepository = cinemaStatsRepository;
    }

    @PostMapping("/command")
    public String handleSlackCommand(@RequestParam String command, @RequestParam String text) {
        if (command.equals("/get-data")) {
            // H2 DB에서 데이터 조회
            List<CinemaStats> stats = cinemaStatsRepository.findAll();

            // 데이터 구성
            String response = stats.stream()
                    .map(stat -> stat.getName() + ": WebConnection=" + stat.getWebConnection() + ", TPS=" + stat.getTps())
                    .collect(Collectors.joining("\n"));

            return "롯데시네마 데이터:\n" + response;
        }
        return "명령어를 잘못 입력하셨습니다.";
    }
}
