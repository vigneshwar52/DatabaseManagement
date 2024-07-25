package com.code.sqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.code.sqlitekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
/*        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.addName.setOnClickListener{
            val db = DBHelper(this@MainActivity)
            val name = binding.enterName.text.toString().trim()
            val age = binding.enterAge.text.toString().trim()

            db.addContent(name,age)

            Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            binding.enterName.text.clear()
            binding.enterAge.text.clear()
        }

        binding.printName.setOnClickListener{

            startActivity(Intent(this,DetailedView::class.java))

            val db = DBHelper(this)

            val cursor = db.getALL()

            cursor?.moveToFirst()
            /*binding.Name.text = db.getName().toString()
            binding.Age.text = db.getAge().toString()*/

            cursor?.let {
                val nameIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME)
                val ageIndex = cursor.getColumnIndex(DBHelper.COLUMN_AGE)
                // Check if column indices are valid
                if (nameIndex != -1 && ageIndex != -1) {
                    while (cursor.moveToNext()) {
                        val name = cursor.getString(nameIndex)
                        val age = cursor.getString(ageIndex)

                        // Append data to TextViews
                        binding.Name.append(name + "\n")
                        binding.Age.append(age + "\n")
                    }
                } else {
                    Log.e("CursorError", "Column index is invalid. Ensure the column names are correct.")
                }

                cursor.close() // Always close the cursor when done
            } ?: run {
                Log.e("CursorError", "Cursor is null.")
            }
        }
    }
}