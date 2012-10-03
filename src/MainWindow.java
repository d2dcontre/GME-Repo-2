
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class MainWindow extends JFrame {
    
    private MainWindow() {
    }
    
    public static MainWindow getInstance() {
        return MainWindowHolder.INSTANCE;
    }
    
    private static class MainWindowHolder {

        private static final MainWindow INSTANCE = new MainWindow();
    }
}
