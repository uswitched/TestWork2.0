package ua.uswitch.testwork20.adapters

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.uswitch.testwork20.R
import ua.uswitch.testwork20.databinding.ItemFilmBinding
import ua.uswitch.testwork20.fragments.DetailsFragment
import ua.uswitch.testwork20.fragments.ListFragments
import ua.uswitch.testwork20.models.Film

class FilmAdapter(private val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<FilmHolder>(), CustomHandler {

    private var items = mutableListOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater, parent, false)
        return  FilmHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val item = items[position]
        item.idPos = position
        holder.bind(item)

        holder.itemUserRecyclerBinding.handler = this
    }

    override fun getItemCount(): Int =
        items.size

    override fun onClick(position: Int) =
        onItemClicked(items[position].ID)

    override fun onLongClickListener(position: Int, view: View): Boolean {
        val alertDialog = AlertDialog.Builder(view.context)
            .setTitle(view.context.getString(R.string.alertDialogMainMsg))
            .setMessage(view.context.getString(R.string.alertDialogMsg))
            .setCancelable(false)
            .setNegativeButton(view.context.getString(R.string.close)) { dialog, _ -> dialog?.cancel(); }
            .setPositiveButton(view.context.getString(R.string.ok)) { _, _ ->
                items.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, itemCount)
            }
        alertDialog.show()
        return true
    }

    fun updateList(results: List<Film>) {
        items = results.toMutableList()
        notifyDataSetChanged()
    }
}

class FilmHolder(
    binding: ItemFilmBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    var itemUserRecyclerBinding: ItemFilmBinding= binding

    fun bind(film: Film) {
        itemUserRecyclerBinding.film = film
        itemUserRecyclerBinding.executePendingBindings()
    }
}