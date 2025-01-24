package com.example.board.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {
//    locback객체 만드는 방법1.
//    logback객체 만드는 방법 2.
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    @GetMapping("/log/test")
    public String logTest(){
//        기존의 시스템 프린트는 실무에서 사용하지 않음
//        1. 성능이 좋지 않음  2. 로그 분류 작업이 불가 /로그는 디버깅을 위해 남김
//
        System.out.println("시스템 프린트 로그");
//        로그레벨:trace < debug < info < error
        logger.trace("trace로그입니다.");
        logger.debug("debug로그입니다.");
        logger.info("info로그입니다.");
        logger.error("error로그입니다.");

        log.info("slf4j 테스트입니다.");
        log.error("slf4j error로그 테스트입니다.");
//        error로그는 에러가 터졌을 때 사용 info는 정ㅂ성 로그 출력시 사용. debug는 테스트 목적으로 사용
        try {
            log.info("에러 테스트 시작");
            log.debug("이재석 테스트 중입니다.");
            throw new RuntimeException("에러 테스트");
        }catch (RuntimeException e){
            log.error(e.getMessage());
        }




        return "ok";
    }

}
