package com.mrspd.sps

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlin.system.exitProcess


class HomePageFragment : Fragment() {
    var highestscore: Int = 0
    var currentscore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentscore = it.getInt("score")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ///////////////////////////
        ForAnim1.visibility = View.VISIBLE
        ForAnim2.visibility = View.VISIBLE
        YoYo.with(Techniques.Wobble)
            .duration(1000)
            .repeat(1000)
            .playOn(view.findViewById(R.id.ForAnim1))
        YoYo.with(Techniques.Shake)
            .duration(1000)
            .repeat(1000)
            .playOn(view.findViewById(R.id.ForAnim2))
        YoYo.with(Techniques.Pulse)
            .duration(2000)
            .repeat(1000)
            .playOn(view.findViewById(R.id.tvHighscore))



        view.startgame.setOnClickListener {
            view.igSolo.visibility = View.VISIBLE
            view.igMulti.visibility = View.VISIBLE
            view.startgame.visibility = View.INVISIBLE
            view.exitgame.visibility = View.INVISIBLE

            view.igSolo.setOnClickListener {
                val fragment = PlayerDetailsFragment.newInstance("Solo")
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.myFragment, fragment)
                transaction?.remove(this)
                transaction?.hide(this)
                transaction?.commit()

            }
            view.igMulti.setOnClickListener {
                val fragment = PlayerDetailsFragment.newInstance("MultiPlayer")
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.myFragment, fragment)
                transaction?.remove(this)
                transaction?.hide(this)
                transaction?.commit()

            }


        }
        view.exitgame.setOnClickListener {
            exitProcess(0)
        }

    }

    private fun runAnim() {
        ForAnim1.setImageResource(R.drawable.paper_right)
        ForAnim2.setImageResource(R.drawable.scissor_left)
        Handler().postDelayed(Runnable {
            ForAnim1.visibility = View.VISIBLE
            ForAnim2.visibility = View.VISIBLE
            YoYo.with(Techniques.SlideInLeft)
                .duration(1000)
                .repeat(0)
                .playOn(view!!.findViewById(R.id.ForAnim1))
            YoYo.with(Techniques.SlideInRight)
                .duration(1000)
                .repeat(0)
                .playOn(view!!.findViewById(R.id.ForAnim2))


            Handler().postDelayed(Runnable {
                YoYo.with(Techniques.Shake)
                    .duration(1000)
                    .repeat(0)
                    .playOn(view!!.findViewById(R.id.ForAnim1))
                YoYo.with(Techniques.Shake)
                    .duration(1000)
                    .repeat(0)
                    .playOn(view!!.findViewById(R.id.ForAnim2))

                Handler().postDelayed(Runnable {
                    YoYo.with(Techniques.SlideOutLeft)
                        .duration(500)
                        .repeat(0)
                        .playOn(view!!.findViewById(R.id.ForAnim1))
                    YoYo.with(Techniques.SlideOutRight)
                        .duration(500)
                        .repeat(0)
                        .playOn(view!!.findViewById(R.id.ForAnim2))

                }, 500)
            }, 1000)
        }, 2500)

    }


    companion object {

        @JvmStatic
        fun newInstance(param1: Int) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putInt("score", param1)

                }
            }
    }
}
