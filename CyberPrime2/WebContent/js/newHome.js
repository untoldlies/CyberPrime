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
	span.style.position="absolute";
	span.style.top="-5px";
	span.style.right="20px";
	p.textContent = txt;
	btn.appendChild(span);
	p.appendChild(btn);
	div.appendChild(p);
	doc.appendChild(div);
	document.getElementById('username').value = "";
	var divHeight = $('#usernames').height() 
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

