import com.eason.majiang.object.Tile;
import com.eason.majiang.utils.LamdaUtils;
import com.eason.majiang.utils.LanguageManager;
import org.junit.Test;

import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    public void TestLamada()
    {
      //  IntStream.range(0,10).forEach(x-> System.out.print(x + " "));

        //IntStream.rangeClosed(0,10).boxed().forEach(x->System.out.print(x.getClass().toString()));

       IntStream.rangeClosed(0,10).flatMap(x-> IntStream.of(x)).forEach(x-> System.out.print(x + " "));




    }


    @Test
    public void TestCollections()
    {
       var result = Collections.singleton(1);

       result.stream().forEach(System.out::println);
    }

    @Test
    public void TestObejctNull()
    {
        var a =3;
        Tile t  = null;
        if(a > a+3)
        {
            t = Tile.of(null,3);
        }

        var ra = Objects.requireNonNull(t);

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
