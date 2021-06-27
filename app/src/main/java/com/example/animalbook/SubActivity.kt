package com.example.animalbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animalbook.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding    //lateinit プロパティを参照するまでに必ず値が代入されることを開発者の責任で保証
    private lateinit var title: TitleFragment
    override fun onCreate(savedInstanceState: Bundle?) {   // onCreateでActivityが初めて生成され、Activityの初期化は全てここに書く。つまり全て初期化される
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lionButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .apply {    //FragmentManagerのクラスのインスタンスを取得 apply: インスタンスに対して、連続して処理をするような場合、スコープ関数
                    replace(
                        R.id.container,
                        LionFragment()
                    )  //replace フラグメントの入れ替え(ビューグループのlayout上に表示する)を行います
                    addToBackStack(null)  //【戻る】ボタンでひとつ前の状態に戻れるように、バックスタックにフラグメントの処理を保存しておく, この引数にはバックスタックの名前が入る
                    commit()  //フラグメントへの操作を確定(トランザクションを確定)
                }
        }
        binding.hippoButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, HippoFragment())
                addToBackStack(null)
                commit()
            }
        }
        binding.giraffeButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, GiraffeFragment())
                addToBackStack(null)
                commit()
            }
        }
        title =
            TitleFragment()   //9行目のlateinitで用意しといたプロパティにここで代入して、伏線回収  TitleFragment()とは、fragment_title.ktで定義したクラス
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.titleFrame, title)   // idがtitleFrameというlayoutに、id=titleのフラグメントを表示する
            commit()  //確定
        }
    }
    override fun onResume() {  // onResume: Activityが表示された時  例： スマホなどでゲーム中に電話などで中断されても、リスタートできるようにするためのメソッド
        super.onResume()
        title.setTitle("サブ画面")  // titleフラグメントに文字列を表示する
    }
}