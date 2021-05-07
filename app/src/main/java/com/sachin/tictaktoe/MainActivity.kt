package com.sachin.tictaktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    var player=true
    var turnCount=0

    var boardStatus=Array(size = 3){IntArray(size = 3)}


    lateinit var board  :Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
                arrayOf(btn0,btn1,btn2),
                arrayOf(btn3,btn4,btn5),
                arrayOf(btn6,btn7,btn8)
        )

        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }

        btnReset.setOnClickListener{
            player=true
            turnCount=0
            initialiseBoardStatus()
            etDisplay.setText("X's Turn")

        }
        initialiseBoardStatus()
    }

    private fun initialiseBoardStatus() {
        for (i in 0..2){
            for (j in 0..2){
                boardStatus[i][j] = -1
            }
            for (i in board){
                for (button in i){
                    button.isEnabled =true
                    button.text=""
                }
            }
        }

    }



    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn0-> {
                updateValue(row=0,col=0,currplayer= player)

            } R.id.btn1 ->{
            updateValue(row=0,col=1,currplayer= player)

            } R . id . btn2->{
            updateValue(row=0,col=2,currplayer= player)

            } R . id . btn3->{
            updateValue(row=1,col=0,currplayer= player)

            } R . id . btn4->{
            updateValue(row=1,col=1,currplayer= player)

            } R . id . btn5->{
            updateValue(row=1,col=2,currplayer= player)

            } R . id . btn6->{
            updateValue(row=2,col=0,currplayer= player)

            } R . id . btn7->{
            updateValue(row=2,col=1,currplayer= player)

            } R . id . btn8->{
            updateValue(row=2,col=2,currplayer= player)

            }

        }
        turnCount++
        if(turnCount==9){
            etDisplay.setText("It's a Draw")

        }
        winner()

    }

    private fun winner() {
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){

                if(boardStatus[i][0]==1) {
                    etDisplay.setText("X Wins")
                    disablebuttons()
                }
                else if(boardStatus[i][0]==0){
                    etDisplay.setText("O Wins")
                    disablebuttons()
                }


            }
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1) {
                    etDisplay.setText("X Wins")
                    disablebuttons()
                }
                else if(boardStatus[0][i]==0){
                    etDisplay.setText("O Wins")
                    disablebuttons()
                }
            }

        }
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0]==1) {
                etDisplay.setText("X Wins")
                disablebuttons()
            }
            else if(boardStatus[0][0]==0){
                etDisplay.setText("O Wins")
                disablebuttons()
            }
        }
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[2][0]==1) {
                etDisplay.setText("X Wins")
                disablebuttons()
            }
            else if(boardStatus[2][0]==0){
                etDisplay.setText("O Wins")
                disablebuttons()
            }
        }

    }

    private fun disablebuttons() {
        for (i in board){
            for (button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, currplayer: Boolean) {

        val text=if (currplayer) "X" else "O"
        val nextPlayer=if (!currplayer) "X" else "O"
        boardStatus[row][col]=if (currplayer) 1 else 0

        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        player=!player

        etDisplay.setText("$nextPlayer's Turn")
    }
}