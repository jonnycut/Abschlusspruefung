"use strict";

var textArea = document.getElementById("textArea");

textArea.addEventListener('keypress', function(){


    let eingabeString = textArea.value;

    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open('GET','serverHash.php?eingabeString='+eingabeString,true);

    xmlhttp.addEventListener('readystatechange',function(){

        if(xmlhttp.readyState ===4 &&xmlhttp.status ===200 ){

            document.getElementById("md5Ausgabe").innerHTML = xmlhttp.responseText.split(";")[0];
            document.getElementById("sha1Ausgabe").innerHTML = xmlhttp.responseText.split(";")[1];
            console.log(xmlhttp.responseText);

        }

    });

    xmlhttp.send();




});