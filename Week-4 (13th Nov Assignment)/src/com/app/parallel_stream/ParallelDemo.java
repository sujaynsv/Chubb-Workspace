package com.app.parallel_stream;
import java.util.stream.LongStream;

public class ParallelDemo {
	
	public static void main(String[] args) {
		long time =System.currentTimeMillis();
		//System.out.println(LongStream.range(0, 10000000).parallel().sum());
		System.out.println(LongStream.range(0, 10000000).sum());
		System.out.println(System.currentTimeMillis()-time);
	}

}
