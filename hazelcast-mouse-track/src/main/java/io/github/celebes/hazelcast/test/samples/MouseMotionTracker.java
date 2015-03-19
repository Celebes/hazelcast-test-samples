package io.github.celebes.hazelcast.test.samples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class MouseMotionTracker extends JPanel implements MouseMotionListener {
	
	private HazelcastInstance client;
	private IMap map;
	
	private BlankArea blankArea;
    private JTextArea textArea;
    static final String NEWLINE = System.getProperty("line.separator");

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {

            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        UIManager.put("swing.boldMetal", Boolean.FALSE);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MouseMotionTracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        JComponent newContentPane = new MouseMotionTracker();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public MouseMotionTracker() {
        super(new GridLayout(0,1));
        blankArea = new BlankArea(Color.YELLOW);
        add(blankArea);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(200, 75));
        
        add(scrollPane);
        
        //Register for mouse events on blankArea and panel.
        blankArea.addMouseMotionListener(this);
        addMouseMotionListener(this);
        
        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        // setup hazelcast
    	ClientConfig clientConfig = new ClientConfig();
    	client = HazelcastClient.newHazelcastClient( clientConfig );
    	map = client.getMap( "mouseEvents" );
    }
    
    void eventOutput(String eventDescription, MouseEvent e) {
    	map.put( "mouseX", e.getX() );
    	map.put( "mouseY", e.getY() );
        textArea.append(eventDescription
                + " (" + e.getX() + "," + e.getY() + ")"
                + " detected on "
                + e.getComponent().getClass().getName()
                + NEWLINE);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    public void mouseMoved(MouseEvent e) {
        eventOutput("Mouse moved", e);
    }
    
    public void mouseDragged(MouseEvent e) {
        eventOutput("Mouse dragged", e);
    }
}
