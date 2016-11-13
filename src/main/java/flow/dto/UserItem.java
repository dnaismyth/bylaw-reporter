package flow.dto;

import java.io.Serializable;

/**
 * Base user information item
 * @author DN
 *
 */
public class UserItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7084541471524486940L;
	private Long id;
	private String name;
	private String login;
	
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getLogin(){
		return login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
}
