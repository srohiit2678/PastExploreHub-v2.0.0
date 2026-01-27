package com.pastexplorehub.dto;

//Create CommentDTO.java (inner class or separate file)
public class CommentDTO {
 private String userName;
 private String enrollId;
 private String message;
 private String formattedDate;
 
 
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getEnrollId() {
	return enrollId;
}
public void setEnrollId(String enrollId) {
	this.enrollId = enrollId;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getFormattedDate() {
	return formattedDate;
}
public void setFormattedDate(String formattedDate) {
	this.formattedDate = formattedDate;
}



@Override
public String toString() {
	return "CommentDTO [userName=" + userName + ", enrollId=" + enrollId + ", message=" + message + ", formattedDate="
			+ formattedDate + "]";
}
 
 
 // Constructors, Getters, Setters
}