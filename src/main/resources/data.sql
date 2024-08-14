delete
from Ingredient_Ref;
delete
from Coffee;
delete
from Coffee_Order;

delete
from Ingredient;
insert into Ingredient (id, name, type)
values ('WMILK', 'Whole Milk', 'MILK');
insert into Ingredient (id, name, type)
values ('LFMILK', 'Low-fat Milk', 'MILK');
insert into Ingredient (id, name, type)
values ('LTFMILK', 'Lactose-free Milk', 'MILK');
insert into Ingredient (id, name, type)
values ('VSYUP', 'Vanilla Syrups', 'SYRUPS');
insert into Ingredient (id, name, type)
values ('CSYUP', 'Caramel Syrups', 'SYRUPS');
insert into Ingredient (id, name, type)
values ('PSYUP', 'Peach Syrups', 'SYRUPS');
insert into Ingredient (id, name, type)
values ('CHOCRE', 'Chocolate Cream', 'CREAM');
