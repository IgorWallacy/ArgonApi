package com.doks.conferencia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doks_pdv_vga")
public class PddVga {
	
	@Id
	private Integer pdv;

	public Integer getPdv() {
		return pdv;
	}

	public void setPdv(Integer pdv) {
		this.pdv = pdv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pdv == null) ? 0 : pdv.hashCode());
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
		PddVga other = (PddVga) obj;
		if (pdv == null) {
			if (other.pdv != null)
				return false;
		} else if (!pdv.equals(other.pdv))
			return false;
		return true;
	}

	
}
