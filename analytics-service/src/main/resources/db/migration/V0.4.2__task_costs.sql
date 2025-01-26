create table task_costs
(
    id          bigserial primary key not null,
    task_uuid   uuid references tasks (uuid),
    assign_cost int                   not null,
    reward      int                   not null
);
