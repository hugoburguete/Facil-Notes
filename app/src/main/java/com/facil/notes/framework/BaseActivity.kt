package com.facil.notes.framework

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    protected fun inflateFragment(resourceId: Int, fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(resourceId, fragment)
            .commitNow()
    }
}
