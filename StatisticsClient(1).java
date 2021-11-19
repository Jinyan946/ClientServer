package top.itwinkle.publib;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

/**
 * HW 11 - client
 *
 * <p> Purdue University -- CS 180 -- 2021</p>
 *
 * @author Jin Yan Purdue CS
 *
 * @version Nov 10th, 2021
 */

public class StatisticsClient {

    public static boolean strisint(String arg){
        char portNumber;
        for(int i= 0 ; i < arg.length() ; i++) {
            portNumber = arg.charAt(i);
            if (portNumber < '0' || portNumber > '9') {
                return false;
            }
        }
        return  true;
    }



    public static void main(String [] args) throws IOException {
        Socket socket;
        JOptionPane.showMessageDialog(null, "Welcome!",
                "Client", JOptionPane.INFORMATION_MESSAGE );

        String enterHostName = JOptionPane.showInputDialog(null, "Enter your host name",
                "Client", JOptionPane.QUESTION_MESSAGE);
        if (enterHostName == null){
            JOptionPane.showMessageDialog(null, "See you next time!",
                    "Client", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        String enterPortNumber = JOptionPane.showInputDialog(null, "Enter your port number",
                "Client", JOptionPane.QUESTION_MESSAGE);
        if (enterPortNumber == null) {
            JOptionPane.showMessageDialog(null, "See you next time!",
                    "Client", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        if (!(strisint(enterPortNumber))) {
            JOptionPane.showMessageDialog(null, "Error",
                    "Client", JOptionPane.ERROR_MESSAGE );
            System.exit(0);
        }

        int portNumber = Integer.parseInt(enterPortNumber);

        try {
            socket = new Socket(enterHostName, portNumber);
            JOptionPane.showMessageDialog(null, "Connected",
                    "Client", JOptionPane.INFORMATION_MESSAGE);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

/*            String s1 = reader.readLine();
            JOptionPane.showMessageDialog(null, "Connected",
                    "Client", JOptionPane.INFORMATION_MESSAGE);*/


            while (true) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Would you like to enter a text ?", "Client",JOptionPane.YES_NO_OPTION);
                System.out.println(reply);
                if (reply == 1) {
                    JOptionPane.showMessageDialog(null, "See you next time!",
                            "Client", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                String text = JOptionPane.showInputDialog(null, "Enter your text",
                        "Client", JOptionPane.QUESTION_MESSAGE);

                if (text == null) {
                    JOptionPane.showMessageDialog(null, "See you next time!",
                            "Client", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error",
                            "Client", JOptionPane.ERROR_MESSAGE );
                } else {
                    writer.write(text);
                    writer.println();
                    writer.flush();

                    String statistics = reader.readLine() +"\n" + reader.readLine() +"\n" + reader.readLine()
                            +"\n" + reader.readLine() +"\n"
                            + reader.readLine() +"\n" + reader.readLine();

                    JOptionPane.showMessageDialog(null, statistics,
                            "Client", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch(UnknownHostException e) {
            System.out.println("Error");
            JOptionPane.showMessageDialog(null, "Error",
                    "Client", JOptionPane.ERROR_MESSAGE );
            System.exit(0);
        }
    }
}
