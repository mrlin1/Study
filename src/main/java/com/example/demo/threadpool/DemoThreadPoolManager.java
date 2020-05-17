package com.example.demo.threadpool;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;

@Component
public class DemoThreadPoolManager {

    // 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 2;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 10;

    /**
     * 拒绝队列
     */
    Queue<Object> rejectQueue = new LinkedBlockingQueue<>();

    /**
     * 拒绝策略
     */
    final RejectedExecutionHandler handler = (Runnable r, ThreadPoolExecutor executor) -> {

        //do something

        if (r instanceof Thread) {

        } else if (r instanceof Callable) {

        }

        throw new RejectedExecutionException();
    };


    /**
     * 创建线程池
     */
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), new DemoThreadFactory(), this.handler);

    /**
     * 将任务加入线程池
     */
    public void addThread() {
        DemoThread thread = new DemoThread();
        try {
            threadPool.execute(thread);
        } catch (RejectedExecutionException e) {
            //拒绝策略
        }
    }

    /**
     * 需要返回结果的
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public Optional<String> addThread4Result() {

        DemoThread thread = new DemoThread();
        Optional<String> opt = Optional.empty();

        try {
            Future<String> submit = threadPool.submit((Callable<String>) thread);

            long startTime = System.currentTimeMillis();
            while (true) {
                if (submit.isDone()) {
                    opt = Optional.of(submit.get());
                    break;
                }
                // 半个小时
                if (System.currentTimeMillis() - startTime > 1800000) {
                    throw new TimeoutException("Execution timeout in thread-pool");
                }

                Thread.sleep(60000);
            }

        } catch (RejectedExecutionException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return opt;
    }

    /**
     * 线程池的定时任务----> 称为(调度线程池)。此线程池支持 定时以及周期性执行任务的需求。
     */
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    /**
     * 检查(调度线程池)，每秒执行一次，查看订单的缓冲队列是否有 订单记录，则重新加入到线程池
     */
    final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
        //判断缓冲队列是否存在记录
        //当线程池的队列容量少于WORK_QUEUE_SIZE，则开始把缓冲队列的订单 加入到 线程池
        while (!rejectQueue.isEmpty() && threadPool.getQueue().size() < WORK_QUEUE_SIZE) {
            String str = (String) rejectQueue.poll();
            DemoThread businessThread = new DemoThread();
            threadPool.execute(businessThread);
        }
    }, 0, 1, TimeUnit.SECONDS);

}
