package com.sailfish.retry;

import com.sailfish.JavaDemoApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author sailfish
 * @create 2017-05-26-下午10:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaDemoApplication.class)
public class AdvancedDummyExchangeRateCalculatorTest {

    @Autowired
    private ExchangeRateCalculator exchangeRateCalculator;

    @Test
    public void getCurrentRate() throws Exception {
        exchangeRateCalculator.getCurrentRate();
    }

}