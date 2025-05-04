import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculatrice extends JFrame {

    private JTextField[] inputFields = new JTextField[5];
    private JLabel resultLabel;
    private static ArrayList<String> name = new ArrayList<String>();
    private Double[] var = new Double[5];

    public Calculatrice() {
        super("Calcul DROP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 5, 5));
        setSize(700, 500);

        name.add("Prospection"); //0
        name.add("Drop de base"); //1
        name.add("Challenge"); //2
        name.add("Anomalie"); //3
        name.add("Autre"); //4

        for (int i = 0; i < 5; i++) {
            inputFields[i] = new JTextField();
            add(new JLabel( name.get(i) + ":"));
            add(inputFields[i]);
        }

        JButton calculerBtn = new JButton("Calculer");
        add(calculerBtn);

        resultLabel = new JLabel("Résultat : ");
        add(resultLabel);

        calculerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculer();
            }
        });

        setVisible(true);
    }

    private void calculer() {
        try {
            int i = 0;
            for (JTextField field : inputFields) {
                String text = field.getText();
                var[i] = Double.parseDouble(text);
                i++;
            }
            double prepp = ((250.0 - var[0])/85);
            double pp = 0.74+(1.755/(1+(Math.exp(prepp))));
            String res = String.format("%.1f", Math.min(pp *var[1]*(1+(var[2]/100))*(1+(var[3]/100))*(1+(var[4]/100)), 100));
            resultLabel.setText("Résultat : " + res + "%");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer uniquement des nombres valides.",
                                          "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Calculatrice();
    }
}
