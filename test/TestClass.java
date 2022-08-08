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
