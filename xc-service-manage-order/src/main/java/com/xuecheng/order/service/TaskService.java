package com.xuecheng.order.service;

import com.xuecheng.framework.domain.task.XcTask;
import com.xuecheng.order.dao.XcTaskRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    XcTaskRepository xcTaskRepository;
    
    @Autowired
    RabbitTemplate rabbitTemplate;
    
    //查询前n条任务
    public List<XcTask> findXcTaskList(Date updateTime, int size){
        //设置分页参数
        Pageable pageable = new PageRequest(0, size);
        //查询前n条任务
        Page<XcTask> all = xcTaskRepository.findByUpdateTimeBefore(pageable, updateTime);
        List<XcTask> list = all.getContent();
        return list;
    
    }
    
    
    //发布消息
    public void publish(XcTask xcTask, String ex, String routingKey){
        Optional<XcTask> optional = xcTaskRepository.findById(xcTask.getId());
        if(optional.isPresent()){
            rabbitTemplate.convertAndSend(ex, routingKey, xcTask);
            //更新任务时间
            XcTask one = optional.get();
            one.setUpdateTime(new Date());
            xcTaskRepository.save(one);
        }
        
        
    }
}
