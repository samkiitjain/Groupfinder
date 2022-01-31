DROP TABLE IF EXISTS GROUPS;

CREATE TABLE GROUPS (
    INDEX INTEGER,
    NODE_ID VARCHAR(256),
    NODE_PARENT_ID VARCHAR(256),
    NODE_HEIGHT INTEGER,
    NODE_NAME VARCHAR(256),
    NODE_TYPE VARCHAR(256)
);

insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (1, '1', 1, 'Group A', 'groupa', '0');
insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (2, '2', 1, 'Group B', 'groupb', '0');
insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (3, '11', 2, 'Group AB', 'groupab', '1');
insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (4, '12', 2, 'Group AC', 'groupac', '1');
insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (5, '21', 2, 'Group BA', 'groupba', '2');
insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (6, '111', 3, 'Group ABA', 'groupaba', '11');
insert into groups (INDEX, NODE_ID, NODE_HEIGHT, NODE_NAME, NODE_TYPE, NODE_PARENT_ID) values (7, '211', 3, 'Group BAA', 'groupbaa', '21');