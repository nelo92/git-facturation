package fr.ma.facturation.xml.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.ma.facturation.utils.ToStringStyle;

@XmlAccessorType(XmlAccessType.FIELD)
public class TvaElement {

	// Attributs --------------------------------------------------------------

	@XmlAttribute(name="ref", required=true)
	private Integer  ref;
	@XmlAttribute(name="value", required=true)
	private Float value;

	// Constructeurs ----------------------------------------------------------

	public TvaElement() {
	}

	public TvaElement(Integer id, Float value) {
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
