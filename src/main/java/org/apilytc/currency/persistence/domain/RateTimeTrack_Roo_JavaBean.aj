// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.apilytc.currency.persistence.domain;

import java.util.Calendar;
import org.apilytc.currency.persistence.domain.RateTimeTrack;

privileged aspect RateTimeTrack_Roo_JavaBean {
    
    public Calendar RateTimeTrack.getModifiedAt() {
        return this.modifiedAt;
    }
    
    public void RateTimeTrack.setModifiedAt(Calendar modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    
}
