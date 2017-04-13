package org.apilytic.currency;

/**
 * Created by g on 4/13/17.
 */
privileged aspect ServiceOutput {

	public int Service.output(int a) {
		return a;
	}
}
