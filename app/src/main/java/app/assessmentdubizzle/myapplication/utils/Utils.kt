package app.assessmentdubizzle.myapplication.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        fun getInRoundedDouble(value: Float?): String? {
            val symbolsUK = DecimalFormatSymbols.getInstance(Locale.UK)
            val decimalFormat = DecimalFormat("0.00", symbolsUK)
            return decimalFormat.format(value)
        }
    }
}