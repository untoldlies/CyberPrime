function addUser(){
	
	var doc = document.getElementById('usernames');

	var p = document.createElement('p');
	var div= document.createElement('div');
	var span = document.createElement("span");
	var btn = document.createElement('button');
	var txt = document.getElementById('username').value;
	
	if(txt.length != 0){
	btn.onclick = removeUser;
	div.className = "userCont";
	span.textContent = '\u00D7';
	div.style.position="relative";
	div.style.backgroundColor = "gray";
	span.style.position="absolute";
	span.style.top="-5px";
	span.style.right="20px";
	p.textContent = txt;
	p.style.position="relative";
	p.style.left="10px";
	btn.appendChild(span);
	p.appendChild(btn);
	div.appendChild(p);
	doc.appendChild(div);
	document.getElementById('username').value = "";
	$('#usernames').animate({
		height : $('#usernames').height() + $('#usernames').child.height()},500);
	}
}

function removeUser(){

	$(this).closest('.userCont').animate({
		height: 0}, 500,function(){
			$(this).remove();
		});

	}



