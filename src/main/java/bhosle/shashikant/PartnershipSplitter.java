package bhosle.shashikant;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;

public class PartnershipSplitter {

	public PartnershipSplitter() {

	}

	final BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);
	final BigDecimal BIGDECIMAL_POINT_01 = BigDecimal.valueOf(0.01);

	public List<Partner> splitPartnership(final List<Partner> partners, final Partner leavingPartner) {

		final Predicate<Partner> excludeLeavingPartner = p -> p.getPartnerNumber() != leavingPartner.getPartnerNumber();

		BigDecimal ramainingShare = BIGDECIMAL_100.subtract(leavingPartner.getShare());

		BigDecimal sum = partners.stream().filter(excludeLeavingPartner).map(p -> {
			BigDecimal updatedPartnership = p.getShare().multiply(BIGDECIMAL_100, MathContext.DECIMAL32)
					.divide(ramainingShare, MathContext.DECIMAL32);
			p.setShare(updatedPartnership);
			return p.getShare().setScale(2, RoundingMode.HALF_UP);
		}).reduce(BigDecimal.ZERO, BigDecimal::add);

		if (sum.compareTo(BIGDECIMAL_100) == 0) {
			partners.stream().filter(excludeLeavingPartner)
					.forEach(p -> p.setShare(p.getShare().setScale(2, RoundingMode.HALF_UP)));

		} else {

			sum = partners.stream().filter(excludeLeavingPartner).map(p -> p.getShare().setScale(2, RoundingMode.FLOOR))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			for (Partner partner : partners) {
				if (sum.compareTo(BIGDECIMAL_100) != 0
						&& partner.getPartnerNumber() != leavingPartner.getPartnerNumber()) {
					partner.setShare(partner.getShare().add(BIGDECIMAL_POINT_01).setScale(2, RoundingMode.FLOOR));
					sum = sum.add(BIGDECIMAL_POINT_01);
				} else {
					partner.setShare(partner.getShare().setScale(2, RoundingMode.FLOOR));
				}
			}
		}
		return partners;

	}

}
