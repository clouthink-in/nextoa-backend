package in.clouthink.synergy.team.engine;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @auther dz
 */
public class TestAkka {
//    public static void main(String[] args) {
//        FutureTask<Integer> future = new FutureTask<>(() -> new Random().nextInt(100));
//        new Thread(future).start();
//        try {
//            Thread.sleep(1000);// 可能做一些事情
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        threadPool.execute(() -> {
//
//            int result = new Random().nextInt(100);
//            System.out.println("result:" + result);
//        });
//        Future<Integer> future = threadPool.submit(() -> {
//            Thread.sleep(2000);// 可能做一些事情
//                                                       int result = new Random().nextInt(100);
//                                                       System.out.println("result:" + result);
//                                                       return result;
//                                                   }
//        );
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        Callable<Integer> func = new Callable<Integer>() {
//            public Integer call() throws Exception {
//                Thread.sleep(1000);
//                System.out.println("inside callable");
//                Thread.sleep(1000);
//                return new Integer(8);
//            }
//        };
//        FutureTask<Integer> futureTask = new FutureTask<>(func);
//        Thread newThread = new Thread(futureTask);
//        newThread.start();
//
//        try {
//            System.out.println("blocking here");
//            Integer result = futureTask.get();
//            System.out.println(result);
//        } catch (InterruptedException ignored) {
//        } catch (ExecutionException ignored) {
//        }
//    }

//    public static void main(String[] args) {
//        MyCallable callable1 = new MyCallable(1000);                       // 要执行的任务
//        MyCallable callable2 = new MyCallable(5000);
//
//        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);// 将Callable写的任务封装到一个由执行者调度的FutureTask对象
//        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);        // 创建线程池并返回ExecutorService实例
//        executor.execute(futureTask1);  // 执行任务
//        executor.execute(futureTask2);
//
//        while (true) {
//            try {
//                if(futureTask1.isDone() && futureTask2.isDone()){//  两个任务都完成
//                    System.out.println("Done");
//                    executor.shutdown();                          // 关闭线程池和服务
//                    return;
//                }
//
//                if(!futureTask1.isDone()){ // 任务1没有完成，会等待，直到任务完成
//                    System.out.println("Waiting for FutureTask1 to complete");
//                    System.out.println("FutureTask1 output="+futureTask1.get());
//                }
//
//                System.out.println("Waiting for FutureTask2 to complete");
//                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
//                if(s !=null){
//                    System.out.println("FutureTask2 output="+s);
//                }
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }catch(TimeoutException e){
//                //do nothing
//            }
//        }
//    }
//
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Future<String> future = executor.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println("call");
//                TimeUnit.SECONDS.sleep(1);
//                return "str";
//            }
//        });
//        System.out.println("waiting for done");
//        System.out.println(future.get());
//        executor.shutdown();
//    }


//    public static void main(String args[]) {
//        System.out.println("start");
//
//        ExecutorService executor = Executors.newCachedThreadPool();
//
//        Future<String> future = executor.submit(() -> { //Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
//            System.out.println("running task");
//            Thread.sleep(10000);
//            return "return task";
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
//
//        System.out.println("do something else");  //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情
//
//        try {
//            System.out.println(future.get(500, TimeUnit.MICROSECONDS));  //等待 future 的执行结果，执行完毕之后打印出来
//        } catch (InterruptedException e) {
//        } catch (ExecutionException e) {
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        } finally {
//            executor.shutdown();
//        }
//    }

//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        try {
//            Future future = executor.submit(new CallableTask());
//            future.get(3000, TimeUnit.MILLISECONDS);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        } finally {
//            executor.shutdown();
//        }
//    }

    public static void main(String[] args) {

    }

}

class CallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("CallableTask-call，查询分公司利润，执行开始！" + new Date());
        Thread.sleep(2000);
        System.out.println("CallableTask-call，查询分公司利润，执行完毕！" + new Date());
        return 10;
    }
}

class MyCallable implements Callable<String> {
    private long waitTime;

    public MyCallable(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }

}