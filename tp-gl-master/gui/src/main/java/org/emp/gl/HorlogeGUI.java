package org.emp.gl;

import java.awt.Font;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class HorlogeGUI implements TimerChangeListener {

    private final String name;
    private final TimerService timerService;

    private final JFrame frame = new JFrame();
    private final JLabel label = new JLabel();

    public HorlogeGUI(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        System.out.println("Horloge " + name + " initialized with timer service");
        init();
    }

    private void init() {
        timerService.addTimeChangeListener(this);

        // STYLE
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 48));
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.GREEN);

        frame.setTitle("Horloge: " + name);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(label);

        // remove listener when window is closed
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                timerService.removeTimeChangeListener(HorlogeGUI.this);
            }
        });

        frame.setVisible(true);
        updateLabel();
    }

    private void updateLabel() {
        label.setText(String.format("%02d:%02d:%02d",
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::updateLabel);
        }
    }
}
