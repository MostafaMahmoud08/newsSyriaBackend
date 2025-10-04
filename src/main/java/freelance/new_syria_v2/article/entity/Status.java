package freelance.new_syria_v2.article.entity;

import lombok.ToString;

@ToString()
public enum Status {
	APPROVED("approved"), REJECTED("rejected"), PENDING("pending");

	private final String value;

	Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Status from(String value) {
		if (value == null) {
			return null;
		}
		for (Status status : Status.values()) {
			if (status.value.equalsIgnoreCase(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException(
				"The status you entered is not valid. Please enter approved, rejected, or pending.");
	}

	@Override
	public String toString() {
		return value;
	}
}
