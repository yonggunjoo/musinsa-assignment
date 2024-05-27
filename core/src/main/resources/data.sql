--brand
INSERT INTO BRAND (name) VALUES ('A');
INSERT INTO BRAND (name) VALUES ('B');
INSERT INTO BRAND (name) VALUES ('C');
INSERT INTO BRAND (name) VALUES ('D');
INSERT INTO BRAND (name) VALUES ('E');
INSERT INTO BRAND (name) VALUES ('F');
INSERT INTO BRAND (name) VALUES ('G');
INSERT INTO BRAND (name) VALUES ('H');
INSERT INTO BRAND (name) VALUES ('I');

--category
INSERT INTO "CATEGORY" (name) VALUES ('상의');
INSERT INTO "CATEGORY" (name) VALUES ('아우터');
INSERT INTO "CATEGORY" (name) VALUES ('바지');
INSERT INTO "CATEGORY" (name) VALUES ('스니커즈');
INSERT INTO "CATEGORY" (name) VALUES ('가방');
INSERT INTO "CATEGORY" (name) VALUES ('모자');
INSERT INTO "CATEGORY" (name) VALUES ('양말');
INSERT INTO "CATEGORY" (name) VALUES ('액세서리');

--브랜드        상의(1)     아우터(2)     바지(3)    스니커즈(4)    가방(5)      모자(6)     양말(7)     액세서리(8)
-- A(1)        11200       5500        4200        9000        2000        1700        1800        2300
-- B(2)        10500       5900        3800        9100        2100        2000        2000        2200
-- C(3)        10000       6200        3300        9200        2200        1900        2200        2100
-- D(4)        10100       5100        3000        9500        2500        1500        2400        2000
-- E(5)        10700       5000        3800        9900        2300        1800        2100        2100
-- F(6)        11200       7200        4000        9300        2100        1600        2300        1900
-- G(7)        10500       5800        3900        9000        2200        1700        2100        2000
-- H(8)        10800       6300        3100        9700        2100        1600        2000        2000
-- I(9)        11400       6700        3200        9500        2400        1700        1700        2400


-- brand : 'A'~'I', category : 상의 (1)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,1,11200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,1,10500);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,1,10000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,1,10100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,1,10700);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,1,11200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,1,10500);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,1,10800);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,1,11400);

-- brand : 'A'~'I', category : 아우터 (2)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,2,5500);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,2,5900);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,2,6200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,2,5100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,2,5000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,2,7200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,2,5800);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,2,6300);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,2,6700);

-- brand : 'A'~'I', category : 바지 (3)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,3,4200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,3,3800);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,3,3300);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,3,3000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,3,3800);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,3,4000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,3,3900);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,3,3100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,3,3200);

-- brand : 'A'~'I', category : 스니커즈 (4)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,4,9000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,4,9100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,4,9200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,4,9500);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,4,9900);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,4,9300);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,4,9000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,4,9700);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,4,9500);

-- brand : 'A'~'I', category : 가방 (5)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,5,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,5,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,5,2200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,5,2500);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,5,2300);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,5,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,5,2200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,5,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,5,2400);

-- brand : 'A'~'I', category : 모자 (6)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,6,1700);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,6,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,6,1900);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,6,1500);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,6,1800);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,6,1600);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,6,1700);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,6,1600);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,6,1700);

-- brand : 'A'~'I', category : 양말 (7)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,7,1800);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,7,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,7,2200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,7,2400);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,7,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,7,2300);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,7,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,7,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,7,1700);

-- brand : 'A'~'I', category : 액세서리 (8)
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (1,8,2300);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (2,8,2200);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (3,8,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (4,8,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (5,8,2100);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (6,8,1900);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (7,8,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (8,8,2000);
INSERT INTO "PRODUCT" (brand_id, category_id, price) VALUES (9,8,2400);