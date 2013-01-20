import com.soundcloud.api.ApiWrapper;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Tommy
 */
public class MainMenu extends JPanel implements ActionListener{
    public static final JFileChooser fc = new JFileChooser();
    
    private ApiWrapper wrapper;
    private Connection connection;
    private Content content;
    private String client_id;
    private String client_secret;
    private String username = "";
    private String password = "";
    
    public static void main(String[] args){
        JFrame frm = new JFrame("lsdfj");
        frm.setSize(500,750);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenu pnl = new MainMenu();
        pnl.start();
        frm.add(pnl);
        frm.setVisible(true);
    }
    public void start(){
        client_id = "59300a92df9799f95258a9ba20992375";
        client_secret = "1efc4b68c039a18d5ba9305d4ea6a0ba";

        
        fc.setCurrentDirectory(new File("./"));
        setLayout(null);
        JButton localBtn = new JButton("Play from local file");
        localBtn.addActionListener(this);
        localBtn.setActionCommand("local");
        localBtn.setBounds(100, 400, 300, 70);
        JButton cloudBtn = new JButton("Play with SoundCloud");
        cloudBtn.addActionListener(this);
        cloudBtn.setActionCommand("cloud");
        cloudBtn.setBounds(100, 500, 300, 70);
        add(localBtn);
        add(cloudBtn);
        //System.out.println("buttons added!");
    }
    public void paint(Graphics g){
        try{
            BufferedImage img;
            img = ImageIO.read(new File("images/cubism.png"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
        } catch(Exception e){
            System.err.println("could not load img for main menu");
        }
        this.paintComponents(g);
    }
    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        if (command.equals("local")){
            int response = fc.showOpenDialog(this);
            if (response == JFileChooser.APPROVE_OPTION){
                DrawPanel.setInputFile(fc.getSelectedFile());
                Main.change("draw");
            }
        } else if (command.equals("cloud")){
            //TODO choose from cloud.
            wrapper = new ApiWrapper(client_id, client_secret, null, null);
            connection = new Connection(wrapper);
            try {
                content = new Content(wrapper);
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            openLoginWindow();
            try {
                content.getContent();
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void openLoginWindow() {
        userWord frame = new userWord(connection);
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
