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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private JButton baueAutos;
    private DefaultListModel listModel;
    private List<String> textList;

    public GUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.listModel = new DefaultListModel<String>();
        jList = new JList<>(listModel);


        this.jPNorth = new JPanel(new FlowLayout());
        this.textFieldPath = new JTextField(30);

        this.einlesen = new JButton("Einlesen!");

        this.baueAutos = new JButton("Baue Autos!");

        baueAutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTextList(textList);
            }
        });

        add(baueAutos, BorderLayout.SOUTH);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jList);


        einlesen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList = textRead(textFieldPath.getText());
                String[] tempArray = textList.toArray(new String[textList.size()]);
                listModel.clear();
                for(String s: tempArray)
                    listModel.addElement(s);

            }
        });

        jPNorth.add(textFieldPath);
        jPNorth.add(einlesen);

        this.add(scrollPane, BorderLayout.CENTER);
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
            JOptionPane.showMessageDialog(null,"Datei nicht gefunden","Fehler",JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textList;

    }

    public List<HashMap<String,String>> checkTextList(List<String> textList){
        System.out.println("Starte Check");
        List<HashMap<String,String>> autoText = new ArrayList<>();

        Pattern P_TYP = Pattern.compile("(Limousine|Geländewagen / Pickup|Kleinwagen|Cabrio / Roadster|Van / Minibus|Kombi|Sportwagen / Coupé|Andere)");
        Pattern P_KM = Pattern.compile("[0-9]*.?[0-9]* *(km|KM|Km|kM)");
        Pattern P_EZ = Pattern.compile("EZ *[0-9]{1,2}/[\\d]{2,4}");
        Pattern P_SCHALTUNG = Pattern.compile("(Schaltung|Automatik)|(schaltung|automatik)");
        Pattern P_Preis = Pattern.compile("\\d*\\.?\\d* ?€");
        Pattern P_KW = Pattern.compile("\\d{2,3} * kW");
        String[] daten = {  "modellMarke" ,"typ" ,"kW", "schaltung","km", "erstZulassung" ,"preis"};
        Matcher matcher;




        for(int index = 0; index < textList.size(); index++){

            HashMap<String,String> auto = new HashMap<>();
            for(String s : daten)
                auto.put(s,"N/A");

            if(textList.get(index).matches("\\w{2,3}\\s-\\s\\d{5}.*")){
                index--;

                while(!textList.get(index).startsWith("Finanzierung, Versicherung") && !textList.get(index).startsWith("FinanzierungVersicherung")){
                    if(textList.get(index+1).matches("\\w{2,3}\\s-\\s\\d{5}.*")) {
                        auto.put("modellMarke", textList.get(index));
                        index++;
                    }else{

                        matcher = P_EZ.matcher(textList.get(index));
                        if(matcher.find()){
                            auto.put("erstZulassung",matcher.group(0));
                        }

                        matcher = P_KM.matcher(textList.get(index));

                        if(matcher.find()){
                            auto.put("km",textList.get(index));
                        }

                        matcher = P_Preis.matcher(textList.get(index));
                        if(matcher.find()){
                            auto.put("preis",textList.get(index));
                        }

                        matcher = P_SCHALTUNG.matcher(textList.get(index));
                        if(matcher.find()){
                            auto.put("schaltung",textList.get(index));
                        }

                        matcher = P_TYP.matcher(textList.get(index));
                        if(matcher.find()){
                            auto.put("typ",textList.get(index));
                        }

                        matcher = P_KW.matcher(textList.get(index));
                        if (matcher.find()){
                            auto.put("kW",matcher.group(0));
                        }

                        index++;

                    }

                }//end innerWhile

                autoText.add(auto);
                System.out.println(auto);

            }


        }

        return autoText;

    }

}
