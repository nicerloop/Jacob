package com.guujiang.jacob.samples;

import static com.guujiang.jacob.Stub.yield;

import java.util.function.Predicate;

import com.guujiang.jacob.annotation.Generator;
import com.guujiang.jacob.annotation.GeneratorMethod;
import com.guujiang.jacob.annotation.OverRangePolicy;

@Generator
public class BasicExample {

	@GeneratorMethod(overIterate=OverRangePolicy.PreFetch)
	public static Iterable<Integer> range(int start, int end) {
		for (int i = start; i < end; ++i) {
			yield(i);
		}
		return null;
	}

	// the generator method can be infinite, just consume as need.
	@GeneratorMethod
	public static Iterable<Integer> infiniteFib() {
		int a = 1;
		int b = 1;
		for (;;) {
			yield(a);
			int c = a + b;
			a = b;
			b = c;
		}
	}

	// with the coroutine syntax, many functional method can by implemented in a
	// straightforward way

	@GeneratorMethod(overIterate=OverRangePolicy.PreFetch)
	public static <T> Iterable<T> filter(Iterable<T> source, Predicate<T> predicate) {
		for (T val : source) {
			if (predicate.test(val)) {
				yield(val);
			}
		}
		return null;
	}

	@GeneratorMethod(overIterate=OverRangePolicy.PreFetch)
	public static <T> Iterable<T> take(Iterable<T> source, int n) {
		int count = 0;
		for (T val : source) {
			if (count++ >= n) {
				break;
			}
			yield(val);
		}
		return null;
	}

	public static void main(String[] args) {
		
		// the generator method can accept arguments.
		range(5, 10).forEach(System.out::println);

		System.out.println("the first 10 even numbers in the fibonacci series: ");
		take(filter(infiniteFib(), x -> x % 2 == 0), 10).forEach(System.out::println);
	}
}
