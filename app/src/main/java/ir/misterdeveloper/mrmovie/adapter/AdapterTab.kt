package ir.misterdeveloper.mrmovie.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * This is an adapter class for viewPager
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */

class AdapterTabs(fm: FragmentManager, var fragmentList: List<Fragment>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}