package fr.ma.facturation.modele;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

public class Tva implements Serializable {

	// Constantes -------------------------------------------------------------

	private static final long	serialVersionUID	= -8669623567670046884L;

	// Attributs --------------------------------------------------------------

	private Integer  ref;
	private Float value;

	// Constructeurs ----------------------------------------------------------

	public Tva(Integer id, float value) {
		this.ref = id;
		this.value = value;
	}

	// Getter/Setters ---------------------------------------------------------

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	// Methods ----------------------------------------------------------------


	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("ref", ref);
		sb.append("value", value);
		return sb.toString();
	}

}
