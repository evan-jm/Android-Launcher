import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(private val apps: List<ApplicationInfo>, private val context: Context) :
    RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    private val packageManager: PackageManager = context.packageManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_item, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.appName.text = app.loadLabel(packageManager)
        holder.appIcon.setImageDrawable(app.loadIcon(packageManager))

        holder.itemView.setOnClickListener {
            val launchIntent = packageManager.getLaunchIntentForPackage(app.packageName)
            launchIntent?.let { context.startActivity(it) }
        }
    }

    override fun getItemCount(): Int = apps.size

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appName: TextView = itemView.findViewById(R.id.app_name)
        val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
    }
}