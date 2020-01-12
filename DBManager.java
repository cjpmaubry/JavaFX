package application;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;

public class DBManager 
{
	public List<String> loadStudents()
	{ 
		List<String> studentNames= new ArrayList<String>();
		Connection myConn= this.Connector();
		try {
				Statement myStmt= myConn.createStatement();
				String sql = "select name from studenttable";
				ResultSet myRs= myStmt.executeQuery(sql);
				while (myRs.next()) 
				{ 
					String name= myRs.getString("name");
					studentNames.add(name);
				} 
				this.close(myConn, myStmt, myRs);
				return studentNames;
			}catch (SQLException e) {
				e.printStackTrace();
			} 
			return null;
		}
	
	
	public Connection Connector(){ 
		try { 
			Connection connection =        
			DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=Europe/Paris", "esilvs6","esilvs6");
			return connection; 
			}
		
			catch (Exception e) {
				e.printStackTrace();
				return null; 
			}
		}
	
			private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			try{
				if(myStmt!=null)
					myStmt.close();
				if(myRs!=null) 
					myRs.close();
				if(myConn!=null) 
					myConn.close();			
			} 
			catch(Exception e){ 
				System.out.println(e.getMessage()); 
			}
		}
			
			public Student fetchStudentByName(String name) {
				Student s = null;
				Connection myConn= Connector();
				try { Statement myStmt= myConn.createStatement();
				String sql = "select * from studenttable where name=\""+ name+"\"";
				ResultSet myRs= myStmt.executeQuery(sql);
				while (myRs.next()) {
					int id=myRs.getInt("id");
					String gender= myRs.getString("gender");
					LocalDate birth=null;
					if (myRs.getDate("dateofbirth")!=null) {
						birth = myRs.getDate("dateofbirth").toLocalDate();
						} 
					String photo = myRs.getString("photo");
					float mark = myRs.getFloat("mark");
					String comments= myRs.getString("comments");
			s = new Student(id,name,gender,birth,photo,mark,comments);
			} 
				this.close(myConn, myStmt, myRs);
				return s;
			} catch (SQLException e) {
				e.printStackTrace();
				return null; 
				}
			}
			
			public void updateStudent(Student s)
			{

				Connection connection = Connector();
	
				try
				{
				Statement myStmt = connection.createStatement();
				

					String sql="update student.studenttable set  name=\""+s.getName()+"\",gender=\""+s.getGender()+"\",dateofbirth=\""+s.getBirthDate()+"\",photo=\""+s.getPhoto()+"\",mark="+s.getMark()+",comments=\""+s.getComments()+"\" where name=\""+s.getName()+"\"";
					myStmt.executeUpdate(sql);
					
				}
				catch (Exception e)
				{		
					System.out.println("error");
				}
				
				
			}
			
			public void addStudent(Student s)
			{
				Connection connection = Connector();
				
				try
				{
				Statement myStmt = connection.createStatement();
				

					String sql="Insert into student.studenttable (name,gender,dateofbirth,photo,mark,comments)  values(\""+s.getName()+"\",\""+s.getGender()+"\",\""+s.getBirthDate()+"\",\""+s.getPhoto()+"\","+s.getMark()+",\""+s.getComments()+"\")";
					myStmt.executeUpdate(sql);
	
				}
				catch (Exception e)
				{		
					System.out.println("error");
				}
				
			}
			
			public void deleteStudent(Student s)
			{
				Connection connection = Connector();
				
				try
				{
				Statement myStmt = connection.createStatement();
				

					String sql="Delete  from student.studenttable  where name=\""+s.getName()+"\"";
					myStmt.executeUpdate(sql);
				
				}
				catch (Exception e)
				{		
					System.out.println("error");
				}
				
				
			}
			
			
			
						
}