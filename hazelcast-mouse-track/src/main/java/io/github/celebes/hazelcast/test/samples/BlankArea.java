package io.github.celebes.hazelcast.test.samples;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BlankArea extends JLabel {
	private static final long serialVersionUID = -322507249531723713L;
	
	Dimension minSize = new Dimension(100, 50);

    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}