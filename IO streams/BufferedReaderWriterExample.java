import java.io.*;  
public class BufferedReaderWriterExample {  
public static void main(String[] args) throws Exception {     
    FileWriter writer = new FileWriter("basic.txt");  
    BufferedWriter buffer = new BufferedWriter(writer);  
    buffer.write("Writing the data into the buffer in the form of string directly");  
    buffer.close();  
    
    
     FileReader fr=new FileReader("basic.txt");    
     BufferedReader br=new BufferedReader(fr);    
  
          int i;    
          while((i=br.read())!=-1){  
          System.out.print((char)i);  
          }  
          br.close();    
          fr.close();    
    System.out.println("successfully done");  
    }  
}  
