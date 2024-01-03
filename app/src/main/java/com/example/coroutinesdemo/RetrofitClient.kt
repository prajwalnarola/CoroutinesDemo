// ApiUtilities.kt
import com.example.coroutinesdemo.ApiCallInterface
import com.example.coroutinesdemo.WebConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WebConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInterface: ApiCallInterface = retrofit.create(ApiCallInterface::class.java)

}
