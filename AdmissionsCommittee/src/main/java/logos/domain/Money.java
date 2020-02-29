package logos.domain;

public class Money {
	
	private String ccy;
	private String base_ccy;
	private String buy;
	private String sale;
	
	public Money() {};
	
	public Money(String ccy, String base_ccy, String buy, String sale) {
		super();
		this.ccy = ccy;
		this.base_ccy = base_ccy;
		this.buy = buy;
		this.sale = sale;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getBase_ccy() {
		return base_ccy;
	}

	public void setBase_ccy(String base_ccy) {
		this.base_ccy = base_ccy;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base_ccy == null) ? 0 : base_ccy.hashCode());
		result = prime * result + ((buy == null) ? 0 : buy.hashCode());
		result = prime * result + ((ccy == null) ? 0 : ccy.hashCode());
		result = prime * result + ((sale == null) ? 0 : sale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (base_ccy == null) {
			if (other.base_ccy != null)
				return false;
		} else if (!base_ccy.equals(other.base_ccy))
			return false;
		if (buy == null) {
			if (other.buy != null)
				return false;
		} else if (!buy.equals(other.buy))
			return false;
		if (ccy == null) {
			if (other.ccy != null)
				return false;
		} else if (!ccy.equals(other.ccy))
			return false;
		if (sale == null) {
			if (other.sale != null)
				return false;
		} else if (!sale.equals(other.sale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Money [ccy=" + ccy + ", base_ccy=" + base_ccy + ", buy=" + buy + ", sale=" + sale + "]";
	}
	
	

}
