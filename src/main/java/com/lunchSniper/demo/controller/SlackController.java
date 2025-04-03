package com.lunchSniper.demo.controller;

import com.lunchSniper.demo.entity.CinemaStats;
import com.lunchSniper.demo.repository.CinemaStatsRepository;
import com.lunchSniper.demo.service.SlackService;
import com.lunchSniper.demo.service.MssqlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SlackController {

    private final SlackService slackService;
    private final MssqlService mssqlService;
    private final CinemaStatsRepository cinemaStatsRepository;

    public SlackController(SlackService slackService, MssqlService mssqlService, CinemaStatsRepository cinemaStatsRepository) {
        this.slackService = slackService;
        this.mssqlService = mssqlService;
        this.cinemaStatsRepository = cinemaStatsRepository;
    }

    @GetMapping("/slack/cinema-stats")
    public String sendCinemaStatsToSlack() {
        // H2 DB에서 데이터 조회
        List<CinemaStats> stats = cinemaStatsRepository.findAll();

        // Slack 메시지에 데이터를 추가
        HashMap<String, String> data = stats.stream()
                .collect(Collectors.toMap(
                        CinemaStats::getName,
                        stat -> "WebConnection: " + stat.getWebConnection() + ", TPS: " + stat.getTps(),
                        (a, b) -> a, HashMap::new
                ));

        String title = "롯데시네마 웹 연결 상태 및 TPS 정보";
        slackService.sendMessage(title, data);

        return "Cinema stats sent to Slack.";
    }

    @PostMapping("/slack/events")
    public String handleSlackEvent(@RequestBody Map<String, Object> payload) {
        // Slack payload 처리
        System.out.println("Slack Payload: " + payload);

        // MSSQL 데이터 가져오기
        HashMap<String, String> data = mssqlService.fetchData();

        // Slack에 메시지 전송
        String title = "MSSQL Data Retrieved";
        slackService.sendMessage(title, data);

        return "Data sent to Slack.";
    }

    // 테스트용 메시지 전송 엔드포인트
    @PostMapping("/slack/send-message")
    public ResponseEntity<String> sendMessageToSlack(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "message") String message) {

        try {
            // 데이터를 Slack 필드로 구성
            HashMap<String, String> data = new HashMap<>();
            data.put("Content", message); // 원하는 메시지를 Slack 필드에 추가

            // SlackService를 통해 메시지 전송
            slackService.sendMessage(title, data);

            // 성공 메시지 반환
            return ResponseEntity.ok("Message successfully sent to Slack.");
        } catch (Exception e) {
            // 오류 발생 시 로그 출력 및 사용자에게 에러 응답 반환
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message to Slack. Error: " + e.getMessage());
        }
    }

}
