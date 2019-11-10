package com.lx.common;

import com.lx.coupon.CommonApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class TestApplication {
    @Value("${info}")
    private String author;
    @Test
    public void contextLoads() {
        System.out.println(author);
    }
}
