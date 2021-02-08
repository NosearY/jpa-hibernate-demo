INSERT INTO acmeb_user(user_id, username, password_hash, create_date) VALUES (1, 'user1@gmail.com', '$2y$12$XCja/B7Nv5nDocZ/9bg0Q.9v.hNb1XZYeMWjh4dqcb4aI8e6oiLuC', CURRENT_TIMESTAMP());

INSERT INTO acmeb_user(user_id, username, password_hash, create_date) VALUES (2, 'user2@gmail.com', '$2y$12$Ob5FlBo3T9tPFVK5iyTjxuHwAFbtFL6f52zvpEUTnGFN6HlKy3o9a', CURRENT_TIMESTAMP());

INSERT INTO acmeb_customer(customer_id, customer_name, create_date, user_id) VALUES (1, 'user1', CURRENT_TIMESTAMP(), 1);

INSERT INTO acmeb_customer(customer_id, customer_name, create_date, user_id) VALUES (2, 'user1', CURRENT_TIMESTAMP(), 2);

INSERT INTO acmeb_account(account_id, account_no, currency_code, balance, create_date, user_id) VALUES (1, '12345678', 'HKD', 1000000, CURRENT_TIMESTAMP(), 1);

INSERT INTO acmeb_account(account_id, account_no, currency_code, balance, create_date, user_id) VALUES (2, '88888888', 'HKD', 1000000, CURRENT_TIMESTAMP(), 2);

INSERT INTO acmeb_transaction(transaction_id, from_account_id, to_account_id, amount, currency_code, create_ts, update_ts, status) VALUES (1, 1, 2, 1000, 'HKD', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'OK');
