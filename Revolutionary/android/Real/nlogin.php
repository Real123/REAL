<?php

	//$username = $_POST['username'];
	//$userpass = $_POST['password'];

$name=$_REQUEST['username'];
$username = mysql_real_escape_string($name);
$p=$_REQUEST['password'];
$userpass = mysql_real_escape_string($p);


$i=0;
$id=0;
$c=0;
$countrow=0;

	if($username && $userpass)
	{

	$connect = mysql_connect("localhost","","") or die("Couldn't connect!");
	mysql_select_db("test") or die("Couldn't find db");

	$query= mysql_query("SELECT * From test.login WHERE user_name='$username'");
	$numrows = mysql_num_rows($query);


	if ($numrows!=0)
	{
	//code to login
	 while ($row = mysql_fetch_assoc($query))
	 	{
			$dbusername = $row['user_name'];
			//$name = $_REQUEST['username'];

			//$dbusername=mysqli_real_escape_string[$name];
			//$pass = $_REQUEST['userpass'];
			//$dbpassword=mysqli_real_escape_string[$pass];	
			//$dbtype = $_REQUEST['type'];
			$dbpassword = $row['user_pass'];
			$dbtypes=$row['type'];
		}
		if($username==$dbusername&&$userpass==$dbpassword&&$dbtypes=='teacher')
		{
			//die("loginsuccess");
			$id=1;
		}
			
		if($username==$dbusername&&$password==$dbpassword&&$dbtypes=='student')
		{
			$id=2;
			//die("failed");
		}

		$c=$id;
$flag['countrow']=$c; 
print(json_encode($flag));


		else
		$id=3;
		//die("fail");

		 //$id=0; 
		
	}
	else
	 $id=4;
	//die("That user does't exist!");

	
	}
	else
	$id=5;
 	//die("Please enter  username and Password!");


$flag['countrow']=$c; 
print(json_encode($flag));

mysql_close($cont);

	?>