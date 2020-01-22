package com.example.topmovies.adapter

import android.annotation.TargetApi
import android.app.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.topmovies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import android.content.Intent
import android.content.Context.ALARM_SERVICE
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.example.topmovies.notification.NotificationReceiver
import java.util.*
import java.util.Calendar.*


class FilmsAdapter : BaseAdapter<FilmsAdapter.FilmsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return FilmsViewHolder(v)
    }

    class FilmsViewHolder(view: View) : BaseAdapter.BaseViewHolder(view) {

        init {
            itemView.schedule_btn.setOnClickListener {
                onButtonClicked(view.context, itemView.title.text, itemView.id)
            }
        }

        @TargetApi(Build.VERSION_CODES.O)
        private fun onButtonClicked(context: Context, title: CharSequence, id: Int){
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val reminder: Calendar = getInstance()
                    TimePickerDialog(
                        context, TimePickerDialog.OnTimeSetListener { _, i, i1 ->
                            reminder.set(SECOND, 0)
                            reminder.set(MINUTE, i1)
                            reminder.set(HOUR_OF_DAY, i)
                            setNotification(reminder, context, title, id)
                            Toast.makeText(context, "Alarm has been set", Toast.LENGTH_SHORT).show()
                        },
                        getInstance().get(HOUR_OF_DAY),
                        getInstance().get(MINUTE),
                        true
                    ).show()

                    reminder.set(YEAR, year)
                    reminder.set(MONTH, monthOfYear)
                    reminder.set(DAY_OF_MONTH, dayOfMonth)
                },
                getInstance().get(YEAR),
                getInstance().get(MONTH),
                getInstance().get(DAY_OF_MONTH)
            ).show()
        }


        private fun setNotification(reminder: Calendar, context: Context, title: CharSequence, id: Int) {

            val time = reminder.timeInMillis
            val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context.applicationContext, NotificationReceiver::class.java)

            alarmIntent.putExtra("title",title)
            alarmIntent.putExtra("id", id)

            val pendingIntent = PendingIntent.getBroadcast(context, (id+time).toInt(), alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)

            }


        override fun bind(item: Any) {
            let {
                item as Film
                view.title.text = item.title

                view.id = item.id!!
                view.release_date.text = item.release_date
                view.overview.text = item.overview
                val url = "https://image.tmdb.org/t/p/w500/" + item.poster_path

                Picasso.get()
                    .load(url)
                    .into(view.poster)
                view.voting.text = item.vote_average.toString()

            }
        }

    }
    data class Film(
        val id: Int?,
        val title: String,
        val release_date: String,
        val overview: String,
        val poster_path: String? = "https://cdn4.iconfinder.com/data/icons/posters/100/poster_07-512.png",
        val vote_average: Float
    )

}

