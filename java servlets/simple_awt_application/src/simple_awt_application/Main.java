package simple_awt_application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.*;

class Employee {
	private String Empno;
	private String Empname;
	private String job;
	private String Salary;
	private String department;

	public Employee(String empno, String empname, String job, String salary, String department) {
		super();
		Empno = empno;
		Empname = empname;
		this.job = job;
		Salary = salary;
		this.department = department;
	}

	public String getEmpno() {
		return Empno;
	}

	public void setEmpno(String empno) {
		Empno = empno;
	}

	public String getEmpname() {
		return Empname;
	}

	public void setEmpname(String empname) {
		Empname = empname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}

class position {
	private int index;

	position(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

public class Main throws Exception{
	static String flag = "empty";

	public static boolean isString(String input) {
	    for (char c : input.toCharArray()) {
	        if (!Character.isLetter(c)) {
	            return false; 
	        }
	    }
	    return true;
	}

	public static boolean validateT1(String input) {
	    if (!Character.isLetter(input.charAt(0))) {
	        return false;
	    }

	    for (int i = 1; i < input.length(); i++) {
	        if (!Character.isDigit(input.charAt(i))) {
	            return false; 
	        }
	    }
	    return true; 
	}

	public static boolean validateDoubleValue(String input) {
	    try {
	        Double.parseDouble(input);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

	public static void main(String args[]) throws IOException
	{
		Frame f=new Frame();
		Color backgroundColor = new Color(220, 220, 220);
	    f.setBackground(backgroundColor);
		f.setVisible(true);
		f.setSize(800,800); 
		f.setTitle("Employee Details Form");
		f.setLayout(null);
		f.setResizable(true);
		Label l1 = new Label("Employee Details Form");
		f.add(l1);
		l1.setBounds(400, 50, 200, 30);

		Label l2 = new Label("Employee No");
		f.add(l2);
		l2.setBounds(350, 100, 100, 30);

		TextField t1 = new TextField(15);
		f.add(t1);
		t1.setBounds(450, 100, 150, 30);

		Label l3 = new Label("Employee Name");
		f.add(l3);
		l3.setBounds(350, 150, 100, 30);

		TextField t2 = new TextField(15);
		f.add(t2);
		t2.setBounds(450, 150, 150, 30);

		Label l4 = new Label("Job Designation");
		f.add(l4);
		l4.setBounds(350, 200, 100, 30);

		TextField t3 = new TextField(15);
		f.add(t3);
		t3.setBounds(450, 200, 150, 30);

		Label l5 = new Label("Salary");
		f.add(l5);
		l5.setBounds(350, 250, 100, 30);

		TextField t4 = new TextField(15);
		f.add(t4);
		t4.setBounds(450, 250, 150, 30);

		Label l6 = new Label("Department");
		f.add(l6);
		l6.setBounds(350, 300, 100, 30);

		TextField t5 = new TextField(15);
		f.add(t5);
		t5.setBounds(450, 300, 150, 30);

		Choice choice = new Choice();
        choice.add("View");
        choice.add("Add");
        choice.add("Del");
        choice.add("Edit");
        f.add(choice);
        choice.setBounds(650, 100, 100, 30);
        
		Button b1 = new Button("First");
		f.add(b1);
		b1.setBounds(250, 400, 80, 30);

		Button b2 = new Button("Next");
		f.add(b2);
		b2.setBounds(350, 400, 80, 30);

		Button b3 = new Button("Prev");
		f.add(b3);
		b3.setBounds(450, 400, 80, 30);

		Button b4 = new Button("Last");
		f.add(b4);
		b4.setBounds(550, 400, 80, 30);

		Button b5 = new Button("Add");
		f.add(b5);
		b5.setBounds(250, 450, 80, 30);

		Button b6 = new Button("Edit");
		f.add(b6);
		b6.setBounds(350, 450, 80, 30);

		Button b7 = new Button("Del");
		f.add(b7);
		b7.setBounds(450, 450, 80, 30);

		Button b8 = new Button("Save");
		f.add(b8);
		b8.setBounds(550, 450, 80, 30);

		Button b9 = new Button("Search");
		f.add(b9);
		b9.setBounds(250, 500, 80, 30);

		Button b10 = new Button("Clear");
		f.add(b10);
		b10.setBounds(350, 500, 80, 30);

		Button b11 = new Button("Exit");
		f.add(b11);
		b11.setBounds(450, 500, 80, 30);
	    
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
	    
		ArrayList<Employee> emplist=new ArrayList<>();
		//Employee emp=new Employee();
		FileReader fr=new FileReader("D:\\Employees.txt");
		BufferedReader br=new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
	        StringTokenizer st = new StringTokenizer(line, ",");
	        Employee emp = new Employee("","","","","");
	        if (st.hasMoreTokens()) {
	            emp.setEmpno(st.nextToken());
	        }
	        if (st.hasMoreTokens()) {
	            emp.setEmpname(st.nextToken());
	        }
	        if (st.hasMoreTokens()) {
	            emp.setJob(st.nextToken());
	        }
	        if (st.hasMoreTokens()) {
	            emp.setSalary(st.nextToken());
	        }
	        if (st.hasMoreTokens()) {
	            emp.setDepartment(st.nextToken());
	        }  
	        emplist.add(emp);
	    }
		if(emplist.size()>0)
		{
			t1.setText(emplist.get(0).getEmpno());
			t2.setText(emplist.get(0).getEmpname());
			t3.setText(emplist.get(0).getJob());
			t4.setText(emplist.get(0).getSalary());
			t5.setText(emplist.get(0).getDepartment());
		}
		//first button
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(emplist.size()>0)
				{
					t1.setText(emplist.get(0).getEmpno());
					t2.setText(emplist.get(0).getEmpname());
					t3.setText(emplist.get(0).getJob());
					t4.setText(emplist.get(0).getSalary());
					t5.setText(emplist.get(0).getDepartment());
				}
				
			}
		});
		
		//next button
		position p=new position(0);
        b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int i=p.getIndex()+1;
					if (i <= emplist.size() - 1) {
			            t1.setText(emplist.get(i).getEmpno());
			            t2.setText(emplist.get(i).getEmpname());
			            t3.setText(emplist.get(i).getJob());
			            t4.setText(emplist.get(i).getSalary());
			            t5.setText(emplist.get(i).getDepartment());
			        } 
					else {
			            
			            i = 0;
			            t1.setText(emplist.get(i).getEmpno());
			            t2.setText(emplist.get(i).getEmpname());
			            t3.setText(emplist.get(i).getJob());
			            t4.setText(emplist.get(i).getSalary());
			            t5.setText(emplist.get(i).getDepartment());
			        }
			        p.setIndex(i);		
			}
		});
        
        //prev button
        b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int i=p.getIndex()-1;
					if(i<0)
					{
						JOptionPane.showMessageDialog(f,"you have reached the first position");
					}
					else
					{
						t1.setText(emplist.get(i).getEmpno());
						t2.setText(emplist.get(i).getEmpname());
						t3.setText(emplist.get(i).getJob());
						t4.setText(emplist.get(i).getSalary());
						t5.setText(emplist.get(i).getDepartment());
						p.setIndex(i);
					}
					
			}
		});
		//Last button
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setIndex(emplist.size()-1);
				int i=p.getIndex();
				if(i>0)
				{
					t1.setText(emplist.get(i).getEmpno());
					t2.setText(emplist.get(i).getEmpname());
					t3.setText(emplist.get(i).getJob());
					t4.setText(emplist.get(i).getSalary());
					t5.setText(emplist.get(i).getDepartment());
				}
				
			}
		});
		
		//Add button
		b5.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if(!choice.getSelectedItem().equals("Add")) {
					JOptionPane.showMessageDialog(f, "Not in Add Mode");
					return;
				}
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				flag="add";
				
			}
		});
		
		
		//edit button
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!choice.getSelectedItem().equals("Edit")) {
					JOptionPane.showMessageDialog(f, "Not in Edit Mode");
					return;
				}
				flag="edit";
			}
		});
	
		//delete button
        b7.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				if(!choice.getSelectedItem().equals("Del")) {
					JOptionPane.showMessageDialog(f, "Not in Delete Mode");
					return;
				}
				int i=p.getIndex();
				emplist.remove(i);
				try
				{
		            FileWriter fw = new FileWriter("D:\\Employees.txt");
		            BufferedWriter bw = new BufferedWriter(fw);

		            for (Employee emp : emplist) {
		                bw.write(emp.getEmpno() + ", " +
		                        emp.getEmpname() + ", " +
		                        emp.getJob() + ", " +
		                        emp.getSalary() + ", " +
		                        emp.getDepartment());
		                bw.newLine();
		            }

		            bw.close();
		            fw.close();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
			}
		});
        
        //save button
        b8.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(flag!="add" && flag!="edit")
				{
					JOptionPane.showMessageDialog(f, "fill the details before save");
				}
				else if(flag=="add")
				{
					if (!validateT1(t1.getText())) {
			            JOptionPane.showMessageDialog(f, "Invalid employee number. It must start with a letter and be followed by digits.");
			     
			        }

					else if (!isString(t2.getText())) {
						JOptionPane.showMessageDialog(f, "Invalid employee name");

					}

					else if (!isString(t3.getText())) {
						JOptionPane.showMessageDialog(f, "It must be a string.");
					
					}
					else if(!validateDoubleValue(t4.getText())) {
						JOptionPane.showMessageDialog(f, "Invalid salary value");
					}
					else if (!isString(t5.getText())) {
						JOptionPane.showMessageDialog(f, "Invalid department");
				
					}
					else if((t1.getText().length() > 0) && (t2.getText().length() > 0) && 
							(t3.getText().length() > 0) && (t4.getText().length() > 0) && (t5.getText().length() > 0))
					{
					    Employee empp = new Employee("", "", "", "", "");
						empp.setEmpno(t1.getText());
						empp.setEmpname(t2.getText());
						empp.setJob(t3.getText());
						empp.setSalary(t4.getText());
						empp.setDepartment(t5.getText());
						emplist.add(empp);
						p.setIndex(emplist.size()-1);
						try
						{
							FileWriter fw = new FileWriter("D:\\Employees.txt");
							BufferedWriter bw = new BufferedWriter(fw);

							for (Employee emp : emplist) {
								bw.write(emp.getEmpno() + ", " +
										emp.getEmpname() + ", " +
										emp.getJob() + ", " +
										emp.getSalary() + ", " +
										emp.getDepartment());
								bw.newLine();
							}

							bw.close();
							fw.close();
							JOptionPane.showMessageDialog(f, "Data saved successfully!");
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f, "empty fields to be filled");
						
					}
					
				}
				else if(flag.equals("edit"))
				{
					if (!validateT1(t1.getText())) {
			            JOptionPane.showMessageDialog(f, "Invalid employee number. It must start with a letter and be followed by digits.");
			  
			        }

					else if (!isString(t2.getText())) {
						JOptionPane.showMessageDialog(f, "Invalid employee name");

					}

					else if (!isString(t3.getText())) {
						JOptionPane.showMessageDialog(f, "It must be a string.");
					
					}
					else if(!validateDoubleValue(t4.getText())) {
						JOptionPane.showMessageDialog(f, "Invalid salary value");
					}
					else if (!isString(t5.getText())) {
						JOptionPane.showMessageDialog(f, "Invalid department");
				
					}

					else if((t1.getText().length() > 0) && (t2.getText().length() > 0) && 
							(t3.getText().length() > 0) && (t4.getText().length() > 0) && (t5.getText().length() > 0))
					{
						int i=p.getIndex();
						if(i>=0 && i<emplist.size())
						{
							Employee emppp=emplist.get(i);
							emppp.setEmpno(t1.getText());
							emppp.setEmpname(t2.getText());
							emppp.setJob(t3.getText());
							emppp.setSalary(t4.getText());
							emppp.setDepartment(t5.getText());
							try
							{
					            FileWriter fw = new FileWriter("D:\\Employees.txt");
					            BufferedWriter bw = new BufferedWriter(fw);

					            for (Employee emp : emplist) {
					                bw.write(emp.getEmpno() + ", " +
					                        emp.getEmpname() + ", " +
					                        emp.getJob() + ", " +
					                        emp.getSalary() + ", " +
					                        emp.getDepartment());
					                bw.newLine();
					            }

					            bw.close();
					            fw.close();
					            JOptionPane.showMessageDialog(f, "Data saved successfully!");
					        } catch (IOException ex) {
					            ex.printStackTrace();  
					        }
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(f, "empty fields to be filled");
						
					}
				}
			}
		});
        
		//search button
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Employee empp = null;
                if (t1.getText().length() > 0) {
                    for (Employee ee : emplist) {
                        if (t1.getText().equals(ee.getEmpno())) {
                            empp = ee;
                            break; 
                        }
                    }
                    if (empp == null) {
                        JOptionPane.showMessageDialog(f, "No employee found");
                    }
                }
                if (empp == null && t2.getText().length() > 0) {
                    for (Employee ee : emplist) {
                        if (t2.getText().equals(ee.getEmpname())) {
                            empp = ee;
                            break; 
                        }
                    }
                    if (empp == null) {
                        JOptionPane.showMessageDialog(f, "No employee found");
                    }
                }
                if (empp == null && t3.getText().length() > 0) {
                    for (Employee ee : emplist) {
                        if (t3.getText().equals(ee.getJob())) {
                            empp = ee;
                            break; 
                        }
                    }
                    if (empp == null) {
                        JOptionPane.showMessageDialog(f, "No employee found");
                    }
                }
                if (empp == null && t4.getText().length() > 0) {
                    for (Employee ee : emplist) {
                        if (t4.getText().equals(ee.getSalary())) {
                            empp = ee;
                            break; 
                        }
                    }
                    if (empp == null) {
                        JOptionPane.showMessageDialog(f, "No employee found");
                    }
                }
                if (empp == null && t5.getText().length() > 0) {
                    for (Employee ee : emplist) {
                        if (t5.getText().equals(ee.getDepartment())) {
                            empp = ee;
                            break; 
                        }
                    }
                    if (empp == null) {
                        JOptionPane.showMessageDialog(f, "No employee found");
                    }
                }
                if (empp != null) {
                    t1.setText(empp.getEmpno());
                    t2.setText(empp.getEmpname());
                    t3.setText(empp.getJob());
                    t4.setText(empp.getSalary());
                    t5.setText(empp.getDepartment());
                }
            }
        });
           
		//clear button
        b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText(""); 
					t5.setText("");			
			}
		});
        
		//exit button
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});	
	}
}
