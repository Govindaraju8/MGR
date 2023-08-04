// inputoutstream example in the form of byte strream only
import java.util.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
public class FileInputOutputStreamexample
{
    public static void main(String args[])
    {
    try{
        FileOutputStream fout=new FileOutputStream("basic.txt");
       // fin.write("i am writing something to the basic.txt file through fileoutputstream ");
       // i am writing byte orientd data using fileOutputStream
        fout.write(65);
        
        String s="this is an inputoutputstream example using byte stream oriented only";    
        byte b[]=s.getBytes();
        fout.write(b);
        fout.close();
        FileInputStream fin=new FileInputStream("basic.txt");
        int i=fin.read();
        // here i am getting the data in the byte oriented and converts into char oriented in the fileinputStream
        System.out.println((char) i);
        
         int j=0;    
         while((j=fin.read())!=-1){
             
             System.out.print((char)j);    
        }    
        System.out.println();
        fin.close();
        System.out.println("this is fileoutputStream main file");
       }
    catch(Exception e)
    {
        System.out.println(e);
    }
    }
}
