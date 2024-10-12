CREATE OR REPLACE VIEW TASK_HISTORY_VIEW AS
SELECT DATE(t.created_date) as created_date, 
t.created_by, 
count(CASE WHEN t.status = true THEN 1 ELSE NULL END) AS completed, 
count(1) as total_tasks,
COUNT(1) OVER () as total_count
FROM TASK t
GROUP BY DATE(t.created_date), t.created_by