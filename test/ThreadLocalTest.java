public class ThreadLocalTest {
    private static int Count = 1;

    public void addCount()
    {
        Count++;
    }

    public int getCount()
    {
        return Count;
    }
}
