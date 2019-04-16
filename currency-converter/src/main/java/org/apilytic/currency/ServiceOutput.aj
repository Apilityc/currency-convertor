package org.apilytic.currency;

privileged aspect ServiceOutput {

	public int Service.output(int a) {
		return a;
	}
}
