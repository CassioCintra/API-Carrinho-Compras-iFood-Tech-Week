INSERT INTO restaurant (id, cep, complement,name) VALUES
(1L, '000001', 'Complemento Endereço Restaurante 1', 'Restaurante 1'),
(2L, '000002', 'Complemento Endereço Restaurante ', 'Restaurante 2');

INSERT INTO customer (id, cep, complement, name) VALUES
(1L, '000001', 'Complemento Endereço Cliente 1', 'Cliente 1');

INSERT INTO product (id, available, name, unit_price, restaurant_id) VALUES
(1L, true, 'Produto 1', 5.0, 1L),
(2L, true, 'Produto 2', 6.0, 1L),
(3L, true, 'Produto 3', 7.0, 2L);

INSERT INTO bag(id, payment_method, closed, amount, customer_id) VALUES
(1L, 0, false, 0.0, 1L);