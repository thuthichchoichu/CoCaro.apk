package com.visualpro.cocaro

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.visualpro.cocaro.databinding.ActivityMainBinding
import com.visualpro.cocaro.model.Coordinates
import com.visualpro.cocaro.model.MovesToWin
import com.visualpro.cocaro.model.ReferenceTo
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImgRenderAdapter
    private var xanhdo: ArrayList<Int> = ArrayList()
    private var monster: ArrayList<Int> = ArrayList()
    private var png = IntArray(9)
    private var png1 = IntArray(9)
    private var png2 = IntArray(9)
    private var png3 = IntArray(9)
    private var gameLogic = GameLogic()
    private var isXanhDoSelected = true

    val MACHINE = 0
    val USER = 1
    val X = 2
    val O = 3
    val STATE_NO_WIN = 9
    val STATE_USER_LOSS = -1
    val STATE_USER_WIN = -2
    val STATE_CONTINUE = -3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialized()

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            play()
        }
        binding.gridview.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (gameLogic.board[p2 / 3][p2 % 3] == 0) {

                    if (binding.radioX.isChecked()) {
                        setDisplay(USER, p2, O);
                    } else {
                        setDisplay(USER, p2, X);
                    }
                    gameLogic.opponentTurn(p2 / 3, p2 % 3);

                }
            }

        })

        binding.btnPrevious.setOnClickListener {
            if (!isXanhDoSelected) {
                isXanhDoSelected = true
                play()
                binding.imageview3.setImageResource(R.drawable.start1)
                binding.imageView.setImageResource(R.drawable.oo)
                binding.imageView1.setImageResource(R.drawable.ss)

            }

        }
        binding.btnNext.setOnClickListener {
            if (isXanhDoSelected) {

                isXanhDoSelected = false
                play()
                binding.imageView.setImageResource(R.drawable.okk)
                binding.imageView1.setImageResource(R.drawable.xkk)
                binding.imageview3.setImageResource(R.drawable.monster)
            }
        }

        gameLogic.startPlay()
    }
    private fun initialized() {
        xanhdo.add(R.drawable.a)
        xanhdo.add(R.drawable.b)
        xanhdo.add(R.drawable.c)
        xanhdo.add(R.drawable.d)
        xanhdo.add(R.drawable.e)
        xanhdo.add(R.drawable.f)
        xanhdo.add(R.drawable.gg1)
        xanhdo.add(R.drawable.h)
        xanhdo.add(R.drawable.i)

        monster.add(R.drawable.e1)
        monster.add(R.drawable.e2)
        monster.add(R.drawable.e3)
        monster.add(R.drawable.e4)
        monster.add(R.drawable.e5)
        monster.add(R.drawable.e6)
        monster.add(R.drawable.e7)
        monster.add(R.drawable.e8)
        monster.add(R.drawable.e9)
        init1()
        adapter = ImgRenderAdapter(
            this@MainActivity, R.layout.render_image, if (isXanhDoSelected) xanhdo else monster
        )
        binding.gridview.adapter = adapter
    }
    fun init1() {
        png[0] = R.drawable.q1
        png[1] = R.drawable.q2
        png[2] = R.drawable.q3
        png[3] = R.drawable.q4
        png[4] = R.drawable.q5
        png[5] = R.drawable.q6
        png[6] = R.drawable.q71
        png[7] = R.drawable.q8
        png[8] = R.drawable.q9

        png1[0] = R.drawable.a1
        png1[1] = R.drawable.a2
        png1[2] = R.drawable.a3
        png1[3] = R.drawable.a4
        png1[4] = R.drawable.a5
        png1[5] = R.drawable.a6
        png1[6] = R.drawable.co1
        png1[7] = R.drawable.a8
        png1[8] = R.drawable.a9

        png2[0] = R.drawable.d1
        png2[1] = R.drawable.d2
        png2[2] = R.drawable.d3
        png2[3] = R.drawable.d4
        png2[4] = R.drawable.d5
        png2[5] = R.drawable.d6
        png2[6] = R.drawable.d7
        png2[7] = R.drawable.d8
        png2[8] = R.drawable.d9

        png3[0] = R.drawable.c1
        png3[1] = R.drawable.c2
        png3[2] = R.drawable.c3
        png3[3] = R.drawable.c4
        png3[4] = R.drawable.c5
        png3[5] = R.drawable.c6
        png3[6] = R.drawable.c7
        png3[7] = R.drawable.c8
        png3[8] = R.drawable.c9

    }
    fun setDisplay(who: Int, pos: Int, type: Int) {
        if (isXanhDoSelected) {
            if (type == X) {
                if (who == MACHINE) {
                    adapter.list.set(pos, png1[pos])
                } else {
                    adapter.list.set(pos, png[pos])
                }
            } else {
                if (who == MACHINE) {
                    adapter.list.set(pos, png[pos])
                } else {
                    adapter.list.set(pos, png1[pos])
                }
            }

        } else {
            if (type == X) {
                if (who == MACHINE) {
                    adapter.list.set(pos, png3[pos])
                } else {
                    adapter.list.set(pos, png2[pos])
                }
            } else {
                if (who == MACHINE) {
                    adapter.list.set(pos, png2[pos])
                } else {
                    adapter.list.set(pos, png3[pos])
                }
            }
        }
        adapter.notifyDataSetChanged()
    }
    fun play() {
        gameLogic.startPlay()
        adapter.setList1(if (isXanhDoSelected) xanhdo else monster)
        adapter.notifyDataSetChanged()
        if (binding.radioO.isChecked()) {
            gameLogic.machineTurn()
        }
    }
    fun showDilogWithTitle(message: String?) {
        val dialog = android.app.AlertDialog.Builder(this@MainActivity)
        dialog.setTitle("Hey!")
        dialog.setOnCancelListener {
            play()
        }
        dialog.setMessage(message)
        dialog.setPositiveButton("OK", { dialog, which ->
            play()
        })
        dialog.show()
    }

    inner class GameLogic {
        var gameState = 0
        var board = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        var calculated_MaxPoint = Array(3) { IntArray(3) }
        var length = calculated_MaxPoint.size

        var allMoveOfMachineCanGetWin: ArrayList<MovesToWin> = ArrayList()
        var allMoveOfUserCanGetWin: ArrayList<MovesToWin> = ArrayList()

        var clone: ArrayList<MovesToWin> = ArrayList()

        var machineMoves: ArrayList<Coordinates> = ArrayList()
        var userMoves: ArrayList<Coordinates> = ArrayList()

        var r: ArrayList<ReferenceTo> = ArrayList()
        var r1: ArrayList<ReferenceTo> = ArrayList()

        fun calculate_Moves() {
            for (i in 0 until length - 2) {
                for (j in 0 until length - 2) {
                    clone.add(MovesToWin(i, j, i + 1, j + 1, i + 2, j + 2))
                    calculated_MaxPoint[i][j] += 1
                    calculated_MaxPoint[i + 1][j + 1] += 1
                    calculated_MaxPoint[i + 2][j + 2] += 1
                    clone.add(MovesToWin(i, j + 2, i + 1, j + 1, i + 2, j))
                    calculated_MaxPoint[i][j + 2] += 1
                    calculated_MaxPoint[i + 1][j + 1] += 1
                    calculated_MaxPoint[i + 2][j] += 1
                }
            }
            // tính nước win trên row và columns
            for (i in 0 until length) {
                for (j in 0 until length) {
                    if (j < length - 2) {
                        clone.add(MovesToWin(i, j, i, j + 1, i, j + 2))
                        calculated_MaxPoint[i][j] += 1
                        calculated_MaxPoint[i][j + 1] += 1
                        calculated_MaxPoint[i][j + 2] += 1
                    }
                    if (i < length - 2) {
                        clone.add(MovesToWin(i, j, i + 1, j, i + 2, j))
                        calculated_MaxPoint[i][j] += 1
                        calculated_MaxPoint[i + 1][j] += 1
                        calculated_MaxPoint[i + 2][j] += 1
                    }
                }
            }
        }

        fun findMove(callBack: (x: Int, y: Int) -> Unit) {
            var max = 0
            var temp: Int
            var x = 0
            var y = 0
            for (i in 0 until length) {
                for (j in 0 until length) {
                    if (board[i][j] == 0) {
                        temp = calculated_MaxPoint[i][j]
                        if (max < temp) {
                            max = temp
                            x = i
                            y = j
                        }
                    }
                }
            }
            callBack(x, y)
        }

        fun checkIsReach2Move(
            coordinatesList: ArrayList<Coordinates>,
            m: ArrayList<MovesToWin>,
            r: ArrayList<ReferenceTo>,
            callBack: (x: Int, y: Int, reach2point: Boolean) -> Unit
        ) {
            if (coordinatesList.size > 0) {
                remappingXY(coordinatesList, m, r)
                for (i in 0 until r.size) {
                    for (j in r.size - 1 downTo i + 1) {
                        if (r[i].referenceToWinMoveAtPosition == r[j].referenceToWinMoveAtPosition) {

                            val c1: String = r[i].xy
                            val c2: String = r[j].xy
                            for (k in m.size - 1 downTo 0) {
                                if (c1.equals(m[k].getAB()) || c2.equals(m[k].getAB())) {
                                    if (c1.equals(m[k].getCD()) || c2.equals(m[k].getCD())) {
                                        callBack(m[k].e, m[k].f, true)
                                        return

                                    }
                                }
                                if (c1.equals(m[k].getAB()) || c2.equals(m[k].getAB())) {
                                    if (c1.equals(m[k].getEF()) || c2.equals(m[k].getEF())) {
                                        callBack(m[k].c, m[k].d, true)
                                        return
                                    }
                                }
                                if (c1.equals(m[k].getCD()) || c2.equals(m[k].getCD())) {
                                    if (c1.equals(m[k].getEF()) || c2.equals(m[k].getEF())) {
                                        callBack(m[k].a, m[k].b, true)
                                        return
                                    }
                                }
                            }
                        }
                    }
                }
            }
            callBack(0, 0, false)


        }


        fun mappingXY(xy: String, m: ArrayList<MovesToWin>, r: ArrayList<ReferenceTo>) {
            for (i in m.size - 1 downTo 0) {
                if (xy.equals(m[i].getAB()) || xy.equals(m[i].getCD()) || xy.equals(m[i].getEF())) {
                    r.add(ReferenceTo(xy, i))
                }
            }
        }

        fun remappingXY(
            c: ArrayList<Coordinates>, m: ArrayList<MovesToWin>, r: ArrayList<ReferenceTo>
        ) {
            r.clear()
            for (c in c) mappingXY("${c.x}${c.y}", m, r)
        }

        fun remove(xy: String, a: ArrayList<MovesToWin>) {
            val listRemove = ArrayList<MovesToWin>()
            for (i in 0 until a.size) if (xy.equals(a[i].getAB()) || xy.equals(a[i].getCD()) || xy.equals(
                    a[i].getEF()
                )
            ) {
                listRemove.add(a[i])
            }
            a.removeAll(listRemove)

        }

        fun machineTurn() {
            Handler(Looper.myLooper()!!).postDelayed({
                var countinue = true
                checkIsReach2Move(
                    machineMoves,
                    allMoveOfMachineCanGetWin,
                    r1
                ) { x, y, isReach2Move ->
                    if (isReach2Move) {
                        countinue = false
                        gameState = STATE_USER_LOSS
                        setDisplay(x, y)
                        checkWin {}
                    }

                }
                if (countinue) {
                    checkIsReach2Move(userMoves, allMoveOfUserCanGetWin, r) { x, y, isReach2Move ->
                        if (board[x][y] == 0 && isReach2Move) {
                            remove("$x$y", allMoveOfUserCanGetWin)
                            gameState++
                            board[x][y] = 1
                            machineMoves.add(Coordinates(x, y))
                            mappingXY(x.toString() + "" + y, allMoveOfMachineCanGetWin, r1)
                            setDisplay(x, y)
                        } else {
                            gameState++
                            findMove { x1, y1 ->
                                remove("$x1$y1", allMoveOfUserCanGetWin)
                                board[x1][y1] = 1
                                machineMoves.add(Coordinates(x1, y1))
                                mappingXY("$x1$y1", allMoveOfMachineCanGetWin, r1)
                                setDisplay(x1, y1)
                            }

                        }
                    }

                    checkWin {}

                }
            }, 200)
        }

        fun setDisplay(x: Int, y: Int) {
            setDisplay(MACHINE, x * 3 + y, if (binding.radioX.isChecked()) O else X)
        }

        fun checkWin(callBack: (state: Int) -> Unit) {
            if (gameState == STATE_USER_LOSS) {
                showDilogWithTitle("Bạn thua rồi")
                return
            }
            if (gameState == STATE_USER_WIN) {
                showDilogWithTitle("Bạn win rồi")
                return
            }
            if (gameState == STATE_NO_WIN) {
                showDilogWithTitle("Hòa!")
                return
            }
            callBack(STATE_CONTINUE)
        }

        fun opponentTurn(x1: Int, y1: Int) {
            gameState++;
            checkIsReach2Move(userMoves, allMoveOfUserCanGetWin, r) { x, y, reach2Move ->
                if (x1 == x && y1 == y && reach2Move) {
                    gameState = STATE_USER_WIN
                } else {
                    remove("$x1$y1", allMoveOfMachineCanGetWin)
                    mappingXY("$x1$y1", allMoveOfUserCanGetWin, r)
                    board[x1][y1] = -1
                    userMoves.add(Coordinates(x1, y1))
                }
            }

            checkWin {
                if (it == STATE_CONTINUE) {
                    machineTurn()
                }
            }

        }

        fun startPlay() {
            calculate_Moves()
            gameState = 0

            r.clear()
            r1.clear()

            machineMoves.clear()
            userMoves.clear()

            allMoveOfMachineCanGetWin.clear()
            allMoveOfMachineCanGetWin.addAll(clone)
            allMoveOfUserCanGetWin.clear()
            allMoveOfUserCanGetWin.addAll(clone)

            for (i in 0 until length) {
                for (j in 0 until length) {
                    board[i][j] = 0
                }
            }
        }

    }
}