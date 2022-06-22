package view;

import javax.swing.*;
import java.awt.Font;

public class RealTimeView extends JFrame {

    private JTextArea loggingTextArea;
    private JLabel timeLabel;
    private JLabel averageWaitingTimeLabel;
    private JLabel averageServiceTimeLabel;

    public RealTimeView() {
        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("SIMULATION");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        titleLabel.setBounds(225, 39, 174, 36);
        this.getContentPane().add(titleLabel);

        loggingTextArea = new JTextArea();
        loggingTextArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        loggingTextArea.setLineWrap(true);
        loggingTextArea.setTabSize(4);
        loggingTextArea.setBounds(53, 95, 520, 320);
        this.getContentPane().add(loggingTextArea);

        timeLabel = new JLabel("Time 0");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timeLabel.setBounds(510, 72, 63, 13);
        this.getContentPane().add(timeLabel);

        averageWaitingTimeLabel = new JLabel("AVG Waiting Time");
        averageWaitingTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        averageWaitingTimeLabel.setBounds(53, 72, 165, 13);
        this.getContentPane().add(averageWaitingTimeLabel);

        averageServiceTimeLabel = new JLabel("AVG Service Time");
        averageServiceTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        averageServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        averageServiceTimeLabel.setBounds(230, 72, 165, 13);
        this.getContentPane().add(averageServiceTimeLabel);
    }

    public void setLoggingTextArea(String string) {
        this.loggingTextArea.setText(string);
    }

    public void setAverageWaitingTimeLabel(float seconds) {
        this.averageWaitingTimeLabel.setText("AVG Waiting Time: " + seconds);
    }

    public void setAverageServiceTimeLabel(float seconds) {
        this.averageServiceTimeLabel.setText("AVG Service Time: " + seconds);
    }

    public void setTimeLabel(int seconds) {
        this.timeLabel.setText("Time " + seconds);
    }
}