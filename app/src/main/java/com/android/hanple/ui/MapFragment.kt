package com.android.hanple.ui

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.hanple.adapter.CategoryPlace
import com.android.hanple.databinding.FragmentMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        const val TAG = "MapFragment"
        private const val ARG_PLACES = "places"

        fun newInstance(places: List<CategoryPlace>): MapFragment {
            val fragment = MapFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PLACES, ArrayList(places))
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private var places: List<CategoryPlace>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            places = it.getParcelableArrayList(ARG_PLACES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this@MapFragment)

        binding.icBackbtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        places?.forEach { place ->
            val latLng = getLatLngFromAddress(place.address ?: "")
            if (latLng != null) {
                setupMarker(latLng, place.address ?: "")
            }
        }

        // 전체 위치를 보기 좋은 위치로 카메라 이동
        if (!places.isNullOrEmpty()) {
            val firstPlace = places!!.first()
            val firstLatLng = getLatLngFromAddress(firstPlace.address ?: "")
            if (firstLatLng != null) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 12f))
            }
        }
    }

    private fun getLatLngFromAddress(address: String): LatLng? {
        val context = context ?: return null // context가 null인지 확인
        val geocoder = Geocoder(context)
        return try {
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses?.isNotEmpty() == true) {
                val location = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun setupMarker(positionLatLng: LatLng, title: String) {
        val markerOption = MarkerOptions().apply {
            position(positionLatLng)
            title(title)
            snippet("위치: $title")
        }
        googleMap.addMarker(markerOption)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        mapView.onDestroy()
        super.onDestroyView()
        _binding = null
    }
}
