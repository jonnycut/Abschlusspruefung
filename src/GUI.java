import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KNapret on 08.06.2016.
 */
public class GUI extends JFrame {

    private JPanel jPNorth;
    private JPanel jPEast;
    private JPanel jPSouth;
    private JPanel jPWest;
    private JPanel jPCenter;
    private JTextField textFieldPath;
    private JList<String> jList;
    private JButton einlesen;
    private DefaultListModel listModel;

    public GUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.listModel = new DefaultListModel<String>();
        jList = new JList<>(listModel);


        this.jPNorth = new JPanel(new FlowLayout());
        this.textFieldPath = new JTextField(30);

        this.einlesen = new JButton("Einlesen!");


        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jList);


        einlesen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> textList = textRead(textFieldPath.getText());
                String[] tempArray = textList.toArray(new String[textList.size()]);
                listModel.clear();
                for(String s: tempArray)
                    listModel.addElement(s);
            }
        });

        jPNorth.add(textFieldPath);
        jPNorth.add(einlesen);

        this.add(scrollPane,BorderLayout.CENTER);
        this.add(jPNorth, BorderLayout.NORTH);


        pack();
        setVisible(true);




    }

    public List<String> textRead(String path){
        List<String> textList = new ArrayList<>();

        try {
            BufferedReader bR = new BufferedReader(new FileReader(path));

            String zeile = null;
            while((zeile = bR.readLine()) !=null){

                textList.add(zeile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textList;

    }

    public List<HashMap> checkTextList(List<String> textList){
        List<HashMap> autoText = new ArrayList<>();
        return autoText;

    }

}
