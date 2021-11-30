package ru.a1exs.mapcheck

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(gmap: GoogleMap) {

        mMap = gmap
        val latLng = LatLng(-34.0, 151.0)

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(requireContext(), "please", Toast.LENGTH_SHORT).show()
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location : Location? ->
            val bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker)
            if (location == null) {
                mMap.addMarker(MarkerOptions().position(latLng).icon(bitmap))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

            } else {
                val latitLng = LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(latitLng).icon(bitmap))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latitLng))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latitLng, 15f))

            }
        }

    }
}