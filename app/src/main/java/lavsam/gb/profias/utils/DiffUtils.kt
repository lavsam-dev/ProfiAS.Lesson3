package lavsam.gb.profias.utils

import androidx.recyclerview.widget.DiffUtil
import lavsam.gb.profias.model.data.Vocabulary

class DiffUtils(
    private val oldList: List<Vocabulary>,
    private val newList: List<Vocabulary>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text.equals(newList[newItemPosition].text)
                && oldList[oldItemPosition].id.equals(newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}