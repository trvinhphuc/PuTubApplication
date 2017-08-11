create DATABASE PuTub_DB

USE PuTub_DB
GO
IF OBJECT_ID('dbo.users', 'U') IS NOT NULL 
  DROP TABLE dbo.users; 

CREATE TABLE users(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	email VARCHAR(50) NOT NULL UNIQUE,
	username VARCHAR(50) NOT NULL UNIQUE,
	pwd VARCHAR(50) NOT NULL,
	link_avt VARBINARY(max),
	chanel_name VARCHAR(255) NOT NULL UNIQUE,
	birthday DATE NOT NULL,
	subcriber INT DEFAULT 0
);

CREATE TABLE videos(
	id INT PRIMARY KEY IDENTITY(1,1),
	video_name NTEXT NOT NULL,
	owner_ VARCHAR(50) NOT NULL,
	upload_time DATETIME NOT NULL,
	description_ NTEXT NOT NULL,
	video VARBINARY(max) NOT NULL,
	review_image VARBINARY(max),
	sub	VARBINARY(max),
	tag NTEXT,
	view_n INT DEFAULT 0,
	like_n INT DEFAULT 0,
	dislike_n INT DEFAULT 0,
	privacy VARCHAR(max) NOT NULL,
);

CREATE TABLE comments(
	id_user INT FOREIGN KEY REFERENCES users(id),
	id_video INT FOREIGN KEY REFERENCES videos(id),
	comment_time DATETIME NOT NULL,
	comment_content NTEXT,
);

CREATE TABLE subcribe(
	id_user	INT FOREIGN KEY REFERENCES users(id) not null,
	id_user_subcribed INT FOREIGN KEY REFERENCES users(id) not null,
	subcribe_time DATETIME NOT NULL,
	CONSTRAINT pk_userSub PRIMARY KEY (id_user,id_user_subcribed)
);

CREATE TABLE channels(
	id_user INT PRIMARY KEY FOREIGN KEY REFERENCES users(id),
	channel_info NTEXT,
	link_bg VARBINARY(MAX),
	setting TEXT,
);

CREATE TABLE playlists(
	id INT PRIMARY KEY IDENTITY(1,1),
	playlist_name VARCHAR(100) NOT NULL,
	video_num INT NOT NULL,
	review_image VARBINARY(MAX),
);

CREATE TABLE playlist_own_videos(
	id_playlist INT FOREIGN KEY REFERENCES playlists(id),
	id_video INT FOREIGN KEY REFERENCES videos(id),
);
ALTER trigger [dbo].[update_sub] on [dbo].[subcribe]
	for update,delete
	as
	declare @count int
	set @count = (select count(*) from inserted a, subcribe where subcribe.id_user_subcribed = a.id_user_subcribed);
	update users
	set subcriber = @count from inserted where id = inserted.id_user_subcribed 
