package io.github.celebes.hazelcast.test.samples;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapEvent;

public class MouseMotionReceiver extends JFrame implements EntryListener<String, Integer> {
	private static final long serialVersionUID = -7457555302212644454L;
	private JLabel label;
	
	private HazelcastInstance client;
	private IMap map;
	
	int x = 0;
	int y = 0;

	public MouseMotionReceiver() {
		super("The title");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400, 90));
		((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
		setLayout(new FlowLayout());
		label = new JLabel("Waiting for mouse movement..");
		add(label);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		setupHazelcast();
	}
	
	private void updateJLabel(EntryEvent<String, Integer> event) {
		String key = event.getKey();
		int value = event.getValue();

		if (key.equals("mouseX")) {
			x = value;
		} else {
			y = value;
		}

		new Thread() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						label.setText("X: " + x + " | Y: " + y);
						revalidate();
					}
				});
			}
		}.start();
	}

	private void setupHazelcast() {
		ClientConfig clientConfig = new ClientConfig();
    	client = HazelcastClient.newHazelcastClient( clientConfig );
    	map = client.getMap( "mouseEvents" );
		map.addEntryListener(this, true);
		System.out.println("EntryListener registered");
	}

	@Override
	public void entryAdded(EntryEvent<String, Integer> event) {
		System.out.println("entryAdded:" + event);
		updateJLabel(event);
	}

	@Override
	public void entryRemoved(EntryEvent<String, Integer> event) {
		System.out.println("entryRemoved:" + event);
	}

	@Override
	public void entryUpdated(EntryEvent<String, Integer> event) {
		System.out.println("entryUpdated:" + event);
		updateJLabel(event);
	}

	@Override
	public void entryEvicted(EntryEvent<String, Integer> event) {
		System.out.println("entryEvicted:" + event);
	}

	@Override
	public void mapEvicted(MapEvent event) {
		System.out.println("mapEvicted:" + event);
	}

	@Override
	public void mapCleared(MapEvent event) {
		System.out.println("mapCleared:" + event);
	}

	public static void main(String[] args) {
		new MouseMotionReceiver();
	}
}