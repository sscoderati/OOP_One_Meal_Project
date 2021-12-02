package display_demo;


import javax.swing.JFrame;
import facade.DataEngineInterface;

public class GUIMain {
    static DataEngineInterface reservationEngine, instantEngine;

    public static void startGUI(DataEngineInterface re, DataEngineInterface ie) {
        reservationEngine = re;
        instantEngine = ie;
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createAndShowGridGUI();
                createAndShowPOSGUI();
            }
        });
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowTableGUI(DataEngineInterface en) {
        // Create and set up the window.
        JFrame tableFrame = new JFrame("예약 / 대기 고객 관리 화면");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create and set up the content pane.
        TableSelectionDemo newContentPane = new TableSelectionDemo(en);
        newContentPane.addComponentsToPane();
        tableFrame.getContentPane().add(newContentPane);

        // Display the window.
        tableFrame.pack();
        tableFrame.setVisible(true);
    }

    /*private static void createAndShowGridGUI() {
        JFrame gridframe = new JFrame("IntroGridDemo");
        gridframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //IntroGridDemo newContentPane = new IntroGridDemo();
        //newContentPane.frameSetup();
        //gridframe.getContentPane().add(newContentPane);

        gridframe.setSize(320, 280);
        gridframe.setVisible(true);
    }*/

    public static void createAndShowPOSGUI() {
        JFrame frame = new JFrame("Pos-Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PosMainDisplay table = new PosMainDisplay();
        table.setupTablePane();
        frame.add(table);

        frame.setSize(700, 480);
        frame.setVisible(true);
    }

    public void createAndShowTableInfoGUI() {
        JFrame frame = new JFrame("Table-Data");

        TableInformationDisplay right = new TableInformationDisplay();
        right.setControlPane();
        frame.add(right);

        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}