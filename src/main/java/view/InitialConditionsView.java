package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitialConditionsView extends JFrame{

    private JTextField nTextField;
    private JTextField qTextField;
    private JTextField maxSimulationTimeTextField;
    private JTextField minSimulationTimeTextField;
    private JTextField maxArrivalTimeTextField;
    private JTextField minServiceTimeTextField;
    private JTextField maxServiceTimeTextField;
    private JButton submitButton;

    public InitialConditionsView() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("SIMULATION INITIAL CONDITIONS");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(124, 39, 378, 36);
        this.getContentPane().add(titleLabel);

        JLabel nLabel = new JLabel("Number of Clients");
        nLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nLabel.setBounds(86, 144, 134, 13);
        this.getContentPane().add(nLabel);

        JLabel qLabel = new JLabel("Number of Queues");
        qLabel.setHorizontalAlignment(SwingConstants.LEFT);
        qLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        qLabel.setBounds(86, 167, 134, 13);
        this.getContentPane().add(qLabel);

        JLabel maxSimulationTimeLabel = new JLabel("MAX Simulation Time");
        maxSimulationTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        maxSimulationTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maxSimulationTimeLabel.setBounds(86, 190, 134, 13);
        this.getContentPane().add(maxSimulationTimeLabel);

        JLabel minArrivalTimeLabel = new JLabel("MIN Arrival Time");
        minArrivalTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        minArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        minArrivalTimeLabel.setBounds(86, 213, 134, 13);
        this.getContentPane().add(minArrivalTimeLabel);

        JLabel maxArrivalTimeLabel = new JLabel("MAX Arrival Time");
        maxArrivalTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        maxArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maxArrivalTimeLabel.setBounds(86, 236, 134, 13);
        this.getContentPane().add(maxArrivalTimeLabel);

        JLabel minServiceTimeLabel = new JLabel("MIN Service Time");
        minServiceTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        minServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        minServiceTimeLabel.setBounds(86, 259, 134, 13);
        this.getContentPane().add(minServiceTimeLabel);

        JLabel maxServiceTimeLabel = new JLabel("MAX Service Time");
        maxServiceTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        maxServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maxServiceTimeLabel.setBounds(86, 282, 134, 13);
        this.getContentPane().add(maxServiceTimeLabel);

        nTextField = new JTextField();
        nTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nTextField.setBounds(246, 143, 292, 19);
        this.getContentPane().add(nTextField);
        nTextField.setColumns(10);

        qTextField = new JTextField();
        qTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        qTextField.setColumns(10);
        qTextField.setBounds(246, 166, 292, 19);
        this.getContentPane().add(qTextField);

        maxSimulationTimeTextField = new JTextField();
        maxSimulationTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maxSimulationTimeTextField.setColumns(10);
        maxSimulationTimeTextField.setBounds(246, 189, 292, 19);
        this.getContentPane().add(maxSimulationTimeTextField);

        minSimulationTimeTextField = new JTextField();
        minSimulationTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        minSimulationTimeTextField.setColumns(10);
        minSimulationTimeTextField.setBounds(246, 212, 292, 19);
        this.getContentPane().add(minSimulationTimeTextField);

        maxArrivalTimeTextField = new JTextField();
        maxArrivalTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maxArrivalTimeTextField.setColumns(10);
        maxArrivalTimeTextField.setBounds(246, 235, 292, 19);
        this.getContentPane().add(maxArrivalTimeTextField);

        minServiceTimeTextField = new JTextField();
        minServiceTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        minServiceTimeTextField.setColumns(10);
        minServiceTimeTextField.setBounds(246, 258, 292, 19);
        this.getContentPane().add(minServiceTimeTextField);

        maxServiceTimeTextField = new JTextField();
        maxServiceTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maxServiceTimeTextField.setColumns(10);
        maxServiceTimeTextField.setBounds(246, 281, 292, 19);
        this.getContentPane().add(maxServiceTimeTextField);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        submitButton.setBounds(246, 369, 134, 31);
        this.getContentPane().add(submitButton);
    }

    public int getNTextField() {
        return Integer.parseInt(nTextField.getText());
    }

    public int getQTextField() {
        return Integer.parseInt(qTextField.getText());
    }

    public int getMaxSimulationTimeTextField() {
        return Integer.parseInt(maxSimulationTimeTextField.getText());
    }

    public int getMinArrivalTimeTextField() {
        return Integer.parseInt(minSimulationTimeTextField.getText());
    }

    public int getMaxArrivalTimeTextField() {
        return Integer.parseInt(maxArrivalTimeTextField.getText());
    }

    public int getMinServiceTimeTextField() {
        return Integer.parseInt(minServiceTimeTextField.getText());
    }

    public int getMaxServiceTimeTextField() {
        return Integer.parseInt(maxServiceTimeTextField.getText());
    }

    public void addSubmitListener(ActionListener action) {
        submitButton.addActionListener(action);
    }
}
