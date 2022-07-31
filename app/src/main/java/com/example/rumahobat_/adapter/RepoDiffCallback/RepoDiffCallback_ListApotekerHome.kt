package com.example.rumahobat_.adapter.RepoDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.rumahobat_.model.Model_List_Chat_Apoteker

class RepoDiffCallback_ListApotekerHome(private val mOldList: List<Model_List_Chat_Apoteker>,
                       private val mNewList: List<Model_List_Chat_Apoteker>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].nama_panjang == mNewList[newItemPosition].nama_panjang
    }

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].status_satu == mNewList[newItemPosition].status_satu
    }
}