package net.etfbl.muzickagroznica.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import net.etfbl.muzickagroznica.model.dao.RoleDao;
import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.Role;
import net.etfbl.muzickagroznica.model.entities.RoleId;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.util.StandardUtil;
import net.etfbl.muzickagroznica.util.StandardUtilsBean;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	StandardUtilsBean standardUtilsBean;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	public UserService() {
		// TODO Auto-generated constructor stub
		System.err.println("------>>> KONSTRUISAN SERVIS!!!");
	}

	/**
	 * Registering new user. Caller should provide User entity with username,
	 *  firstName, lastName, jmb, email, and other optional parameters.
	 *  Method will hash password, set activation time to null, and registering time to now.
	 *  
	 *  It will also add ROLE_REGISTERED role for user.
	 * 
	 * @param user user with information from registration form or something like that
	 * @param rawPassword a raw, unencrypted password for user
	 */
	@Transactional
	public void registerNewUser(User user, String rawPassword){
		
		user.setActivationTime(null);
		user.setRegistrationTime(StandardUtil.now());
		
		user.setPassword(encoder.encode(rawPassword));
		
		userDao.persist(user);
		
		Role registered = new Role();
		registered.setId(new RoleId(user.getId(), "ROLE_REGISTERED"));
		roleDao.persist(registered);
	
	}
	
	
	@Transactional
	public User updateUserData(User user, String rawOldPassword){

		if (authenticateUserById(user.getId(), rawOldPassword)) {
			return userDao.merge(user);
		}
		
		return null;
	}
	
	@Transactional
	public User changeUserPassword(int userId, String newRawPassword, String oldRawPassword){
		User user = userDao.findById(userId);
		
		if(encoder.matches(oldRawPassword, user.getPassword())){
			user.setPassword(encoder.encode(newRawPassword));
		} else {
			return null;
		}
		
		return user;
	}
	
	@Transactional
	public User activateUserAccount(int userId){
		User dbuser = userDao.findById(userId);
		
		dbuser.setActivationTime(StandardUtil.now());
		dbuser.setActive(true);
		
		return dbuser;
		
		//it will commit changes at the end of transaction		
	}

	
	@Transactional
	public boolean authenticateUserById(int userId, String rawPassword){
		User dbuser = userDao.findById(userId);
		
		return encoder.matches(rawPassword, dbuser.getPassword());
	}
	
	@Transactional
	public User findUser(int id){
		return userDao.findById(id);
	}
	
	public User changeAvatarForUser(int userId, byte[] image){
		
		String uufilename = UUID.randomUUID().toString();
		
		File outputFile = new File(standardUtilsBean.getAvatarUploadDir(), uufilename);
		
		try {
			FileUtils.writeByteArrayToFile(outputFile, image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		User ret = null;
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus txstat = null;
		
		
		try {
		
			txstat = transactionManager.getTransaction(def);
			
			ret = userDao.findById(userId);
			ret.setAvatarPath(uufilename);
			
			transactionManager.commit(txstat);
			
		} catch(Exception ex) {
			outputFile.delete();
			try {
				transactionManager.rollback(txstat);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		return ret;	
		
	}

	@Transactional
	public List<User> findUsersWithDeactivatedAccount(){
		return userDao.findByActive(false);
	}
	
	@Transactional
	public List<User> findActiveUsers(){
		return userDao.findByActive(true);
	}
	
	@Transactional
	public User addRoleToUser(int userId, String roleName){
		User user = userDao.findById(userId);

		boolean has = false;
		
		for(Role role : user.getRoles()){
			if(role.getId().getRoleName().equals(roleName)){
				has = true;
				break;
			}
		}
		
		if(!has){
			Role role = new Role(new RoleId(user.getId(),roleName), user);
			user.getRoles().add(role);
			roleDao.persist(role);
		}
		
		return user;
	}
	
	@Transactional
	public User removeRoleFromUser(int userId, String roleName){
		
		User user = userDao.findById(userId);
		
		Role theone = null;
		
		for(Role role : user.getRoles()){
			
			if(role.getId().getRoleName().equals(roleName)){
				theone = role;
				break;
			}
			
		}
		
		if(theone != null){
			user.getRoles().remove(theone);
			roleDao.delete(theone);
		}
		
		return user;
		
	}
	
	@Transactional
	public List<Role> findRolesForUser(int userId){
		return roleDao.findByUserId(userId);
	}
	
	@Transactional
	public List<User> findRegisteredAfter(java.util.Date date){
		return userDao.findRegisteredAfter(date);
	}
	
}
