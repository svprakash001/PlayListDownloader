package in.shopperstreet.PlaylistDownloader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by prakash.s on 13/06/17.
 */
public class UtubeUI implements ActionListener{


    JFrame jFrame;
    JPanel jPanel;
    JButton jButton;
    JTextField jTextField;

    private String playlistId;


    ApiCaller caller;



    public static void main(String[] args) {

        UtubeUI ui = new UtubeUI();
        ui.caller = new ApiCaller();
        ui.createGui();
    }


    private void createGui(){

        jFrame = new JFrame("PlaylistDownloader");
        jPanel = new JPanel();
        jTextField = new JTextField(30);
        jButton = new JButton("Download");


        // Add to jFrame
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 200);
        jFrame.setVisible(true);
        jFrame.getContentPane().add(jPanel);

        //Add to jPanel
        jPanel.add(jTextField);
        jPanel.add(jButton);


        jTextField.requestFocus();
        jTextField.setText("Prakash");
        jPanel.revalidate();     //Revalidate.. Else UI changes will not be reflected

        //Add eventlistener to the button
        jButton.addActionListener(this);

    }


    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == jButton){

            playlistId = jTextField.getText();
            caller.makeRequest(playlistId);
        }
    }

}
