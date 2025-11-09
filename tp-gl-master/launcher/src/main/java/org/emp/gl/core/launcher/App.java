package org.emp.gl.core.launcher;

import javax.swing.*;
import java.awt.*;

import org.emp.gl.HorlogeGUI;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

public class App {

    public static void main(String[] args) {

        // Initialise le timer (il tick automatiquement)
        TimerService timer = new DummyTimeServiceImpl();

        // Affiche ton horloge (comme tu avais déjà)
        new HorlogeGUI("Horloge 1", timer);

        // ---- Fenêtre Compte à Rebours ----

        JFrame frame = new JFrame("Compte à Rebours");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Aucun compte lancé");
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton bouton = new JButton("Lancer un compte");

        bouton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame,
                "Entrez la valeur du compte à rebours :");

            if (input != null && input.matches("\\d+")) {
                int val = Integer.parseInt(input);

                // Créer un nouveau compte à rebours avec le label
                CompteARebours c = new CompteARebours(val, timer, label);

                // On l'abonne au timer
                timer.addTimeChangeListener(c);
            }
        });

        frame.add(label);
        frame.add(bouton);
        frame.setVisible(true);
    }
}
