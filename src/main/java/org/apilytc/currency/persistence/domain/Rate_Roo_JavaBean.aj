// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.apilytc.currency.persistence.domain;

import java.util.Map;
import org.apilytc.currency.persistence.domain.Rate;

privileged aspect Rate_Roo_JavaBean {
    
    public Map<String, String> Rate.getValue() {
        return this.value;
    }
    
    public void Rate.setValue(Map<String, String> value) {
        this.value = value;
    }
    
}
