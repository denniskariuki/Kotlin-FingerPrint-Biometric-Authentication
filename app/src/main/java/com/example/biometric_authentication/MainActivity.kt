package com.example.biometric_authentication

import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

lateinit var btnAuth: Button
lateinit var authenticationstatus:TextView
lateinit var executor: Executor
lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()



        btnAuth = findViewById(R.id.btAuth)
        authenticationstatus= findViewById(R.id.authenticationstatus)
        executor=ContextCompat.getMainExecutor(this)
        biometricPrompt= androidx.biometric.BiometricPrompt(this@MainActivity, executor,object :androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                authenticationstatus.text="Error.... Try Again"+errString
            }

            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                authenticationstatus.text="Successful Biometric Authentication"
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                authenticationstatus.text="Authentication Failed"
            }
        })
        promptInfo=androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Bits and Bytes Biometric Authentication")
            .setSubtitle("Login Using FingerPrint")
            .setNegativeButtonText("Cancel")
            .build()

btnAuth.setOnClickListener {
    biometricPrompt.authenticate(promptInfo)

}


    }
}



/*Developed by Bits and Bytes : Dennnis Kariuki
On Saturday 23rd April 2022
 */