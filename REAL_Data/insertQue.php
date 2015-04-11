<?php
	$host='localhost';
	$uname='root';
	$pwd='';
	$db="real";

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	$id=$_REQUEST['id'];
	$name=$_REQUEST['question'];

	$flag['code']=0;

	if($r=mysql_query("insert into real.test values('$id','$name') ",$con))
	{
		$flag['code']=1;
		echo"hi";
	}

	print(json_encode($flag));
	mysql_close($con);
?>