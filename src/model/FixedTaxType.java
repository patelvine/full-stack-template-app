//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.25 at 11:30:02 AM PDT 
//


package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fixedTaxType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fixedTaxType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INPUT"/>
 *     &lt;enumeration value="INPUT2"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="OUTPUT"/>
 *     &lt;enumeration value="OUTPUT2"/>
 *     &lt;enumeration value="ZERORATED"/>
 *     &lt;enumeration value="EXEMPTINPUT"/>
 *     &lt;enumeration value="EXEMPTOUTPUT"/>
 *     &lt;enumeration value="RRINPUT"/>
 *     &lt;enumeration value="RROUTPUT"/>
 *     &lt;enumeration value="ZERORATEDINPUT"/>
 *     &lt;enumeration value="ZERORATEDOUTPUT"/>
 *     &lt;enumeration value="CAPEXINPUT"/>
 *     &lt;enumeration value="CAPEXINPUT2"/>
 *     &lt;enumeration value="SRINPUT"/>
 *     &lt;enumeration value="SROUTPUT"/>
 *     &lt;enumeration value="GSTONIMPORTS"/>
 *     &lt;enumeration value="GSTONCAPIMPORTS"/>
 *     &lt;enumeration value="CAPEXOUTPUT"/>
 *     &lt;enumeration value="CAPEXOUTPUT2"/>
 *     &lt;enumeration value="CAPEXSRINPUT"/>
 *     &lt;enumeration value="CAPEXSROUTPUT"/>
 *     &lt;enumeration value="ECACQUISITIONS"/>
 *     &lt;enumeration value="ECZRINPUT"/>
 *     &lt;enumeration value="ECZROUTPUT"/>
 *     &lt;enumeration value="ECZROUTPUTSERVICES"/>
 *     &lt;enumeration value="REVERSECHARGES"/>
 *     &lt;enumeration value="BLINPUT"/>
 *     &lt;enumeration value="DSOUTPUT"/>
 *     &lt;enumeration value="ES33OUTPUT"/>
 *     &lt;enumeration value="ESN33OUTPUT"/>
 *     &lt;enumeration value="IGDSINPUT"/>
 *     &lt;enumeration value="IMINPUT"/>
 *     &lt;enumeration value="MEINPUT"/>
 *     &lt;enumeration value="NRINPUT"/>
 *     &lt;enumeration value="OPINPUT"/>
 *     &lt;enumeration value="OSOUTPUT"/>
 *     &lt;enumeration value="TXESSINPUT"/>
 *     &lt;enumeration value="TXINPUT"/>
 *     &lt;enumeration value="TXN33INPUT"/>
 *     &lt;enumeration value="TXPETINPUT"/>
 *     &lt;enumeration value="TXREINPUT"/>
 *     &lt;enumeration value="IMINPUT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fixedTaxType")
@XmlEnum
public enum FixedTaxType {


    /**
     * Input
     * 
     */
    INPUT("INPUT"),

    /**
     * Input2
     * 
     */
    @XmlEnumValue("INPUT2")
    INPUT_2("INPUT2"),

    /**
     * None
     * 
     */
    NONE("NONE"),

    /**
     * Output
     * 
     */
    OUTPUT("OUTPUT"),

    /**
     * Output2
     * 
     */
    @XmlEnumValue("OUTPUT2")
    OUTPUT_2("OUTPUT2"),

    /**
     * Zero Rated
     * 
     */
    ZERORATED("ZERORATED"),

    /**
     * Exempt Input
     * 
     */
    EXEMPTINPUT("EXEMPTINPUT"),

    /**
     * Exempt Output
     * 
     */
    EXEMPTOUTPUT("EXEMPTOUTPUT"),

    /**
     * Reduced Rate Input
     * 
     */
    RRINPUT("RRINPUT"),

    /**
     * Reduced Rate Output
     * 
     */
    RROUTPUT("RROUTPUT"),

    /**
     * Zero Rated Input
     * 
     */
    ZERORATEDINPUT("ZERORATEDINPUT"),

    /**
     * Zero Rated Output
     * 
     */
    ZERORATEDOUTPUT("ZERORATEDOUTPUT"),

    /**
     * Capital Input
     * 
     */
    CAPEXINPUT("CAPEXINPUT"),

    /**
     * VAT on Capital Purchases
     * 
     */
    @XmlEnumValue("CAPEXINPUT2")
    CAPEXINPUT_2("CAPEXINPUT2"),

    /**
     * Special Rate Input
     * 
     */
    SRINPUT("SRINPUT"),

    /**
     * Special Rate Output
     * 
     */
    SROUTPUT("SROUTPUT"),

    /**
     * GST On Imports
     * 
     */
    GSTONIMPORTS("GSTONIMPORTS"),

    /**
     * GST On Capital Imports
     * 
     */
    GSTONCAPIMPORTS("GSTONCAPIMPORTS"),

    /**
     * VAT on Capital Sales
     * 
     */
    CAPEXOUTPUT("CAPEXOUTPUT"),

    /**
     * VAT on Capital Sales
     * 
     */
    @XmlEnumValue("CAPEXOUTPUT2")
    CAPEXOUTPUT_2("CAPEXOUTPUT2"),

    /**
     * VAT on Capital Purchases
     * 
     */
    CAPEXSRINPUT("CAPEXSRINPUT"),

    /**
     * VAT on Capital Sales
     * 
     */
    CAPEXSROUTPUT("CAPEXSROUTPUT"),

    /**
     * EC Acquisitions
     * 
     */
    ECACQUISITIONS("ECACQUISITIONS"),

    /**
     * Zero Rated EC Expenses
     * 
     */
    ECZRINPUT("ECZRINPUT"),

    /**
     * Zero Rated EC Goods Income
     * 
     */
    ECZROUTPUT("ECZROUTPUT"),

    /**
     * Zero Rated EC Services
     * 
     */
    ECZROUTPUTSERVICES("ECZROUTPUTSERVICES"),

    /**
     * Reverse Charge Expenses
     * 
     */
    REVERSECHARGES("REVERSECHARGES"),

    /**
     * Disallowed Expenses
     * 
     */
    BLINPUT("BLINPUT"),

    /**
     * Deemed
     * 
     */
    DSOUTPUT("DSOUTPUT"),

    /**
     * Regulation 33 Exempt Supplies
     * 
     */
    @XmlEnumValue("ES33OUTPUT")
    ES_33_OUTPUT("ES33OUTPUT"),

    /**
     * Non-Regulation 33 Exempt Supplies
     * 
     */
    @XmlEnumValue("ESN33OUTPUT")
    ESN_33_OUTPUT("ESN33OUTPUT"),

    /**
     * Imports under the Import GST Deferment Scheme
     * 
     */
    IGDSINPUT("IGDSINPUT"),

    /**
     * Imports
     * 
     */
    IMINPUT("IMINPUT"),

    /**
     * Imports under a Special Scheme
     * 
     */
    MEINPUT("MEINPUT"),

    /**
     * Purchases from Non-GST Registered Suppliers
     * 
     */
    NRINPUT("NRINPUT"),

    /**
     * Out Of Scope Purchases
     * 
     */
    OPINPUT("OPINPUT"),

    /**
     * Out Of Scope Supplies
     * 
     */
    OSOUTPUT("OSOUTPUT"),

    /**
     * Partially Exempt Traders Regulation 33 Exempt
     * 
     */
    TXESSINPUT("TXESSINPUT"),

    /**
     * Standard-Rated Purchases
     * 
     */
    TXINPUT("TXINPUT"),

    /**
     * Partially Exempt Traders Non-Regulation 33 Exempt
     * 
     */
    @XmlEnumValue("TXN33INPUT")
    TXN_33_INPUT("TXN33INPUT"),

    /**
     * Partially Exempt Traders Standard-Rated Purchases
     * 
     */
    TXPETINPUT("TXPETINPUT"),

    /**
     * Partially Exempt Traders Residual Input tax
     * 
     */
    TXREINPUT("TXREINPUT");
    private final String value;

    FixedTaxType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FixedTaxType fromValue(String v) {
        for (FixedTaxType c: FixedTaxType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
