--;;
CREATE TABLE users (
    user_id INTEGER PRIMARY KEY
);
--;;
CREATE TABLE social (
    social_id INTEGER PRIMARY KEY,
    user_id INTEGER,
    use_default BOOLEAN,
    email TEXT,
    first_name TEXT,
    last_name TEXT,
    auth_token TEXT,
    refresh_token TEXT,
    extra TEXT,
    foreign key(user_id) references users(user_id)
);
