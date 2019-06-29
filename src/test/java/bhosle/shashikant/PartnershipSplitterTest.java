package bhosle.shashikant;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import bhosle.shashikant.Partner;
import bhosle.shashikant.PartnershipSplitter;

public class PartnershipSplitterTest {

	final BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);
	final BigDecimal BIGDECIMAL_POINT_01 = BigDecimal.valueOf(0.01);

	@Test
	public void parnetshipShouldBeEquallyDistributedAsPerOriginalContributionRatio() {

		Partner partner1 = new Partner(1, "A", new BigDecimal(20D));
		Partner partner2 = new Partner(2, "B", new BigDecimal(30D));
		Partner partner3 = new Partner(3, "C", new BigDecimal(50D));

		List<Partner> partners = Arrays.asList(partner1, partner2, partner3);

		PartnershipSplitter partnershipSplitter = new PartnershipSplitter();
		List<Partner> updatedPartners = partnershipSplitter.splitPartnership(partners, partner2);

		Assert.assertEquals(BIGDECIMAL_100
				.compareTo(updatedPartners.stream().filter(p -> p.getPartnerNumber() != partner2.getPartnerNumber())
						.map(p -> p.getShare()).reduce(BigDecimal.ZERO, BigDecimal::add)),
				0);

		Assert.assertEquals(0, updatedPartners.get(0).getShare().compareTo(BigDecimal.valueOf(28.57)));
		Assert.assertEquals(0, updatedPartners.get(2).getShare().compareTo(BigDecimal.valueOf(71.43)));

	}

	@Test
	public void parnetshipShouldBeEquallyDistributedAsPerOriginalContributionRatio1() {

		Partner partner1 = new Partner(1, "A", new BigDecimal(10D));
		Partner partner2 = new Partner(2, "B", new BigDecimal(30D));
		Partner partner3 = new Partner(3, "C", new BigDecimal(30D));
		Partner partner4 = new Partner(4, "D", new BigDecimal(30D));

		List<Partner> partners = Arrays.asList(partner1, partner2, partner3, partner4);

		PartnershipSplitter partnershipSplitter = new PartnershipSplitter();
		List<Partner> updatedPartners = partnershipSplitter.splitPartnership(partners, partner1);

		Assert.assertEquals(BIGDECIMAL_100
				.compareTo(updatedPartners.stream().filter(p -> p.getPartnerNumber() != partner1.getPartnerNumber())
						.map(p -> p.getShare()).reduce(BigDecimal.ZERO, BigDecimal::add)),
				0);

		Map<Object, Long> counters = updatedPartners.stream()
				.collect(Collectors.groupingBy(p -> p.getShare(), Collectors.counting()));

		Assert.assertTrue((counters.get(BigDecimal.valueOf(33.33)) == 2));
		Assert.assertTrue((counters.get(BigDecimal.valueOf(33.34)) == 1));

	}

	@Test
	public void parnetshipShouldBeEquallyDistributedAsPerOriginalContributionRatio2() {

		Partner partner1 = new Partner(1, "A", new BigDecimal(12.5D));
		Partner partner2 = new Partner(2, "B", new BigDecimal(12.5D));
		Partner partner3 = new Partner(3, "C", new BigDecimal(12.5D));
		Partner partner4 = new Partner(4, "D", new BigDecimal(12.5D));
		Partner partner5 = new Partner(5, "E", new BigDecimal(12.5D));
		Partner partner6 = new Partner(6, "F", new BigDecimal(12.5D));
		Partner partner7 = new Partner(7, "G", new BigDecimal(12.5D));
		Partner partner8 = new Partner(8, "H", new BigDecimal(12.5D));

		List<Partner> partners = Arrays.asList(partner1, partner2, partner3, partner4, partner5, partner6, partner7,
				partner8);

		PartnershipSplitter partnershipSplitter = new PartnershipSplitter();
		List<Partner> updatedPartners = partnershipSplitter.splitPartnership(partners, partner1);

		Assert.assertEquals(BIGDECIMAL_100
				.compareTo(updatedPartners.stream().filter(p -> p.getPartnerNumber() != partner1.getPartnerNumber())
						.map(p -> p.getShare()).reduce(BigDecimal.ZERO, BigDecimal::add)),
				0);

		Map<Object, Long> counters = updatedPartners.stream()
				.collect(Collectors.groupingBy(p -> p.getShare(), Collectors.counting()));

		Assert.assertTrue((counters.get(BigDecimal.valueOf(14.28)) == 3));
		Assert.assertTrue((counters.get(BigDecimal.valueOf(14.29)) == 4));

	}

}
