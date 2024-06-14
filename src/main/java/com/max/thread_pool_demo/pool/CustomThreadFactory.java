package com.max.thread_pool_demo.pool;

import jakarta.annotation.Nonnull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

  final AtomicInteger threadNumber = new AtomicInteger( 1 );

  @Override
  public Thread newThread(@Nonnull Runnable r) {
    return new Thread(r, "Thread No : " + threadNumber.getAndIncrement());
  }
}
