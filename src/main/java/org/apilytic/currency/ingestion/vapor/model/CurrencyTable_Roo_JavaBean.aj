// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.apilytic.currency.ingestion.vapor.model;

import java.util.Set;
import org.apilytic.currency.ingestion.vapor.model.CurrencyCode;
import org.apilytic.currency.ingestion.vapor.model.CurrencyTable;

privileged aspect CurrencyTable_Roo_JavaBean {
    
    public Set<CurrencyCode> CurrencyTable.getCurrencyCodes() {
        return this.currencyCodes;
    }
    
    public void CurrencyTable.setCurrencyCodes(Set<CurrencyCode> currencyCodes) {
        this.currencyCodes = currencyCodes;
    }
    
}