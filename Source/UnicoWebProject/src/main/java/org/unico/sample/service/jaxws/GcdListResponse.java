
package org.unico.sample.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "gcdListResponse", namespace = "http://service.sample.unico.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gcdListResponse", namespace = "http://service.sample.unico.org/")
public class GcdListResponse {

    @XmlElement(name = "return", namespace = "")
    private org.unico.sample.model.GcdCalculatedVO _return;

    /**
     * 
     * @return
     *     returns GcdCalculatedVO
     */
    public org.unico.sample.model.GcdCalculatedVO getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(org.unico.sample.model.GcdCalculatedVO _return) {
        this._return = _return;
    }

}
