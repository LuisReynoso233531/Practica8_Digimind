package com.example.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydigimind.R
import com.example.mydigimind.databinding.FragmentDashboardBinding
import com.example.mydigimind.ui.Task
import com.example.mydigimind.ui.home.HomeFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
          val root: View = binding.root

        //       val textView: TextView = binding.name
        //      dashboardViewModel.text.observe(viewLifecycleOwner) {
        //         textView.text = it
        //    }
        val btn_time: Button=root.findViewById(R.id.btn_time)
        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()
        }

        val btn_save = root.findViewById<Button>(R.id.done) as Button
        val et_titulo = root.findViewById<EditText>(R.id.name) as EditText
        val checkMonday = root.findViewById<CheckBox>(R.id.monday) as CheckBox
        val checkTuesday = root.findViewById<CheckBox>(R.id.Tuesday) as CheckBox
        val checkWednesday = root.findViewById<CheckBox>(R.id.wednesday) as CheckBox
        val checkThursday = root.findViewById<CheckBox>(R.id.thursday) as CheckBox
        val checkFriday = root.findViewById<CheckBox>(R.id.friday) as CheckBox
        val checkSaturday = root.findViewById<CheckBox>(R.id.saturday) as CheckBox
        val checkSunday = root.findViewById<CheckBox>(R.id.sunday) as CheckBox

        btn_save.setOnClickListener{
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()
            if (checkMonday.isChecked){
                days.add("Monday")
            }
            if (checkTuesday.isChecked){
                days.add("Tuesday")
            }
            if (checkWednesday.isChecked){
                days.add("Wednesday")
            }
            if (checkThursday.isChecked){
                days.add("Thursday")
            }
            if (checkFriday.isChecked){
                days.add("Friday")
            }
            if (checkSaturday.isChecked){
                days.add("Saturday")
            }
            if (checkSunday.isChecked){
                days.add("Sunday")
            }
            var task = Task(title,days,time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context,"new task added",Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}