import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();


    public void addTaskForThread()
    {
        try {
            addTask("helloworld");
        }
        catch (Exception e)
        {

        }

    }

    public void addTask(String str) throws Exception
    {
        while(true)
        {
            lock.lock();
            try {
                queue.add(str);
                System.out.println("queue size is " + queue.size());
                condition.signalAll();
            }
            finally {
                lock.unlock();
            }
            Thread.sleep(5000);
        }
    }

    public String getTask()
    {
        lock.lock();
        try {
             while (queue.isEmpty())
             {
                 condition.await();
                 System.out.println("Reading Task is waiting   ----"+ Thread.currentThread().getName());
             }
            return queue.poll();
        }
        catch (Exception e)
        {
            return null;
        }
        finally {
            lock.unlock();
        }
    }
}
