package br.edu.ifpb.scream.core;

/**
 * 
 * @author Hugo Correia
 *
 */
public class UserAuthorizationItem {
	
	private String resourceID;
	private AuthorizationLevel authorizationLevel;
	
	
	
	
	/**
	 * @return the resourceID
	 */
	public String getResourceID() {
		return resourceID;
	}
	/**
	 * @param resourceID the resourceID to set
	 */
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
	/**
	 * @return the authorizationLevel
	 */
	public AuthorizationLevel getAuthorizationLevel() {
		return authorizationLevel;
	}
	/**
	 * @param authorizationLevel the authorizationLevel to set
	 */
	public void setAuthorizationLevel(AuthorizationLevel authorizationLevel) {
		this.authorizationLevel = authorizationLevel;
	}

}
