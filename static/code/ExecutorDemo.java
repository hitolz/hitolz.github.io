import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorDemo {
    public static void main(String[] args) {
        // 创建数量不固定的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建数量固定的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        // 创建单线程的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 创建定时执行的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool();

    }
}
