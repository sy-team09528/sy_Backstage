package com.xuecheng.order.mq;

import com.xuecheng.framework.domain.task.XcTask;
import com.xuecheng.order.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Component
public class ChooseCourseTask {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseTask.class);
    
    @Autowired
    TaskService taskService;
    
    @Scheduled(cron = "0/3 * * * * *")
    //定时发送添加选课任务
    public void sendChoosecourseTask(){
        //得到1分钟之前的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(GregorianCalendar.MINUTE, -1);
        Date time = calendar.getTime();
        List<XcTask> xcTaskList = taskService.findXcTaskList(time, 100);
        System.out.println(xcTaskList);
        //调用service发布消息，将添加选课的任务发给mq
        for (XcTask xcTask : xcTaskList) {
            String ex = xcTask.getMqExchange();//要发送的交换机
            String routingKey = xcTask.getMqRoutingkey();//发送消息要带routingkey
            taskService.publish(xcTask, ex, routingKey);
        }
        
        
        
        
    }
    
    
    
    
    
    //定义任务调度策略
//    @Scheduled(cron = "0/3 * * * * *")//每隔3秒执行
//    @Scheduled(fixedRate = 3000)
    public void task1(){
        LOGGER.info("==========测试定时任务1开始==========");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("==========测试定时任务1结束==========");
        
    }
    
//    @Scheduled(fixedRate = 3000)
    public void task2(){
        LOGGER.info("==========测试定时任务2开始==========");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("==========测试定时任务2结束==========");
        
    }
    
}
