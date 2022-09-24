package app.test.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private var REQUEST_CODE_ENABLE_BT : Int = 1
    lateinit var bAdapter : BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val turnOffButton = findViewById<Button>(R.id.turnOffButton)
        val turnOnButton = findViewById<Button>(R.id.turnOnButton)
        val bluetoothTv = findViewById<ImageView>(R.id.bluetoothTv)
        val bluetoothStatus = findViewById<TextView>(R.id.bluetoothStatus)
        bAdapter = BluetoothAdapter.getDefaultAdapter()

        if (bAdapter == null){
            bluetoothStatus.text = "Bluetooth not available"
        }else{
            bluetoothStatus.text = "Bluetooth is available"
        }

        if (bAdapter.isEnabled){
            bluetoothTv.setImageResource(R.drawable.ic_bluetooth)
        }else{
            bluetoothTv.setImageResource(R.drawable.ic_bluetooth_off)
        }

        turnOnButton.setOnClickListener {
            if (bAdapter.isEnabled){
                Toast.makeText(this, "Already on", Toast.LENGTH_SHORT).show()
            }else{
                var intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) { }
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BT)
            }
        }

        turnOffButton.setOnClickListener {
            if (bAdapter.isEnabled){
                bAdapter.disable()
                Toast.makeText(this, "Already off", Toast.LENGTH_SHORT).show()
            }else{
                bAdapter.disable()
                bluetoothTv.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(this, "Bluetooth turned off", Toast.LENGTH_SHORT).show()
            }
        }

    }
}