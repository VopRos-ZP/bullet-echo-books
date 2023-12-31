package com.vopros.bulkapedia.ui.screens.categories

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.category.Category
import com.vopros.bulkapedia.category.CategoryRepository
import com.vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ErrViewModel() {

    private val _categories = MutableStateFlow<List<Category>?>(null)
    val categories = _categories.asStateFlow()

    fun fetchCategories() {
        coroutine {
            val categories = categoryRepository.fetchAll()
            _categories.emit(categories)
            Log.d("CategoriesViewModel", "$categories")
        }
    }

}