import java.awt.Frame;
import java.awt.*;

public class WindowFrame {
    public static void main(String[] args) {
        Frame frame = new Frame("AWT FRAME"); 

        frame.setSize(900, 700); 
        frame.setVisible(true); 

        Label label1 = new Label("Emp No:");
	Label label2 = new Label("Name:");
	Label label3 = new Label("Job:");
	Label label4 = new Label("Salary:");
	Label label5 = new Label("Department:");

        label1.setBounds(100,100,200,20);
	label2.setBounds(100,150,200,20);
	label3.setBounds(100,200,200,20);
	label4.setBounds(100,250,200,20);
	label5.setBounds(100,300,200,20);
     
        frame.setLayout(null);
        frame.add(label1); 
	frame.add(label2); 
	frame.add(label3); 
	frame.add(label4); 
	frame.add(label5); 

	TextField textField1 = new TextField();
	TextField textField2 = new TextField();
	TextField textField3 = new TextField();
	TextField textField4 = new TextField();
	Choice choice1=new Choice();

	textField1.setBounds(200,100,200,20);
	textField2.setBounds(200,150,200,20);
	textField3.setBounds(200,200,200,20);
	textField4.setBounds(200,250,200,20);
	choice1.setBounds(200,300,200,20);

	choice1.add("Software Development");
	choice1.add("Finance");
	choice1.add("Quality Assurance");
	choice1.add("Human Resource");
	
	frame.add(textField1);
	frame.add(textField2);
	frame.add(textField3);
	frame.add(textField4);
	frame.add(choice1);
	
	Button button1 = new Button("First");
	Button button2 = new Button("Next");
	Button button3 = new Button("Prev");
	Button button4 = new Button("Last");
	Button button5 = new Button("Add");
	Button button6 = new Button("Edit");
	Button button7 = new Button("Delete");
	Button button8 = new Button("Save");
	Button button9 = new Button("Search");
	Button button10 = new Button("Clear");
	Button button11 = new Button("Exit");

	button1.setBounds(100,400,100,20);
	button2.setBounds(200,400,100,20);
	button3.setBounds(300,400,100,20);
	button4.setBounds(400,400,100,20);
	button5.setBounds(500,400,100,20);
	button6.setBounds(600,400,100,20);
	button7.setBounds(150,450,100,20);
	button8.setBounds(250,450,100,20);
	button9.setBounds(350,450,100,20);
	button10.setBounds(450,450,100,20);
	button11.setBounds(550,450,100,20);

	frame.add(button1);
	frame.add(button2);
	frame.add(button3);
	frame.add(button4);
	frame.add(button5);
	frame.add(button6);
	frame.add(button7);
	frame.add(button8);
	frame.add(button9);	
	frame.add(button10);
	frame.add(button11);
	
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0); 
            }
        });
    }
}