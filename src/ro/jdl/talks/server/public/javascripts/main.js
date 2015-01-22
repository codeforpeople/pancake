var socket = io.connect('//localhost:3000');
var noOfTweets = 0;

socket.on('tweet', function (tweet) {
	var item = document.createElement('div');
	item.classList.add('tweet');

	var photo = document.createElement('div');
	photo.classList.add('photo');

	var text = document.createElement('div');
	text.classList.add('text');

	var img = document.createElement('img');
	img.classList.add('author');
	img.setAttribute('src', tweet.user.profile_image_url);

	var i = document.createElement('i');
	i.innerHTML = '@' + tweet.user.screen_name;

	var h3 = document.createElement('h3');
	h3.innerHTML = tweet.text;

	text.appendChild(i);
	text.appendChild(h3);

	photo.appendChild(img);

	item.appendChild(photo);
	item.appendChild(text);

	document.querySelector('.feed').appendChild(item);

	if (noOfTweets == 5) {
		document.querySelectorAll('.tweet')[0].remove();
	} else
		noOfTweets ++;
});