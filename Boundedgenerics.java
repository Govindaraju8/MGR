import java.util.*;
public class Boundedgenerics
{
    public static <T extends Number> void method1(List<T> l1)
    {
        for(T ele : l1)
        {
            System.out.println(ele);
        }
    }
    public static void main(String args[])
    {
        //create an list with integer values
        List<Integer> intvallist=new ArrayList<>();
        intvallist.add(10);
        intvallist.add(20);
        intvallist.add(30);
        System.out.println("integer type of values : ");
        method1(intvallist);
        
        // create an list with float values
        List<Float> floatvallist=new ArrayList<>();
        floatvallist.add(10.0f);
        floatvallist.add(20.9f);
        floatvallist.add(30.7f);
        System.out.println("float type of values : ");
        method1(floatvallist);
        
    }
}