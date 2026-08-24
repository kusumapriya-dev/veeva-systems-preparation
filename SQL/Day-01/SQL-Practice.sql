-- 1. Find the total amount of orders per day
SELECT order_date, SUM(order_amount) AS total_amount
FROM orders
GROUP BY order_date;

-- 2. Find the total order amount for each month
SELECT MONTH(order_date) AS month,
       SUM(order_amount) AS total_amount
FROM orders
GROUP BY MONTH(order_date);

-- 3. Find the total number of orders for each month
SELECT MONTH(order_date) AS month,
       COUNT(*) AS total_orders
FROM orders
GROUP BY MONTH(order_date);

-- 4. Find the total order amount for each month in YYYY-MM format
SELECT DATE_FORMAT(order_date, '%Y-%m') AS month,
       SUM(order_amount) AS total_amount
FROM orders
GROUP BY DATE_FORMAT(order_date, '%Y-%m');

-- 5. Find the total number of orders for each month in YYYY-MM format
SELECT DATE_FORMAT(order_date, '%Y-%m') AS month,
       COUNT(*) AS total_orders
FROM orders
GROUP BY DATE_FORMAT(order_date, '%Y-%m');

-- 6. Find the total number of orders placed by each customer,
--    excluding orders placed in June
SELECT c.cust_id,
       c.name,
       COUNT(o.order_id) AS total_orders
FROM customers c
JOIN orders o
    ON c.cust_id = o.cust_id
WHERE MONTH(o.order_date) <> 6
GROUP BY c.cust_id, c.name;

-- 7. Find the customer who placed the highest single order value
SELECT c.name,
       o.order_amount
FROM customers c
JOIN orders o
    ON c.cust_id = o.cust_id
WHERE o.order_amount = (
    SELECT MAX(order_amount)
    FROM orders
);

-- 8. Find the customer with the highest total order amount
SELECT c.name,
       SUM(o.order_amount) AS total_amount
FROM customers c
JOIN orders o
    ON c.cust_id = o.cust_id
GROUP BY c.cust_id, c.name
ORDER BY total_amount DESC
LIMIT 1;

-- 9. List all orders placed between 2023-06-04 and 2027-06-03
SELECT *
FROM orders
WHERE order_date BETWEEN '2023-06-04' AND '2027-06-03';

-- 10. Find the average order value for each city
SELECT c.city,
       AVG(o.order_amount) AS average_order_value
FROM customers c
JOIN orders o
    ON c.cust_id = o.cust_id
GROUP BY c.city;


-- 11. Identify customers who have not placed any orders
SELECT c.cust_id,
       c.name
FROM customers c
LEFT JOIN orders o
    ON c.cust_id = o.cust_id
WHERE o.order_id IS NULL;


-- 12. Find the month with the highest total order value
SELECT DATE_FORMAT(order_date, '%Y-%m') AS month,
       SUM(order_amount) AS total_value
FROM orders
GROUP BY DATE_FORMAT(order_date, '%Y-%m')
ORDER BY total_value DESC
LIMIT 1;

-- 13. Display the top 2 customers with the most orders
--     in the last 30 days
SELECT c.cust_id,
       c.name,
       COUNT(o.order_id) AS total_orders
FROM customers c
JOIN orders o
    ON c.cust_id = o.cust_id
WHERE o.order_date >= CURRENT_DATE - INTERVAL 30 DAY
GROUP BY c.cust_id, c.name
ORDER BY total_orders DESC
LIMIT 2;

-- 14. Alternative way to find orders from the last 30 days
SELECT *
FROM orders
WHERE order_date >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY);
