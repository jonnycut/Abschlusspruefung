import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;
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
    private JList jList;
    private JButton einlesen;
    private JButton sucheAutos;
    private JButton baueAutos;
    private DefaultListModel listModel;
    private List<String> textList;
    List<TreeMap<String, String>> autoTextList;
    List<Auto> autoObjectList;

    public GUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.listModel = new DefaultListModel<String>();



        jList = new JList<>(listModel);
        jList.setCellRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel cell = new JLabel();
                cell.setOpaque(true);
                cell.setBackground(Color.gray);
                cell.setText(value);
                return cell;
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu dateiMenue = new JMenu("Datei");
        JMenuItem dateiOeffnen = new JMenuItem("Öffnen");
        JMenuItem dateiSpeichern = new JMenuItem("Speichern");

        ActionListener dateiOeffnenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                textList = textRead(fileChooser.getSelectedFile().getPath());
                listModel.clear();
                for(String s : textList){
                    listModel.addElement(s);
                }

            }
        };

        dateiOeffnen.addActionListener(dateiOeffnenListener);

        dateiMenue.add(dateiOeffnen);
        dateiMenue.add(dateiSpeichern);
        dateiSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Auto> toSaveList = jList.getSelectedValuesList();

                autosSpeichern(toSaveList);
            }
        });
        menuBar.add(dateiMenue);
        setJMenuBar(menuBar);




        this.jPNorth = new JPanel(new FlowLayout());
        this.textFieldPath = new JTextField(30);

        this.einlesen = new JButton("Einlesen!");

        this.sucheAutos = new JButton("Finde Autos!");

        sucheAutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoTextList = checkTextList(textList);

                listModel = new DefaultListModel<TreeMap>();
                jList.setModel(listModel);
                jList.setCellRenderer(new ListCellRenderer<TreeMap>() {

                    @Override
                    public Component getListCellRendererComponent(JList<? extends TreeMap> list, TreeMap value, int index, boolean isSelected, boolean cellHasFocus) {
                        JLabel cell = new JLabel();
                        cell.setBackground(Color.gray);
                        cell.setOpaque(true);
                        cell.setText(value.toString());
                        if(isSelected){
                            cell.setBackground(Color.DARK_GRAY);
                        }
                        return cell;
                    }
                });

                for (TreeMap tM : autoTextList) {

                    listModel.addElement(tM);
                }
            }
        });

        this.baueAutos = new JButton("Baue Autos!");
        baueAutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listModel = new DefaultListModel<Auto>();
                jList.setModel(listModel);
                jList.setCellRenderer(new ListCellRenderer<Auto>() {
                    @Override
                    public Component getListCellRendererComponent(JList<? extends Auto> list, Auto value, int index, boolean isSelected, boolean cellHasFocus) {
                        JPanel cell = new JPanel();
                        cell.setBackground(Color.LIGHT_GRAY);
                        cell.setOpaque(true);

                        cell.add(new JLabel(value.getModellMarke()));
                        JLabel jLpreis =new JLabel(value.getPreis() +" €");

                        cell.add(jLpreis);
                        cell.add(new JLabel(value.getKm()+" Km"));

                        if(isSelected){
                            cell.setBackground(Color.gray);
                        }

                        if(hasFocus())
                            cell.setBackground(Color.WHITE);
                        return cell;
                    }
                });


                autoObjectList = baueAutos(autoTextList);



                for(Auto a : autoObjectList){
                    listModel.addElement(a);
                    System.out.println(a);
                }
                jList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(e.getClickCount()==2){
                            //Position des Clicks auf einen Index der Liste umrechnen
                            int index = jList.locationToIndex(e.getPoint());

                            //Element (Auto) der Liste an INDEX holen
                            Auto a = (Auto) listModel.getElementAt(index);

                            //Label und Textfield des Optionpanes erstellen
                            JLabel tempLable = new JLabel("Preis ändern:");
                            JTextField tempText = new JTextField();
                            tempText.setText(a.getPreis()+"");

                            //Beides in ein Array um es der OptionPane mitzugeben
                            Object[] tempArray = {tempLable,tempText};

                            int result = JOptionPane.showConfirmDialog(null,tempArray,"Neuer Preis: ",JOptionPane.OK_CANCEL_OPTION);
                            if(result==JOptionPane.OK_OPTION){
                                a.setPreis(Integer.parseInt(tempText.getText()));
                                jList.repaint();
                            }else if(result==JOptionPane.CANCEL_OPTION){

                            }
                        }



                    }
                });

            }
        });


        jPSouth = new JPanel();
        jPSouth.add(sucheAutos);
        jPSouth.add(baueAutos);
        add(jPSouth, BorderLayout.SOUTH);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jList);


        einlesen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList = textRead(textFieldPath.getText());
                String[] tempArray = textList.toArray(new String[textList.size()]);
                listModel.clear();
                for (String s : tempArray)
                    listModel.addElement(s);

            }
        });

        jPWest = new JPanel();
        jPWest.setLayout(new BoxLayout(jPWest,BoxLayout.Y_AXIS));
        jPWest.add(new JLabel("Sortieren"));

        JRadioButton aufsteigend = new JRadioButton("Aufsteigend");
        JRadioButton absteigend = new JRadioButton("Absteigend");
        ButtonGroup br = new ButtonGroup();
        br.add(aufsteigend);
        br.add(absteigend);

        jPWest.add(aufsteigend);
        jPWest.add(absteigend);
        jPWest.add(new JPopupMenu.Separator());



        JRadioButton nachKW = new JRadioButton("nach kW");
        JRadioButton nachPreis = new JRadioButton("nach Preis");
        JRadioButton nachKm = new JRadioButton("nach Laufleistung");

        ButtonGroup br2 = new ButtonGroup();
        br2.add(nachKm);
        br2.add(nachKW);
        br2.add(nachPreis);

        JButton sortieren = new JButton("Sortiere");

        sortieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aufsteigend.isSelected()) {
                    if (nachKm.isSelected()){
                        Collections.sort(autoObjectList, Auto.KM_ORDER);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }
                    }else if(nachKW.isSelected()){
                        Collections.sort(autoObjectList, Auto.kW_ORDER);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }
                    }else if(nachPreis.isSelected()){
                        Collections.sort(autoObjectList, Auto.PREIS_ORDER);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }

                    }else{
                        Collections.sort(autoObjectList);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }
                    }

                } else if (absteigend.isSelected()) {
                    if (nachKm.isSelected()){
                        Collections.sort(autoObjectList, Auto.KM_ORDER_DESC);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }
                    }else if(nachKW.isSelected()){
                        Collections.sort(autoObjectList, Auto.kW_ORDER_DESC);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }
                    }else if(nachPreis.isSelected()){
                        Collections.sort(autoObjectList, Auto.PREIS_ORDER_DESC);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }

                    }else{
                        Collections.sort(autoObjectList,Auto.DESC);
                        listModel.clear();
                        for(Auto a:autoObjectList){
                            listModel.addElement(a);
                        }
                    }
                }else{
                    Collections.sort(autoObjectList);
                    listModel.clear();
                    for(Auto a: autoObjectList)
                        listModel.addElement(a);
                }
            }
        });

        jPWest.add(nachKm);
        jPWest.add(nachKW);
        jPWest.add(nachPreis);
        jPWest.add(sortieren);

        add(jPWest, BorderLayout.EAST);



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

    public List<TreeMap<String,String>> checkTextList(List<String> textList){
        System.out.println("Starte Check");
        List<TreeMap<String,String>> autoText = new ArrayList<>();

        Pattern P_TYP = Pattern.compile("(Limousine|Geländewagen / Pickup|Kleinwagen|Cabrio / Roadster|Van / Minibus|Kombi|Sportwagen / Coupé|Andere)");
        Pattern P_KM = Pattern.compile("(\\d*.?\\d*)( *(km|KM|Km|kM))");
        Pattern P_EZ = Pattern.compile("(EZ\\s)(\\d{1,2}/\\d{2,4})");
        Pattern P_SCHALTUNG = Pattern.compile("(Schaltung|Automatik)|(schaltung|automatik)");
        Pattern P_Preis = Pattern.compile("(\\d*\\.?\\d*)( ?€)");
        Pattern P_KW = Pattern.compile("(\\d{2,3})( * kW)");
        String[] daten = {"modellMarke" ,"typ" ,"kW", "schaltung","km", "erstZulassung" ,"preis"};
        Matcher matcher;




        for(int index = 0; index < textList.size(); index++){

            TreeMap<String,String> auto = new TreeMap<>();
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
                            auto.put("erstZulassung",matcher.group(2));
                        }

                        matcher = P_KM.matcher(textList.get(index));

                        if(matcher.find()){
                            auto.put("km",matcher.group(1));
                        }

                        matcher = P_Preis.matcher(textList.get(index));
                        if(matcher.find()){
                            auto.put("preis",matcher.group(1));
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
                            auto.put("kW",matcher.group(1));
                        }

                        index++;

                    }

                }//end innerWhile

                autoText.add(auto);


            }


        }
        System.out.println(autoText);
        return autoText;

    }

    public List<Auto> baueAutos(List<TreeMap<String,String>> autoTextList){

        List<Auto> autoObjectList = new ArrayList<>();

        for(TreeMap tM : autoTextList){
            Auto tempAuto = new Auto(tM);
            autoObjectList.add(tempAuto);

        }


        return autoObjectList;

    }

    public void autosSpeichern(List<Auto> speicherListe) {

        try(BufferedWriter bW = new BufferedWriter(new FileWriter("autoSpeicher.txt"))){
            for(Auto a : speicherListe){
                bW.write(a.toString());
                bW.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
