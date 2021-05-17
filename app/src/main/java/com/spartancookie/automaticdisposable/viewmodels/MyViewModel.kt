package com.spartancookie.automaticdisposable.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.spartancookie.automaticdisposable.disposable.AutomaticDisposable
import com.spartancookie.automaticdisposable.dtos.TestDTO

private const val DTO1_UPDATE = 1000F
private const val DTO2_UPDATE = 2000F
private const val DTO3_UPDATE = 4000F
private const val DTO4_UPDATE = 8000F
private const val DTO5_UPDATE = 16000F

class MyViewModel : ViewModel() {

    // Don't do this, I just need the context to show toasts. This must be deleted
    lateinit var context: Context

    val dto1: TestDTO by lazy { TestDTO(AutomaticDisposable<String>(DTO1_UPDATE, name = "DTO1", context = context).apply { setValue("Value") }) }
    val dto2: TestDTO by lazy { TestDTO(AutomaticDisposable<String>(DTO2_UPDATE, name = "DTO2", context = context).apply { setValue("Value") }) }
    val dto3: TestDTO by lazy { TestDTO(AutomaticDisposable<String>(DTO3_UPDATE, name = "DTO3", context = context).apply { setValue("Value") }) }
    val dto4: TestDTO by lazy { TestDTO(AutomaticDisposable<String>(DTO4_UPDATE, name = "DTO4", context = context, callable = { "New Value" }).apply { setValue("Value") }) }
    val dto5: TestDTO by lazy { TestDTO(AutomaticDisposable<String>(DTO5_UPDATE, name = "DTO5", context = context).apply { setValue("Value") }) }

    fun accessDTO1() { dto1.string.get() }
    fun accessDTO2() { dto2.string.get() }
    fun accessDTO3() { dto3.string.get() }
    fun accessDTO4() { dto4.string.get() }
    fun accessDTO5() { dto5.string.get() }

    fun resetDTO1() { dto1.string.setValue("Value") }
    fun resetDTO2() { dto2.string.setValue("Value") }
    fun resetDTO3() { dto3.string.setValue("Value") }
    fun resetDTO4() { dto4.string.setValue("Value") }
    fun resetDTO5() { dto5.string.setValue("Value") }

}