package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class StudentController implements Initializable
{
	@FXML
	TextField Name;
	@FXML
	TextField Mark;
	@FXML
	TextField Comments;
	@FXML
	DatePicker Date;
	@FXML
	ComboBox<String> Gender;
	@FXML
	ImageView Photo;
	@FXML
	ListView<String> LStudent;
	@FXML
	Button btnEdit;
	@FXML
	Button btnSave;
	@FXML
	Button btnCancel;
	@FXML
	Button btnDelete;
	@FXML
	Label lbPath;
	boolean add=false;
	
	DBManager manager;
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		manager = new DBManager();
		List<String> gvalues = new ArrayList<String>(); 
		gvalues.add("Male"); 
		gvalues.add("Female"); 
		ObservableList<String> gender = FXCollections.observableArrayList(gvalues);
		Gender.setItems(gender);
		LStudent.getSelectionModel().selectedItemProperty().addListener(e-> displayStudentDetails(LStudent.getSelectionModel().getSelectedItem()));
		
		fetchStudents();
		}
	
	public void fetchStudents(){ 	
		ObservableList<String> students;
		if (manager.loadStudents()!=null){ 
			students= FXCollections.observableArrayList(manager.loadStudents());
			LStudent.setItems(students); 
			} 
	}
		
		private void displayStudentDetails(String name) {
			try{
				Student s =manager.fetchStudentByName(name);
				Name.setText(s.getName());
				Gender.setValue(s.getGender());
				Date.setValue(s.getBirthDate());
				
				Image image;
				InputStream is = null;        
					if(s.getPhoto()!=null) {
						is = new FileInputStream(s.getPhoto());
						image = new Image(is); 
						Photo.setImage(image); }        
					else {                
						is = new FileInputStream("C:\\Users\\corentin\\Pictures\\imagestudent.png");
						image= new Image (is);
						Photo.setImage(image);
						} 
					Mark.setText(String.valueOf(s.getMark()))
					; Comments.setText(s.getComments());
					
					onCancel();
					} 
			catch (Exception e) {
				System.out.println(e.getMessage()); 
				}
			}
		
		public void onEdit()
		{
			btnEdit.setDisable(true);
			btnCancel.setDisable(false);
			btnSave.setDisable(false);
			btnDelete.setDisable(true);
		}
	
		public void onCancel()
		{
			btnEdit.setDisable(false);
			btnCancel.setDisable(true);
			btnSave.setDisable(true);
			btnDelete.setDisable(false);
		}
		
		public void chooseImage()
		{
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);				
		if (selectedFile != null)
		{
		lbPath.setText(selectedFile.getAbsolutePath());		
		}
		}
		
		public void onSave()
		{
			
		
			if(add==false)
			{
				Student s =manager.fetchStudentByName(Name.getText());
				s.setName(Name.getText());
				s.setGender(Gender.getValue());
				s.setBirthDate(Date.getValue());
				s.setPhoto(lbPath.getText());
				s.setMark(Float.parseFloat(Mark.getText()));
				s.setComments(Comments.getText());
			manager.updateStudent(s);
			
			
			}
			else
			{
				Student s =new Student();
				s.setName(Name.getText());
				s.setGender(Gender.getValue());
				s.setBirthDate(Date.getValue());
				s.setPhoto(lbPath.getText());
				s.setMark(Float.parseFloat(Mark.getText()));
				s.setComments(Comments.getText());
				
			manager.addStudent(s);
			
			
			
			}
			onCancel();
			
		}
		
		public void onNew()
		{
			
			onEdit();
			Name.setText(null);
			Gender.setValue(null);
			Date.setValue(null);
			lbPath.setText(null);
			Mark.setText(null);
			Comments.setText(null);
			try
			{
			InputStream is1 = null;
			Image image2;
			is1 = new FileInputStream("C:\\Users\\corentin\\Pictures\\imagestudent.png");
			image2= new Image (is1);
			Photo.setImage(image2);
			}
			catch (Exception e) {
				System.out.println(e.getMessage()); 
				}
			
		add=true;
			
			
			
		}
		
		public void onDelete()
		{
			Student s =manager.fetchStudentByName(Name.getText());

			
			
			manager.deleteStudent(s);
			
			
		}
		
		
		
}
