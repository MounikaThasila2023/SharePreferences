package com.example.sharepreferences


import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sharepreferences.databinding.GpsLocationFetchingBinding

class GPSLocation : AppCompatActivity(), LocationListener, View.OnClickListener {

    lateinit var binding_gps: GpsLocationFetchingBinding


    private val locationPermissionCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_gps = GpsLocationFetchingBinding.inflate(layoutInflater)
        setContentView(binding_gps.root)
    }

    override fun onClick(v: View?) {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //-------------
        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        /*    OR    */

//        if ((ContextCompat.checkSelfPermission(
//                this,
//                ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED)
//        ) {
//            return
//        }
        //-------------

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0f, this)
    }


    override fun onLocationChanged(location: Location) {
        Toast.makeText(this, "Location is changed", Toast.LENGTH_SHORT).show()
        val myLocation: String =
            "Latitude: " + location.latitude + "  ,\n Longitude: " + location.longitude
        binding_gps.txtLocation.text = myLocation
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
