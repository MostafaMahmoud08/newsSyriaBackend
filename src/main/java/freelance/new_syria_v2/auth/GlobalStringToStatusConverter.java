package freelance.new_syria_v2.auth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import freelance.new_syria_v2.article.entity.Status;

@Component
public class GlobalStringToStatusConverter implements Converter<String, Status> {

	@Override
	public Status convert(String source) {
		if (source == null)
			return null;

		switch (source.trim().toLowerCase()) {
		case "rejected":
			return Status.REJECTED;
		case "approved":
			return Status.APPROVED;
		case "pending":
			return Status.PENDING;
		default:
			return Status.PENDING;
		}
	}
}
