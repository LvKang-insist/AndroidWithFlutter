package com.example.androidwithflutter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.androidwithflutter.ui.login.LoginActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {
    //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
    private val flutterEngine by lazy { initFlutterEngine("init") };


    var list = arrayOf<String>(
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F71%2F49553%2F6b10daec9e6a8e1e_1024x1024.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=efb9f59224d70d3d0f184f2629ce2c55",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F76%2F52808%2Fb7fc7f059717ce45_1440x900.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=51ba240618dbdae5f97697cd9b0dda8a",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic156.nipic.com%2Ffile%2F20180226%2F4665280_111106114735_2.jpg&refer=http%3A%2F%2Fpic156.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=101ae7b780b933b8608df968f87dee10",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F72%2F49864%2F97c2dff601ef5b92_1280x1024.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=65e321ad85d30090bb8172360022f442",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F71%2F49574%2F298c27a63b27190c_2560x1440.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=faad29b8fe079407a8791e15814a457a",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48490%2F54020dd89976eacc_1440x900.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=df0d522516356a7e8781a3d2a6a72886",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48681%2F365b0062ef7c61bf_1680x1050.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=41a0b73e030c7dd14c5f08e25a7a152b",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F173%2F121048%2F69d87c13a75b07fd_480x640.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=7b500c8602865f5526013796b236ce37",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48789%2F5674af387cdfbc67_1366x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=a10fc9c45c54f2d3b487be147b76337b",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F44%2F30274%2Fe6593669ce8bed25_1024x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=42522f316921bfca031ec56a93e9dfd5",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F73%2F50415%2Ff3ff7e04f46ee910_1680x1050.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=135598080e55ce8488a87698488ea596",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F73%2F51014%2F45d8a2d57b46bdce_1024x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=1f5db6aa4e3ccc373ccb310964d5b32e",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F24%2F16102%2Fade59ed7423e6ef9_1366x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=8ff79222fb1f70ccba7b8955b7681657",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F122%2F85327%2Fed3780ca576b337d_1366x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=c04e335baa730a83f5d0650c753e6287",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F75%2F52410%2F01a8464eba14f33c_1024x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020124&t=db0328e94278ef097da7925c45ac0767",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F72%2F49821%2Fcf747eec35da3db3_1440x900.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=775da6b1e5bd4b33cf577e1819b10d30",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48580%2F3f73ec669416816a_1680x1050.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=403ed233e2949d8e9c8fd514fe29b210",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48623%2F1bb824ff90bfc9aa_1680x1050.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=a9d942939569f752a83a387af90f18bf",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48588%2F9296e5bded9870f3_1680x1050.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=31ff8040830340ab6b06c661654de9e5",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F122%2F84831%2F800859b5381bd557_1366x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=917789633af42cd4a6a9d3e09f2a0a43",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F71%2F49572%2F51d577f9b9b67488_768x1024.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=27ce884efc3f143080ea969e9fb116f7",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F190%2F132406%2F6ff395d4e604b06a_2560x1440.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=2eb276214a9cce888d2ebae89bc8595d",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48704%2Fa719a1820d057db2_1366x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=fe105f42c6be4ef3333e2bd05a17539e",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F42%2F28749%2F517fe53894bab4f1_1280x1024.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=0cfcad4c4c9613d53b715dc96e85b0c5",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F71%2F49688%2Fead31d996cb44042_1680x1050.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=a2cd7d0bca35423097c3db0e3d607b8c",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F101%2F70623%2F7e959ddf8dca2efe_1440x900.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=148331f78876dc87442f7ac83ef604c7",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F122%2F84754%2F043ce8047b83af56_1024x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=2e3d9fe096ea2eed692fb2755811b79a",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48549%2F0943a8c6eadaa18a_1440x900.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=82aede51a7f2ad06744e48abba4f0a39",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F74%2F51158%2F74f932216099730a_1024x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=edd1cd9595addab0a8e0b4ab332919c9",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F74%2F51605%2F1841c9ad8a0abc24_1024x768.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=0f1690c62d5d61b31ccfd6e092fcfa12",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F70%2F48880%2F62345c5dabe7044d_1440x900.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645020359&t=567f9a2fce597b87c4cce05d65c4fc26",
    )

    //定义一个标记字符串来表示其中的FlutterFragment 活动的FragmentManager。这个值可以是你想要的任何值。
    private val tagFlutterFragment = "flutter_fragment"
    private var flutterFragment: FlutterFragment? = null

    private val layout: FrameLayout by lazy { findViewById(R.id.layout) }
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val methodChannel = MethodChannel(flutterEngine.dartExecutor, "flutterWithAndroid")
        findViewById<View>(R.id.start).setOnClickListener {
            startFlutterActivity()
//            startFlutterFragment()
        }

//        缓存多个引擎，测试占用内存
//        initFlutterEngine("init_one")
//        initFlutterEngine("init_two")
//        initFlutterEngine("init_three")
    }


    //测试图片占用内存
    private fun testImage() {
        if (pos < list.size) {
            val image: AppCompatImageView =
                LayoutInflater.from(this)
                    .inflate(R.layout.layout_image, layout, false) as AppCompatImageView
            val params = ViewGroup.LayoutParams(800 - (pos + 20), 1200 - (pos + 40))
            image.layoutParams = params
            Glide.with(image).load(list[pos]).into(image)
            layout.addView(image)
            pos++
        }
    }

    ////监听flutter调用 android
    private fun flutterListener(channel: MethodChannel) {
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                "AndroidMethod" -> {
                    result.success(mapOf("Android 返回值" to "\"我是Android\""))
                }
                "jumpToNative" -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                else -> {
                    result.success("我是Android,没招到对应方法")
                }
            }
        }
    }

    private fun invoke(channel: MethodChannel) {
        //调用 Flutter 方法
        channel.invokeMethod(
            "flutterMethod",
            "调用 Flutter 参数",
            object : MethodChannel.Result {
                override fun success(result: Any?) {
                    Log.e("---345--->", "$result");
                }

                override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                    Log.e("---345--->", "调用Flutter失败");
                }

                override fun notImplemented() {

                }
            })
    }

    /** 打开 FlutterActivity */
    private fun startFlutterActivity() {
        startActivity(
            FlutterActivity.withCachedEngine("init")
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(this)
        )
    }

    /** 打开 FlutterFragment */
    fun startFlutterFragment() {
        flutterFragment =
            supportFragmentManager.findFragmentByTag(tagFlutterFragment) as FlutterFragment?

        if (flutterFragment == null) flutterFragment =
            FlutterFragment.withCachedEngine("init")
                .shouldAttachEngineToActivity(false)
                .build()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layout, flutterFragment!!, tagFlutterFragment)
            .commit()
    }

    private fun initFlutterEngine(id: String): FlutterEngine {
        //创建 Flutter 引擎
        val flutterEngine = FlutterEngine(this)
        //指定要跳转的flutter页面
        flutterEngine.navigationChannel.setInitialRoute("main")
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        //这里做一个缓存，可以在适当的时候执行它，例如app里，在跳转前执行预加载
        val flutterEngineCache = FlutterEngineCache.getInstance();
        flutterEngineCache.put(id, flutterEngine)
        //上面代码一般在跳转之前调用，这样可以使得跳转树的加快
        return flutterEngine
    }

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment?.onNewIntent(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        flutterFragment?.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        flutterFragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        flutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment?.onTrimMemory(level)
    }

    override fun onDestroy() {
        super.onDestroy()
//        flutterEngine.destroy()
    }

}