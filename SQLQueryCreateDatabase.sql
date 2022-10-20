
USE Wings

Create Table Login(
	[User] varchar(50) not null Primary Key,
	[Password] varchar(255) Not Null
)
go

select * from Login

Create Table Product(
	Product_Code varchar(18) not null Primary Key,
	Product_Name varchar(30),
	Price numeric(6),
	Currency varchar(5),
	Discount numeric(6),
	Dimension varchar(50),
	Unit varchar(5)
)
go

select * from Product

Create Table Transaction_Header(
	Document_Code varchar(3) not null Primary Key,
	Document_Number varchar(10),
	[User] varchar(50) foreign key references Login ([User]),
	Total numeric(10),
	[Date] Date
)
go

select * from Transaction_Header

Create Table Transaction_Detail(
	
	Document_Code varchar(3)   not null Foreign Key references Transaction_Header (Document_Code),
	Document_Number varchar(10) not null,
	Product_Code varchar(18) not null  foreign Key references Product (Product_Code),
	Price numeric(6),
	Quantity integer,
	Unit varchar(5),
	Sub_Total numeric(10),
	Currency varchar(5)
)
go

select * from Transaction_Detail

insert into Login values ('Smit', '_sm1t_OK')

insert into Product values ('SKUSKILNP', 'So klin Pewangi', 15000, 'IDR', 10, '13cm x 10cm', 'PCS')

insert into Transaction_Header values ('TRX', '001', 'Smit', 67500, '2000-05-20')

insert into Transaction_Detail values ('TRX', '001', 'SKUSKILNP', 13500, 5, 'PCS', 67500, 'IDR')