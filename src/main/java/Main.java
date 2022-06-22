import controller.Controller;
import view.InitialConditionsView;
import view.RealTimeView;

public class Main {

    public static void main(String[] args) {
        InitialConditionsView initialConditionsView = new InitialConditionsView();
        RealTimeView realTimeView = new RealTimeView();
        Controller controller = new Controller(initialConditionsView, realTimeView);

        initialConditionsView.setVisible(true);
    }
}
