<?php


	$cont = mysql_connect('localhost','root','') 
    or die('Could not connect to the server!');
 

// Select a database:
mysql_select_db('real') 
    or die('Could not select a database.');
 


    // Get image string posted from Android App
	$uid=$_REQUEST['id'];
	
	//$s='best';
   

$query = "UPDATE real.finalsolution "."SET status = 'best'".
       "WHERE user_id = '$uid'" ;

mysql_query($query) or die('Error, query failed');






    echo 'Image upload complete, Please check your php file directory';
?>