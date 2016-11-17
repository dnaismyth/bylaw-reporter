package flow.dto;

import java.time.ZonedDateTime;

/**
 * User DTO
 * @author DN
 *
 */
public class User extends UserItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5260960298060363200L;

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private ZonedDateTime createdDate;
	private RoleType role;
	
	public User(){}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public RoleType getRole(){
		return role;
	}
	public void setRole(RoleType role){
		this.role = role;
	}
	
}
