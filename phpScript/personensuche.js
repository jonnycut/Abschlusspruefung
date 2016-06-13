"use strict"

var button = document.getElementById("starteSuche");
var ausgabe = document.getElementById("ausgabe");



button.addEventListener('click', function(e){
    var  gesuchtePerson = document.getElementById("eingabe").value;
    console.log("geht");
    personenSuche(gesuchtePerson);
});


function personenSuche(name){
   let xmlhttp = new XMLHttpRequest();

    xmlhttp.open('GET','server.php?eingabe='+name,true);
    xmlhttp.addEventListener('readystatechange',function(){
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200){
            ausgabe.innerHTML = xmlhttp.responseText;
        }
    });

    xmlhttp.send();
}