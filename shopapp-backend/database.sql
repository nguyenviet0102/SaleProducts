CREATE DATABASE shopapp;
use shopapp;
-- Khách hàng muốn mua hàng >>> đăng kí tài khoản >>> table user
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT, --khoa chinh va tu tang
    fullname VARCHAR(100) DEFAULT '',   --khong nhap de mac dinh
    phone_number VARCHAR(10) NOT NULL,  --Bat buoc nhap
    address VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT, --mat khau da ma hoa java Sa256: enciprted passwrod
    created_at DATETIME,        -- theo doi thoi gian tao user va chinh sua
    update_at DATETIME,     --cap nhat lai gay gio vao thoi gian sua //checking user da duoc chinh sua hay chua vao luc nao
    is_active TINYINT(1) DEFAULT 1,      --boolean xóa mềm: TRUE - đang hoạt động//  FALSE - tạm thời khóa người dùng chứ không xóa
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,  --Co the dang nhap bang account facebook, neu dang nhap bang facebook khong the lay duoc password va de mac dinh trong
    google_account_id INT DEFAULT 0 --giong facebook

);
ALTER TABLE users ADD COLUMN role_id INT;


CREATE TABLE roles(
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);
ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);

--Cap token cho user sau lan dang nhap lan dau, khong can dang nhap cac lan sau
CREATE TABLE tokens(
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR (255) UNIQUE NOT NULL,   --cchuoi ki tu, dung extract thong tin user/cname
    token_type VARCHAR (50) NOT NULL,   --gwt token
    expiration_date DATETIME,   --ngay het han token vd nhan OTP 
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,    --foreign key
    FOREIGN KEY (user_id) REFERENCES users(id)
);

--Hỗ trtojw đăng nhập bằng facebook hoặc google
CREATE TABLE social_accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'Ten socail network',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL COMMENT 'Email name',
    name VARCHAR(100) NOT NULL COMMENT 'User name',
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
)

--Danh mục sản phẩm
CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Tên danh mục vs: đồ điện tử'
);

--Bảng chứa sản phẩm
CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(350) COMMENT 'Ten san pham',
    price FLOAT NOT NULL CHECK(price >= 0),
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT DEFAULT,
    created_at DATETIME,
    update_at DATETIME,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP, --THOI DIEM DAT HANG
    status VARCHAR(20),
    total_money FLOAT CHECK(total_money >= 0)
);
ALTER TABLE orders
ADD COLUMN shipping_method VARCHAR(100),
ADD COLUMN shipping_address VARCHAR(200),
ADD COLUMN shipping_date DATE,
ADD COLUMN tracking_number VARCHAR(100),
ADD COLUMN payment_method VARCHAR(100);
--xoa 1 don hang >> xoa mem 
ALTER TABLE orders ADD COLUMN active TINYINT(1);
--TRẠNG THÁI ĐƠN HÀNG CHỈ ĐƯỢC PHÉP NHẬN 'GIÁ TRỊ CỤ THỂ'
ALTER TABLE orders 
MODIFY COLUMN  status ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled') COMMENT 'Trạng thái đơn hàng';

CREATE TABLE order_details(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    price FLOAT CHECK(price >= 0),
    number_of_product INT CHECK(number_of_product > 0),
    total_money FLOAT CHECK(total_money >= 0),
    color VARCHAR(20) DEFAULT ''
);