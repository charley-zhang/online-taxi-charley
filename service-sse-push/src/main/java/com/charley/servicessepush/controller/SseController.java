package com.charley.servicessepush.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class SseController {

    public static Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    /**
     * 建立链接
     * @param driverId
     * @return
     */
    @GetMapping(value = "/connect/{driverId}")
    public SseEmitter connect(@PathVariable String driverId){
        log.info("司机Id：" + driverId);
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitterMap.put(driverId, sseEmitter);
        return sseEmitter;
    }


    /**
     * 发送消息
     * @param driverId 消息接收者
     * @param content 消息具体的内容
     * @return
     */
    @GetMapping(value = "/push")
    public String push(@RequestParam String driverId, @RequestParam String content){

        try {
            if (sseEmitterMap.containsKey(driverId)) {
                sseEmitterMap.get(driverId).send(content);
            }else {
                return "推送失败";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "给用户：" + driverId + "发送了消息：" + content;
    }

    /**
     * 关闭链接
     * @param driverId
     * @return
     */
    @GetMapping(value = "/close/{driverId}")
    public String close(@PathVariable String driverId){
        log.info("close方法执行"  + driverId);
        if (sseEmitterMap.containsKey(driverId)){
            sseEmitterMap.remove(driverId);
        }
        return "移除成功";
    }

}
