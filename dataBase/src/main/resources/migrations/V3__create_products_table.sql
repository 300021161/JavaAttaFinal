CREATE TABLE IF NOT EXISTS public."Products"
(
    "id" serial PRIMARY KEY,
    "cost" bigint,
    "idOrder" bigint,
    FOREIGN KEY ("idOrder") REFERENCES public."Orders"("id")
);
