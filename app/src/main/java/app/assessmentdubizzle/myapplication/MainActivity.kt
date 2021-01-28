package app.assessmentdubizzle.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import app.assessmentdubizzle.myapplication.utils.MDToast
import app.assessmentdubizzle.myapplication.utils.Utils
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min


fun Context.resIdByName(resIdName: String?, resType: String): Int {
    resIdName?.let {
        return resources.getIdentifier(it, resType, packageName)
    }
    throw Resources.NotFoundException()
}

class MainActivity : AppCompatActivity() {
    private lateinit var mCustomFont: Typeface
    private lateinit var mdToast: MDToast
    private var minValue: Float?  = 0f
    private var maxValue: Float?  = 1000f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sb_vertical_2?.setIndicatorTextDecimalFormat("0")
        sb_vertical_2?.setIndicatorTextStringFormat("%s AED")
        minValue?.let { maxValue?.let { it1 -> sb_vertical_2?.setProgress(it, it1) } }

        /*val progressDefaultDrawableResId = this@MainActivity.resIdByName("progress_defalut", "drawable")
        val progressDrawableResId = this@MainActivity.resIdByName("progress", "drawable")*/

        mCustomFont = ResourcesCompat.getFont(this@MainActivity, R.font.sfprotext_medium)!!
        sb_vertical_2?.leftSeekBar?.setTypeface(mCustomFont)
        sb_vertical_2?.rightSeekBar?.setTypeface(mCustomFont)


        /*sb_vertical_2?.setTypeface(mCustomFont)*/

        /* sb_vertical_2?.progressDefaultDrawableId = progressDefaultDrawableResId
         sb_vertical_2?.progressDrawableId = progressDrawableResId*/

        sb_vertical_2?.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(rangeSeekBar: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                /*changeSeekBarThumb(rangeSeekBar.leftSeekBar, leftValue)
                changeSeekBarThumb(rangeSeekBar.rightSeekBar, rightValue)*/
                Log.i("Left Value", ": $leftValue")
                Log.i("Right Value", ": $rightValue")
                minValue = leftValue
                maxValue = rightValue

            }

            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }

        })

        btnApply.setOnClickListener {
            mdToast =
                    MDToast.makeText(this@MainActivity, "Minimum Cost :${Utils.getInRoundedDouble(minValue)} Maximum Cost :${Utils.getInRoundedDouble(maxValue)}", MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS)
            mdToast.show()
        }
    }
}