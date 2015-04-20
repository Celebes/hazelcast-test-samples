package io.github.celebes.hazelcast.test.samples;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class StartNode {
	public static void main(String[] args) {
		Hazelcast.newHazelcastInstance();
	}
}
