CREATE TABLE "suppliers" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"description" varchar,
	CONSTRAINT suppliers_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "category" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"department" varchar,
	"description" varchar,
	CONSTRAINT category_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);





