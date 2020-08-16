package com.facil.notes.framework

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facil.notes.R

abstract class BaseActivity : AppCompatActivity() {
    protected fun inflateFragment(resourceId: Int, fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(resourceId, fragment)
            .commitNow()
    }
}
