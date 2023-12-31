-- 不要なメタテーブルの削除
DROP TABLE IF EXISTS BATCH_STEP_EXECUTION_SEQ;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_SEQ;

DROP TABLE IF EXISTS BATCH_JOB_SEQ;

DROP TABLE IF EXISTS BATCH_STEP_EXECUTION_CONTEXT;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_CONTEXT;

DROP TABLE IF EXISTS BATCH_STEP_EXECUTION;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_PARAMS;

DROP TABLE IF EXISTS BATCH_JOB_EXECUTION;

DROP TABLE IF EXISTS BATCH_JOB_INSTANCE;

DROP TABLE IF EXISTS `orders`;

DROP TABLE IF EXISTS `customers`;

DROP TABLE IF EXISTS `products`;

-- テスト用なので他の項目を実装しない
CREATE TABLE customers (customer_id CHAR(36) PRIMARY KEY);

-- テスト用なので他の項目を実装しない
CREATE TABLE products (product_id CHAR(36) PRIMARY KEY);

CREATE TABLE orders (
    order_id CHAR(36) PRIMARY KEY COMMENT '注文 ID（Order ID）: 一意の識別子。UUID を使用。',
    customer_id CHAR(36) COMMENT '顧客 ID（Customer ID）: 顧客を識別するための ID。UUID を使用。',
    product_id CHAR(36) COMMENT '商品 ID（Product ID）: 注文された商品を識別するための ID。UUID を使用。',
    quantity INT COMMENT '数量（Quantity）: 注文された商品の数。',
    order_date DATE COMMENT '注文日（Order Date）: 注文が行われた日付。',
    delivery_status VARCHAR(50) COMMENT '配送状況（Delivery Status）: 商品の配送状態を示す。',
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);