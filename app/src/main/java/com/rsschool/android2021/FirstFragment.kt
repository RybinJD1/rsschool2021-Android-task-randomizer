package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValue: EditText? = null
    private var maxValue: EditText? = null
    private var sender: Sender? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Sender) sender = context
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
        previousResult?.text = resources.getString(
            R.string.previous_result,
            result
        )
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            val min = minValue?.text.toString()
            val max = maxValue?.text.toString()
            if (validateInputData(min, max)) {
                sender?.send(min.toInt(), max.toInt())
            }
        }
    }

    private fun validateInputData(min: String, max: String): Boolean {
        return if (min.isEmpty() || max.isEmpty()) {
            ViewUtil.showMessage(
                requireContext(), resources.getString(R.string.error_empty_fields)
            )
            false
        } else if (max.toInt() < min.toInt()) {
            ViewUtil.showMessage(
                requireContext(),
                resources.getString(R.string.error_incorrect_value)
            )
            false
        } else {
            true
        }
    }

    companion object {

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val args = bundleOf(PREVIOUS_RESULT_KEY to previousResult)
            return FirstFragment().apply { arguments = args }
        }
    }
}