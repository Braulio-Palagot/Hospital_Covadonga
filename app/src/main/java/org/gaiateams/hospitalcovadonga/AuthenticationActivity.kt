package org.gaiateams.hospitalcovadonga

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.gaiateams.hospitalcovadonga.utils.IS_LOGGED
import org.gaiateams.hospitalcovadonga.utils.PREFS_NAME
import org.gaiateams.hospitalcovadonga.utils.auth
import org.gaiateams.hospitalcovadonga.utils.preferences

class AuthenticationActivity : AppCompatActivity() {
    companion object {
        const val PERMISSION_ID = 34
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        if (preferences.getBoolean(IS_LOGGED, false)) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        if (!checkPermission()) {
            requestPermissions()
        }
    }

    private fun checkPermission(): Boolean {
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CALL_PHONE),
            PERMISSION_ID
        )
    }
}