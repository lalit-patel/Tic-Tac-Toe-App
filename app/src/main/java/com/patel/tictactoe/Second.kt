package com.patel.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_first.button1
import kotlinx.android.synthetic.main.activity_first.button2
import kotlinx.android.synthetic.main.activity_first.button3
import kotlinx.android.synthetic.main.activity_first.button4
import kotlinx.android.synthetic.main.activity_first.button5
import kotlinx.android.synthetic.main.activity_first.button6
import kotlinx.android.synthetic.main.activity_first.button7
import kotlinx.android.synthetic.main.activity_first.button8
import kotlinx.android.synthetic.main.activity_first.button9
import kotlinx.android.synthetic.main.activity_first.displayTv
import kotlinx.android.synthetic.main.activity_first.menubtn
import kotlinx.android.synthetic.main.activity_first.resetBtn
import kotlinx.android.synthetic.main.activity_second.*

class Second : AppCompatActivity(),View.OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(4){IntArray(4)}
    lateinit var board : Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        board = arrayOf(
            arrayOf(button1,button2,button3,button4),
            arrayOf(button5,button6,button7,button8),
            arrayOf(button9,button10,button11,button12),
            arrayOf(button13,button14,button15,button16)
        )

        for (i:Array<Button> in board){
            for (button:Button in i) button.setOnClickListener(this)
        }


        initializeBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()

        }

        menubtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun initializeBoardStatus() {
        for (i:Int in 0..3){
            for (j:Int in 0..3){
                boardStatus[i][j] = -1
            }
        }
        for (i:Array<Button> in board){
            for (button:Button in i){
                button.isEnabled = true
                button.text = ""
            }
        }


    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button1 -> {
                updateValue(row = 0 , col = 0 , player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0 , col = 1 , player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0 , col = 2 , player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 0 , col = 3 , player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1 , col = 0 , player = PLAYER)
            }
            R.id.button6-> {
                updateValue(row = 1 , col = 1 , player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 1 , col = 2 , player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 1 , col = 3 , player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2 , col = 0 , player = PLAYER)
            }
            R.id.button10 -> {
                updateValue(row = 2 , col = 1 , player = PLAYER)
            }
            R.id.button11 -> {
                updateValue(row = 2 , col = 2 , player = PLAYER)
            }R.id.button12 -> {
                updateValue(row = 2 , col = 3 , player = PLAYER)
            }R.id.button13 -> {
                updateValue(row = 3 , col = 0 , player = PLAYER)
            }R.id.button14 -> {
                updateValue(row = 3 , col = 1 , player = PLAYER)
            }R.id.button15 -> {
                updateValue(row = 3 , col = 2 , player = PLAYER)
            }R.id.button16 -> {
                updateValue(row = 3 , col = 3 , player = PLAYER)
            }


        }
        TURN_COUNT++
        PLAYER = !PLAYER



        if (PLAYER){
            updateDisplay("Player1 Turn")
        }else{
            updateDisplay("Player2 Turn ")
        }

        if (TURN_COUNT == 16){
            updateDisplay("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal rows
        for (i in 0..3){
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2] && boardStatus[i][0] == boardStatus[i][3]){
                if (boardStatus[i][0] == 1){
                    updateDisplay("Player1 is winner")
                    break
                }else if (boardStatus[i][0] == 0){
                    updateDisplay("Player2 winner")
                    break
                }
            }
        }
        //Vertical columns
        for (i in 0..3){
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i] && boardStatus[0][i] == boardStatus[3][i]){
                if (boardStatus[0][i] == 1){
                    updateDisplay("Player1 is winner")
                    break
                }else if (boardStatus[0][i] == 0){
                    updateDisplay("Player2 winner")
                    break
                }
            }
        }

        //First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2] && boardStatus[0][0] == boardStatus[3][3]){
            if (boardStatus[0][0] == 1){
                updateDisplay("Player1 winner")
            }else if (boardStatus[0][0] == 0){
                updateDisplay("Player2 winner")
            }
        }

        //Second Diagonal
        if (boardStatus[0][3] == boardStatus[1][2] && boardStatus[0][3] == boardStatus[2][1] && boardStatus[1][2] == boardStatus[3][0]){
            if (boardStatus[0][3] == 1){
                updateDisplay("Player1 is winner")
            }else if (boardStatus[0][3] == 0){
                updateDisplay("Player2 is winner")
            }
        }
    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if (text.contains("winner")){
            disableButton()
        }
    }

    private fun disableButton(){
        for (i:Array<Button> in board){
            for (button:Button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text:String  = if (player) "X" else "O"
        val value:Int  = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        boardStatus[row][col] = value
    }
}
