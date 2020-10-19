INSERT INTO app_user (id, first_name, last_name) VALUES
  ('Example-User-1','Simon', 'Elbrink'),
  ('Example-User-2', 'Ulf', 'Bengtsson'),
  ('Example-User-3','Fredrik', 'Odin'),
  ('Example-User-4', 'Mehrdad', 'Javan'),
  ('Example-User-5','Teresia', 'Gable');

INSERT INTO todo_item(id, title, description, deadline, done, assignee_id) VALUES
    ('Example-TodoItem-1', 'Finish Assignment!','The assignment is to create TodoItems.', '2020-01-01 17:00:00', false, 'Example-User-1'),
    ('Example-TodoItem-2', 'Create More Assignments!','Spawn new ideas with teammates.', '2020-01-02 8:00:00', false, 'Example-User-3'),
    ('Example-TodoItem-3', 'Create More Exercises!','Spawn new ideas with teammates.', '2020-01-03 8:00:00', false, 'Example-User-3');
