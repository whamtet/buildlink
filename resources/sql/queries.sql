-- Place your queries here. Docs available https://www.hugsql.org/
-- :name user-by-email-platform
SELECT user_id FROM social
WHERE platform = :platform AND email = :email
