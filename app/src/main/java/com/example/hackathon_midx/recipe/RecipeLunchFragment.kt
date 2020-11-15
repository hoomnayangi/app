package com.example.hackathon_midx.recipe

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import com.example.hackathon_midx.*
import com.example.hackathon_midx.api_config.APIService
import com.example.hackathon_midx.api_config.APIUtils
import com.example.hackathon_midx.base_adapter.BaseItemAction
import com.example.hackathon_midx.base_adapter.OnItemActionListener
import com.example.hackathon_midx.base_view.BaseFragment
import com.example.hackathon_midx.stock_adapter.StockItemModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_recipe_child.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class RecipeLunchFragment : BaseFragment(), BaseFragmentInteraction {

    private var apiService: APIService? = null
    private var recipeAdapter: RecipeAdapter? = null
    private var recipeItemModel: List<RecipeItemModel> = arrayListOf()
    private var lat: Double = 0.0
    private var long: Double = 0.0
    private var position: Int = 0

    override fun getLayoutRes(): Int = R.layout.fragment_recipe_child

    override fun addControls() {
        retainInstance = true
        apiService = APIUtils().getServer()
        recipeAdapter =
            RecipeAdapter(object :
                OnItemActionListener<RecipeItemModel, BaseItemAction> {
                override fun onItemAction(
                    data: RecipeItemModel,
                    action: BaseItemAction,
                    position: Int
                ) {
                    startActivity(Intent(requireContext(), RecipeDetailActivity::class.java).apply {
                        putExtra(HomeFragment.DATA, data)
                    })
                }
            })
        rv_recipe.adapter = recipeAdapter

        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                getLongAndLat()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                (requireActivity() as? MainActivity)?.showSnackBar(
                    "Nếu bạn từ chối quyền, bạn không thể sử dụng dịch vụ này \n" +
                            " \n" +
                            "Vui lòng bật quyền tại [Setting]> [Permission]"
                )
            }
        }

        getLongAndLat()

        TedPermission.with(requireContext())
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("Nếu bạn từ chối quyền, bạn không thể sử dụng dịch vụ này \n \nVui lòng bật quyền tại [Setting]> [Permission]")
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
            .check()

        callAPIRecipeHighlight()
    }

    private fun getLongAndLat() {
        val lm =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        val location = lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        long = location?.longitude ?: 0.0
        lat = location?.latitude ?: 0.0

        val locationListener = LocationListener { location ->
            long = location.longitude;
            lat = location.latitude;
        }
        lm?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000L, 10f, locationListener)

    }

    private fun callAPIRecipeHighlight() {
        apiService?.getRecipeHighlightList(lat.toString(), long.toString())
            ?.enqueue(object : Callback<RecipeResponse> {
                override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                    Timber.d(t)
                }

                override fun onResponse(
                    call: Call<RecipeResponse>,
                    response: Response<RecipeResponse>
                ) {
                    response.body()?.listRecipes?.let {
//                        rvSkeletonRcvQuick?.hide()
                        recipeAdapter?.updateItems(it.filter { data ->
                            data.category == "2"
                        })
                    }

                }

            })
    }

    fun updateCategory(data: List<RecipeItemModel>) {
        var listTemp: List<RecipeItemModel> = listOf()
        when (position) {
            0 -> {
                listTemp = data.filter {
                    it.category == "1"
                }
                Log.d("AAAA", "listTemp0 $listTemp ")
            }
            1 -> {
                listTemp = data.filter {
                    it.category == "2"
                }
                Log.d("AAAA", "listTemp1 $listTemp ")
            }
            2 -> {
                listTemp = data.filter {
                    it.category == "3"
                }
                Log.d("AAAA", "listTemp1 $listTemp ")
            }
        }
        recipeAdapter?.updateItems(listTemp)
    }

    override fun addEvents() {
    }

    override fun updateFragmentData(position: Int) {
        this.position = position
        if (position == 0) {
//            val listTemp: List<RecipeItemModel> = recipeItemModel.filter {
//                it.category == "1"
//            }
//            recipeAdapter?.updateItems(listTemp)
//            Log.d("AAAA", "position $position ")
//            Log.d("AAAA", "listTemp $listTemp ")
            Log.d("AAAA", "recipeItemModel $recipeItemModel ")
        }

    }

}