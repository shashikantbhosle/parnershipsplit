package bhosle.shashikant;

import java.math.BigDecimal;

public class Partner {

	private int partnerNumber;
	private String partnerName;
	private BigDecimal share;

	public Partner() {

	}

	public Partner(int partnerNumber, String partnerName, BigDecimal share) {
		super();
		this.partnerNumber = partnerNumber;
		this.partnerName = partnerName;
		this.share = share;
	}

	public int getPartnerNumber() {
		return partnerNumber;
	}

	public void setPartnerNumber(int partnerNumber) {
		this.partnerNumber = partnerNumber;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public BigDecimal getShare() {
		return share;
	}

	public void setShare(BigDecimal share) {
		this.share = share;
	}

}
