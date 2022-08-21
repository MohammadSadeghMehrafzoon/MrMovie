package ir.misterdeveloper.mrmovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.misterdeveloper.mrmovie.R
import ir.misterdeveloper.mrmovie.adapter.AdapterTabs
import ir.misterdeveloper.mrmovie.databinding.ActivityMainBinding
import ir.misterdeveloper.mrmovie.ui.home.HomeFragment
import ir.misterdeveloper.mrmovie.ui.movieList.MovieListFragment
import java.util.ArrayList

/**
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        viewPagerForFragment()
    }

    private fun viewPagerForFragment() {
        val fragmentList: MutableList<Fragment> = ArrayList()
        fragmentList.add(HomeFragment())
        fragmentList.add(MovieListFragment())
        val adapterTabs = AdapterTabs(supportFragmentManager, fragmentList)
        mainBinding.viewPager.adapter = adapterTabs
        mainBinding.BottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    mainBinding.viewPager.currentItem = 0
                    mainBinding.BottomNavigationView.menu.findItem(R.id.home).isChecked = true
                }
                R.id.movieList -> {
                    mainBinding.viewPager.currentItem = 1
                    mainBinding.BottomNavigationView.menu.findItem(R.id.movieList).isChecked = true
                }
            }
            false
        })
        mainBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> mainBinding.BottomNavigationView.menu.findItem(R.id.home).isChecked = true
                    1 -> mainBinding.BottomNavigationView.menu.findItem(R.id.movieList).isChecked = true

                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}