package com.facil.notes.framework

import androidx.appcompat.app.AppCompatActivity
import com.facil.notes.database.AppDatabase

abstract class BaseActivity : AppCompatActivity() {
    protected fun inflateFragment(resourceId: Int, fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(resourceId, fragment)
            .commitNow()
    }

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this)
    }
}
