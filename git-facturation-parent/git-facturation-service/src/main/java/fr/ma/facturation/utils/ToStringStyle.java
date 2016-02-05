package fr.ma.facturation.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Classe définissant le formatage à  utiliser dans un objet ToStringBuilder
 */
public class ToStringStyle extends org.apache.commons.lang.builder.ToStringStyle {

	private static final long serialVersionUID = -4478254460976699298L;

	/**
     * Style par défaut
     */
    public static final ToStringStyle DEFAULT_STYLE = new ToStringStyle();

    private static final String DEFAULT_FIELD_SEPARATOR = "; ";
    private static final String COLLECTION_START_TEXT = "{";
    private static final String COLLECTION_END_TEXT = "}";

    static {
        DEFAULT_STYLE.setUseIdentityHashCode(false);
        DEFAULT_STYLE.setUseClassName(false);
        DEFAULT_STYLE.setFieldSeparator(DEFAULT_FIELD_SEPARATOR);
    }

    protected ToStringStyle() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang.builder.ToStringStyle#appendStart(java.lang.StringBuffer, java.lang.Object)
     */
    @Override
	public void appendStart(StringBuffer buffer, Object object) {
        if(!(object instanceof Collection) && !(object instanceof Object[]) && !(object instanceof Map)) {
            super.appendStart(buffer,object);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang.builder.ToStringStyle#appendEnd(java.lang.StringBuffer, java.lang.Object)
     */
    @Override
	public void appendEnd(StringBuffer buffer, Object object) {
        if(!(object instanceof Collection) && !(object instanceof Object[]) && !(object instanceof Map)) {
            super.appendEnd(buffer,object);
        }
        else {
            if(!this.isFieldSeparatorAtEnd()) {
                this.removeLastFieldSeparator(buffer);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.lang.Object)
     */
    @Override
	protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (value instanceof Calendar) {
        	Calendar cal = (Calendar) value;
        	value = DateFormatUtils.format(cal.getTime(), DateUtils.SHORT_DATE_FORMAT);
            buffer.append(value);
        }
        else {
            if (value instanceof Iterator) {
                Iterator iter = (Iterator) value;
                Collection collection = new ArrayList();
                while (iter.hasNext()) {
                    collection.add(iter.next());
                }
                this.appendDetail(buffer, fieldName, collection);
            }
            else {
                buffer.append(value);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Collection)
     */
    @Override
	protected void appendDetail(StringBuffer buffer, String fieldName, Collection coll) {
        this.appendDetail(buffer, fieldName, coll.toArray());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Map)
     */
    @Override
	protected void appendDetail(StringBuffer buffer, String fieldName, Map map) {
        buffer.append(COLLECTION_START_TEXT);
        Object[] keys = map.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            this.appendDetail(buffer, fieldName, keys[i]);
            buffer.append("=");
            this.appendDetail(buffer, fieldName, map.get(keys[i]));
            if (i != keys.length - 1) {
                buffer.append(DEFAULT_FIELD_SEPARATOR);
            }
        }
        buffer.append(COLLECTION_END_TEXT);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.lang.Object[])
     */
    @Override
	protected void appendDetail(StringBuffer buffer, String fieldName, Object[] array) {
        buffer.append(COLLECTION_START_TEXT);
        for (int i = 0; i < array.length; i++) {
            this.appendDetail(buffer, fieldName, array[i]);
            if (i != array.length - 1) {
                buffer.append(DEFAULT_FIELD_SEPARATOR);
            }
        }
        buffer.append(COLLECTION_END_TEXT);
    }
}
