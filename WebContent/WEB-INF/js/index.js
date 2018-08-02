var xmlhttp = new XMLHttpRequest();

xmlhttp.onreadystatechange = function(){
	if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
		var result = xmlhttp.responseText;
	}
}

/*xmlhttp.open("get","${path}/category/",true);
xmlhttp.send();*/

xmlhttp.open("post","${path}/category/",true);
xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlhttp.send("name=lisi&age=20");

