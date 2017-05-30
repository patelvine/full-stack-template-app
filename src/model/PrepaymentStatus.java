//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.25 at 11:30:02 AM PDT 
//


package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prepaymentStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prepaymentStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AUTHORISED"/>
 *     &lt;enumeration value="PAID"/>
 *     &lt;enumeration value="VOIDED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "prepaymentStatus")
@XmlEnum
public enum PrepaymentStatus {


    /**
     * Authorised
     * 
     */
    AUTHORISED,

    /**
     * Paid
     * 
     */
    PAID,

    /**
     * Voided
     * 
     */
    VOIDED;

    public String value() {
        return name();
    }

    public static PrepaymentStatus fromValue(String v) {
        return valueOf(v);
    }

}
