<?php
/**
 * Created by IntelliJ IDEA.
 * User: KNApret
 * Date: 13.06.2016
 * Time: 17:59
 */

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


if (isset($_GET['eingabe']) && $_GET['eingabe'] != "*") {
    $gesuchtePerson = $_GET['eingabe'];

    foreach ($personen as $key => $value) {
        if ($key == $gesuchtePerson) {
            $tHead = "<table><thead><tr><th>Name </th><th>Alter </th><th>Familenstand </th><th>Wohnort </th></tr></thead><tbody>";
            $tInhalt = "<tr><td> " . $key . "</td><td> " . $value['alter'] . "</td><td> " . $value['Familienstand'] . "</td><td> " . $value['Wohnort'] . "</td></tr></tbody></table>";


            echo $tHead . $tInhalt;
            return;
        }

    }
    echo "Person nicht gefunden!";

} elseif ($_GET['eingabe'] == "*") {
    $tHead = $tHead = "<table><thead><tr><th>Name </th><th>Alter </th><th>Familenstand </th><th>Wohnort </th></tr></thead><tbody>";
    $tInhalt ="";
    foreach($personen as $key =>$value){

        $tInhalt.="<tr><td>".$key."</td><td>".$value['alter']."</td><td>".$value['Familienstand']."</td><td>".$value['Wohnort']."</td></tr>";


    }

    $tInhalt.="</tbody></table>";

    echo $tHead.$tInhalt;


} else {
    echo "Fehler in der Übertragung";
    return;
}







