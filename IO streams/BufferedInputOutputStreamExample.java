  
import java.io.*;  
public class BufferedInputOutputStreamExample{    
    public static void main(String args[]) throws Exception{  
    
     FileOutputStream fout=new FileOutputStream("basic.txt");    
     //here i am creating buffer to store our data as a local
     BufferedOutputStream bout=new BufferedOutputStream(fout);    
     String s="writing the data into the buffer in the form of bytes only";    
     byte b[]=s.getBytes();    
     bout.write(b);    
     bout.flush();    
     bout.close();    
     fout.close();    
     
     
    FileInputStream fin=new FileInputStream("basic.txt");    
    BufferedInputStream bin=new BufferedInputStream(fin);    
    int i;    
    while((i=bin.read())!=-1){    
     System.out.print((char)i);    
    }    
    bin.close();    
    fin.close();    
     System.out.println("success completed");    
}    
}  
