package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {
    
    @Autowired
    PageService pageService;
    
    @Test
    public void testgetPageHtml(){
        String pageHtml = pageService.getPageHtml("5d633ae706260e35a05edf00");
        System.out.println(pageHtml);
    
    }
    
    
    
    
}
