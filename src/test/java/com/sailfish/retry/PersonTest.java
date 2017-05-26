package com.sailfish.retry;

import com.sailfish.JavaDemoApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author sailfish
 * @create 2017-05-26-下午9:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaDemoApplication.class)
public class PersonTest {

    @Autowired
    private Person person;


    @Test
    public void service() throws Exception {
        person.service();
    }

    @Test
    public void recover() throws Exception {

    }

}