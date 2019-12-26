import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
    public static void main(String[] args) {
        // 创建数量不固定的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建数量固定的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        // 创建单线程的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 创建定时执行的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

        // newCachedThreadPool 源码
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        // newFixedThreadPool 源码
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        // newSingleThreadExecutor源码
        // 封装成FinalizableDelegatedExecutorService
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        // newScheduledThreadPool 源码
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);


        Future<?> future = cachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        future.get();

    }
}
