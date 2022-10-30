package GUI;

import javax.swing.*;
import java.awt.*;

public class MyClass {

    private String programmLogik(String text1, String text2) {
        //Strings zu zahlen machen
        double a, b;
        String Ausgabe ="Fehler";
        try {
            a = Double.parseDouble(text1);
            b = Double.parseDouble(text2);
        }catch(Exception e) {
            return "Error";
        }

        if(b-a>=50) {//miss
            Ausgabe = "Fehlschlag!";
        }
        if(b>=100-(10-a/10) || b==100) {//krit miss
            Ausgabe = "Kritischer Fehlschlag!";
        }
        if(b<=a/10 || b == 1) {//krit
            Ausgabe = "Kritischer Treffer!";
        }
        if(b>a/10 && b<100-(10-a/10) && b-a<50) {//erfolg
            Ausgabe = "Treffer!";
        }
        return Ausgabe;
    }


    private String programmLogik2(String text1, String text2, String text3, String text4) {
        double a, b, c, d;
        String Ausgabe ="Fehler";
        try {
            a = Double.parseDouble(text1);
            b = Double.parseDouble(text2);
            c = Double.parseDouble(text3);
            d = Double.parseDouble(text4);
        }catch(Exception e) {
            return "Error";
        }
        return ""+(a+b+c+d);

    }

    private JPanel mainPanel = new JPanel();

    private JPanel topPanel = new JPanel();
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JLabel result1;

    private JPanel lowerPanel = new JPanel();
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JButton button2;
    private JLabel result2;

    public static void main(String[] args) {
        MyClass app = new MyClass();
    }

    public MyClass() {
        JFrame frame = new JFrame("Hit Rechner");

        frame.setContentPane(mainPanel);
        setupUI();
        button1.addActionListener(e -> result1.setText(programmLogik(textField1.getText(), textField2.getText())));
        button2.addActionListener(e -> result2.setText(programmLogik2(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText())));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 180);
        frame.setVisible(true);
    }


    public void setupUI() {
        mainPanel.setLayout(new FlowLayout());

        label1 = new JLabel();
        label1.setText("Fähigkeitswert");
        topPanel.add(label1);

        textField1 = new JTextField(10);
        topPanel.add(textField1);

        final JPanel spacer1 = new JPanel();
        topPanel.add(spacer1);

        label2 = new JLabel();
        label2.setText("Wurf");
        topPanel.add(label2);

        textField2 = new JTextField(10);
        topPanel.add(textField2);

        final JPanel spacer2 = new JPanel();
        topPanel.add(spacer2);

        button1 = new JButton();
        button1.setText("Berechnen");
        topPanel.add(button1);

        final JPanel spacer3 = new JPanel();
        topPanel.add(spacer3);

        result1 = new JLabel("Ergebnis");
        topPanel.add(result1);

        mainPanel.add(topPanel);

        label3 = new JLabel();
        label3.setText("Fähigkeitswert 2");
        lowerPanel.add(label3);

        textField3 = new JTextField(10);
        lowerPanel.add(textField3);

        final JPanel spacer4 = new JPanel();
        lowerPanel.add(spacer4);

        label4 = new JLabel();
        label4.setText("Wurf 2");
        lowerPanel.add(label4);

        textField4 = new JTextField(10);
        lowerPanel.add(textField4);

        final JPanel spacer5 = new JPanel();
        lowerPanel.add(spacer5);

        button2 = new JButton();
        button2.setText("Berechnen");
        lowerPanel.add(button2);

        final JPanel spacer6 = new JPanel();
        lowerPanel.add(spacer6);

        result2 = new JLabel("Ergebnis 2");
        lowerPanel.add(result2);

        mainPanel.add(lowerPanel);

    }

}
