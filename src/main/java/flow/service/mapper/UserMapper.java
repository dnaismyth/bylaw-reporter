package flow.service.mapper;

import flow.domain.RUser;
import flow.dto.User;

/**
 * Map User and RUser
 * @author DN
 *
 */
public class UserMapper {
	
	/**
	 * To User DTO
	 * @param ru
	 * @return
	 */
	public User toUser(RUser ru){
		User user = null;
		if(ru != null){
			user = new User();
			user.setCreatedDate(ru.getCreatedDate());
			user.setEmail(ru.getEmail());
			user.setFirstName(ru.getFirstName());
			user.setLastName(ru.getLastName());
			user.setId(ru.getId());
			user.setLogin(ru.getLogin());
			user.setPassword(ru.getPassword());
		}
		
		return user;
	}
	
	/**
	 * To RUser entity
	 * @param u
	 * @return
	 */
	public RUser toRUser(User u){
		RUser ru = null;
		if (u != null){
			ru = new RUser();
			ru.setCreatedDate(u.getCreatedDate());
			ru.setEmail(u.getEmail());
			ru.setFirstName(u.getFirstName());
			ru.setLastName(u.getLastName());
			ru.setId(u.getId());
			ru.setLogin(u.getLogin());
			ru.setPassword(u.getPassword());
		}
		
		return ru;
	}
}
