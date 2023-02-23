package com.charley.servicessepush.controller;

import com.charley.internalcommon.util.SseprefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Charley_Zhang
 */
@RestController
@Slf4j
public class SseController {

    public static Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    /**
     * 建立链接
     * @param userId
     * @param identity
     * @return
     */
    @GetMapping(value = "/connect")
    public SseEmitter connect(@RequestParam Long userId, @RequestParam String identity){
        log.info("用户id：" + userId + "， 身份类型："+identity);
        SseEmitter sseEmitter = new SseEmitter(0L);
        String sseMapKey = SseprefixUtils.generatorSseKey(userId,identity);
        sseEmitterMap.put(sseMapKey, sseEmitter);
        return sseEmitter;
    }


    /**
     * 发送消息
     * @param userId 消息接收者
     * @param identity 身份类型
     * @param content 消息具体的内容
     * @return
     */
    @GetMapping(value = "/push")
    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content){
        log.info("用户id："+userId + "，身份："+identity+"消息："+content);
        String sseMapKey = SseprefixUtils.generatorSseKey(userId,identity);
        try {
            if (sseEmitterMap.containsKey(sseMapKey)) {
                sseEmitterMap.get(sseMapKey).send(content);
            }else {
                return "推送失败";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "给用户：" + sseMapKey + "发送了消息：" + content;
    }

    /**
     * 关闭链接
     * @param userId 用户id
     * @param identity  身份标识
     * @return
     */
    @GetMapping(value = "/close")
    public String close(@RequestParam Long userId, @RequestParam String identity){
        String sseMapKey = SseprefixUtils.generatorSseKey(userId,identity);
        log.info("close方法执行"  + sseMapKey);
        if (sseEmitterMap.containsKey(sseMapKey)){
            sseEmitterMap.remove(sseMapKey);
        }
        return "移除成功";
    }

}
