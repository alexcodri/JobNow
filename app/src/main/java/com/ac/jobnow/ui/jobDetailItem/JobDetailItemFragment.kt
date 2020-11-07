package com.ac.jobnow.ui.jobDetailItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ac.jobnow.databinding.FragmentJobDetailItemBinding
import com.ac.jobnow.repository.model.jobModels.Job
import com.ac.jobnow.utils.extensions.addSkillTextViews

class JobDetailItemFragment : Fragment() {

    private var jobDetailsBinding: FragmentJobDetailItemBinding? = null
    private val binding get() = jobDetailsBinding!!
    private var job: Job? = null

    companion object {
        @JvmStatic
        fun newInstance(instanceJob: Job): JobDetailItemFragment {
            val fragment = JobDetailItemFragment()
            val args = Bundle()
            args.putParcelable("job", instanceJob)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        job = arguments?.getParcelable("job")
        jobDetailsBinding = FragmentJobDetailItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            jobDetailJobDescriptionTv.text = job?.jobDescription
            jobDetailsFragmentSalaryTv.text = job?.salary
            job?.jobSkill?.forEach {
                fragmentJobDescriptionFlexBoxJobSkills.addSkillTextViews(it)
            }
        }
    }
}