//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.25 at 11:30:02 AM PDT 
//


package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrackingCategories complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackingCategories">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmployeeGroups" type="{}EmployeeGroups" minOccurs="0"/>
 *         &lt;element name="TimesheetCategories" type="{}TimesheetCategories" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackingCategories", propOrder = {
    "employeeGroups",
    "timesheetCategories"
})
public class TrackingCategories {

    @XmlElement(name = "EmployeeGroups")
    protected EmployeeGroups employeeGroups;
    @XmlElement(name = "TimesheetCategories")
    protected TimesheetCategories timesheetCategories;

    /**
     * Gets the value of the employeeGroups property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeGroups }
     *     
     */
    public EmployeeGroups getEmployeeGroups() {
        return employeeGroups;
    }

    /**
     * Sets the value of the employeeGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeGroups }
     *     
     */
    public void setEmployeeGroups(EmployeeGroups value) {
        this.employeeGroups = value;
    }

    /**
     * Gets the value of the timesheetCategories property.
     * 
     * @return
     *     possible object is
     *     {@link TimesheetCategories }
     *     
     */
    public TimesheetCategories getTimesheetCategories() {
        return timesheetCategories;
    }

    /**
     * Sets the value of the timesheetCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimesheetCategories }
     *     
     */
    public void setTimesheetCategories(TimesheetCategories value) {
        this.timesheetCategories = value;
    }

}
