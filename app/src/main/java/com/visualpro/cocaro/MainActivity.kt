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
    private var ko = IntArray(9)
    private var ko1 = IntArray(9)
    private var ko2 = IntArray(9)
    private var ko3 = IntArray(9)
    private var gameLogic = GameLogic()
    private var isNormalDrawble = true
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
                    gameLogic.count++;
                    if (binding.radioX.isChecked()) {
                        setDisplay("nguoi", p2, "x");
                    } else {
                        setDisplay("nguoi", p2, "o");
//                    }
                    }
                    gameLogic.opponentTurn(p2 / 3, p2 % 3);

                }
            }

        })

        binding.btnPrevious.setOnClickListener {
            if (!isNormalDrawble) {
                isNormalDrawble = true
                play()
                binding.imageview3.setImageResource(R.drawable.start1)
                binding.imageView.setImageResource(R.drawable.oo)
                binding.imageView1.setImageResource(R.drawable.ss)

            }

        }
        binding.btnNext.setOnClickListener {
            if (isNormalDrawble) {

                isNormalDrawble = false
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
        adapter = ImgRenderAdapter(this@MainActivity, R.layout.render_image, if(isNormalDrawble) xanhdo else monster)
        binding.gridview.adapter = adapter
    }

    fun init1() {
        ko[0] = R.drawable.q1
        ko[1] = R.drawable.q2
        ko[2] = R.drawable.q3
        ko[3] = R.drawable.q4
        ko[4] = R.drawable.q5
        ko[5] = R.drawable.q6
        ko[6] = R.drawable.q71
        ko[7] = R.drawable.q8
        ko[8] = R.drawable.q9

        ko1[0] = R.drawable.a1
        ko1[1] = R.drawable.a2
        ko1[2] = R.drawable.a3
        ko1[3] = R.drawable.a4
        ko1[4] = R.drawable.a5
        ko1[5] = R.drawable.a6
        ko1[6] = R.drawable.co1
        ko1[7] = R.drawable.a8
        ko1[8] = R.drawable.a9

        ko2[0] = R.drawable.d1
        ko2[1] = R.drawable.d2
        ko2[2] = R.drawable.d3
        ko2[3] = R.drawable.d4
        ko2[4] = R.drawable.d5
        ko2[5] = R.drawable.d6
        ko2[6] = R.drawable.d7
        ko2[7] = R.drawable.d8
        ko2[8] = R.drawable.d9

        ko3[0] = R.drawable.c1
        ko3[1] = R.drawable.c2
        ko3[2] = R.drawable.c3
        ko3[3] = R.drawable.c4
        ko3[4] = R.drawable.c5
        ko3[5] = R.drawable.c6
        ko3[6] = R.drawable.c7
        ko3[7] = R.drawable.c8
        ko3[8] = R.drawable.c9

    }

    fun setDisplay(who: String, pos: Int, type: String) {
        if (isNormalDrawble) {
            if (type.equals("x")) {
                if (who == "máy") {
                    adapter.list.set(pos, ko1[pos])
                } else {
                    adapter.list.set(pos, ko[pos])
                }
            } else {
                if (who == "máy") {
                    adapter.list.set(pos, ko[pos])
                } else {
                    adapter.list.set(pos, ko1[pos])
                }
            }

        } else {
            if (type.equals("x")) {
                if (who == "máy") {
                    adapter.list.set(pos, ko3[pos])
                } else {
                    adapter.list.set(pos, ko2[pos])
                }
            } else {
                if (who == "máy") {
                    adapter.list.set(pos, ko2[pos])
                } else {
                    adapter.list.set(pos, ko3[pos])
                }
            }
        }
        adapter.notifyDataSetChanged()
    }

    fun play() {
        gameLogic.startPlay()
        adapter.setList1(if(isNormalDrawble) xanhdo else monster)
        adapter.notifyDataSetChanged()
        if (binding.radioO.isChecked()) {
            gameLogic.machineTurn()
        }
    }

    fun Dialog(message: String?) {
        val dialog = android.app.AlertDialog.Builder(this@MainActivity)
        dialog.setTitle("Hey!")
        dialog.setOnCancelListener {
            play()
        }
        dialog.setMessage(message)
        dialog.setPositiveButton("OK", { dialog, which ->

            play()
//            binding.gridview.invalidateViews()
        })
        dialog.show()
    }

    inner class GameLogic {
        var count = 0

        var board = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        var numbered_Board = Array(3) { IntArray(3) }

        var length = numbered_Board.size
        var my: ArrayList<MovesToWin> = ArrayList()
        var opponent: ArrayList<MovesToWin> = ArrayList()

        var clone: ArrayList<MovesToWin> = ArrayList()

        var machineMoves: ArrayList<Coordinates> = ArrayList()
        var opponentMoves: ArrayList<Coordinates> = ArrayList()

        var r: ArrayList<ReferenceTo> = ArrayList()
        var r1: ArrayList<ReferenceTo> = ArrayList()

        fun calculate_Moves() {
            for (i in 0 until length - 2) {
                for (j in 0 until length - 2) {
                    my.add(MovesToWin(i, j, i + 1, j + 1, i + 2, j + 2))
                    numbered_Board[i][j] += 1
                    numbered_Board[i + 1][j + 1] += 1
                    numbered_Board[i + 2][j + 2] += 1
                    my.add(MovesToWin(i, j + 2, i + 1, j + 1, i + 2, j))
                    numbered_Board[i][j + 2] += 1
                    numbered_Board[i + 1][j + 1] += 1
                    numbered_Board[i + 2][j] += 1
                }
            }

            for (i in 0 until length) {
                for (j in 0 until length) {
                    if (j < length - 2) {
                        my.add(MovesToWin(i, j, i, j + 1, i, j + 2))
                        numbered_Board[i][j] += 1
                        numbered_Board[i][j + 1] += 1
                        numbered_Board[i][j + 2] += 1
                    }
                    if (i < length - 2) {
                        my.add(MovesToWin(i, j, i + 1, j, i + 2, j))
                        numbered_Board[i][j] += 1
                        numbered_Board[i + 1][j] += 1
                        numbered_Board[i + 2][j] += 1
                    }
                }
                clone = my.clone() as ArrayList<MovesToWin>
            }
        }

        fun findMove(callBack: (x: Int, y: Int) -> Unit) {
            var max = 0
            var temp = 0
            var x = 0;
            var y = 0
            for (i in 0 until length) {
                for (j in 0 until length) {
                    if (board[i][j] == 0) {
                        temp = numbered_Board[i][j]
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

        fun detectReach2Move(
            c: ArrayList<Coordinates>,
            m: ArrayList<MovesToWin>,
            r: ArrayList<ReferenceTo>,
            callBack: (x: Int, y: Int, reach2point: Boolean) -> Unit
        ) {
            if (c.isEmpty() == false) {
                remappingXY(c, m, r)
                for (i in 0 until r.size) {
                    for (j in r.size - 1 downTo i + 1) {
                        if (r[i].adress == r[j].adress) {
                            val c1: String = r[i].xy
                            val c2: String = r[j].xy
                            for (ix in m.size - 1 downTo 0) {
                                if (c1 == m[ix].getAB() || c2 == m[ix].getAB()) {
                                    if (c1 == m[ix].getCD() || c2 == m[ix].getCD()) {
                                        callBack(m[ix].e, m[ix].f, true)
                                        return

                                    }
                                }
                                if (c1 == m[ix].getAB() || c2 == m[ix].getAB()) {
                                    if (c1 == m[ix].getEF() || c2 == m[ix].getEF()) {
                                        callBack(m[ix].c, m[ix].d, true)
                                        return
                                    }
                                }
                                if (c1 == m[ix].getCD() || c2 == m[ix].getCD()) {
                                    if (c1 == m[ix].getEF() || c2 == m[ix].getEF()) {
                                        callBack(m[ix].a, m[ix].b, true)
                                        return
                                    }
                                }
                            }
                        }
                    }
                }
                callBack(0, 0, false)
            } else {
                callBack(0, 0, false)
            }

        }

        fun mappingXY(xy: String, m: ArrayList<MovesToWin>, r: ArrayList<ReferenceTo>) {
            if (m === my) {
                remove(xy, opponent)
            } else {
                remove(xy, my)
            }
            for (i in m.size - 1 downTo 0) {
                if (xy == m[i].getAB() || xy == m[i].getCD() || xy == m[i].getEF()) {
                    r.add(ReferenceTo(xy, i))
                }
            }
        }

        fun remappingXY(
            px: ArrayList<Coordinates>, m: ArrayList<MovesToWin>, r: ArrayList<ReferenceTo>
        ) {
            r.clear()
            for (c in px) mappingXY(c.x.toString() + "" + c.y, m, r)
        }

        fun remove(xy: String, a: ArrayList<MovesToWin>) {
            var i = 0
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
                detectReach2Move(machineMoves, my, r1) { x, y, isReach2Move ->
                    if (isReach2Move) {
                        countinue = false
                        count = -1
                        setDisplay(x, y)
                        gameLogic.checkIsWin {}
                    }

                }
                if (countinue) {
                    detectReach2Move(opponentMoves, opponent, r) { x, y, isReach2Move ->
                        if (board[x][y] == 0 && isReach2Move) {
                            count++
                            board[x][y] = 1
                            machineMoves.add(Coordinates(x, y))
                            mappingXY(x.toString() + "" + y, my, r1)
                            setDisplay(x, y)
                        } else {
                            count++
                            findMove { x1, y1 ->
                                board[x1][y1] = 1
                                machineMoves.add(Coordinates(x1, y1))
                                mappingXY(x1.toString() + "" + y1, my, r1)
                                setDisplay(x1, y1)
                            }

                        }
                    }

                }
            }, 200)
        }

        fun setDisplay(x: Int, y: Int) {
            setDisplay("máy", x * 3 + y, if (binding.radioX.isChecked()) "o" else "x")
        }

        fun checkIsWin(callBack: (state: Int) -> Unit) {
            if (count == -1) {
                Dialog("Bạn thua rồi")
                return
            }
            if (count == -2) {
                Dialog("Bạn win rồi")
                return
            }
            if (count == 9) {
                Dialog("Hòa!")
                return
            }
            callBack(0)
        }

        fun opponentTurn(x1: Int, y1: Int) {
            remove(x1.toString() + "" + y1, my)
            mappingXY(x1.toString() + "" + y1, opponent, r)
            board[x1][y1] = -1
            opponentMoves.add(Coordinates(x1, y1))
            checkIsWin {
                if (it == 0) {
                    machineTurn()
                }
            }

        }

        fun startPlay() {
            calculate_Moves()
            count = 0
            r.clear()
            r1.clear()
            machineMoves.clear()
            opponentMoves.clear()
            my.clear()
            my.addAll(clone)
            opponent.clear()
            opponent.addAll(clone)
            for (i in 0 until length) {
                for (j in 0 until length) {
                    board[i][j] = 0
                }
            }
        }

    }
}