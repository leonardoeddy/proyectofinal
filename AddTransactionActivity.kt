class AddTransactionActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        dbHelper = DBHelper(this)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val type = findViewById<Spinner>(R.id.spinnerType).selectedItem.toString()
            val category = findViewById<EditText>(R.id.editCategory).text.toString()
            val amount = findViewById<EditText>(R.id.editAmount).text.toString().toDouble()
            val date = findViewById<EditText>(R.id.editDate).text.toString()

            val transaction = Transaction(0, type, category, amount, date)
            if (dbHelper.insertTransaction(transaction)) {
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
