package com.max.thread_pool_demo.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class CustomThreadPoolExecutor {

  private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 30,
      TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new CustomThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

  private static final ThreadPoolExecutor executor2 = new ThreadPoolExecutor(10, 15, 30,
      TimeUnit.SECONDS, new SynchronousQueue<>(), new CustomThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

  public void run(Runnable runnable) throws Exception {

//    executor.execute(runnable);
    executor2.execute(runnable);

  }

}