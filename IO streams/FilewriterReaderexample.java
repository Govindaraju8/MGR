import java.io.*;
import java.io.FileWriter;
public class FilewriterReaderexample
{
    public static void main(String args[])
    {
        try{
            FileWriter fw=new FileWriter("basic.txt");
            // here filewriter class is used to direct writethe character orinted data 
            fw.write("here i am writing the data in the form of character oriented(string) using filewriter");
            fw.close();
            
            // filereader class gets the character oriented data from the given file
            FileReader fr=new FileReader("basic.txt");
            int i=0;
            // filereader class returns data in the form of byte format we have to typecast to char type
            while((i=fr.read())!=-1)
            {
                System.out.print((char)i);
            }
            fr.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
