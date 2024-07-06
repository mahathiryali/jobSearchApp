package com.example.jobsearchapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private var jobs: List<Job>, private val showSaveButton: Boolean, private val showDeleteButton: Boolean) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {
    var onSaveJobClickListener: ((Job) -> Unit)? = null
    var onDeleteJobClickListener: ((Job) -> Unit)? = null

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val jobCompany: TextView = itemView.findViewById(R.id.jobCompany)
        val jobLocation: TextView = itemView.findViewById(R.id.jobLocation)
        val saveJobBtn: Button = itemView.findViewById(R.id.saveJobBtn)
        val deleteJobBtn: Button = itemView.findViewById(R.id.deleteJobBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_item, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]
        holder.jobTitle.text = job.title
        holder.jobCompany.text = job.company.display_name
        holder.jobLocation.text = job.location.display_name

        holder.saveJobBtn.visibility = if (showSaveButton) View.VISIBLE else View.GONE
        holder.saveJobBtn.setOnClickListener {
            onSaveJobClickListener?.invoke(job)
        }

        holder.deleteJobBtn.visibility = if (showDeleteButton) View.VISIBLE else View.GONE
        holder.deleteJobBtn.setOnClickListener {
            onDeleteJobClickListener?.invoke(job)
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    fun updateJobs(jobs: List<Job>) {
        this.jobs = jobs
        notifyDataSetChanged()
    }
}