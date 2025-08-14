class DBHelper(context: Context) : SQLiteOpenHelper(context, "PresupuestoDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Transacciones (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                type TEXT,
                category TEXT,
                amount REAL,
                date TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Transacciones")
        onCreate(db)
    }

    fun insertTransaction(transaction: Transaction): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("type", transaction.type)
            put("category", transaction.category)
            put("amount", transaction.amount)
            put("date", transaction.date)
        }
        return db.insert("Transacciones", null, values) > 0
    }

    fun getAllTransactions(): List<Transaction> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Transacciones", null)
        val list = mutableListOf<Transaction>()
        while (cursor.moveToNext()) {
            list.add(
                Transaction(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getString(4)
                )
            )
        }
        cursor.close()
        return list
    }
}
