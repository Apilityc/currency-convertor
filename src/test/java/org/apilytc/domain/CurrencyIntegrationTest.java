package org.apilytc.domain;
import org.junit.Test;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Currency.class, transactional = false)
public class CurrencyIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
}
