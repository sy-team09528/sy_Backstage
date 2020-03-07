package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void testRedis(){
        //定义key
        String key = "user_token:48b29393-e0da-4754-ba6c-9580ddc3bfb2";
        //定义value
        Map<String, String> value = new HashMap<>();
        value.put("jwt", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4MTQ1MjQ3MSwianRpIjoiNDhiMjkzOTMtZTBkYS00NzU0LWJhNmMtOTU4MGRkYzNiZmIyIiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.m07BivOUGE7aWu85JIIUGd2hCNal8lTXXgzZUwsJnqP7kHVGr9ePL1NlrfxsPO4tnuUf1SfMbeMRPTnF8Fc5wQJvhg7QvdcgaLduJ-6Ywl22Oo6KeYRwbVVShKoR0ySgrkG9a-4ZJfhiy4gOgxUj4QnNnrHNR2dQxV8I9aCIZAhKzByWpAf43MQjNoO6BmB1VYH77DZYHH-CMNMsWx2AcaYHkiiIUkNmd_n4s1jP5eeQjPUin7C5l2e3P_RaTonWHxxpHvMjjNhmEyBmvogmhlx5dWqM_bNWNsECvsaihpAIvxAInCAIEyruI2VuruqB2tCNwFUwdxpiQeh10Mei0g");
        value.put("refresh_token", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJhdGkiOiI0OGIyOTM5My1lMGRhLTQ3NTQtYmE2Yy05NTgwZGRjM2JmYjIiLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4MTQ1MjQ3MSwianRpIjoiOTQyZTM2NTMtYWUyOC00MjYyLTliMzktZDNhN2NlMjE5MjY4IiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.U2oA0TK2JXKAuunfBTYa5kEqB-Mdy_1wTjJIjEzx5f01QAd6HLRIVnJhVyJfk5G6eldp3nwr4UzckYwsAitNAb5H7g1Tqvbq3W76YFZoyTBauDbaDDo1jWLod-0oTd0H7vwTr78qfuEzy3LiqAf5R5miyeojIaLo81JsLMG1V1rRwAu_-C9NVd4k6NL79HzGNWf8qxd_J53ndu-FB0zkQKe1Yn-L0aOeoBs4JSUDFqilDGJ7_qsx1gdyqBbyIiIYnuzI28pX3LOOrsgjioWuNGXjyGfldYnixRr9Mhw2sqNaxwpd6PM7Psl_KFLRmtyRhoVAtUr1Xu6h4lLFSVG9FQ");
        String jsonString = JSON.toJSONString(value);
        //校验key是否存在，如果不存在，则返回-2
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        System.out.println(expire);
        //存储数据
        stringRedisTemplate.boundValueOps(key).set(jsonString, 30, TimeUnit.SECONDS);
        //获取数据
        String string = stringRedisTemplate.opsForValue().get(key);
        System.out.println(string);
    
    
    }
}
