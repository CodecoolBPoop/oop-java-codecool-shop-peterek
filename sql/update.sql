ALTER TABLE public.items ALTER COLUMN supplier TYPE bigint USING supplier::bigint;
ALTER TABLE public.items ALTER COLUMN category TYPE BIGINT USING category::BIGINT;

ALTER TABLE public.items
ADD CONSTRAINT items_category_id_fk
FOREIGN KEY (category) REFERENCES public.category (id);

ALTER TABLE public.items
ADD CONSTRAINT items_suppliers_id_fk
FOREIGN KEY (supplier) REFERENCES public.suppliers (id);


ALTER TABLE public.items RENAME COLUMN price TO defaultPrice;
ALTER TABLE public.items RENAME COLUMN currency TO currencyString;