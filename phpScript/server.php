<?php
/**
 * Created by IntelliJ IDEA.
 * User: KNApret
 * Date: 13.06.2016
 * Time: 17:59
 */

/*TestArray mit Personen*/
$personen = [

    'Kai' => [
        'alter' => 20,
        'Familienstand' => 'verheiratet',
        'Wohnort' => 'Grevenbroich'

    ],

    'Opa' => [
        'alter' => 70,
        'Familienstand' => 'verheiratet',
        'Wohnort' => 'Am Arsch der Welt'
    ],

    'Flurry' => [

        'alter' => 34,
        'Familienstand' => 'ledig',
        'Wohnort' => 'Nicht so ganz am Arsch der Welt'
    ]];


/*Wenn Variable gesetzt und nicht "*" als Eingabe kommt*/
if (isset($_GET['eingabe']) && $_GET['eingabe'] != "*") {
    $gesuchtePerson = $_GET['eingabe'];

    /*Array durchjuckeln, wenn $Key = $gesuchtePerson, ReturnString mit HTML Tabelle bauen
    mit $value hat man das Array der jeweiligen Person in der Hand, mit den entsprechenden
    Schlüsseln ('alter','Familienstand' etc) kommt man dann an die Werte des Arrays*/

    foreach ($personen as $key => $value) {
        if ($key == $gesuchtePerson) {
            $tHead = "<table><thead><tr><th>Name </th><th>Alter </th><th>Familenstand </th><th>Wohnort </th></tr></thead><tbody>";
            $tInhalt = "<tr><td> " . $key . "</td><td> " . $value['alter'] . "</td><td> " . $value['Familienstand'] . "</td><td> " . $value['Wohnort'] . "</td></tr></tbody></table>";

            /*Gebaute String zusammen zurückgeben, dies wird dann im script in die DIV Box geschrieben*/
            echo $tHead . $tInhalt;
            return;
        }

    }
    echo "Person nicht gefunden!";

} elseif (isset($_GET['eingabe'])&& $_GET['eingabe'] == "*") {
    /*Bei eingabe = * werden alle Personen des Arrays in eine Tabelle geschrieben und diese als Ganzes returnt*/

    $tHead = $tHead = "<table><thead><tr><th>Name </th><th>Alter </th><th>Familenstand </th><th>Wohnort </th></tr></thead><tbody>";
    $tInhalt ="";
    foreach($personen as $key =>$value){

        $tInhalt.="<tr><td>".$key."</td><td>".$value['alter']."</td><td>".$value['Familienstand']."</td><td>".$value['Wohnort']."</td></tr>";


    }

    $tInhalt.="</tbody></table>";

    echo $tHead.$tInhalt;


}elseif(isset($_POST['personenDaten'])){

    $personenDaten = json_decode($_POST['personenDaten']);
    $key = $personenDaten[0];
    $value = [
        'alter'=> $personenDaten[1],
        'Familienstand' => $personenDaten[2],
        'Wohnort' => $personenDaten[3]
    ];

    $personen[$key] = $value;

    //Hinzufügen funktioniert, $Personen wird aber nicht gespeichert....
    echo "Person wurde hinzugefügt: ".$personenDaten[1];

} else {

    echo "Fehler in der Übertragung";
    return;
}







