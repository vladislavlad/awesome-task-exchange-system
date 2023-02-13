insert into accounts (uuid, type, description, status, user_id)
values ('00000001-aacc-4b9e-256a-4138e40e42a9', 'Business', 'Company checking account', 'Active', null),
       ('00000010-caaa-4b9e-512b-4148e40e42a9', 'Business', 'Cash operations account', 'Active', null);

insert into billing_cycles (status, start_datetime)
values ('Active', current_date::timestamp AT TIME ZONE 'UTC')
