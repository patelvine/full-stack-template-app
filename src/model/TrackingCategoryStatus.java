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
 * <p>Java class for trackingCategoryStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="trackingCategoryStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="ARCHIVED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "trackingCategoryStatus")
@XmlEnum
public enum TrackingCategoryStatus {


    /**
     * Active
     * 
     */
    ACTIVE,

    /**
     * Archived
     * 
     */
    ARCHIVED;

    public String value() {
        return name();
    }

    public static TrackingCategoryStatus fromValue(String v) {
        return valueOf(v);
    }

}
