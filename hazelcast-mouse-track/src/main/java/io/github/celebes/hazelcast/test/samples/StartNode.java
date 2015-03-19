package io.github.celebes.hazelcast.test.samples;

import com.hazelcast.core.Hazelcast;

public class StartNode {
	public static void main(String[] args) {
		System.out.println("Hello Hazelcast!");
		
		Hazelcast.newHazelcastInstance();
		
		System.out.println("New Hazelcast Cluster Node Added.");
	}
}
