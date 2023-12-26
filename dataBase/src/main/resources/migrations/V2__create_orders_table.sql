CREATE TABLE IF NOT EXISTS public."Orders"
(
    "id" serial PRIMARY KEY,
    "amount" bigint,
    "idRestaurant" bigint,
    FOREIGN KEY ("idRestaurant") REFERENCES public."Restaurants"("id")
);
