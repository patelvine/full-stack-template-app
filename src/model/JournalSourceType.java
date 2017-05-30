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
 * <p>Java class for journalSourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="journalSourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ACCREC"/>
 *     &lt;enumeration value="ACCPAY"/>
 *     &lt;enumeration value="ACCRECCREDIT"/>
 *     &lt;enumeration value="ACCPAYCREDIT"/>
 *     &lt;enumeration value="ACCRECPAYMENT"/>
 *     &lt;enumeration value="ACCPAYPAYMENT"/>
 *     &lt;enumeration value="ARCREDITPAYMENT"/>
 *     &lt;enumeration value="APCREDITPAYMENT"/>
 *     &lt;enumeration value="CASHREC"/>
 *     &lt;enumeration value="CASHPAID"/>
 *     &lt;enumeration value="TRANSFER"/>
 *     &lt;enumeration value="ARPREPAYMENT"/>
 *     &lt;enumeration value="APPREPAYMENT"/>
 *     &lt;enumeration value="AROVERPAYMENT"/>
 *     &lt;enumeration value="APOVERPAYMENT"/>
 *     &lt;enumeration value="EXPCLAIM"/>
 *     &lt;enumeration value="EXPPAYMENT"/>
 *     &lt;enumeration value="MANJOURNAL"/>
 *     &lt;enumeration value="PAYSLIP"/>
 *     &lt;enumeration value="WAGEPAYABLE"/>
 *     &lt;enumeration value="INTEGRATEDPAYROLLPE"/>
 *     &lt;enumeration value="INTEGRATEDPAYROLLPT"/>
 *     &lt;enumeration value="EXTERNALSPENDMONEY"/>
 *     &lt;enumeration value="INTEGRATEDPAYROLLPTPAYMENT"/>
 *     &lt;enumeration value="INTEGRATEDPAYROLLCN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "journalSourceType")
@XmlEnum
public enum JournalSourceType {


    /**
     * Accounts Receivable Invoice
     * 
     */
    ACCREC,

    /**
     * Accounts Payable Invoice
     * 
     */
    ACCPAY,

    /**
     * Accounts Receivable Credit Note
     * 
     */
    ACCRECCREDIT,

    /**
     * Accounts Payable Credit Note
     * 
     */
    ACCPAYCREDIT,

    /**
     * Payment on an Accounts Receivable Invoice
     * 
     */
    ACCRECPAYMENT,

    /**
     * Payment on an Accounts Payable Invoice
     * 
     */
    ACCPAYPAYMENT,

    /**
     * Accounts Receivable Credit Note Payment
     * 
     */
    ARCREDITPAYMENT,

    /**
     * Accounts Payable Credit Note Payment
     * 
     */
    APCREDITPAYMENT,

    /**
     * Receive Money Bank Transaction
     * 
     */
    CASHREC,

    /**
     * Spend Money Bank Transaction
     * 
     */
    CASHPAID,

    /**
     * Bank Transfer
     * 
     */
    TRANSFER,

    /**
     * Accounts Receivable Prepayment
     * 
     */
    ARPREPAYMENT,

    /**
     * Accounts Payable Prepayment
     * 
     */
    APPREPAYMENT,

    /**
     * Accounts Receivable Overpayment
     * 
     */
    AROVERPAYMENT,

    /**
     * Accounts Payable Overpayment
     * 
     */
    APOVERPAYMENT,

    /**
     * Expense Claim
     * 
     */
    EXPCLAIM,

    /**
     * Expense Claim Payment
     * 
     */
    EXPPAYMENT,

    /**
     * Manual Journal
     * 
     */
    MANJOURNAL,

    /**
     * Payslip
     * 
     */
    PAYSLIP,

    /**
     * Payroll Payable
     * 
     */
    WAGEPAYABLE,

    /**
     * Payroll Expense
     * 
     */
    INTEGRATEDPAYROLLPE,

    /**
     * Payroll Payment
     * 
     */
    INTEGRATEDPAYROLLPT,

    /**
     * Payroll Employee Payment
     * 
     */
    EXTERNALSPENDMONEY,

    /**
     * Payroll Tax Payment
     * 
     */
    INTEGRATEDPAYROLLPTPAYMENT,

    /**
     * Payroll Credit Note
     * 
     */
    INTEGRATEDPAYROLLCN;

    public String value() {
        return name();
    }

    public static JournalSourceType fromValue(String v) {
        return valueOf(v);
    }

}