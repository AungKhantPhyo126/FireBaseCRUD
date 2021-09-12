package co.xware_tech.firebasecrud.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import co.xware_tech.firebasecrud.R
import co.xware_tech.firebasecrud.StudentData
import co.xware_tech.firebasecrud.databinding.ItemRecyclerStudentsBinding


class RecyclerAdapter(private val viewModel: HomeViewModel,private val navigateEdit: (StudentData) -> Unit) :
    BaseListAdapter<StudentData, RecyclerViewHolder>(CategoryDiffCallback) {

    override fun getItemLayoutId(): Int = R.layout.item_recycler_students

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemRecyclerStudentsBinding.inflate(LayoutInflater.from(parent.context), parent, false),viewModel,
            navigateEdit
        )
    }
}

class RecyclerViewHolder(private val binding: ItemRecyclerStudentsBinding,private val viewModel: HomeViewModel,
                         private val navigateEdit: (StudentData) -> Unit) :
    BaseViewHolder<StudentData>(binding.root) {

    override fun bind(data: StudentData) {
        binding.tvName.text=data.name
        binding.btnDelete.setOnClickListener {
            viewModel.deleteName(data.id)
        }
        binding.btnEdit.setOnClickListener {
            navigateEdit(data)
        }
    }

}

object CategoryDiffCallback : DiffUtil.ItemCallback<StudentData>() {
    override fun areItemsTheSame(oldItem: StudentData, newItem: StudentData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StudentData, newItem: StudentData): Boolean {
        return oldItem == newItem
    }

}