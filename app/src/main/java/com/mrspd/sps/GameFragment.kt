package com.mrspd.sps

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"


class GameFragment : Fragment() {

    var rock : Int =-1
    var ps: Int = 0
    var cs: Int = 0
    var PlayerMove: Int = 0
    var CompMove: Int = 0

    // TODO: Rename and change types of parameters
    private var gamename: String? = null
    private var player1namee: String? = null
    private var player2namee: String? = null
    private var Mode: String? = null
    private var roundscount: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gamename = it.getString(ARG_PARAM1)
            player1namee = it.getString(ARG_PARAM4)
            player2namee = it.getString(ARG_PARAM5)
            Mode = it.getString(ARG_PARAM3)
            roundscount = it.getInt(ARG_PARAM2)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("playerscore", ps)
        outState.putInt("compscore", cs)
        outState.putInt("CompMove", CompMove)
        outState.putInt("PlayerMove", PlayerMove)
        outState.putInt("rock", rock)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {

            ps = savedInstanceState.getInt("playerscore")
            cs = savedInstanceState.getInt("compscore")
            CompMove = savedInstanceState.getInt("CompMove")
            PlayerMove = savedInstanceState.getInt("PlayerMove")
            rock = savedInstanceState.getInt("rock")

            ///////////
            when (rock) {
                1 -> {
                    rock_stone.visibility = View.VISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                }
                2 -> {
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.VISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                }
                3 -> {
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.VISIBLE
                }
            }
            ///////////
            playerMove.setImageResource(PlayerMove)
            compMove.setImageResource(CompMove)
            player_score.text = ps.toString()
            comp_score.text = cs.toString()
        }


        view.backButton.setOnClickListener {
            val fragment = HomePageFragment()
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.myFragment, fragment)
            transaction.commit()

        }

        d("SPD", Mode)
        if (Mode == "Solo") {
            rock_button3.visibility = View.INVISIBLE
            scissor_button3.visibility = View.INVISIBLE
            paper_button3.visibility = View.INVISIBLE
            view.paper_button.setOnClickListener {
                PlayerMove = R.drawable.paper_right
                playerMove.setImageResource(PlayerMove)
                val r = Random()
                val cmove = r.nextInt(3)
                doNecessaryComputation(cmove, 1, 0)
            }
            view.rock_button.setOnClickListener {
                PlayerMove = R.drawable.stone_right
                playerMove.setImageResource(PlayerMove)
                val r = Random()
                val cmove = r.nextInt(3)
                doNecessaryComputation(cmove, 0, 0)
            }
            view.scissor_button.setOnClickListener {
                PlayerMove = R.drawable.scissor_right
                playerMove.setImageResource(PlayerMove)
                val r = Random()
                val cmove = r.nextInt(3)
                doNecessaryComputation(cmove, 2, 0)
            }


        } else if (Mode == "MultiPlayer") {

            computer_choice_label.text = "Player2 "
            your_choice_label.text = "Player1 "
            computer_win_label.text = "Player2 Wins"
            your_win_label.text = "Player1 Wins"

            rock_button3.visibility = View.INVISIBLE
            scissor_button3.visibility = View.INVISIBLE
            paper_button3.visibility = View.INVISIBLE
            view.paper_button.setOnClickListener {
                PlayerMove = R.drawable.paper_right
                playerMove.setImageResource(PlayerMove)
                getPlayer2choice(1)
            }
            view.rock_button.setOnClickListener {
                PlayerMove = R.drawable.stone_right
                playerMove.setImageResource(PlayerMove)
                getPlayer2choice(0)
            }
            view.scissor_button.setOnClickListener {
                PlayerMove = R.drawable.scissor_right
                playerMove.setImageResource(PlayerMove)
                getPlayer2choice(2)
            }


        }

    }

    private fun getPlayer2choice(player1choice: Int) {
        rock_button3.visibility = View.VISIBLE
        scissor_button3.visibility = View.VISIBLE
        paper_button3.visibility = View.VISIBLE
        rock_button.visibility = View.INVISIBLE
        scissor_button.visibility = View.INVISIBLE
        paper_button.visibility = View.INVISIBLE
        view!!.paper_button3.setOnClickListener {


            rock_button3.visibility = View.INVISIBLE
            scissor_button3.visibility = View.INVISIBLE
            paper_button3.visibility = View.INVISIBLE
            rock_button.visibility = View.VISIBLE
            scissor_button.visibility = View.VISIBLE
            paper_button.visibility = View.VISIBLE



            CompMove = R.drawable.paper_right
            compMove.setImageResource(CompMove)
            doNecessaryComputation(player1choice, 1, 1)
        }
        view!!.rock_button3.setOnClickListener {


            rock_button3.visibility = View.INVISIBLE
            scissor_button3.visibility = View.INVISIBLE
            paper_button3.visibility = View.INVISIBLE
            rock_button.visibility = View.VISIBLE
            scissor_button.visibility = View.VISIBLE
            paper_button.visibility = View.VISIBLE


            CompMove = R.drawable.stone_right
            compMove.setImageResource(CompMove)
            doNecessaryComputation(player1choice, 0, 1)
        }
        view!!.scissor_button3.setOnClickListener {


            rock_button3.visibility = View.INVISIBLE
            scissor_button3.visibility = View.INVISIBLE
            paper_button3.visibility = View.INVISIBLE
            rock_button.visibility = View.VISIBLE
            scissor_button.visibility = View.VISIBLE
            paper_button.visibility = View.VISIBLE

            CompMove = R.drawable.scissor_right
            compMove.setImageResource(CompMove)
            doNecessaryComputation(player1choice, 2, 1)
        }


    }

    @SuppressLint("SetTextI18n")
    private fun doNecessaryComputation(cmove: Int, pmove: Int, mode: Int) {
        if (mode == 0) {
            when (cmove) {
                0 -> compMove.setImageResource(R.drawable.stone_left)
                1 -> compMove.setImageResource(
                    R.drawable.paper_left
                )
                2 -> compMove.setImageResource(R.drawable.scissor_left)
            }
            when (cmove) {
                0 -> CompMove = R.drawable.stone_left
                1 -> CompMove =
                    R.drawable.paper_left

                2 -> CompMove = R.drawable.scissor_left
            }
            ps = player_score.text.toString().toInt()
            cs = comp_score.text.toString().toInt()

           rock = -1
            ////
            if (cmove == 0 && pmove == 1) {
                ps++
                rock = 2
            } else if (cmove == 0 && pmove == 0) {
                rock_stone.visibility = View.INVISIBLE
                rock_paper.visibility = View.INVISIBLE
                rock_Scissor.visibility = View.INVISIBLE
            } else if (cmove == 0 && pmove == 2) {
                cs++
                rock = 1
            } else if (cmove == 1 && pmove == 0) {
                cs++
                rock = 2
            } else if (cmove == 1 && pmove == 2) {
                ps++
                rock = 2
            } else if (cmove == 1 && pmove == 1) {
                rock_stone.visibility = View.INVISIBLE
                rock_paper.visibility = View.INVISIBLE
                rock_Scissor.visibility = View.INVISIBLE
            } else if (cmove == 2 && pmove == 1) {
                cs++
                rock = 3
            } else if (cmove == 2 && pmove == 2) {
                rock_stone.visibility = View.INVISIBLE
                rock_paper.visibility = View.INVISIBLE
                rock_Scissor.visibility = View.INVISIBLE
            } else if (cmove == 2 && pmove == 0) {
                ps++
                rock = 1
            }
            /////

            when (rock) {
                1 -> {
                    rock_stone.visibility = View.VISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                }
                2 -> {
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.VISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                }
                3 -> {
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.VISIBLE
                }
            }

            if (ps == roundscount || cs == roundscount) {
                val winLoseStatus = Dialog(context!!)
                winLoseStatus.setContentView(R.layout.win_lose_status)
                winLoseStatus.setTitle("Results")
                val cfs =
                    winLoseStatus.findViewById<View>(R.id.comp_final_score) as TextView
                val pfs =
                    winLoseStatus.findViewById<View>(R.id.player_final_score) as TextView
                val status =
                    winLoseStatus.findViewById<View>(R.id.win_lose) as TextView
                val play_again =
                    winLoseStatus.findViewById<View>(R.id.play_again) as Button
                val exit =
                    winLoseStatus.findViewById<View>(R.id.exit) as Button
                val smiley =
                    winLoseStatus.findViewById<View>(R.id.smiley) as ImageView
                exit.setOnClickListener {
                    winLoseStatus.dismiss()
                    val fragment = HomePageFragment()
                    val transaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.myFragment, fragment)
                    transaction.commit()
                }
                play_again.setOnClickListener {
                    ps = 0
                    cs = 0
                    player_score.text = ps.toString()
                    comp_score.text = cs.toString()
                    compMove.setImageResource(0)
                    playerMove.setImageResource(0)
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                    winLoseStatus.dismiss()
                }
                cfs.text = cs.toString()
                pfs.text = ps.toString()
                if (ps < cs) {
                    status.text = "$player1namee Lose!"
                    smiley.setImageResource(R.mipmap.sad_smiley_large)
                } else {
                    status.text = "$player1namee Win!"
                    smiley.setImageResource(R.mipmap.cool_smiley_large)
                }
                winLoseStatus.show()
            }

            player_score.text = ps.toString()
            comp_score.text = cs.toString()

        } else {
            ps = player_score.text.toString().toInt()
            cs = comp_score.text.toString().toInt()

          rock = -1
            if (cmove == 0 && pmove == 1) {
                cs++
                rock = 2
            } else if (cmove == 0 && pmove == 0) {
                rock_stone.visibility = View.INVISIBLE
                rock_paper.visibility = View.INVISIBLE
                rock_Scissor.visibility = View.INVISIBLE
            } else if (cmove == 0 && pmove == 2) {
                ps++
                rock = 1
            } else if (cmove == 1 && pmove == 0) {
                ps++
                rock = 2
            } else if (cmove == 1 && pmove == 2) {
                cs++
                rock = 2
            } else if (cmove == 1 && pmove == 1) {
                rock_stone.visibility = View.INVISIBLE
                rock_paper.visibility = View.INVISIBLE
                rock_Scissor.visibility = View.INVISIBLE
            } else if (cmove == 2 && pmove == 1) {
                ps++
                rock = 3
            } else if (cmove == 2 && pmove == 2) {
                rock_stone.visibility = View.INVISIBLE
                rock_paper.visibility = View.INVISIBLE
                rock_Scissor.visibility = View.INVISIBLE
            } else if (cmove == 2 && pmove == 0) {
                cs++
                rock = 1
            }
            ////
            when (rock) {
                1 -> {
                    rock_stone.visibility = View.VISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                }
                2 -> {
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.VISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                }
                3 -> {
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.VISIBLE
                }
            }
            if (ps == roundscount || cs == roundscount) {
                val winLoseStatus = Dialog(context!!)
                winLoseStatus.setContentView(R.layout.win_lose_status)
                winLoseStatus.setTitle("Results")
                val player2 =
                    winLoseStatus.findViewById<View>(R.id.comp_final_score) as TextView
                val player1name =
                    winLoseStatus.findViewById<View>(R.id.afterwin_clabel) as TextView
                val player2name =
                    winLoseStatus.findViewById<View>(R.id.afterwin_plabel) as TextView
                val player1 =
                    winLoseStatus.findViewById<View>(R.id.player_final_score) as TextView
                val status =
                    winLoseStatus.findViewById<View>(R.id.win_lose) as TextView
                val play_again =
                    winLoseStatus.findViewById<View>(R.id.play_again) as Button
                val exit =
                    winLoseStatus.findViewById<View>(R.id.exit) as Button
                val smiley =
                    winLoseStatus.findViewById<View>(R.id.smiley) as ImageView
                exit.setOnClickListener {
                    winLoseStatus.dismiss()
                    val fragment = HomePageFragment()
                    val transaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.myFragment, fragment)
                    transaction.commit()

                }
                play_again.setOnClickListener {
                    ps = 0
                    cs = 0
                    player_score.text = ps.toString()
                    comp_score.text = cs.toString()
                    compMove.setImageResource(0)
                    playerMove.setImageResource(0)
                    rock_stone.visibility = View.INVISIBLE
                    rock_paper.visibility = View.INVISIBLE
                    rock_Scissor.visibility = View.INVISIBLE
                    winLoseStatus.dismiss()
                }

                player2.text = cs.toString()
                player2name.text = "Player2 :"
                player1name.text = "Player1 :"
                player1.text = ps.toString()
                if (ps < cs) {
                    status.text = "$player2namee Win"
                    smiley.setImageResource(R.mipmap.cool_smiley_large)
                } else
                    status.text = " $player1namee Win"
                smiley.setImageResource(R.mipmap.cool_smiley_large)
                winLoseStatus.show()
            }
            player_score.text = ps.toString()
            comp_score.text = cs.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            gamenumber: String,
            roundscount: Int,
            Mode: String,
            player1namee: String,
            player2namee: String
        ) =
            GameFragment().apply {
                Log.d("SPD", Mode)

                arguments = Bundle().apply {
                    putString(ARG_PARAM1, gamenumber)
                    putString(ARG_PARAM3, Mode)
                    putInt(ARG_PARAM2, roundscount)
                    putString(ARG_PARAM4, player1namee)
                    putString(ARG_PARAM5, player2namee)
                }
            }
    }
}
