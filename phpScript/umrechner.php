<?php
if(isset($_POST['zahl'])){
    $ergebnis = $_POST["zahl"];
    $eingabe = $_POST["zahl"];

    if($_POST["rButton"]==1){
        $ergebnis/=1.6;

        echo $eingabe.'Km sind'.$ergebnis.'Meilen';


    }else if($_POST["rButton"]==0){

        $ergebnis*=1.6;

        echo $eingabe.' Meilen sind '.$ergebnis.' Kilometer';

    }



}

?>
<br>
<br>

<a href="index.html"> zurück</a>
