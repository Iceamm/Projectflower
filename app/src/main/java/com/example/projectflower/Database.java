package com.example.projectflower;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "Flower";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "FlowerData";

    public static final String COL_THNAME = "c_name_th";
    public static final String COL_ENNAME = "c_name_en";
    public static final String COL_MEAN = "c_mean";
    public static final String COL_IMAGE = "c_image";


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_IMAGE + " TEXT,"
                + COL_THNAME + " TEXT, "
                + COL_ENNAME + " TEXT, "
                + COL_MEAN + " TEXT );");

        insert(db,"one.png", "ดอกกล้วยไม้", "Orchid", "ความมั่นคงความรักความสง่างาม");
        insert(db,"two.png", "ดอกบัว", "Lotus", "ความสงบและความบริสุทธิ์");
        insert(db,"three.png", "ดอกกุหลาบแดง", "Red Rose", "การตกหลุมรักความรักที่มั่นคง");
        insert(db,"four.png", "ดอกทานตะวัน", "Sunflower", "ความเชื่อมั่น มั่นคง รักเดียวใจเดียว");
        insert(db,"five.png", "ดอกมะลิ", "Jasmine", "ความบริสุทธิ์ ผุดผ่อง อ่อนโยน");
        insert(db,"six.png", "ดอกพุทธรักษา", "Canna Flower", "พระพุทธเจ้าคอยปกป้องคุ้มครอง");
        insert(db,"seven.png", "ดอกรัก", "Colotropis", "ความรักอันบริสุทธิ");
        insert(db,"eight.png", "ดอกเบญจมาศ", "Chrysanthemum", "ความรื่นเริงและความบริสุทธิ์ใจ");
        insert(db,"nine.png", "ดอกบานไม่รู้โรย", "Globe Amaranth", "ความรักมั่นคงไม่มีวันเสื่อมคลาย");
        insert(db,"ten.png", "ดอกอัญชัน", "Asian Pigeonwings", "ความใจดี เอื้อเฟื้อเผื่อแผ่");
        insert(db,"eleven.png", "ดอกแก้ว", "Orange Jessamine", "ความใสสะอาดบริสุทธิ์ มีคนรักดังแก้วตาดวงใจ");
        insert(db,"twelve.png", "ดอกปีป", "Cork Tree", "เก็บเงินเก็บทอง ชื่อเสียงโด่งดัง");
        insert(db,"thirteen.png", "ดอกลีลาวดี", "Plumeria", "ความสวยงาม อ่อนช้อย");
        insert(db,"fourteen.png", "ดอกเฟื่องฟ้า", "Paper Flower", "ความเบิกบาน สว่างไสว ความรุ่งเรืองที่ก้าวไกลแห่งชีวิต");
        insert(db,"fifteen.png", "ดอกเข็ม", "Rubiaceae", "สติปัญญาเฉียบคม ชีวิตมีความสดชื่นเหมือนรสหวานของดอกเข็ม");
        insert(db,"sixteen.png", "ดอกชบา", "Hibiscus", "ความเป็นอิสระ น้ำหนึ่งใจเดียวกัน");
        insert(db,"seventeen.png", "ดอกโป๊ยเซียน", "Crown Of Thorns", "คุ้มครองและนำโชคลาภมาให้");
        insert(db,"eightteen.png", "ดอกเล็บมือนาง", "Rangoon Creeper", "ความเย้ายวนเกินห้ามใจ");
        insert(db,"nineteen.png", "ดอกโมก", "Water Jasmine", "ความสุข ความบริสุทธิ์ ผู้ที่หลุดพ้นด้วยทุกข์ทั้งปวง");
        insert(db,"twenty.png", "ดอกพุด", "East Indian Rosebay", "ความเจริญ มั่นคง แข็งแรง");

    }

    void insert(SQLiteDatabase db, String image, String Tname, String Ename, String meaning){
        ContentValues cv = new ContentValues();
        cv.put(COL_IMAGE, image);
        cv.put(COL_THNAME, Tname);
        cv.put(COL_ENNAME, Ename);
        cv.put(COL_MEAN, meaning);
        db.insert(TABLE_NAME, null, cv);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion
            , int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
}
