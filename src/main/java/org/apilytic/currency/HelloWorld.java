package org.apilytic.currency;

import io.reactivex.Flowable;

/**
 * Created by g on 4/2/17.
 */
public class HelloWorld {

	public int sum(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		Flowable.just("Hello world").subscribe(System.out::println);
	}
}
