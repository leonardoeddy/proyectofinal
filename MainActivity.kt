class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        val transactions = dbHelper.getAllTransactions()

        adapter = TransactionAdapter(transactions)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            startActivity(Intent(this, AddTransactionActivity::class.java))
        }
    }
}
