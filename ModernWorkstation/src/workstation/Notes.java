package workstation;
/*
 * Author: Curtis Warren,
 * Description: This is a demo for the project SDEV Workstation (Modern Workstation)
 * to show how files should be saved in json form and how to work with the data for use in the program.
 * Version 2.2
 */
public class Notes {

	private int id;
	private String title;
	private String content;
	private String category;

 public Notes() {

	 this.title = "";
	 this.content = "";
     this.category = "";

 }

 public int getID() {

	 return this.id;

 }

 public String getTitle() {

	 return this.title;

 }

 public String getCategory() {

	 return this.category;

 }

 public String getContent() {

	 return this.content;

 }
 public void setID(int newID) {

	 this.id = newID;

 }
 public void setTitle(String newTitle) {

	 this.title = newTitle;

 }

 public void setCategory(String newCategory) {

	 this.category = newCategory;

 }

 public void setContent(String newContent) {

	 this.content = newContent;

 }

 @Override
public String toString() {

	 if (this.category == null) {
		 
		 return "{\n\"Title\":\"" + this.title +"\",\n" + "\"Category\":\"\",\n" + "\"Content\":\"" + this.content + "\"";  
		 
	 }
	 
	 return "{\n\"Title\":\"" + this.title +"\",\n" +  "\"Category\":\"" + this.category + "\",\n" + "\"Content\":\"" + this.content + "\"";  

	 
 }

}
