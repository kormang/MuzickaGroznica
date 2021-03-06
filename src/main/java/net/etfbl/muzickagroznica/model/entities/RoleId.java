package net.etfbl.muzickagroznica.model.entities;

// Generated Mar 18, 2015 12:34:37 PM by Hibernate Tools 4.3.1

/**
 * RoleId generated by hbm2java
 */
public class RoleId implements java.io.Serializable {

	private int userId;
	private String roleName;

	public RoleId() {
	}

	public RoleId(int userId, String roleName) {
		this.userId = userId;
		this.roleName = roleName;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleId))
			return false;
		RoleId castOther = (RoleId) other;

		return (this.getUserId() == castOther.getUserId())
				&& ((this.getRoleName() == castOther.getRoleName()) || (this
						.getRoleName() != null
						&& castOther.getRoleName() != null && this
						.getRoleName().equals(castOther.getRoleName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserId();
		result = 37 * result
				+ (getRoleName() == null ? 0 : this.getRoleName().hashCode());
		return result;
	}

}
