CREATE TABLE "user" (
	"id" serial NOT NULL,
	"e-mail" varchar NOT NULL UNIQUE,
	"pw" varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "items" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"price" bigint NOT NULL,
	"currency" varchar NOT NULL,
	"description" varchar NOT NULL,
	"category" varchar NOT NULL,
	"supplier" varchar NOT NULL,
	CONSTRAINT items_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cart" (
	"id" serial NOT NULL,
	"user_id" bigint NOT NULL,
	"item_id" bigint NOT NULL,
	"quantity" bigint NOT NULL,
	"active" BOOLEAN NOT NULL DEFAULT '1',
	"historydate" DATE DEFAULT 'null',
	CONSTRAINT cart_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "address" (
	"id" serial NOT NULL,
	"f_name" varchar NOT NULL,
	"l_name" varchar NOT NULL,
	"address" varchar NOT NULL,
	"postalcode" varchar NOT NULL,
	"city" varchar NOT NULL,
	"country" varchar NOT NULL,
	"phonenumber" int NOT NULL,
	"cart_id" bigint NOT NULL,
	"date" DATE NOT NULL,
	CONSTRAINT address_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);





ALTER TABLE "cart" ADD CONSTRAINT "cart_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "cart" ADD CONSTRAINT "cart_fk1" FOREIGN KEY ("item_id") REFERENCES "items"("id");

ALTER TABLE "address" ADD CONSTRAINT "address_fk0" FOREIGN KEY ("cart_id") REFERENCES "cart"("id");

