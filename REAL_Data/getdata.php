<?php
/*
** Connect to database:
*/
 
// Connect to the database (hostname, username, password)
$cont = mysql_connect('localhost','root','') 
    or die('Could not connect to the server!');
 

// Select a database:
mysql_select_db('real') 
    or die('Could not select a database.');
 
 
$count=0;

// Example query: (TOP 10 equal LIMIT 0,10 in SQL)
$SQL = "SELECT   * FROM real.solution";
 
// Execute query:
$result = mysql_query($SQL) 
    or die('A error occured: ' . sql_error());
 
// Get result count:
$Count = mysql_num_rows($result); 
$i=0; 
$c=0;
$countrow=0;
// Fetch rows:
while ($Row = mysql_fetch_assoc($result)) {
 
    
	$flag['user_id'.$i]=$Row['user_id'];
	$flag['name'.$i]=$Row['names'];
	$flag['solution'.$i]=base64_encode($Row['solutions']);
	$i++;
}

$c=$i;
$flag['countrow']=$c;

print(json_encode($flag));
mysql_close($cont);
?>
