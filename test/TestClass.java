import com.eason.majiang.utils.LamdaUtils;
import com.eason.majiang.utils.LanguageManager;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.nCopies;

public class TestClass {

    @Test
    public void test()
    {
        var person  =  new Person();

        var result = nCopies(5,person);

        AtomicInteger i = new AtomicInteger(10);

        result.stream().forEach(x-> {
            x.setAge(i.getAndIncrement());


        });

        result.stream().forEach(x-> System.out.println(x.getAge()));



        System.out.println(person.getAge());
    }


    @Test
    public void TestEnum()
    {
        System.out.println(Friut.Banana.ordinal());
    }


    @Test
    public void TestLamda()
    {
       var result = LamdaUtils.rethrowConsumer(x->{
           System.out.println(x);
       });

      result.accept("hello world");


    }

    @Test
    public void TestLamda2()
    {
        LanguageManager.Message x = ()->{return "野东西";};
        System.out.println(x.str());
    }

    @Test
    public void TestEnum2()
    {
        System.out.println(Friut.Banana.name());
        System.out.println(Friut.Banana.ordinal());
    }
}
class Person
{
    private String userName;
    private int age;

    Person() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

enum Friut
{
    Apple,
    Orange,
    Banana,
    Grape

}
