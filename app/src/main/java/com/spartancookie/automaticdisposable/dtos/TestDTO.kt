package com.spartancookie.automaticdisposable.dtos

import com.spartancookie.automaticdisposable.disposable.AutomaticDisposable

data class TestDTO(var string: AutomaticDisposable<String>)