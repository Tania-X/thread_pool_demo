package com.max.thread_pool_demo.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    System.out.println("custom rejected execution strategy: " + r.toString());
  }
}
