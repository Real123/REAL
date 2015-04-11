<?php


	$cont = mysql_connect('localhost','root','') 
    or die('Could not connect to the server!');
 

// Select a database:
mysql_select_db('real') 
    or die('Could not select a database.');
 


    // Get image string posted from Android App
	$uid=$_REQUEST['uid'];
	$filename = $_REQUEST['filename'];

    $base=$_REQUEST['solution'];
    // Get file name posted from Android App

    

    // Decode Image
    $binary=base64_decode($base);

    header('Content-Type: bitmap; charset=utf-8');

    // Images will be saved under 'www/imgupload/uplodedimages' folder
    $file = fopen('uploadSolution/'.$filename, 'wb');
    // Create File
    fwrite($file, $binary);
    fclose($file);


$query = "INSERT INTO real.solution (user_id, names, solutions ) ".
"VALUES ('$uid', '$filename', '$binary')";

mysql_query($query) or die('Error, query failed');






    echo 'Image upload complete, Please check your php file directory';
?>