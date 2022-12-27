insert into accounts (uuid, type, description, status, user_id)
values ('00000001-aacc-4b9e-917b-4138e40e43a9', 'Business', 'Company checking account', 'Active', null);

insert into billing_cycles (status, start_datetime)
values ('Active', current_date::timestamp AT TIME ZONE 'UTC')
