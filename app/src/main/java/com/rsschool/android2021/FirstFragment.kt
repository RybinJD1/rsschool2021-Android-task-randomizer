package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minValue: EditText
    private lateinit var maxValue: EditText
    private lateinit var sender: Sender

    override fun onAttach(context: Context) {
        super.onAttach(context);
        sender = context as Sender
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            val min = minValue.text.toString()
            val max = maxValue.text.toString()
            validateInputData(min, max)
        }
    }

    private fun validateInputData(min: String, max: String) {
        if (min.isEmpty() || max.isEmpty()) {
            ViewUtil.showMessage(
                requireContext(), "Empty fields are not allowed!\n" +
                        "Please, try again."
            )
        } else if (max.toInt() < min.toInt()) {
            ViewUtil.showMessage(
                requireContext(),
                "The minimum value must be less than the maximum.\nPlease, try again."
            )
        } else {
            sender.send(min.toInt(), max.toInt());
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}