package com.wisely.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;  //通过SimpMessagingTemplate向浏览器发送消息

    @MessageMapping("/chat")
    /**
     * 在Spring MVC中,可以直接在参数中获得principal,principal中包含当前用户的信息。
     */
    public void handleChat(Principal principal,String msg){
        /**
         * 这里是一段硬编码,如果发送人是gwf,则发送给jd,如果发送人是jd,则发送给gwf
         */
        if(principal.getName().equals("gwf")){
            /**
             * 根据simpMessagingTemplate.convertAndSendToUser向用户发送消息,
             * 第一个参数是接收消息的用户,
             * 第二个参数是浏览器订阅的地址,
             * 第三个参数是消息本身
             */
            simpMessagingTemplate.convertAndSendToUser("jd","/queue/notifications",principal.getName()+"-send:"+msg);
        }else {
            simpMessagingTemplate.convertAndSendToUser("gwf","/queue/notifications",principal.getName()+"-sebd:"+msg);
        }
    }
}
