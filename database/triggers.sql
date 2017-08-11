create trigger update_sub on subcribe
	for insert
	as
	declare @count int
	set @count = (select count(*) from inserted a, subcribe where subcribe.id_user_subcribed = a.id_user_subcribed);
	update users
	set subcriber = @count from inserted where users.id = inserted.id_user_subcribed; 
	
create trigger delete_sub on subcribe
	for delete
	as
	declare @count int
	set @count = (select count(*) from deleted a, subcribe where subcribe.id_user_subcribed = a.id_user_subcribed);
	update users
	set subcriber = @count from deleted where users.id = deleted.id_user_subcribed; 