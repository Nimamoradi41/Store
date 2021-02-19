package com.example.store



import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.store.Dialog.Dial_App
import com.example.store.Main_Fragments.ErrorCode500
import com.example.store.Main_Fragments.Login
import com.example.store.Models.ResponseAddress
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.fragment_frag__phone__number.view.*
import kotlinx.android.synthetic.main.layout_modal_address.*
import org.neshan.core.LngLat
import org.neshan.core.Range
import org.neshan.core.Variant
import org.neshan.layers.Layer
import org.neshan.layers.VectorElementLayer
import org.neshan.services.NeshanMapStyle
import org.neshan.services.NeshanServices
import org.neshan.styles.AnimationStyle
import org.neshan.styles.AnimationStyleBuilder
import org.neshan.styles.AnimationType
import org.neshan.styles.MarkerStyleCreator
import org.neshan.ui.ClickData
import org.neshan.ui.ClickType
import org.neshan.ui.MapEventListener
import org.neshan.utils.BitmapUtils
import org.neshan.vectorelements.Marker
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class MapActivity : BaseActiity() {
    var markerLayer: VectorElementLayer? = null
    var animSt: AnimationStyle? = null
    var MarkId = -1
    var Model: ModelAddress? = null
    var latitude = ""
    var longitude = ""
    var Pos: Int? = null
    var nManager: LocationManager? = null
    var flag = false
    var Bto: BottomSheetBehavior<View>? = null
    var Id_3 = "00000000-0000-0000-0000-000000000000"
    override fun onBackPressed() {
        if (Bto?.state == BottomSheetBehavior.STATE_EXPANDED) {
            Bto?.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }


    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    var marker: Marker? = null

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
//        window.statusBarColor = Color.parseColor("#ec4646")
        markerLayer = NeshanServices.createVectorElementLayer()
        map.layers.add(markerLayer)

        Bto = BottomSheetBehavior.from(bto)


        if (intent.getStringExtra("Type").equals("B")) {

            var data = intent.getSerializableExtra("DATA_2") as ModelAddress
            Log.i("cdvadv", data.plaque.toString())
            Pos = intent.getIntExtra("Pos", -8)
//            Model?.plaque=data.plaque
//            Model?.unit=data.unit
//            Model?.floor=data.floor
//            Model?.home=data.home
//            Model?.fullLocation=data.fullLocation
//            Model?.fullName=data.fullName
//            Model?.fullLocation=data.fullLocation
//            Model?.tel=data.tel
//            Model?.id=data.id
//            Model?.longitude=data.longitude
//            Model?.latitude=data.latitude
//            Model?.location=data.location
//            Model?.name=data.name
            Id_3 = data.id.toString()
            longitude = data.longitude.toString()
            latitude = data.latitude.toString()
            edt_name_address.setText(data.name)
            edt_pluq.setText(data.plaque)
            edt_unit.setText(data.unit)
            edt_floor.setText(data.floor)
            edt_building.setText(data.home)
            edt_address.setText(data.location)
            edt_phonenumber.setText(data.tel)
            edt_fullname.setText(data.fullName)
            var Loc = LngLat(latitude.toDouble(), longitude.toDouble())
            AddMarker(Loc, MarkId.toLong())
            Bto?.state = BottomSheetBehavior.STATE_EXPANDED
            map.setFocalPointPosition(LngLat(latitude.toDouble(), longitude.toDouble()), 0f)
        }else{
            map.setFocalPointPosition(LngLat(48.692709, 31.331426), 0f)
        }

        btn_location.setOnClickListener {
            nManager = getSystemService(LOCATION_SERVICE) as LocationManager
            if (!nManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)!!) {
                Snackbar.make(holder_44, "موقعیت یاب خود را روشن کنید", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                if (!btn_location.isEnabled)
                {
                    return@setOnClickListener
                }
                getLastLocation()
            }
        }

        card_anniti.setOnClickListener {

        }
        Model = ModelAddress()
        button8.setOnClickListener {

            if (!isNetConnected()) {
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {

                        }
                    },
                    this@MapActivity
                )
                p.show()
                return@setOnClickListener
            }

            if (latitude.isNullOrEmpty() || latitude.isNullOrEmpty()) {
                Snackbar.make(holder_44, "آدرس خود را بر روی نقشه وارد کنید", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (edt_name_address.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_name_address.setError("نام آدرس را وارد کنید")
                return@setOnClickListener
//                edt_name_address
            }

            if (edt_fullname.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_fullname.setError("نام تحویل گیرنده را وارد کنید")
                return@setOnClickListener
//                edt_name_address
            }



            if (edt_pluq.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_pluq.setError("پلاک خود را وارد کنید")
                return@setOnClickListener
//                edt_name_address
            }

            if (edt_address.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_address.setError("  آدرس خود را وارد کنید")

                return@setOnClickListener
//                edt_name_address
            }


            if (edt_floor.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_floor.setError("طبقه خود را وارد کنید")
                return@setOnClickListener
//                edt_name_address
            }


            if (edt_unit.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_unit.setError("واحد خود را وارد کنید")
                return@setOnClickListener
//                edt_name_address
            }



            if (edt_phonenumber.text?.trim().isNullOrEmpty()) {
//                Toast.makeText(this,"نام آدرس را وارد کنید")
                edt_phonenumber.setError("شماره تلفن خود را وارد کنید")
                return@setOnClickListener
//                edt_name_address
            }




            Model?.name = edt_name_address.text.toString()
            Model?.plaque = edt_pluq.text.toString()
            Model?.location = edt_address.text.toString()
            Model?.floor = edt_floor.text.toString()
            Model?.unit = edt_unit.text.toString()
            Model?.tel = edt_phonenumber.text.toString()
            Model?.latitude = latitude
            Model?.home = edt_building.text.toString()
            Model?.longitude = longitude
            Model?.fullName = edt_fullname.text.toString()

            AddAddress()


        }
        // add Standard_day map to layer BASE_MAP_INDEX
        // add Standard_day map to layer BASE_MAP_INDEX
        map.options.setZoomRange(Range(4.5f, 18f))
        val baseMap: Layer = NeshanServices.createBaseMap(NeshanMapStyle.STANDARD_DAY)
        map.layers.insert(0, baseMap)

        // Setting map focal position to a fixed position and setting camera zoom

        // Setting map focal position to a fixed position and setting camera zoom

        map.setZoom(14f, 0f)





        imageView44.setOnClickListener {
            if (Bto?.state == BottomSheetBehavior.STATE_EXPANDED) {
                Bto?.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                Bto?.state = BottomSheetBehavior.STATE_EXPANDED
            }

        }



        Bto?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    imageView44.animate().rotation(90f).setDuration(500).start()
                }

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    imageView44.animate().rotation(270f).setDuration(500).start()
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })


//        markerLayer?.setVectorElementEventListener(object : VectorElementEventListener() {
//            override fun onVectorElementClicked(clickInfo: ElementClickData): Boolean {
//                // If a double click happens on a marker...
//                if (clickInfo.clickType == ClickType.CLICK_TYPE_DOUBLE) {
//                    val removeId = clickInfo.vectorElement.getMetaDataElement("id").long
//                    runOnUiThread { Toast.makeText(this@MapActivity, "نشانگر شماره $removeId حذف شد!", Toast.LENGTH_SHORT).show() }
//                    //getting marker reference from clickInfo and remove that marker from markerLayer
//                    markerLayer?.remove(clickInfo.vectorElement)
//
//                    // If a single click happens...
//                } else if (clickInfo.clickType == ClickType.CLICK_TYPE_SINGLE) {
//                    // changing marker to blue
//                    changeMarkerToBlue(clickInfo.vectorElement as Marker)
//                }
//                return true
//            }
//        }
//        )


        map.mapEventListener = object : MapEventListener() {
            override fun onMapClicked(mapClickInfo: ClickData) {
                super.onMapClicked(mapClickInfo)
                if (mapClickInfo.clickType == ClickType.CLICK_TYPE_LONG) {
                    if (marker != null) {
                        Log.i("dvnsavdbans", "A")
                        markerLayer?.remove(marker)
                        latitude = ""
                        longitude = ""
                    }
                    val clickedLocation = mapClickInfo.clickPos
                    Log.i("dmlvmldva", clickedLocation.x.toString())
                    Log.i("dmlvmldva", clickedLocation.y.toString())
                    AddMarker(clickedLocation, MarkId.toLong())
                    latitude = clickedLocation.x.toString()
                    longitude = clickedLocation.y.toString()
                    Bto?.state = BottomSheetBehavior.STATE_EXPANDED

                    // addMarker adds a marker (pretty self explanatory :D) to the clicked location
                }
            }
        }


    }
    fun getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)

        val locationClient: FusedLocationProviderClient = getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
             ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                     Manifest.permission.ACCESS_COARSE_LOCATION),45)
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        btn_location.isEnabled=false
        locationClient.lastLocation
            .addOnSuccessListener { location -> // GPS location can be null if GPS is switched off
                btn_location.isEnabled=true
                Log.i("adncajcbac", "A")
                Log.i("alavmlas", "${location.latitude}")
                Log.i("alavmlas", "${location.longitude}")
                onLocationChange(location)
//                var Loc = LngLat(location.latitude.toDouble(), location.longitude.toDouble())
//                AddMarker(Loc, MarkId.toLong())
            }
            .addOnFailureListener { e ->
                btn_location.isEnabled=true
                Log.d("MapDemoActivity", "Error trying to get last GPS location")
                e.printStackTrace()
            }
    }
     fun onLocationChange(location: Location) {
        val loc = LngLat(location.longitude, location.latitude)
        setMarker(loc)
    }
    private fun setMarker(markerLocation: LngLat) {
        markerLayer?.clear()

        latitude= markerLocation.x.toString()
        longitude= markerLocation.y.toString()
        val animSt = AnimationStyleBuilder()
            .let {
                it.fadeAnimationType = AnimationType.ANIMATION_TYPE_SMOOTHSTEP
                it.sizeAnimationType = AnimationType.ANIMATION_TYPE_SPRING
                it.phaseInDuration = 0.5f
                it.phaseOutDuration = 0.5f
                it.buildStyle()
            }

        val st = MarkerStyleCreator()
            .let {
                it.size = 48f
                it.bitmap = BitmapUtils.createBitmapFromAndroidBitmap(
                    BitmapFactory.decodeResource(resources, R.drawable.pinpin))
                it.animationStyle = animSt
                it.buildStyle()
            }
        map.setFocalPointPosition(LngLat(markerLocation.x,markerLocation.y), 0f)
        marker = Marker(markerLocation, st)
        markerLayer?.add(marker)
        Bto?.state = BottomSheetBehavior.STATE_EXPANDED
    }
    private fun Getlocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                14
            );
        } else {
            var locationGPS = nManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                var lat = locationGPS.getLatitude();
                var longi = locationGPS.getLongitude();
                latitude = lat.toString()
                longitude = longi.toString()
                Log.i("svmsavkadv", latitude)
                Log.i("svmsavkadv", longitude)
//                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }






    }
    fun AddAddress() {
        DialLoad()
        Model?.id = Id_3
        var req = api?.AddEditAddress("Bearer " + token, Model)
        req?.enqueue(object : retrofit2.Callback<ResponseAddress> {
            override fun onResponse(
                call: Call<ResponseAddress>,
                response: Response<ResponseAddress>
            ) {
                Log.i("knknvsz", response.code().toString())
                Dial_Close()
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean) {
                            if (success) {
                                AddAddress()
                            }
                        }

                    })
                }
                if (response.code() == 500) {
                    var code500: ErrorCode500? = null
                    val gson = Gson()
                    val adapter: TypeAdapter<ErrorCode500> =
                        gson.getAdapter(ErrorCode500::class.java)
                    try {
                        if (response.errorBody() != null) code500 = adapter.fromJson(
                            response.errorBody()!!.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                        this@MapActivity,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("knknvsz", code500?.getMessage().toString())
                    return
                }

                if (response.isSuccessful) {

                    if (Pos != -8) {
                        var V = Intent()
                        V.putExtra("data", response.body()?.data)
                        V.putExtra("pos", Pos)
                        setResult(RESULT_OK, V)
                        finish()
                        Log.i("sdvknsvns00", "A")
                    } else {
                        var V = Intent()
                        V.putExtra("data", response.body()?.data)
                        setResult(RESULT_OK, V)
                        finish()
                        Log.i("sdvknsvns00", "B")
                    }

                }
            }

            override fun onFailure(call: Call<ResponseAddress>, t: Throwable) {
                Dial_Close()
                var I = 2;
                var p = Dialapp(
                    2,
                    "اتصال خود را به اینترنت بررسی کنید",
                    object : Dial_App.Interface_new {
                        override fun News() {

                        }
                    },
                    this@MapActivity
                )
                p.show()
            }
        })
    }
    fun AddMarker(loc: LngLat, id: Long) {
        val animStBl = AnimationStyleBuilder()
        animStBl.fadeAnimationType = AnimationType.ANIMATION_TYPE_SMOOTHSTEP
        animStBl.sizeAnimationType = AnimationType.ANIMATION_TYPE_SPRING
        animStBl.phaseInDuration = 0.5f
        animStBl.phaseOutDuration = 0.5f
        animSt = animStBl.buildStyle()
        val markStCr = MarkerStyleCreator()
        markStCr.size = 30f
        markStCr.bitmap = BitmapUtils.createBitmapFromAndroidBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.pinpin
            )
        )
        // AnimationStyle object - that was created before - is used here
        // AnimationStyle object - that was created before - is used here
        markStCr.animationStyle = animSt
        val markSt = markStCr.buildStyle()
        marker = Marker(loc, markSt)
        Log.i("adsjvnaja","A")
        // Setting a metadata on marker, here we have an id for each marker
        // Setting a metadata on marker, here we have an id for each marker
        marker?.setMetaDataElement("id", Variant(id))
        markerLayer?.add(marker)


    }
}


