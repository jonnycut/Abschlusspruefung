<?php
/**
 * Created by IntelliJ IDEA.
 * User: KNapret
 * Date: 15.06.2016
 * Time: 09:23
 */


if(isset($_GET['eingabeString'])){

    $md5Hash = md5($_GET['eingabeString']);
    $sha1Hash = sha1($_GET['eingabeString']);

    echo $md5Hash.";".$sha1Hash;

}



