package com.mrspd.sps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_player_details.*
import kotlinx.android.synthetic.main.fragment_player_details.view.*

class PlayerDetailsFragment : Fragment() {
    var Hack = 1
    private var player1name: String? = "No Name"
    private var player2namee: String? = "No name"
    private var Mode: String = ""
    private var roundscount: Int? = 0
    var score = 0

    // TODO: Rename and change types of parameters
    private var Modee: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Modee = it.getString(Companion.ARG_PARAM3)
            param2 = it.getString(Companion.ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_player_details, container!!, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ////////////////
        view.backButton.setOnClickListener {
            val fragment = HomePageFragment()
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.myFragment, fragment)
            transaction.commit()

        }
        ////////////////











        if (Modee == "Solo"){

            player2name.visibility = View.INVISIBLE
            etPlayer2name.visibility = View.INVISIBLE

        }
        else
        {


            Hack = 2
            player2name.visibility = View.VISIBLE
            etPlayer2name.visibility = View.VISIBLE

        }

            view.btGo.setOnClickListener {
                if (Hack ==1){
                    player1name = etEnteredNum!!.text.toString()

                    if (!(player1name.equals(""))) {
                        if (etEnteredNum2!!.text.toString() != "") {
                            roundscount = etEnteredNum2!!.text.toString().toInt()
                        }
                        if (roundscount != 0 && (roundscount!! < 8)) {
                            //.newInstance(gamenumber!!.toInt(), roundscount!!)
                            val fragment = GameFragment.newInstance(player1name!!, roundscount!!,Modee!!,player1name!!,player2namee!!)
                            val transaction = fragmentManager!!.beginTransaction()
                            transaction.replace(R.id.myFragment, fragment)
                            transaction.commit()
                        } else {
                            val snack =
                                Snackbar.make(
                                    it,
                                    "Please Enter Number Of Rounds 🙂 ",
                                    Snackbar.LENGTH_LONG
                                )
                            snack.show()
                        }


                    } else {
                        val snack =
                            Snackbar.make(it, "Please Enter Your Name 🙂 ", Snackbar.LENGTH_LONG)
                        snack.show()
                    }
                }
                else{
                    player1name = etEnteredNum!!.text.toString()
                    player2namee = etPlayer2name!!.text.toString()

                    if (!(player1name.equals("")) || !(player2namee.equals(""))) {
                        if (etEnteredNum2!!.text.toString() != "") {
                            roundscount = etEnteredNum2!!.text.toString().toInt()
                        }
                        if (roundscount != 0 && (roundscount!! < 100)) {
                            //.newInstance(gamenumber!!.toInt(), roundscount!!)
                            val fragment = GameFragment.newInstance(player1name!!, roundscount!!,Modee!!,player1name!!,player2namee!!)
                            val transaction = fragmentManager!!.beginTransaction()
                            transaction.replace(R.id.myFragment, fragment)
                            transaction.commit()
                        } else {
                            val snack =
                                Snackbar.make(
                                    it,
                                    "Please Enter Number Of Rounds 🙂 ",
                                    Snackbar.LENGTH_LONG
                                )
                            snack.show()
                        }


                    } else {
                        val snack =
                            Snackbar.make(it, "Please Enter Your Name 🙂 ", Snackbar.LENGTH_LONG)
                        snack.show()
                    }


            }
        }

        }

        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment PlayerDetailsFragment.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance( Mode : String) =
                PlayerDetailsFragment().apply {
                    Log.d("SPD", Mode)

                    arguments = Bundle().apply {
                        putString(Companion.ARG_PARAM3, Mode)

                    }
                }

            private const val ARG_PARAM5 = "param5"
            private const val ARG_PARAM1 = "param1"
            private const val ARG_PARAM2 = "param2"
            private const val ARG_PARAM3 = "param3"
            private const val ARG_PARAM4 = "param4"
        }
    }
