/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button button;
    @FXML
    private Button send;
    ArrayList<String> filesPath = new ArrayList<String>();
    ArrayList<String> filesName = new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    File[] list;

    @FXML
    void action(ActionEvent event) {
        if (event.getSource() == button) {
            list = new DirectoryChooser().showDialog(null).listFiles();

            for (File f : list) {
//                System.out.println(f.getName());
//                System.out.println(f.getAbsolutePath());
                filesPath.add(f.getAbsolutePath());
                filesName.add(f.getName());

            }

        }
        // action button send to server .... 
        if (event.getSource() == send) {

            int numberOfFiles = list.length;
            // parser1 files
            int parser1 = numberOfFiles / 2;
            // parser2 files
            int counter = 0;
//            int parser2 = numberOfFiles - parser1;
            //sendnumber of files to parser1 
//            for (int i = 0; i < parser1; i++) {
//                counter++;
            try {
//                    System.out.println(counter);
                // send Some files To Parser 1 ....... 
                FileSender(filesPath.get(0), filesName.get(0), "127.0.0.1", 4444);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            }
            // send Another files to parser2 
//            for (int i = counter + 1; i < numberOfFiles; i++) {
//                try {
//                    System.out.println(i);
//                    // send Some files To Parser 1 ....... 
//                    FileSender(filesPath.get(i), filesName.get(i), "127.0.0.1", 55555);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }

        }
    }

    public void FileSender(String FilePath, String fileName, String ip, int port) throws IOException {
        Socket socket = null;

        socket = new Socket(ip, port);

        File file = new File(FilePath);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
//     din.
        dout.writeUTF(fileName);
        dout.flush();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        String name = din.readUTF();
        System.out.println(name + "    ******************************************************* ");
        dout.close();
        din.close();
        out.close();
        in.close();
        socket.close();

    }

}
