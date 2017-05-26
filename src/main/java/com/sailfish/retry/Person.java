package com.sailfish.retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author sailfish
 * @create 2017-05-26-下午9:53
 */
@Service
public class Person {

    @Retryable(RemoteAccessException.class)
    public void service() {
        // ... do something
        System.out.println("retry");
        throw new RuntimeException();
    }
    @Recover
    public void recover(RemoteAccessException e) {
        // ... panic
        System.out.println("recover");
    }
}
