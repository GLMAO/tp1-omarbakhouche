package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;
import javax.swing.JLabel;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private int value;
    private TimerService service;
    private JLabel label;

    public CompteARebours(int val, TimerService service, JLabel label) {
        this.service = service;
        this.value = val;
        this.label = label;
        label.setText("Value : " + this.value);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        switch (event.getPropertyName()) {
            case SECONDE_PROP:
                
                if (this.value > 0) {
                    this.value -= 1;
                    label.setText("Value : " + this.value);
                } else if (this.value == 0){
                    label.setText("Value reached 0");
                    service.removeTimeChangeListener(this);
                }
                break;
        }
    }
}
