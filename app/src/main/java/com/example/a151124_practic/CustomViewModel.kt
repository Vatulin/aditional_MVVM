package com.example.a151124_practic

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class CustomViewModel : ViewModel() {

    private var appContext : Context? = null

    val mutableLDEditText = MutableLiveData<Int>().apply { value = View.GONE }
    val mutableLDSum = MutableLiveData<Int>().apply { value = 0 }

    val products = ArrayList<Boolean>(List(6) {false})
    val delivery = arrayListOf("Самовывоз", "Курьер", "Дрон")
    var TypeOfDelivery = "Самовывоз"
    var switch = false

    fun initContext(context: Context) {
        appContext = context.applicationContext
    }

    fun GetMoney() {
        if (mutableLDEditText.value == View.GONE) {
            mutableLDEditText.value = View.VISIBLE
            switch = true
        }
        else {
            mutableLDEditText.value = View.GONE
            switch = false
        }
    }

    fun updateProduct(index: Int, isChecked: Boolean) {
        if (index in products.indices) {
            products[index] = isChecked
        }
    }

    fun GetProducts(poductsBuy : ArrayList<Boolean>) : ArrayList<String> {
        val products = appContext?.resources?.getStringArray(R.array.products)
        val buy = ArrayList<String>()
        for (i in 0..poductsBuy.size - 1) {
            if (poductsBuy[i]) { buy.add(products!![i])}
        }
        return buy
    }

    fun updateDelivery(index: Int, isChecked: Boolean) {
        if (index in products.indices) {
            TypeOfDelivery = delivery[index]
        }
    }

    fun getSumOfMoney(editText: EditText) {
        if (editText.text.toString().isNotEmpty()) {
            mutableLDSum.value = editText.text.toString().toInt()
            println(mutableLDSum.value)
        }
        else {
            mutableLDSum.value = 0
        }
    }


    fun SendButton(view: View) {

        if (switch) {
            if (mutableLDSum.value == 0) {
                Snackbar.make(view, "Пожалуйста введите сумму чаевых", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, "Данные о заказе: ${GetProducts(products).joinToString(" ")} " +
                        "Тип доставки: $TypeOfDelivery Сумма чаевых: ${mutableLDSum.value.toString()}", Snackbar.LENGTH_SHORT).show()
            }
        }
        else {
            if (GetProducts(products).isEmpty()) {
                Snackbar.make(view, "Пожалуйста выберите хотя бы один товар", Snackbar.LENGTH_SHORT).show()
            }
            else {
                Snackbar.make(view, "Данные о заказе: ${GetProducts(products).joinToString(" ")} " +
                        "Тип доставки: $TypeOfDelivery", Snackbar.LENGTH_SHORT).show()
            }
        }

    }
}