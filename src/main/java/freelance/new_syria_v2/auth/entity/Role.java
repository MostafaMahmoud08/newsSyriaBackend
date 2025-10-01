package freelance.new_syria_v2.auth.entity;

public enum Role {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN"),;

	private final String code;

	Role(String code) {
		this.code = code;
	}
}
