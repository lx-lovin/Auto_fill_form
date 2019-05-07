package com.example.autofill

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var data:ArrayList<String>

    lateinit var usernames:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data = arrayListOf()
        usernames = arrayListOf()
        username.threshold = 1
        var database: SQLiteDatabase = this.openOrCreateDatabase("SavedData", Context.MODE_PRIVATE,null)
        database.execSQL("CREATE TABLE IF NOT EXISTS saved(username VARCHAR,password VARCHAR,fromm VARCHAR,too VARCHAR,date VARCHAR,name VARCHAR,age INT(10),contact VARCHAR)")
        var c: Cursor = database.rawQuery("SELECT * FROM saved",null)
        if(c.count!=0){
            c.moveToFirst()
            for (i in 0..c.count-1){
                usernames.add(c.getString(0))
                data.add(c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3)+","+c.getString(4)+","+c.getString(5)+","+c.getString(6)+","+c.getString(7))
                c.moveToNext()
            }
            var adapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,usernames)
            username.setAdapter(adapter)

            var tempusername = ""
            username.setOnItemClickListener { parent, view, position, id ->
                var s:List<String> = data[position].split(",")
                username.setText(s[0])
                password.setText(s[1])
                from.setText(s[2])
                to.setText(s[3])
                date.setText(s[4])
                name.setText(s[5])
                age.setText(s[6])
                contactNo.setText(s[7])
                tempusername = s[0]
            }

            book.setOnClickListener {

                if(username.text.isEmpty()){
                    username.setError("Required Field")
                    return@setOnClickListener
                }else if(password.text.isEmpty()){
                    password.setError("Required Field")
                    return@setOnClickListener
                }else if(from.text.isEmpty()){
                    from.setError("Required Field")
                    return@setOnClickListener
                }else if (to.text.isEmpty()){
                    to.setError("Required Field")
                    return@setOnClickListener
                }else if(date.text.isEmpty()){
                    date.setError("Required Field")
                    return@setOnClickListener
                }else if(name.text.isEmpty()){
                   name.setError("Required Field")
                    return@setOnClickListener
                }else if(age.text.isEmpty()){
                    age.setError("Required Field")
                    return@setOnClickListener
                }else if(contactNo.text.isEmpty()){
                    contactNo.setError("Required Field")
                    return@setOnClickListener
                }


                var username:String = username.text.toString()
                var password:String = password.text.toString()
                var name:String = name.text.toString()
                var age = age.text
                var gender:String = contactNo.text.toString()
                var from:String = from.text.toString()
                var to:String = to.text.toString()
                var date:String = date.text.toString()

                if(username==tempusername){
                    var intent:Intent=Intent(this,BookingActivity::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("password",password)
                    startActivity(intent)
                }else {
                    database.execSQL("INSERT INTO saved (username,password,fromm,too,date,name,age,contact) VALUES ('" + username + "','" + password + "','" + from + "','" + to + "','" + date + "','" + name + "'," + age + ",'" + gender + "')")
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    var intent: Intent = Intent(this, BookingActivity::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("password",password)
                    startActivity(intent)
                }
            }

        }else{
            book.setOnClickListener {

                if(username.text.isEmpty()){
                    username.setError("Required Field")
                    return@setOnClickListener
                }else if(password.text.isEmpty()){
                    password.setError("Required Field")
                    return@setOnClickListener
                }else if(from.text.isEmpty()){
                    from.setError("Required Field")
                    return@setOnClickListener
                }else if (to.text.isEmpty()){
                    to.setError("Required Field")
                    return@setOnClickListener
                }else if(date.text.isEmpty()){
                    date.setError("Required Field")
                    return@setOnClickListener
                }else if(name.text.isEmpty()){
                    name.setError("Required Field")
                    return@setOnClickListener
                }else if(age.text.isEmpty()){
                    age.setError("Required Field")
                    return@setOnClickListener
                }else if(contactNo.text.isEmpty()){
                    contactNo.setError("Required Field")
                    return@setOnClickListener
                }

                var username:String = username.text.toString()
                var password:String = password.text.toString()
                var name:String = name.text.toString()
                var age = age.text
                var gender:String = contactNo.text.toString()
                var from:String = from.text.toString()
                var to:String = to.text.toString()
                var date:String = date.text.toString()

                database.execSQL("INSERT INTO saved (username,password,fromm,too,date,name,age,contact) VALUES ('" + username + "','" + password + "','" + from + "','" + to + "','" + date + "','" + name + "'," + age + ",'" + gender + "')")
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, BookingActivity::class.java)
                intent.putExtra("username",username)
                intent.putExtra("password",password)
                startActivity(intent)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()

        data = arrayListOf()
        usernames = arrayListOf()

        username.threshold = 1

        username.setText("")
        password.setText("")
        from.setText("")
        to.setText("")
        date.setText("")
        name.setText("")
        age.setText("")
        contactNo.setText("")
        username.requestFocus()


        var database: SQLiteDatabase = this.openOrCreateDatabase("SavedData", Context.MODE_PRIVATE,null)
        database.execSQL("CREATE TABLE IF NOT EXISTS saved(username VARCHAR,password VARCHAR,fromm VARCHAR,too VARCHAR,date VARCHAR,name VARCHAR,age INT(4),contact VARCHAR)")
        var c: Cursor = database.rawQuery("SELECT * FROM saved",null)
        if(c.count!=0){
            c.moveToFirst()
            for (i in 0..c.count-1){
                usernames.add(c.getString(0))
                data.add(c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3)+","+c.getString(4)+","+c.getString(5)+","+c.getString(6)+","+c.getString(7))
                c.moveToNext()
            }
            var adapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,usernames)
            username.setAdapter(adapter)

            var tempusername = ""
            username.setOnItemClickListener { parent, view, position, id ->
                var s:List<String> = data[position].split(",")
                username.setText(s[0])
                password.setText(s[1])
                from.setText(s[2])
                to.setText(s[3])
                date.setText(s[4])
                name.setText(s[5])
                age.setText(s[6])
                contactNo.setText(s[7])
                tempusername = s[0]
            }

            book.setOnClickListener {

                if(username.text.isEmpty()){
                    username.setError("Required Field")
                    return@setOnClickListener
                }else if(password.text.isEmpty()){
                    password.setError("Required Field")
                    return@setOnClickListener
                }else if(from.text.isEmpty()){
                    from.setError("Required Field")
                    return@setOnClickListener
                }else if (to.text.isEmpty()){
                    to.setError("Required Field")
                    return@setOnClickListener
                }else if(date.text.isEmpty()){
                    date.setError("Required Field")
                    return@setOnClickListener
                }else if(name.text.isEmpty()){
                    name.setError("Required Field")
                    return@setOnClickListener
                }else if(age.text.isEmpty()){
                    age.setError("Required Field")
                    return@setOnClickListener
                }else if(contactNo.text.isEmpty()){
                    contactNo.setError("Required Field")
                    return@setOnClickListener
                }


                var username:String = username.text.toString()
                var password:String = password.text.toString()
                var name:String = name.text.toString()
                var age = age.text
                var gender:String = contactNo.text.toString()
                var from:String = from.text.toString()
                var to:String = to.text.toString()
                var date:String = date.text.toString()

                if(username==tempusername){
                    var intent:Intent=Intent(this,BookingActivity::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("password",password)
                    startActivity(intent)
                }else {
                    database.execSQL("INSERT INTO saved (username,password,fromm,too,date,name,age,contact) VALUES ('" + username + "','" + password + "','" + from + "','" + to + "','" + date + "','" + name + "'," + age + ",'" + gender + "')")
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    var intent: Intent = Intent(this, BookingActivity::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("password",password)
                    startActivity(intent)
                }
            }

        }else{
            book.setOnClickListener {

                if(username.text.isEmpty()){
                    username.setError("Required Field")
                    return@setOnClickListener
                }else if(password.text.isEmpty()){
                    password.setError("Required Field")
                    return@setOnClickListener
                }else if(from.text.isEmpty()){
                    from.setError("Required Field")
                    return@setOnClickListener
                }else if (to.text.isEmpty()){
                    to.setError("Required Field")
                    return@setOnClickListener
                }else if(date.text.isEmpty()){
                    date.setError("Required Field")
                    return@setOnClickListener
                }else if(name.text.isEmpty()){
                    name.setError("Required Field")
                    return@setOnClickListener
                }else if(age.text.isEmpty()){
                    age.setError("Required Field")
                    return@setOnClickListener
                }else if(contactNo.text.isEmpty()){
                    contactNo.setError("Required Field")
                    return@setOnClickListener
                }

                var username:String = username.text.toString()
                var password:String = password.text.toString()
                var name:String = name.text.toString()
                var age = age.text
                var gender:String = contactNo.text.toString()
                var from:String = from.text.toString()
                var to:String = to.text.toString()
                var date:String = date.text.toString()


                database.execSQL("INSERT INTO saved (username,password,fromm,too,date,name,age,contact) VALUES ('" + username + "','" + password + "','" + from + "','" + to + "','" + date + "','" + name + "'," + age + ",'" + gender + "')")
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, BookingActivity::class.java)
                intent.putExtra("username",username)
                intent.putExtra("password",password)
                startActivity(intent)
            }
        }
    }
}
