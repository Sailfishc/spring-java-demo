package com.sailfish.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sailfish
 * @create 2017-05-26-下午10:04
 */
@Service
public class AdvancedDummyExchangeRateCalculator implements ExchangeRateCalculator {

    private static final double BASE_EXCHANGE_RATE = 1.09;
    private int attempts = 0;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private RetryTemplate retryTemplate;


    @Retryable(value=RuntimeException.class,maxAttempts=10,backoff = @Backoff(delay = 5000,multiplier=1.5))
    @Override
    public Double getCurrentRate() {
        System.out.println("Calculating - Attempt " + attempts + " at " + sdf.format(new Date()));
        attempts++;
        throw new RuntimeException("Error");
    }

    @Recover
    public Double recover(RuntimeException e){
        System.out.println("Recovering - returning safe value");
        return BASE_EXCHANGE_RATE;
    }

    public void retryTemplateDemo(){
        // 重试策略
        RetryPolicy retryPolicy = new SimpleRetryPolicy(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        //退避策略：指数退避策略
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(100);
        backOffPolicy.setMaxInterval(3000);
        backOffPolicy.setMultiplier(2);
        backOffPolicy.setSleeper(new ThreadWaitSleeper());
        retryTemplate.setBackOffPolicy(backOffPolicy);

        //当重试失败后，抛出异常
        retryTemplate.execute(new RetryCallback<String, RuntimeException>() {

            @Override
            public String doWithRetry(RetryContext context) throws RuntimeException {
                throw new RuntimeException("timeout");
            }
        }, new RecoveryCallback<String>(){

            @Override
            public String recover(RetryContext context) throws Exception {
                return "default";
            }
        });


    }
}
