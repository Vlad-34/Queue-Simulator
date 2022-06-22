package controller;

import model.Server;
import model.Task;
import view.InitialConditionsView;
import view.RealTimeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Controller implements Runnable {
    private int n;
    private int q;
    private int maxSimulationTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;

    private InitialConditionsView initialConditionsView;
    private RealTimeView realTimeView;
    private List<Task> tasks;
    private List<Server> servers;
    private ArrayList<Task> servedTasks;
    private Thread thread;

    public Controller(InitialConditionsView initialConditionsView, RealTimeView realTimeView) {
        this.initialConditionsView = initialConditionsView;
        this.realTimeView = realTimeView;

        this.tasks = new ArrayList<>();
        this.servers = new ArrayList<>();
        this.servedTasks = new ArrayList<>();
        this.thread = new Thread(this);

        this.initialConditionsView.addSubmitListener(new AddSubmitListener());
    }

    public class AddSubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                getData();
                generate(minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
                for(int i = 0; i < q; i++) // initialize servers
                    servers.add(new Server());
                initialConditionsView.setVisible(false);
                realTimeView.setVisible(true);
                thread.start();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(initialConditionsView, "Bad input. Try again with integers.");
            }
        }
    }

    public void getData() throws NumberFormatException {
        this.n = initialConditionsView.getNTextField();
        this.q = initialConditionsView.getQTextField();
        this.maxSimulationTime = initialConditionsView.getMaxSimulationTimeTextField();
        this.minArrivalTime = initialConditionsView.getMinArrivalTimeTextField();
        this.maxArrivalTime = initialConditionsView.getMaxArrivalTimeTextField();
        this.minServiceTime = initialConditionsView.getMinServiceTimeTextField();
        this.maxServiceTime = initialConditionsView.getMaxServiceTimeTextField();
    }

    public void generate(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime) {
        Random random = new Random(System.currentTimeMillis()); // randomly generate clients
        for (int i = 0; i < this.n; i++) {
            int arrivalTime = this.minArrivalTime + Math.abs(random.nextInt()) % (this.maxArrivalTime - this.minArrivalTime + 1);
            int serviceTime = this.minServiceTime + Math.abs(random.nextInt()) % (this.maxServiceTime - this.minServiceTime + 1);
            this.tasks.add(new Task(i, arrivalTime, serviceTime));
        }
        Collections.sort(this.tasks); // sort clients by arrivalTime
        System.out.println(this.tasks.toString());
    }

    public void updateUI() {
        String result = "";
        for(int i = 1; i <= this.q; i++) {
            result += "Server " + i + ": " + this.servers.get(i - 1).toString() + "\n";
        }
        realTimeView.setLoggingTextArea(result);
    }

    public void log(int currentTime) {
        String result = "Time " + currentTime + "\n";
        for(int i = 1; i <= this.q; i++) {
            result += "Server " + i + ": " + this.servers.get(i - 1).toString() + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Vlad Ursache\\OneDrive - Technical University of Cluj-Napoca\\Desktop\\22\\TP\\Laburi\\tp2022_30228_ursache_vlad_assignment_2\\logs.txt", true);
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("An error occurred while logging to file.");
        }
    }

    public void log(float avgTime, int peakHour) {
        String result = "\nAverage Service Time: " + avgTime + "\n" + "Peak hour: " + peakHour + "\n";
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Vlad Ursache\\OneDrive - Technical University of Cluj-Napoca\\Desktop\\22\\TP\\Laburi\\tp2022_30228_ursache_vlad_assignment_2\\logs.txt", true);
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("An error occurred while logging to file.");
        }
    }

    public void stats(int currentTime, int maxLength, int peakHour) {
        float avgWaitingTime = 0, avgServiceTime = 0;
        for(int i = 0; i < this.servers.size(); i++) {
            if(this.servers.get(i).getTasks().size() > maxLength){
                maxLength = this.servers.get(i).getTasks().size();
                peakHour = currentTime;
            }
            avgWaitingTime += this.servers.get(i).getWaitingPeriod();
            if(this.servers.get(i).getTasks().peek() != null)
                avgServiceTime += this.servers.get(i).getTasks().peek().getServiceTime();
        }

        avgWaitingTime /= this.servers.size();
        avgServiceTime /= this.servers.size();
        realTimeView.setAverageWaitingTimeLabel(avgWaitingTime);
        realTimeView.setAverageServiceTimeLabel(avgServiceTime);
    }

    @Override
    public void run() {
        int currentTime = 0;
        int i = 0;
        int maxLength = 0;
        int peakHour = 0;

        while (currentTime <= this.maxSimulationTime) {
            while(i < this.tasks.size()) {
                if(this.tasks.get(i).getArrivalTime() == currentTime){
                    this.dispatchTask(this.tasks.get(i));

                    Task t = new Task(this.tasks.get(i).getId(), this.tasks.get(i).getArrivalTime(), this.tasks.get(i).getServiceTime());
                    this.servedTasks.add(t);
                    i++;
                }
                else{

                    stats(currentTime, maxLength, peakHour);
                    realTimeView.setTimeLabel(currentTime);
                    updateUI();

                    currentTime++;
                    log(currentTime);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            int nullServers = 0;
            for(int j = 0; j < this.servers.size(); j++) {
                if(this.servers.get(j).getTasks().peek() == null)
                    nullServers++;
            }
            if(nullServers != this.servers.size()){
                stats(currentTime, maxLength, peakHour);

                realTimeView.setTimeLabel(currentTime);
                updateUI();

                log(currentTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTime++;
            }else {
                stats(currentTime, maxLength, peakHour);

                realTimeView.setTimeLabel(currentTime);
                updateUI();
                log(currentTime);
                currentTime++;
                break;
            }
        }
        float avgService = 0;
        for(int j = 0; j < this.servedTasks.size(); j++){
            avgService += this.servedTasks.get(j).getServiceTime();
        }
        avgService /= this.servedTasks.size();
        log(avgService, peakHour);
    }

    public void dispatchTask(Task task) {
        int minTime = Integer.MAX_VALUE, serverIndex = 0;

        for(int i = 0; i < this.servers.size(); i++)
            if(this.servers.get(i).getWaitingPeriod() < minTime) {
                minTime = this.servers.get(i).getWaitingPeriod();
                serverIndex = i;
            }
        Server optimalServer = this.servers.get(serverIndex);
        optimalServer.addTask(task);
        optimalServer.setWaitingPeriod(optimalServer.getWaitingPeriod() + task.getServiceTime());
    }
}