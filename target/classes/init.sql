INSERT INTO airlines (id, name, num_planes)
VALUES (1, 'Iberia', 132),(2, 'Swiss', 12),(3, 'Vueling', 45),(4, 'Ryanair', 28);

INSERT INTO planes (id, model, capacity, airline_id)
VALUES (1, 'Boeing 747', 100, 1),(2, 'Boeing 747', 200, 2),(3, 'Boeing 747', 200, 3),(4, 'Boeing 747', 200, 4);


INSERT INTO flights (id, flight_date, departure_time, takeoff_time, origin, destination, status, plane_id)
VALUES (1, '2018-01-01', '10:00:00', '10:00:00', 'Madrid', 'Barcelona', 5, 1),(2, '2018-01-01', '10:00:00', '10:00:00', 'Madrid', 'Barcelona', 4, 2),(3, '2018-01-01', '10:00:00', '10:00:00', 'Madrid', 'Barcelona', 5, 3);
