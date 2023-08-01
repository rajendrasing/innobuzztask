package com.rr.innobuzztask.ui

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rr.innobuzztask.R
import com.rr.innobuzztask.databinding.ActivityMainBinding
import com.rr.innobuzztask.utils.services.MyAccessibilityService

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private val REQUEST_CODE_ENABLE_ACCESSIBILITY = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.getPermission.setOnClickListener(this)


        binding.edPassword.setOnTouchListener(OnTouchListener { view, motionEvent ->
            val DRABLE_LEFT = 0
            val DRABLE_TOP = 1
            val DRABLE_RIGHT = 2
            val DRABLE_BOTTOM = 3
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (motionEvent.rawX >= binding.edPassword.getRight() -
                    binding.edPassword.getCompoundDrawables().get(DRABLE_RIGHT).getBounds().width()
                ) {
                    if (showPassword==true){
                        showPassword = false
                        binding.edPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        binding.edPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0)
                    }else if (showPassword==false){
                        showPassword=true
                        binding.edPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        binding.edPassword.setCompoundDrawables(null,null,null,null)
                        binding.edPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24, 0)
                    }
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.getPermission->{
                if (!isAccessibilityServiceEnabled()) {
                    requestAccessibilityService()
                }
                else{
                    Toast.makeText(this, "Service Start", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val accessibilityService = ComponentName(this, MyAccessibilityService::class.java)
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        return enabledServices?.contains(accessibilityService.flattenToString()) == true
    }

    private fun requestAccessibilityService() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivityForResult(intent, REQUEST_CODE_ENABLE_ACCESSIBILITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ENABLE_ACCESSIBILITY) {
            if (isAccessibilityServiceEnabled()) {
                Toast.makeText(this, "Accessibility service is enabled", Toast.LENGTH_SHORT).show()
                // Accessibility service is enabled
            } else {
                Toast.makeText(this, "Accessibility service is not enabled", Toast.LENGTH_SHORT).show()
                // Accessibility service is not enabled
            }
        }
    }
}