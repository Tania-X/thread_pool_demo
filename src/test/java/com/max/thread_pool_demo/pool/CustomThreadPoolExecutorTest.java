package com.max.thread_pool_demo.pool;

import com.max.thread_pool_demo.ThreadPoolDemoApplicationTests;
import jakarta.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomThreadPoolExecutorTest extends ThreadPoolDemoApplicationTests {

  @Resource
  private CustomThreadPoolExecutor customThreadPoolExecutor;

  private static final Integer CIRCULATION_TIMES = 1000;

  private static final AtomicInteger atomicInteger = new AtomicInteger(0);

  @Test
  public void test() {

    CountDownLatch countDownLatch = new CountDownLatch(CIRCULATION_TIMES);

    for (int i = 0; i < CIRCULATION_TIMES; i++) {
      // variables in lambda expression should be final or effectively final
      String iVal = String.valueOf(i);
      try {
        customThreadPoolExecutor.run(() -> {
//         if (iVal.equals("100")) {
//           throw new RuntimeException();
//         }
          String threadName = Thread.currentThread().getName();
          log.info("Concurrent test " +  iVal + "threadName = " + threadName);
        });
      } catch (Exception e) {
        log.error("error: ", e);
      } finally {
        atomicInteger.incrementAndGet();
        countDownLatch.countDown();
      }
    }

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    log.info("总共执行次数: " + atomicInteger);
  }

}
