package com.example.androidwithflutter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidwithflutter.ui.login.LoginActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    private val flutterEngine by lazy { initFlutterEngine("init") };


    //定义一个标记字符串来表示其中的FlutterFragment 活动的FragmentManager。这个值可以是你想要的任何值。
    private val tagFlutterFragment = "flutter_fragment"
    private var flutterFragment: FlutterFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val methodChannel = MethodChannel(flutterEngine.dartExecutor, "flutterWithAndroid")
        findViewById<View>(R.id.start).setOnClickListener {
            startFlutterActivity()
//            startFlutterFragment()
        }

        //监听flutter调用 android
        methodChannel.setMethodCallHandler { call, result ->
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
//        initFlutterEngine("init_one")
//        initFlutterEngine("init_two")
//        initFlutterEngine("init_three")
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
        flutterEngine.destroy()
    }

}